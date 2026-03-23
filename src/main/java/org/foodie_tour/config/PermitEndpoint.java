package org.foodie_tour.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermitEndpoint {
    public static String[] PUBLIC_ENDPOINTS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/ws/**",
            "/api/booking/**",
            "/api/bookings/**",
            "/api/feedback/**",
            "/api/images/**",
            "/api/mail/send",
            "/api/onepay/**",
            "/api/payment/vnpay/**",
    };

    public static String[] PUBLIC_POST_ENDPOINTS = {
            "/api/auth/login",
            "/api/booking",
            "/api/booking/cancel",
            "/api/booking/*/vnpay-confirm"
    };

    public static String[] PUBLIC_GET_ENDPOINTS = {
            "/api/booking/all",                    // Admin xem tất cả booking
            "/api/booking/{bookingCode}",        // Xem chi tiết 1 booking
            "/api/booking/{bookingCode}/logs",   // Xem lịch sử booking
            "/api/routes",
            "/api/routes/{id}",
            "/api/schedules",
            "/api/dishes",
            "/api/dishes/{id}",
            "/api/tour/{id}",
            "/api/tour",
            "/api/tour/search",
            "/api/checkpoints/**",
            "/api/guide/tracking/*/trail",
            "/api/guide/tracking/*/checkins",
            "/api/guide/tracking/*",
            "/api/guide/tracking/active"
    };

    public static String[] PUBLIC_PUT_ENDPOINTS = {
            "/api/booking/*/complete-payment",  // Admin xác nhận thanh toán
            "/api/booking/*/approve-refund"     // Admin hoàn tiền
    };
}
