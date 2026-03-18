package org.foodie_tour.modules.tracking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuideLocationResponse {
    private Long locationId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime recordedAt;
}
