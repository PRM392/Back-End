package org.foodie_tour.modules.routes.dto.response;

import lombok.Builder;
import lombok.Data;
import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.foodie_tour.modules.routes.enums.RouteStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RouteResponse {
    private Long routeId;
    private Long tourId;
    private String routeName;
    private RouteStatus routeStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<RouteCheckpointResponse> routeCheckpoints;
}
