package org.foodie_tour.modules.tracking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateLocationRequest {

    @NotNull(message = "sessionId không được để trống")
    private Long sessionId;

    @NotNull(message = "latitude không được để trống")
    private Double latitude;

    @NotNull(message = "longitude không được để trống")
    private Double longitude;
}
