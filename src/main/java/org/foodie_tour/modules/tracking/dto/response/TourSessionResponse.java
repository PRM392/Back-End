package org.foodie_tour.modules.tracking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TourSessionResponse {
    private Long sessionId;
    private Long scheduleId;
    private Long guideId;
    private String guideName;
    private String sessionStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime createdAt;
    private Long tourId;
    private Long routeId;
    private String tourTitle;
    private String departureDate;
    private String departureTime;
    private Integer guests;
    private String location;
    private List<CheckinLogResponse> checkinLogs;
}
