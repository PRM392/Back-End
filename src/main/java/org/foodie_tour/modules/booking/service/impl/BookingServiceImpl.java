package org.foodie_tour.modules.booking.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.common.exception.InvalidateDataException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.common.utils.RandomCode;
import org.foodie_tour.modules.auth.entity.OtpCode;
import org.foodie_tour.modules.auth.enums.TokenScope;
import org.foodie_tour.modules.auth.repository.OtpCodeRepository;
import org.foodie_tour.modules.auth.service.AuthService;
import org.foodie_tour.modules.booking.dto.request.BookingCancelRequest;
import org.foodie_tour.modules.booking.dto.request.BookingCreateRequest;
import org.foodie_tour.modules.booking.dto.request.ProcessRelocateRequest;
import org.foodie_tour.modules.booking.dto.request.RelocateBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingLogResponse;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;
import org.foodie_tour.modules.booking.dto.response.RelocateBookingResponse;
import org.foodie_tour.modules.booking.entity.Booking;
import org.foodie_tour.modules.booking.entity.BookingLog;
import org.foodie_tour.modules.booking.entity.RelocateBooking;
import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.booking.enums.PaymentMethod;
import org.foodie_tour.modules.booking.enums.RefundStatus;
import org.foodie_tour.modules.booking.enums.RelocateRequestStatus;
import org.foodie_tour.modules.booking.mapper.BookingLogMapper;
import org.foodie_tour.modules.booking.mapper.BookingMapper;
import org.foodie_tour.modules.booking.mapper.RelocateBookingMapper;
import org.foodie_tour.modules.booking.repository.BookingLogRepository;
import org.foodie_tour.modules.booking.repository.BookingRepository;
import org.foodie_tour.modules.booking.repository.RelocateBookingRepository;
import org.foodie_tour.modules.booking.service.BookingService;
import org.foodie_tour.modules.customer.entity.Customer;
import org.foodie_tour.modules.customer.entity.CustomerBooking;
import org.foodie_tour.modules.customer.enums.CustomerStatus;
import org.foodie_tour.modules.customer.repository.CustomerBookingRepository;
import org.foodie_tour.modules.customer.repository.CustomerRepository;
import org.foodie_tour.modules.onepay.service.OnePayService;
import org.foodie_tour.modules.mail.dto.request.SendMailRequest;
import org.foodie_tour.modules.mail.service.MailService;
import org.foodie_tour.modules.schedules.entity.Schedule;
import org.foodie_tour.modules.schedules.enums.ScheduleStatus;
import org.foodie_tour.modules.schedules.repository.ScheduleRepository;
import org.foodie_tour.modules.system.entity.SystemConfig;
import org.foodie_tour.modules.system.repository.SystemConfigRepository;
import org.foodie_tour.modules.tours.entity.Tour;
import org.foodie_tour.modules.transaction.entity.Transactions;
import org.foodie_tour.modules.transaction.enums.CashFlow;
import org.foodie_tour.modules.transaction.enums.TransactionStatus;
import org.foodie_tour.modules.transaction.repository.TransactionsRepository;
import org.foodie_tour.modules.vnpay.dto.request.PaymentRequest;
import org.foodie_tour.modules.vnpay.service.VNPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    BookingLogRepository bookingLogRepository;
    ScheduleRepository scheduleRepository;
    BookingMapper bookingMapper;
    BookingLogMapper bookingLogMapper;
    VNPayService vnPayService;
    CustomerRepository customerRepository;
    CustomerBookingRepository customerBookingRepository;
    OnePayService onePayService;
    MailService mailService;
    OtpCodeRepository otpCodeRepository;
    AuthService authService;
    RelocateBookingRepository relocateBookingRepository;
    RelocateBookingMapper relocateBookingMapper;
    private final SystemConfigRepository systemConfigRepository;
    private final TransactionsRepository transactionsRepository;

    @Transactional
    public BookingResponse createBooking(BookingCreateRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .map(existing -> {
                    existing.setCustomerName(request.getCustomerName());
                    existing.setPhone(request.getPhone());
                    existing.setStatus(CustomerStatus.PENDING);
                    return customerRepository.save(existing);
                })
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    newCustomer.setEmail(request.getEmail());
                    newCustomer.setCustomerName(request.getCustomerName());
                    newCustomer.setPhone(request.getPhone());
                    newCustomer.setStatus(CustomerStatus.PENDING);
                    return customerRepository.save(newCustomer);
                });

        Schedule template = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Khung giờ khởi hành không tồn tại"));

        LocalDateTime actualDepartureAt = LocalDateTime.of(
                request.getDepartureDate(),
                template.getDepartureAt().toLocalTime()
        );

        Tour tour = template.getTour();
        if (tour == null) throw new ResourceNotFoundException("Tour không tồn tại");

        Schedule actualSchedule = scheduleRepository.findActualSchedule(tour, actualDepartureAt)
                .orElseGet(() -> {
                    Schedule newSchedule = new Schedule();
                    newSchedule.setTour(tour);
                    newSchedule.setDepartureAt(actualDepartureAt);
                    newSchedule.setIsTemplate(false);
                    newSchedule.setScheduleStatus(ScheduleStatus.ACTIVE);
                    newSchedule.setMaxPax(template.getMaxPax());
                    newSchedule.setMinPax(template.getMinPax());
                    newSchedule.setScheduleDescription(template.getScheduleDescription());
                    newSchedule.setRoute(template.getRoute());
                    return scheduleRepository.save(newSchedule);
                });

        Booking booking = bookingMapper.toBooking(request);
        booking.setSchedule(actualSchedule);
        booking.setTour(tour);
        booking.setRoute(actualSchedule.getRoute());
        booking.setDepartureTime(actualDepartureAt);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setRefundStatus(RefundStatus.INACTIVE);
        booking.setDeposit(request.isDeposit());

        long adultPrice = tour.getBasePriceAdult() * request.getAdultCount();
        long childPrice = tour.getBasePriceChild() * request.getChildrenCount();
        long totalPrice = adultPrice + childPrice;
        booking.setTotalPrice(adultPrice + childPrice);
        booking.setAmountPaid(0L);
        booking.setRemainingAmount(0L);

        if (request.isDeposit()) {
            long depositAmount = (long) (totalPrice * 0.3); // 30% cọc
            booking.setRemainingAmount(totalPrice - depositAmount);
            booking.setBookingStatus(BookingStatus.PENDING); // Chờ cọc 30%
        } else {
            booking.setBookingStatus(BookingStatus.PENDING); // Chờ thanh toán đủ
        }

        String bookingCode = RandomCode.generateRandomCode(10);
        booking.setBookingCode(bookingCode);

        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description("Đặt tour thành công. Khởi hành: " + actualDepartureAt)
                .bookingStatus(booking.getBookingStatus())
                .build();

        if (booking.getBookingLogs() == null) booking.setBookingLogs(new ArrayList<>());
        booking.getBookingLogs().add(log);

        bookingRepository.save(booking);

        CustomerBooking customerBooking = new CustomerBooking();
        customerBooking.setCustomer(customer);
        customerBooking.setBooking(booking);
        customerBooking.setIsMain(true);
        customerBookingRepository.save(customerBooking);

        return bookingMapper.toResponse(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getAll(BookingStatus bookingStatus,Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Lịch trình không tồn tại"));;
        return bookingRepository.findByStatusAndSchedule(bookingStatus, schedule).stream()
                .map(bookingMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookingResponse getBookingByCode(String bookingCode) {
        return bookingMapper.toResponse(findByBookingCode(bookingCode));
    }

    @Transactional(readOnly = true)
    public List<BookingLogResponse> getLogsByBookingCode(String bookingCode) {
        return bookingLogRepository.getLogsByBookingCode(bookingCode).stream().map(bookingLogMapper::toResponse)
                .toList();
    }

    public String generatePaymentUrl(long bookingId, HttpServletRequest servletRequest) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Đặt lịch không tồn tại"));

        long amountToPay = Boolean.TRUE.equals(booking.getDeposit()) ? (long) (booking.getTotalPrice() * 0.3) : booking.getTotalPrice();
        PaymentRequest request = new PaymentRequest(bookingId, amountToPay);
        bookingRepository.save(booking);
        if (booking.getPaymentMethod() == PaymentMethod.VNPAY) {
            return vnPayService.generatePaymentUrl(request, servletRequest);
        } else if (booking.getPaymentMethod() == PaymentMethod.VISA) {
            return onePayService.generatePaymentUrl(bookingId, servletRequest);
        }

        throw new InvalidateDataException("Phương thức thanh toán không được hỗ trợ");
    }

    private Booking findByBookingCode(String bookingCode) {
        return bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Đặt lịch không tồn tại"));
    }

    @Transactional
    public String requestRelocateBooking(String bookingCode) {
        Booking booking = findByBookingCode(bookingCode);

        // Verify request
        // Time request
        int timeAllow = 8;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = booking.getSchedule().getDepartureAt();
        if (!now.plusHours(timeAllow).isBefore(startTime)) {
            String error = String.format("Chỉ được phép dời lịch trình trước khi khởi hành ít nhất %s giờ", timeAllow);
            throw new InvalidateDataException(error);
        }

        // Generate otp
        String otp;

        do {
            otp = RandomCode.generateRandomCode(6);
        } while (otpCodeRepository.existsById(otp));

        String token = authService.generateTokenByScope(TokenScope.RELOCATE_BOOKING, 15, bookingCode);

        // Save otp
        OtpCode otpEntity = new OtpCode();
        otpEntity.setOtpCode(otp);
        otpEntity.setToken(token);
        otpCodeRepository.save(otpEntity);

        // Send code to mail
        String[] receiver = new String[1];
        receiver[0] = booking.getEmail();
        SendMailRequest request = new SendMailRequest(receiver, "Xác nhận dời lịch cho mã đặt lịch " + bookingCode,
                otp);
        mailService.sendMail(request);

        // Return access token for next step
        return token;
    }

    @Transactional
    public void createRelocateBookingRequest(String token, RelocateBookingRequest request) {
        // Verify otp
        OtpCode otpEntity = otpCodeRepository.findById(request.getOtp())
                .orElseThrow(() -> new ResourceNotFoundException("Mã xác nhận không tồn tại"));

        if (!otpEntity.getToken().equals(token)) {
            throw new InvalidateDataException("Mã xác nhận không đúng");
        }

        String bookingCodeFromToken = authService.getSubjectFromToken(token);
        String bookingCodeFromRequest = request.getBookingCode();

        if (!bookingCodeFromToken.equals(bookingCodeFromRequest)) {
            throw new InvalidateDataException("Mã xác nhận không hợp lệ");
        }

        otpCodeRepository.delete(otpEntity);

        LocalDateTime departureTime = scheduleRepository.getDepartureTime(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Lịch khởi hành không tồn tại"));

        // Save request
        RelocateBooking entity = RelocateBooking.builder()
                .bookingCode(request.getBookingCode())
                .scheduleId(request.getScheduleId())
                .departureAt(departureTime)
                .relocateRequestStatus(RelocateRequestStatus.PENDING)
                .build();

        relocateBookingRepository.save(entity);
        updateBookingStatus(findByBookingCode(request.getBookingCode()),
                BookingStatus.PENDING,
                "Đang chờ xử lý yêu cầu dời lịch trình mới");
    }

    @Transactional(readOnly = true)
    public List<RelocateBookingResponse> getAllPendingRelocateRequest() {
        return relocateBookingRepository.getAllByStatus(RelocateRequestStatus.PENDING).stream()
                .map(relocateBookingMapper::toResponse).toList();
    }

    @Transactional
    public BookingResponse processRelocateRequest(ProcessRelocateRequest request) {
        RelocateBooking relocateBooking = relocateBookingRepository.findById(request.getRelocateRequestId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy yêu cầu"));

        String bookingCode = relocateBooking.getBookingCode();
        long scheduleId = relocateBooking.getScheduleId();
        Booking booking = findByBookingCode(bookingCode);

        if (request.isApproved()) {
            if (scheduleRepository.existsById(scheduleId)) {
                Schedule newSchedule = scheduleRepository.getReferenceById(scheduleId);
                booking.setSchedule(newSchedule);
            } else {
                throw new ResourceNotFoundException("Lịch khởi hành không tồn tại");
            }
            relocateBooking.setRelocateRequestStatus(RelocateRequestStatus.APPROVED);
            bookingRepository.save(booking);
        } else {
            relocateBooking.setRelocateRequestStatus(RelocateRequestStatus.REJECTED);
        }

        relocateBookingRepository.save(relocateBooking);
        return bookingMapper.toResponse(booking);
    }

    @Override
    public String cancelBooking(BookingCancelRequest request) {
        Booking booking = bookingRepository.findByBookingCode(request.getBookingCode())
                .orElseThrow(() -> new ResourceNotFoundException("Đặt lịch không tồn tại"));

        if (!booking.getEmail().equalsIgnoreCase(request.getEmail())) {
            throw new InvalidateDataException("Email không khớp với mã đặt tour");
        }

        int allowHours = Integer.parseInt(
                systemConfigRepository.findById("CANCEL_ALLOW_HOURS")
                        .map(SystemConfig::getConfigValue)
                        .orElse("8")
        );

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = booking.getSchedule().getDepartureAt();
        boolean isEligibleForRefund = now.plusHours(allowHours).isBefore(departureTime);

        booking.setCancellationReason(request.getCancellationReason());

        String refundNote = "";
        if (isEligibleForRefund) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
            if (booking.getTotalPrice() > 0) {
                Transactions refundTrans = Transactions.builder()
                        .booking(booking)
                        .amount(booking.getTotalPrice())
                        .cashFlow(CashFlow.OUTCOME)
                        .status(TransactionStatus.SUCCESS)
                        .paymentMethod(booking.getPaymentMethod())
                        .build();
                transactionsRepository.save(refundTrans);
                booking.setRefundStatus(RefundStatus.COMPLETED);
            }
            refundNote = " Tiền đã được hoàn về ví của bạn. ";
        } else {
            refundNote = " Tuy nhiên vì sát giờ khởi hành, yêu cầu hoàn tiền của bạn đang chờ bộ phận phê duyệt. ";
        }

        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description("Lý do: " + request.getCancellationReason())
                .bookingStatus(BookingStatus.CANCELLED)
                .build();
        booking.getBookingLogs().add(log);
        bookingRepository.save(booking);

        mailService.sendMail(new SendMailRequest(
                new String[]{booking.getEmail()},
                "XÁC NHẬN HỦY TOUR THÀNH CÔNG - " + booking.getBookingCode(),
                "Chào " + booking.getCustomerName() + ", yêu cầu hủy tour của bạn đã thực hiện thành công."
        ));
        return refundNote;
    }

    @Override
    public String approveManualRefund(String bookingCode) {
        Booking booking = bookingRepository.findByBookingCode(bookingCode)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy booking"));

        if (booking.getRefundStatus() == RefundStatus.COMPLETED) {
            throw new InvalidateDataException("Tour này đã được hoàn tiền trước đó.");
        }

        Transactions refundTrans = Transactions.builder()
                .booking(booking)
                .amount(booking.getTotalPrice())
                .cashFlow(CashFlow.OUTCOME)
                .status(TransactionStatus.SUCCESS)
                .paymentMethod(booking.getPaymentMethod())
                .build();
        transactionsRepository.save(refundTrans);
        booking.setRefundStatus(RefundStatus.COMPLETED);

        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description("Admin phê duyệt hoàn tiền thủ công.")
                .bookingStatus(BookingStatus.CANCELLED)
                .build();
        booking.getBookingLogs().add(log);

        bookingRepository.save(booking);

        return "Yêu cầu của bạn đã được phê duyệt.";
    }

    @Override
    @Transactional
    public BookingResponse completeOnTourPayment(String bookingCode, PaymentMethod method) {
        Booking booking = findByBookingCode(bookingCode);

        // Chỉ booking đã cọc 30% (DEPOSIT_PAID) mới được thanh toán 70% còn lại
        if (booking.getBookingStatus() != BookingStatus.DEPOSIT_PAID) {
            throw new InvalidateDataException("Booking phải ở trạng thái DEPOSIT_PAID (đã cọc) mới có thể thanh toán nốt.");
        }

        if (booking.getRemainingAmount() == null || booking.getRemainingAmount() == 0) {
            throw new InvalidateDataException("Tour này đã được thanh toán đủ.");
        }

        // Thanh toán phần còn lại 70%
        Long paymentAmount = booking.getRemainingAmount();

        Transactions finalTrans = Transactions.builder()
                .booking(booking)
                .amount(paymentAmount)
                .paymentMethod(method)
                .cashFlow(CashFlow.INCOME)
                .status(TransactionStatus.SUCCESS)
                .build();
        transactionsRepository.save(finalTrans);

        // Cập nhật số tiền đã thanh toán và số tiền còn lại
        Long newAmountPaid = (booking.getAmountPaid() != null ? booking.getAmountPaid() : 0L) + paymentAmount;
        booking.setAmountPaid(newAmountPaid);
        booking.setRemainingAmount(0L);

        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description("Xác nhận thanh toán 70% còn lại: " + paymentAmount + " bằng " + method + " - Admin xác nhận")
                .bookingStatus(BookingStatus.COMPLETED)
                .build();
        booking.getBookingLogs().add(log);

        booking.setBookingStatus(BookingStatus.COMPLETED);

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public BookingResponse confirmVnpayPaymentSuccess(String bookingCode) {
        Booking booking = findByBookingCode(bookingCode);

        // Kiểm tra booking đang ở trạng thái PENDING (chưa thanh toán)
        if (booking.getBookingStatus() != BookingStatus.PENDING) {
            // Nếu đã thanh toán rồi thì trả về booking hiện tại (idempotent)
            if (booking.getAmountPaid() != null && booking.getAmountPaid() > 0) {
                return bookingMapper.toResponse(booking);
            }
            throw new InvalidateDataException("Booking không ở trạng thái chờ thanh toán.");
        }

        // Tính số tiền cần cọc 30%
        long expectedDeposit = Boolean.TRUE.equals(booking.getDeposit())
                ? (long) (booking.getTotalPrice() * 0.3)
                : booking.getTotalPrice();

        // Cập nhật amountPaid
        booking.setAmountPaid(expectedDeposit);

        // Tạo transaction
        Transactions transaction = Transactions.builder()
                .booking(booking)
                .amount(expectedDeposit)
                .paymentMethod(PaymentMethod.VNPAY)
                .cashFlow(CashFlow.INCOME)
                .status(TransactionStatus.SUCCESS)
                .build();
        transactionsRepository.save(transaction);

        // Tạo booking log
        String logDesc = Boolean.TRUE.equals(booking.getDeposit())
                ? "Thanh toán cọc 30% (" + expectedDeposit + ") qua VNPAY thành công. Còn nợ: " + (booking.getTotalPrice() - expectedDeposit)
                : "Thanh toán đủ (" + expectedDeposit + ") qua VNPAY thành công.";

        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description(logDesc)
                .bookingStatus(BookingStatus.PENDING)
                .build();
        bookingLogRepository.save(log);

        // Cập nhật trạng thái booking
        if (Boolean.TRUE.equals(booking.getDeposit())) {
            booking.setBookingStatus(BookingStatus.DEPOSIT_PAID);
            booking.setRemainingAmount(booking.getTotalPrice() - expectedDeposit);
        } else {
            booking.setBookingStatus(BookingStatus.COMPLETED);
            booking.setRemainingAmount(0L);
        }

        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookings(String status, String email, String fromDate, String toDate) {
        List<Booking> bookings = bookingRepository.findAll();
        
        return bookings.stream()
                .filter(b -> {
                    // Filter by status
                    if (status != null && !status.isBlank()) {
                        try {
                            BookingStatus bookingStatus = BookingStatus.valueOf(status.toUpperCase());
                            if (b.getBookingStatus() != bookingStatus) return false;
                        } catch (IllegalArgumentException ignored) {}
                    }
                    // Filter by email
                    if (email != null && !email.isBlank()) {
                        if (!b.getEmail().equalsIgnoreCase(email)) return false;
                    }
                    // Filter by date range
                    if (fromDate != null && !fromDate.isBlank()) {
                        java.time.LocalDate from = java.time.LocalDate.parse(fromDate);
                        if (b.getCreateAt() != null && b.getCreateAt().toLocalDate().isBefore(from)) return false;
                    }
                    if (toDate != null && !toDate.isBlank()) {
                        java.time.LocalDate to = java.time.LocalDate.parse(toDate);
                        if (b.getCreateAt() != null && b.getCreateAt().toLocalDate().isAfter(to)) return false;
                    }
                    return true;
                })
                .sorted((a, b) -> {
                    if (a.getCreateAt() == null && b.getCreateAt() == null) return 0;
                    if (a.getCreateAt() == null) return 1;
                    if (b.getCreateAt() == null) return -1;
                    return b.getCreateAt().compareTo(a.getCreateAt()); // Mới nhất trước
                })
                .map(bookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    private void updateBookingStatus(Booking booking, BookingStatus newStatus, String description) {
        if (booking.getBookingStatus() == newStatus) {
            return;
        }

        booking.setBookingStatus(newStatus);
        BookingLog log = BookingLog.builder()
                .booking(booking)
                .description(description)
                .bookingStatus(newStatus)
                .build();
        booking.getBookingLogs().add(log);
        bookingRepository.save(booking);
    }
}
