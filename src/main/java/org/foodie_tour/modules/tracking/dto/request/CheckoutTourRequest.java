package org.foodie_tour.modules.tracking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutTourRequest {

    @NotNull(message = "sessionId không được để trống")
    private Long sessionId;
}
