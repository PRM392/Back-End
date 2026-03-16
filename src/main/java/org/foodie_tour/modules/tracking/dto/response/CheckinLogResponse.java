package org.foodie_tour.modules.tracking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckinLogResponse {
    private Long checkinId;
    private Long routeCheckpointId;
    private String locationName;
    private Integer order;
    private String status;
    private Double latitude;
    private Double longitude;
    private String note;
    private LocalDateTime checkedAt;
}
