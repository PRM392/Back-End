package org.foodie_tour.modules.feedback.service.impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.InvalidateDataException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.aws.s3.service.S3Service;
import org.foodie_tour.modules.booking.entity.Booking;
import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.booking.repository.BookingRepository;
import org.foodie_tour.modules.customer.entity.CustomerBooking;
import org.foodie_tour.modules.customer.repository.CustomerBookingRepository;
import org.foodie_tour.modules.feedback.dto.request.FeedbackRequest;
import org.foodie_tour.modules.feedback.dto.response.FeedbackResponse;
import org.foodie_tour.modules.feedback.dto.response.FeedbackSummaryResponse;
import org.foodie_tour.modules.feedback.entity.Feedback;
import org.foodie_tour.modules.feedback.enums.FeedbackStatus;
import org.foodie_tour.modules.feedback.mapper.FeedbackMapper;
import org.foodie_tour.modules.feedback.repository.FeedbackRepository;
import org.foodie_tour.modules.feedback.service.FeedbackService;
import org.foodie_tour.modules.images.entity.FeedbackImage;
import org.foodie_tour.modules.images.entity.Image;
import org.foodie_tour.modules.images.enums.FeedBackImageStatus;
import org.foodie_tour.modules.images.enums.ImageStatus;
import org.foodie_tour.modules.images.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final S3Service s3Service;
    private final BookingRepository bookingRepository;
    private final CustomerBookingRepository customerBookingRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public FeedbackResponse createFeedback(FeedbackRequest feedbackRequest,  List<MultipartFile> files) throws IOException {
        Booking booking = bookingRepository.findByBookingCode(feedbackRequest.getBookingCode())
                .orElseThrow(() -> new ResourceNotFoundException("Đặt lịch không tồn tại"));

        CustomerBooking customerBooking = customerBookingRepository.findByBooking(booking)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin khách hàng cho mã đặt tour này"));

        if (!booking.getEmail().equalsIgnoreCase(feedbackRequest.getEmail())) {
            throw new InvalidateDataException("Email không khớp với mã đặt tour");
        }

        if (booking.getBookingStatus() != BookingStatus.COMPLETED) {
            throw new InvalidateDataException("Bạn chỉ có thể đánh giá sau khi tour đã hoàn thành");
        }

        Feedback feedback = feedbackMapper.toEntity(feedbackRequest);
        feedback.setCustomer(customerBooking.getCustomer());
        feedback.setTour(booking.getSchedule().getTour());
        feedback.setSchedule(booking.getSchedule());
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setStatus(FeedbackStatus.ACTIVE);
        feedback = feedbackRepository.save(feedback);

        return getFeedbackResponse(files, feedback);
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackSummaryResponse getFeedbackSummary(Long tourId) {
        Double avgRating = feedbackRepository.getAverageRatingByTourId(tourId);
        Long total = feedbackRepository.countByTourId(tourId);

        List<FeedbackResponse> reviews = feedbackRepository
                .findByTour_TourIdAndStatusOrderByCreatedAtDesc(tourId, FeedbackStatus.ACTIVE)
                .stream()
                .map(feedbackMapper::toResponse)
                .toList();

        return FeedbackSummaryResponse.builder()
                .averageRating(avgRating != null ? Math.round(avgRating * 10.0) / 10.0 : 0.0)
                .totalReviews(total)
                .reviews(reviews)
                .build();
    }

    @Override
    @Transactional
    public FeedbackResponse updateFeedback(Long feedbackId, FeedbackRequest feedbackRequest, List<MultipartFile> files) throws IOException {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Đánh giá này không tồn tại"));

        feedbackMapper.updatedEntity(feedbackRequest, feedback);
        feedback.setUpdatedAt(LocalDateTime.now());

        return getFeedbackResponse(files, feedback);

    }

    private FeedbackResponse getFeedbackResponse(List<MultipartFile> files, Feedback feedback) throws IOException {
        if (!CollectionUtils.isEmpty(files)) {
            for (MultipartFile file : files) {
                String url = s3Service.uploadFile(file);
                Image img = Image.builder()
                        .imageUrl(url)
                        .imageStatus(ImageStatus.ACTIVE)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                img = imageRepository.save(img);

                FeedbackImage feedbackImage = FeedbackImage.builder()
                        .feedback(feedback)
                        .image(img)
                        .status(FeedBackImageStatus.ACTIVE)
                        .createdAt(LocalDateTime.now())
                        .build();
                feedback.getFeedbackImages().add(feedbackImage);
            }
        }
        Feedback saved = feedbackRepository.save(feedback);
        return feedbackMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Đánh giá này không tồn tại"));

        feedback.setStatus(FeedbackStatus.DELETED);
        feedbackRepository.save(feedback);
    }
}
