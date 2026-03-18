package org.foodie_tour.modules.tracking.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StartTourRequest {

    @NotNull(message = "scheduleId không được để trống")
    private Long scheduleId;

    @NotNull(message = "guideId không được để trống")
    private Long guideId;
}
