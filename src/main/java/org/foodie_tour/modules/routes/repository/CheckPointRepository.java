package org.foodie_tour.modules.routes.repository;

import org.foodie_tour.modules.routes.entity.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {

    List<CheckPoint> findByTour_TourId(Long tourId);
}
