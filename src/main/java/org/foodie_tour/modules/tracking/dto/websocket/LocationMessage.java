package org.foodie_tour.modules.tracking.dto.websocket;

import lombok.Data;

/**
 * Message Guide gửi lên server qua WebSocket STOMP.
 *
 * Android (Guide app) gửi tới:
 *   /app/tracking/location
 *
 * Payload JSON:
 * {
 *   "sessionId": 1,
 *   "latitude": 10.762622,
 *   "longitude": 106.660172
 * }
 */
@Data
public class LocationMessage {

    /** ID phiên tour đang IN_PROGRESS */
    private Long sessionId;

    /** Vĩ độ hiện tại của guide */
    private Double latitude;

    /** Kinh độ hiện tại của guide */
    private Double longitude;
}
