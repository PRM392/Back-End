
package org.foodie_tour.modules.booking.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.dto.request.BookingCancelRequest;
import org.foodie_tour.modules.booking.dto.request.BookingCreateRequest;
import org.foodie_tour.modules.booking.dto.request.ProcessCancelRequest;
import org.foodie_tour.modules.booking.dto.request.ProcessRelocateRequest;
import org.foodie_tour.modules.booking.dto.request.RelocateBookingRequest;
import org.foodie_tour.modules.booking.dto.response.BookingLogResponse;
import org.foodie_tour.modules.booking.dto.response.BookingResponse;
import org.foodie_tour.modules.booking.dto.response.RelocateBookingResponse;
import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.booking.enums.PaymentMethod;
import org.foodie_tour.modules.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/booking")
public class BookingController {
    BookingService bookingService;

    @PostMapping()
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingCreateRequest request) {
        var result = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping()
    public ResponseEntity<List<BookingResponse>> getAllBookings(
            @RequestParam (required = false) BookingStatus bookingStatus,
            @RequestParam (required = false) Long scheduleId
    ) {
        return ResponseEntity.ok(bookingService.getAll(bookingStatus, scheduleId));
    }

    @GetMapping("/{bookingCode}")
    public ResponseEntity<BookingResponse> getBookingByCode(@PathVariable String bookingCode) {
        var result = bookingService.getBookingByCode(bookingCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{bookingCode}/logs")
    public ResponseEntity<List<BookingLogResponse>> getLogsByBookingId(@PathVariable String bookingCode) {
        var result = bookingService.getLogsByBookingCode(bookingCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{bookingId}/payment")
    public ResponseEntity<String> generatePaymentUrl(
            @PathVariable long bookingId,
            @RequestParam(value = "returnUrl", required = false) String customReturnUrl,
            HttpServletRequest servletRequest) {
        var result = bookingService.generatePaymentUrl(bookingId, servletRequest, customReturnUrl);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/relocate/all-request")
    public ResponseEntity<List<RelocateBookingResponse>> getAllRelocateRequest() {
        var result = bookingService.getAllPendingRelocateRequest();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{bookingCode}/relocate")
    public ResponseEntity<String> relocateBooking(@PathVariable String bookingCode) {
        var result = bookingService.requestRelocateBooking(bookingCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/relocate/verify")
    public ResponseEntity<String> verifyBooking(@RequestHeader (value = "Access-Token") String accessToken, @RequestBody RelocateBookingRequest request) {
        bookingService.createRelocateBookingRequest(accessToken, request);
        return ResponseEntity.status(HttpStatus.OK).body("Tạo yêu cầu thành công");
    }

    @PutMapping("/relocate/process")
    public ResponseEntity<BookingResponse> processRequest(@RequestBody ProcessRelocateRequest request) {
        var result = bookingService.processRelocateRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestBody BookingCancelRequest request) {
        String result = bookingService.cancelBooking(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{bookingCode}/approve-refund")
    public ResponseEntity<String> approveManualRefund(
            @PathVariable String bookingCode
    ) {
        String result = bookingService.approveManualRefund(bookingCode);
        return ResponseEntity.ok(result);
    }

    /**
     * Admin lấy danh sách booking chờ duyệt hủy
     * GET /api/booking/cancel/pending
     */
    @GetMapping("/cancel/pending")
    public ResponseEntity<List<BookingResponse>> getPendingCancelRequests() {
        var result = bookingService.getAllPendingCancelRequest();
        return ResponseEntity.ok(result);
    }

    /**
     * Admin duyệt hoặc từ chối yêu cầu hủy tour
     * PUT /api/booking/cancel/process
     */
    @PutMapping("/cancel/process")
    public ResponseEntity<BookingResponse> processCancel(
            @RequestBody ProcessCancelRequest request
    ) {
        var result = bookingService.processCancelRequest(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{bookingCode}/complete-payment")
    public ResponseEntity<BookingResponse> completePayment(
            @PathVariable String bookingCode,
            @RequestParam PaymentMethod method
    ) {
        var result = bookingService.completeOnTourPayment(bookingCode, method);
        return ResponseEntity.ok(result);
    }

    /**
     * Mobile gọi sau khi VNPay redirect về app để xác nhận thanh toán thành công
     * Không cần auth - mobile tự gọi sau khi VNPay trả về success
     */
    @PostMapping("/{bookingCode}/vnpay-confirm")
    public ResponseEntity<BookingResponse> confirmVnpayPayment(
            @PathVariable String bookingCode
    ) {
        var result = bookingService.confirmVnpayPaymentSuccess(bookingCode);
        return ResponseEntity.ok(result);
    }

    // ============== ADMIN ENDPOINTS ==============

    /**
     * Lấy tất cả booking (Admin)
     * GET /api/booking/all
     * Auth: BẮT BUỘC - Token có quyền ADMIN
     */
    @GetMapping("/all")
    // Temporarily removed @PreAuthorize for testing
    public ResponseEntity<List<BookingResponse>> getAllBookings(
            @RequestParam(required = false) String status,  // Filter theo status: PENDING, DEPOSIT_PAID, COMPLETED, CANCELLED
            @RequestParam(required = false) String email,   // Filter theo email khách
            @RequestParam(required = false) String fromDate, // Filter từ ngày (yyyy-MM-dd)
            @RequestParam(required = false) String toDate   // Filter đến ngày (yyyy-MM-dd)
    ) {
        var result = bookingService.getAllBookings(status, email, fromDate, toDate);
        return ResponseEntity.ok(result);
    }
}