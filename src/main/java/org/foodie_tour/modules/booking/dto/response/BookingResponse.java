package org.foodie_tour.modules.booking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.enums.BookingStatus;
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
    Long totalPrice;
    String pickupLocation;
    BookingStatus bookingStatus;
    LocalDateTime departureTime;
    boolean deposit;
    Long amountPaid;
    Long remainingAmount;
    
    // Customer info
    String customerName;
    String customerEmail;
    String customerPhone;
    
    // Passenger count
    int adultCount;
    int childrenCount;
    
    // Payment info
    PaymentMethod paymentMethod;
    RefundStatus paymentStatus;
    
    // Timestamps
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}