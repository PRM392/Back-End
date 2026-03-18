package org.foodie_tour.modules.tracking.service;

import org.foodie_tour.modules.tracking.dto.request.CheckinRequest;
import org.foodie_tour.modules.tracking.dto.request.CheckoutTourRequest;
import org.foodie_tour.modules.tracking.dto.request.StartTourRequest;
import org.foodie_tour.modules.tracking.dto.request.UpdateLocationRequest;
import org.foodie_tour.modules.tracking.dto.response.CheckinLogResponse;
import org.foodie_tour.modules.tracking.dto.response.GuideLocationResponse;
import org.foodie_tour.modules.tracking.dto.response.TourSessionResponse;

import java.util.List;

public interface TourTrackingService {

    /** Guide bấm "Bắt đầu tour" */
    TourSessionResponse startTour(StartTourRequest request);

    /** Guide gửi vị trí GPS định kỳ (mỗi 10-30 giây) */
    void updateLocation(UpdateLocationRequest request);

    /** Guide bấm "Check-in" tại checkpoint */
    CheckinLogResponse checkinCheckpoint(CheckinRequest request);

    /** Guide bấm "Kết thúc tour" */
    TourSessionResponse checkoutTour(CheckoutTourRequest request);

    /** Lấy toàn bộ tọa độ đã đi (vẽ polyline) */
    List<GuideLocationResponse> getLocationTrail(Long sessionId);

    /** Lấy danh sách checkpoint đã check-in */
    List<CheckinLogResponse> getCheckinLogs(Long sessionId);

    /** Lấy thông tin phiên tour theo ID */
    TourSessionResponse getSessionById(Long sessionId);

    /** Lấy phiên tour đang IN_PROGRESS của guide */
    TourSessionResponse getActiveSession(Long scheduleId, Long guideId);
}
