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
    private List<CheckinLogResponse> checkinLogs;
}
