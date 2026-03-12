package org.foodie_tour.modules.routes.dto.request;

public record CheckPointOrderRequest(
        Long checkpointId,
        Integer order
) {
}
