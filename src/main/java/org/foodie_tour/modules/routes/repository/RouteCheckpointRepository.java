package org.foodie_tour.modules.routes.repository;

import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteCheckpointRepository extends JpaRepository<RouteCheckpoint, Long> {

    List<RouteCheckpoint> findByRoute_RouteIdOrderByOrderAsc(Long routeId);
}
