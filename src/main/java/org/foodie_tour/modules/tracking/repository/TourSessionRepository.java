package org.foodie_tour.modules.tracking.repository;

import org.foodie_tour.modules.tracking.entity.TourSession;
import org.foodie_tour.modules.tracking.enums.TourSessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourSessionRepository extends JpaRepository<TourSession, Long> {

    Optional<TourSession> findBySchedule_ScheduleIdAndGuide_EmployeeIdAndSessionStatus(
            Long scheduleId, Long guideId, TourSessionStatus status);

    List<TourSession> findByGuide_EmployeeId(Long guideId);

    List<TourSession> findBySchedule_ScheduleId(Long scheduleId);

    boolean existsBySchedule_ScheduleIdAndGuide_EmployeeIdAndSessionStatus(
            Long scheduleId, Long guideId, TourSessionStatus status);
}
