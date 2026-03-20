package org.foodie_tour.modules.tracking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.foodie_tour.modules.tracking.dto.request.CheckinRequest;
import org.foodie_tour.modules.tracking.dto.request.CheckoutTourRequest;
import org.foodie_tour.modules.tracking.dto.request.StartTourRequest;
import org.foodie_tour.modules.tracking.dto.request.UpdateLocationRequest;
import org.foodie_tour.modules.tracking.dto.response.CheckinLogResponse;
import org.foodie_tour.modules.tracking.dto.response.GuideLocationResponse;
import org.foodie_tour.modules.tracking.dto.response.TourSessionResponse;
import org.foodie_tour.modules.tracking.service.TourTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guide/tracking")
@RequiredArgsConstructor
public class TourTrackingController {

    private final TourTrackingService tourTrackingService;

    /**
     * [1] Guide bắt đầu tour → tạo phiên mới
     * POST /api/guide/tracking/start
     */
    @PostMapping("/start")
//    @PreAuthorize("hasAuthority('GUIDE_START_TOUR')")
    public ResponseEntity<TourSessionResponse> startTour(
            @RequestBody @Valid StartTourRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tourTrackingService.startTour(request));
    }

    /**
     * [2] FALLBACK - Guide gửi vị trí GPS qua REST (dự phòng khi WebSocket mất kết nối)
     * Ưu tiên dùng WebSocket STOMP: /app/tracking/location
     * POST /api/guide/tracking/location
     */
    @PostMapping("/location")
//    @PreAuthorize("hasAuthority('GUIDE_UPDATE_LOCATION')")
    public ResponseEntity<Void> updateLocation(
            @RequestBody @Valid UpdateLocationRequest request) {
        tourTrackingService.updateLocation(request);
        return ResponseEntity.ok().build();
    }

    /**
     * [3] Guide check-in tại một checkpoint
     * POST /api/guide/tracking/checkin
     */
    @PostMapping("/checkin")
//    @PreAuthorize("hasAuthority('GUIDE_CHECKIN')")
    public ResponseEntity<CheckinLogResponse> checkinCheckpoint(
            @RequestBody @Valid CheckinRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tourTrackingService.checkinCheckpoint(request));
    }

    /**
     * [4] Guide kết thúc tour
     * POST /api/guide/tracking/checkout
     */
    @PostMapping("/checkout")
//    @PreAuthorize("hasAuthority('GUIDE_CHECKOUT_TOUR')")
    public ResponseEntity<TourSessionResponse> checkoutTour(
            @RequestBody @Valid CheckoutTourRequest request) {
        return ResponseEntity.ok(tourTrackingService.checkoutTour(request));
    }

    /**
     * [5] Lấy trail GPS để vẽ polyline trên bản đồ
     * GET /api/guide/tracking/{sessionId}/trail
     */
    @GetMapping("/{sessionId}/trail")
    public ResponseEntity<List<GuideLocationResponse>> getLocationTrail(
            @PathVariable Long sessionId) {
        return ResponseEntity.ok(tourTrackingService.getLocationTrail(sessionId));
    }

    /**
     * [6] Lấy danh sách checkpoint đã check-in
     * GET /api/guide/tracking/{sessionId}/checkins
     */
    @GetMapping("/{sessionId}/checkins")
    public ResponseEntity<List<CheckinLogResponse>> getCheckinLogs(
            @PathVariable Long sessionId) {
        return ResponseEntity.ok(tourTrackingService.getCheckinLogs(sessionId));
    }

    /**
     * [7] Lấy thông tin phiên tour
     * GET /api/guide/tracking/{sessionId}
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<TourSessionResponse> getSession(
            @PathVariable Long sessionId) {
        return ResponseEntity.ok(tourTrackingService.getSessionById(sessionId));
    }

    /**
     * [8] Lấy phiên tour đang IN_PROGRESS của guide (dùng khi mở lại app)
     * GET /api/guide/tracking/active?scheduleId=X&guideId=Y
     */
    @GetMapping("/active")
    public ResponseEntity<TourSessionResponse> getActiveSession(
            @RequestParam Long scheduleId,
            @RequestParam Long guideId) {
        return ResponseEntity.ok(tourTrackingService.getActiveSession(scheduleId, guideId));
    }

    /**
     * [9] Lấy tất cả phiên tour của guide
     * GET /api/guide/tracking/assignments/{guideId}
     */
    @GetMapping("/assignments/{guideId}")
    public ResponseEntity<List<TourSessionResponse>> getSessionsByGuideId(
            @PathVariable Long guideId) {
        return ResponseEntity.ok(tourTrackingService.getSessionsByGuideId(guideId));
    }
}
