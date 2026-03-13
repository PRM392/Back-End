package org.foodie_tour.modules.tours.mapper;

import org.foodie_tour.modules.tours.dto.request.DishRequest;
import org.foodie_tour.modules.tours.dto.response.DishResponse;
import org.foodie_tour.modules.tours.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DishMapper {

    @Mapping(target = "dishId", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "dishImages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "checkPoint", ignore = true)
    Dish toEntity(DishRequest dishRequest);

    @Mapping(target = "tourId", source = "tour.tourId")
    @Mapping(target = "checkpointId", source = "checkPoint.checkpointId")
//    @Mapping(target = "locationName", source = "checkpoint.locationName")
    DishResponse toResponse(Dish dish);

    @Mapping(target = "dishId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(DishRequest dishRequest, @MappingTarget Dish dish);
}
