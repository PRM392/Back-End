package org.foodie_tour.modules.tracking.repository;

import org.foodie_tour.modules.tracking.entity.GuideLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideLocationRepository extends JpaRepository<GuideLocation, Long> {

    List<GuideLocation> findBySession_SessionIdOrderByRecordedAtAsc(Long sessionId);
}
