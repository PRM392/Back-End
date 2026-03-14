package org.foodie_tour.modules.booking.dto.response;

import lombok.Builder;
import lombok.Data;
import org.foodie_tour.modules.booking.enums.PaymentMethod;

import java.util.List;

@Data
@Builder
public class CustomBookingResponse {

    private Long bookingId;
    private Long scheduleId;
    private List<Long> selectedCheckpointIds;

    private String customerName;
    private String email;
    private String phone;
    private Integer adultCount;
    private Integer childrenCount;
    private String pickupLocation;
    private String customerNote;
    private PaymentMethod paymentMethod;
}
