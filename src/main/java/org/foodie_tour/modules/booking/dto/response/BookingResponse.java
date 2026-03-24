package org.foodie_tour.modules.booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.booking.enums.BookingType;
import org.foodie_tour.modules.booking.enums.PaymentMethod;
import org.foodie_tour.modules.booking.enums.RefundStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    Long bookingId;
    String bookingCode;
    Long scheduleId;
    Long routeId;
    Long tourId;
    String tourName;
    String pickupLocation;
    BookingStatus bookingStatus;
    LocalDateTime departureTime;
    boolean deposit;

    // Customer info
    String customerName;
    String customerEmail;
    String customerPhone;

    // Passenger count
    int adultCount;
    int childrenCount;
    int numberOfPeople;

    // Refund info
    RefundStatus refundStatus;

    // Payment info
    PaymentMethod paymentMethod;

    // Payment summary (useful for frontend display)
    Long totalPrice;
    Long amountPaid;
    Long remainingAmount;
    Long depositAmount;
    Long remainingPercent;

    // Computed status text for display
    String paymentStatusText;
    String bookingStatusText;

    // Timestamps
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    BookingType bookingType;
}