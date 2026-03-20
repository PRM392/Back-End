package org.foodie_tour.modules.tracking.service.impl;

import lombok.RequiredArgsConstructor;
import org.foodie_tour.common.exception.InvalidateDataException;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.employee.entity.Employee;
import org.foodie_tour.modules.employee.repository.EmployeeRepository;
import org.foodie_tour.modules.routes.entity.RouteCheckpoint;
import org.foodie_tour.modules.routes.repository.RouteCheckpointRepository;
import org.foodie_tour.modules.schedules.entity.Schedule;
import org.foodie_tour.modules.schedules.repository.ScheduleRepository;
import org.foodie_tour.modules.tracking.dto.request.CheckinRequest;
import org.foodie_tour.modules.tracking.dto.request.CheckoutTourRequest;
import org.foodie_tour.modules.tracking.dto.request.StartTourRequest;
import org.foodie_tour.modules.tracking.dto.request.UpdateLocationRequest;
import org.foodie_tour.modules.tracking.dto.response.CheckinLogResponse;
import org.foodie_tour.modules.tracking.dto.response.GuideLocationResponse;
import org.foodie_tour.modules.tracking.dto.response.TourSessionResponse;
import org.foodie_tour.modules.tracking.entity.CheckinLog;
import org.foodie_tour.modules.tracking.entity.GuideLocation;
import org.foodie_tour.modules.tracking.entity.TourSession;
import org.foodie_tour.modules.tracking.enums.CheckinStatus;
import org.foodie_tour.modules.tracking.enums.TourSessionStatus;
import org.foodie_tour.modules.tracking.repository.CheckinLogRepository;
import org.foodie_tour.modules.tracking.repository.GuideLocationRepository;
import org.foodie_tour.modules.tracking.repository.TourSessionRepository;
import org.foodie_tour.modules.tracking.service.TourTrackingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourTrackingServiceImpl implements TourTrackingService {

    private final TourSessionRepository tourSessionRepository;
    private final GuideLocationRepository guideLocationRepository;
    private final CheckinLogRepository checkinLogRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final RouteCheckpointRepository routeCheckpointRepository;

    @Override
    @Transactional
    public TourSessionResponse startTour(StartTourRequest request) {
        // Kiểm tra schedule tồn tại
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule không tồn tại"));

        // Kiểm tra guide tồn tại
        Employee guide = employeeRepository.findById(request.getGuideId())
                .orElseThrow(() -> new ResourceNotFoundException("Tour guide không tồn tại"));

        // Kiểm tra guide đang chạy tour khác không
        if (tourSessionRepository.existsBySchedule_ScheduleIdAndGuide_EmployeeIdAndSessionStatus(
                request.getScheduleId(), request.getGuideId(), TourSessionStatus.IN_PROGRESS)) {
            throw new InvalidateDataException("Tour guide đang thực hiện tour này rồi");
        }

        TourSession session = TourSession.builder()
                .schedule(schedule)
                .guide(guide)
                .sessionStatus(TourSessionStatus.IN_PROGRESS)
                .startedAt(LocalDateTime.now())
                .build();

        tourSessionRepository.save(session);
        return toSessionResponse(session);
    }

    @Override
    @Transactional
    public void updateLocation(UpdateLocationRequest request) {
        TourSession session = getActiveSessionEntity(request.getSessionId());

        GuideLocation location = GuideLocation.builder()
                .session(session)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();

        guideLocationRepository.save(location);
    }

    @Override
    @Transactional
    public CheckinLogResponse checkinCheckpoint(CheckinRequest request) {
        TourSession session = getActiveSessionEntity(request.getSessionId());

        RouteCheckpoint routeCheckpoint = routeCheckpointRepository.findById(request.getRouteCheckpointId())
                .orElseThrow(() -> new ResourceNotFoundException("Checkpoint không tồn tại trong tuyến đường"));

        // Kiểm tra đã check-in checkpoint này chưa
        if (checkinLogRepository.existsBySession_SessionIdAndRouteCheckpoint_RouteCheckpointId(
                request.getSessionId(), request.getRouteCheckpointId())) {
            throw new InvalidateDataException("Bạn đã check-in tại checkpoint này rồi");
        }

        CheckinLog log = CheckinLog.builder()
                .session(session)
                .routeCheckpoint(routeCheckpoint)
                .status(CheckinStatus.CHECKED_IN)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .note(request.getNote())
                .build();

        checkinLogRepository.save(log);
        return toCheckinResponse(log);
    }

    @Override
    @Transactional
    public TourSessionResponse checkoutTour(CheckoutTourRequest request) {
        TourSession session = getActiveSessionEntity(request.getSessionId());

        session.setSessionStatus(TourSessionStatus.COMPLETED);
        session.setEndedAt(LocalDateTime.now());

        tourSessionRepository.save(session);
        return toSessionResponse(session);
    }

    @Override
    public List<GuideLocationResponse> getLocationTrail(Long sessionId) {
        return guideLocationRepository.findBySession_SessionIdOrderByRecordedAtAsc(sessionId)
                .stream()
                .map(this::toLocationResponse)
                .toList();
    }

    @Override
    public List<CheckinLogResponse> getCheckinLogs(Long sessionId) {
        return checkinLogRepository.findBySession_SessionIdOrderByCheckedAtAsc(sessionId)
                .stream()
                .map(this::toCheckinResponse)
                .toList();
    }

    @Override
    public TourSessionResponse getSessionById(Long sessionId) {
        TourSession session = tourSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Phiên tour không tồn tại"));
        return toSessionResponse(session);
    }

    @Override
    public TourSessionResponse getActiveSession(Long scheduleId, Long guideId) {
        TourSession session = tourSessionRepository
                .findBySchedule_ScheduleIdAndGuide_EmployeeIdAndSessionStatus(
                        scheduleId, guideId, TourSessionStatus.IN_PROGRESS)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không có phiên tour đang hoạt động cho guide này"));
        return toSessionResponse(session);
    }

    @Override
    public List<TourSessionResponse> getSessionsByGuideId(Long guideId) {
        return tourSessionRepository.findByGuide_EmployeeId(guideId)
                .stream()
                .map(this::toSessionResponse)
                .toList();
    }

    // ─── Helper ─────────────────────────────────────────────────────────────────

    private TourSession getActiveSessionEntity(Long sessionId) {
        TourSession session = tourSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Phiên tour không tồn tại"));
        if (session.getSessionStatus() != TourSessionStatus.IN_PROGRESS) {
            throw new InvalidateDataException("Phiên tour này đã kết thúc hoặc bị huỷ");
        }
        return session;
    }

    private TourSessionResponse toSessionResponse(TourSession session) {
        TourSessionResponse res = new TourSessionResponse();
        res.setSessionId(session.getSessionId());
        
        org.foodie_tour.modules.schedules.entity.Schedule schedule = session.getSchedule();
        if (schedule != null) {
            res.setScheduleId(schedule.getScheduleId());
            if (schedule.getTour() != null) {
                res.setTourId(schedule.getTour().getTourId());
                res.setTourTitle(schedule.getTour().getTourName());
            }
            if (schedule.getRoute() != null) res.setLocation(schedule.getRoute().getRouteName());
            if (schedule.getDepartureAt() != null) {
                res.setDepartureDate(schedule.getDepartureAt().toLocalDate().toString());
                int hour = schedule.getDepartureAt().getHour();
                int minute = schedule.getDepartureAt().getMinute();
                res.setDepartureTime(String.format("%02d:%02d", hour, minute));
            }
            res.setGuests(schedule.getMaxPax());
        }

        res.setGuideId(session.getGuide().getEmployeeId());
        res.setGuideName(session.getGuide().getEmployeeName());
        res.setSessionStatus(session.getSessionStatus().name());
        res.setStartedAt(session.getStartedAt());
        res.setEndedAt(session.getEndedAt());
        res.setCreatedAt(session.getCreatedAt());
        return res;
    }

    private GuideLocationResponse toLocationResponse(GuideLocation loc) {
        GuideLocationResponse res = new GuideLocationResponse();
        res.setLocationId(loc.getLocationId());
        res.setLatitude(loc.getLatitude());
        res.setLongitude(loc.getLongitude());
        res.setRecordedAt(loc.getRecordedAt());
        return res;
    }

    private CheckinLogResponse toCheckinResponse(CheckinLog log) {
        CheckinLogResponse res = new CheckinLogResponse();
        res.setCheckinId(log.getCheckinId());
        res.setRouteCheckpointId(log.getRouteCheckpoint().getRouteCheckpointId());
        res.setLocationName(log.getRouteCheckpoint().getCheckPoint().getLocationName());
        res.setOrder(log.getRouteCheckpoint().getOrder());
        res.setStatus(log.getStatus().name());
        res.setLatitude(log.getLatitude());
        res.setLongitude(log.getLongitude());
        res.setNote(log.getNote());
        res.setCheckedAt(log.getCheckedAt());
        return res;
    }
}
