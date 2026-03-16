package org.foodie_tour.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * Lắng nghe sự kiện WebSocket STOMP để log kết nối từ Android/Client.
 *
 * Xem log trên console server để biết:
 *   - Có client nào đang kết nối không
 *   - Client đang subscribe topic nào
 *   - Client nào vừa ngắt kết nối
 */
@Slf4j
@Component
public class WebSocketEventListener {

    /**
     * Khi một client (Android app, Postman, ws-test.html...) kết nối thành công
     */
    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) {
        String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
        log.info("✅ [WebSocket] Client kết nối mới | SessionId: {}", sessionId);
    }

    /**
     * Khi client subscribe vào một topic (ví dụ: /topic/tracking/session/1)
     */
    @EventListener
    public void handleSessionSubscribe(SessionSubscribeEvent event) {
        String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
        String destination = (String) event.getMessage().getHeaders().get("simpDestination");
        log.info("📡 [WebSocket] Client subscribe | SessionId: {} | Topic: {}", sessionId, destination);
    }

    /**
     * Khi client ngắt kết nối (app bị đóng, mất mạng, v.v.)
     */
    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        log.info("🔌 [WebSocket] Client ngắt kết nối | SessionId: {}", sessionId);
    }
}
