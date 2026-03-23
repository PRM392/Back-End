package org.foodie_tour.modules.routes.mapper;

import org.foodie_tour.modules.routes.dto.request.CheckPointOrderRequest;
import org.foodie_tour.modules.routes.dto.request.RouteRequest;
import org.foodie_tour.modules.routes.dto.response.RouteCheckpointResponse;
import org.foodie_tour.modules.routes.dto.response.RouteResponse;
import org.foodie_tour.modules.routes.entity.Route;

import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    @Mapping(target = "routeId", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "routeCheckpoints", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    Route toEntity(RouteRequest request);

    @Mapping(target = "routeCheckpointId", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE")
    RouteCheckpoint toRouteCheckpoint(CheckPointOrderRequest request);

    @Mapping(target = "tourId", source = "tour.tourId")
    RouteResponse toResponse(Route route);

    @Mapping(target = "checkpointId", source = "checkPoint.checkpointId")
    @Mapping(target = "status", expression = "java(entity.getStatus() != null ? entity.getStatus().name() : null)")
    RouteCheckpointResponse toRouteCheckPointResponse(RouteCheckpoint entity);

    @Mapping(target = "routeId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "routeName", ignore = true)
    @Mapping(target = "routeStatus", ignore = true)
    @Mapping(target = "routeCheckpoints", ignore = true)
    void updateEntity(RouteRequest routeRequest, @MappingTarget Route route);
}
