package org.foodie_tour.modules.routes.repository;

import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {

    List<CheckPoint> findByTour_TourId(Long tourId);

    @EntityGraph(attributePaths = {"checkpointImages", "checkpointImages.image"})
    Optional<CheckPoint> findWithImagesByCheckpointId(Long id);
}
