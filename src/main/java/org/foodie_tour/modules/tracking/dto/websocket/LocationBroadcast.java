package org.foodie_tour.modules.tracking.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Payload server broadcast xuống tất cả Viewer (khách, admin) đang theo dõi.
 *
 * Viewer (Android/Web) subscribe vào topic:
 *   /topic/tracking/session/{sessionId}
 *
 * Nhận JSON:
 * {
 *   "sessionId": 1,
 *   "guideId"  : 7,
 *   "guideName": "Nguyễn Văn A",
 *   "latitude" : 10.762622,
 *   "longitude": 106.660172,
 *   "recordedAt": "2026-03-16T13:45:00"
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationBroadcast {

    private Long sessionId;
    private Long guideId;
    private String guideName;
    private Double latitude;
    private Double longitude;
    private LocalDateTime recordedAt;
}
