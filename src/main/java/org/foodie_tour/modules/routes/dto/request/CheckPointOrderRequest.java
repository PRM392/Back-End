package org.foodie_tour.modules.routes.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckPointOrderRequest {
    private Long checkpointId;
    private Integer order;
}
