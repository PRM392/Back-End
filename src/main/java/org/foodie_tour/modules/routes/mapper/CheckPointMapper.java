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
    @Mapping(target = "ggmapUrl", source = "entity.ggmapUrl")
    @Mapping(target = "mainImage", expression = "java(getMainImageUrl(entity))")
    @Mapping(target = "imageUrls", expression = "java(getImageUrls(entity))")
    CheckPointResponse toResponse(CheckPoint entity);

    default String getMainImageUrl(CheckPoint entity) {
        if (entity.getCheckpointImages() == null) return null;
        return entity.getCheckpointImages().stream()
                .filter(img -> Boolean.TRUE.equals(img.getIsPrimary()))
                .findFirst()
                .map(img -> img.getImage().getImageUrl())
                .orElse(entity.getCheckpointImages().isEmpty() ? null : entity.getCheckpointImages().get(0).getImage().getImageUrl());
    }

    default java.util.List<String> getImageUrls(CheckPoint entity) {
        if (entity.getCheckpointImages() == null) return java.util.Collections.emptyList();
        return entity.getCheckpointImages().stream()
                .map(img -> img.getImage().getImageUrl())
                .collect(java.util.stream.Collectors.toList());
    }

    @Mapping(target = "checkpointId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "tour", ignore = true)
    void updateEntity(CheckPointRequest request, @MappingTarget CheckPoint entity);
}
