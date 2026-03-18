package org.foodie_tour.modules.tracking.repository;

import org.foodie_tour.modules.tracking.entity.CheckinLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckinLogRepository extends JpaRepository<CheckinLog, Long> {

    List<CheckinLog> findBySession_SessionIdOrderByCheckedAtAsc(Long sessionId);

    Optional<CheckinLog> findBySession_SessionIdAndRouteCheckpoint_RouteCheckpointId(
            Long sessionId, Long routeCheckpointId);

    boolean existsBySession_SessionIdAndRouteCheckpoint_RouteCheckpointId(
            Long sessionId, Long routeCheckpointId);
}
