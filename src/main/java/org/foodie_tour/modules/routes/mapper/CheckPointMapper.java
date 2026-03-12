package org.foodie_tour.modules.routes.mapper;

import org.foodie_tour.modules.routes.dto.request.CheckPointRequest;
import org.foodie_tour.modules.routes.dto.response.CheckPointResponse;
import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CheckPointMapper {

    @Mapping(target = "checkpointId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "checkpointImages", ignore = true)
    CheckPoint toEntity(CheckPointRequest request);

    @Mapping(target = "tourId", source = "tour.tourId")
    @Mapping(target = "imageUrls", expression = "java(entity.getCheckpointImages() != null ? " +
            "entity.getCheckpointImages().stream().map(ci -> ci.getImage().getImageUrl()).toList() : new java.util.ArrayList<>())")
    CheckPointResponse toResponse(CheckPoint entity);

    @Mapping(target = "checkpointId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "tour", ignore = true)
    void updateEntity(CheckPointRequest request, @MappingTarget CheckPoint entity);
}
