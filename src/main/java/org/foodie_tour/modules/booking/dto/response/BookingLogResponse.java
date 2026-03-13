package org.foodie_tour.modules.booking.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import org.foodie_tour.modules.booking.enums.BookingStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingLogResponse {
    private Long bookingLogId;

    private String description;
    private BookingStatus bookingStatus;

    LocalDateTime createAt;




}