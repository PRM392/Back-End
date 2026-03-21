package org.foodie_tour.modules.booking.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.foodie_tour.modules.booking.enums.PaymentMethod;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateRequest {
    long tourId;
    long scheduleId;
    LocalDate departureDate;
    String customerName;
    String email;
    String phone;
    int adultCount;
    int childrenCount;
    String pickupLocation;
    String customerNote;
    boolean deposit;
    PaymentMethod paymentMethod;
}
