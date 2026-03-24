package org.foodie_tour.modules.routes.dto.response;

import lombok.Data;

@Data
public class RouteCheckpointResponse {
    private Long routeCheckpointId;
    private Long checkpointId;
    private String name;
    private Integer order;
    private String status;
}
