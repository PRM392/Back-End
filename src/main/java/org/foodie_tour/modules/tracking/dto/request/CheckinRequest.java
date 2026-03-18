package org.foodie_tour.modules.tracking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckinRequest {

    @NotNull(message = "sessionId không được để trống")
    private Long sessionId;

    @NotNull(message = "routeCheckpointId không được để trống")
    private Long routeCheckpointId;

    private Double latitude;

    private Double longitude;

    private String note;
}
