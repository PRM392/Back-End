package org.foodie_tour.modules.report.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.enums.BookingStatus;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingReportResponse {
    String bookingCode;
    Long totalPrice;
    BookingStatus bookingStatus;
    LocalDateTime departureTime;
    Integer totalCustomers;
}