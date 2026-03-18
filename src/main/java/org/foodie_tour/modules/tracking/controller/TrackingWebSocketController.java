package org.foodie_tour.modules.tracking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.foodie_tour.common.exception.ResourceNotFoundException;
import org.foodie_tour.modules.tracking.dto.websocket.LocationBroadcast;
import org.foodie_tour.modules.tracking.dto.websocket.LocationMessage;
import org.foodie_tour.modules.tracking.entity.GuideLocation;
import org.foodie_tour.modules.tracking.entity.TourSession;
import org.foodie_tour.modules.tracking.enums.TourSessionStatus;
import org.foodie_tour.modules.tracking.repository.GuideLocationRepository;
import org.foodie_tour.modules.tracking.repository.TourSessionRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * WebSocket STOMP Controller cho real-time location tracking.
 *
 * ┌─────────────────────────────────────────────────────────────────────────┐
 * │  LUỒNG DỮ LIỆU                                                          │
 * │                                                                         │
 * │  Guide App  ──STOMP──▶  /app/tracking/location                          │
 * │                               │                                         │
 * │                         [Backend lưu DB]                                │
 * │                               │                                         │
 * │                         broadcast xuống                                 │
 * │                               │                                         │
 * │  Viewer App  ◀──STOMP──  /topic/tracking/session/{sessionId}            │
 * └─────────────────────────────────────────────────────────────────────────┘
 *
 * CÁCH ANDROID KẾT NỐI:
 *
 * 1. Guide gửi location:
 *    - Kết nối WS tới: ws://host/ws
 *    - Subscribe: không bắt buộc
 *    - Send tới: /app/tracking/location
 *    - Payload: { sessionId, latitude, longitude }
 *
 * 2. Viewer nhận real-time:
 *    - Kết nối WS tới: ws://host/ws
 *    - Subscribe: /topic/tracking/session/{sessionId}
 *    - Nhận: { sessionId, guideId, guideName, latitude, longitude, recordedAt }
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class TrackingWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final TourSessionRepository tourSessionRepository;
    private final GuideLocationRepository guideLocationRepository;

    /**
     * Guide gửi vị trí GPS lên server qua WebSocket.
     * Mapping: /app/tracking/location  →  phương thức này xử lý
     */
    @MessageMapping("/tracking/location")
    public void handleLocationUpdate(@Payload LocationMessage message) {
        log.info("📍 Nhận location từ session {}: lat={}, lng={}",
                message.getSessionId(), message.getLatitude(), message.getLongitude());

        // 1. Tìm phiên tour đang hoạt động
        TourSession session = tourSessionRepository.findById(message.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Phiên tour không tồn tại: " + message.getSessionId()));

        if (session.getSessionStatus() != TourSessionStatus.IN_PROGRESS) {
            log.warn("⚠️ Session {} không ở trạng thái IN_PROGRESS, bỏ qua", message.getSessionId());
            return;
        }

        // 2. Lưu vị trí vào DB
        GuideLocation location = GuideLocation.builder()
                .session(session)
                .latitude(message.getLatitude())
                .longitude(message.getLongitude())
                .build();
        guideLocationRepository.save(location);

        // 3. Broadcast vị trí mới tới tất cả viewer đang subscribe session này
        LocationBroadcast broadcast = LocationBroadcast.builder()
                .sessionId(session.getSessionId())
                .guideId(session.getGuide().getEmployeeId())
                .guideName(session.getGuide().getEmployeeName())
                .latitude(message.getLatitude())
                .longitude(message.getLongitude())
                .recordedAt(LocalDateTime.now())
                .build();

        String destination = "/topic/tracking/session/" + session.getSessionId();
        messagingTemplate.convertAndSend(destination, broadcast);

        log.info("✅ Broadcast location tới {} thành công", destination);
    }
}
