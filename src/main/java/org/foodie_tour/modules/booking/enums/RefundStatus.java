package org.foodie_tour.modules.booking.enums;

public enum RefundStatus {
    INACTIVE,
    PENDING,     // Đang chờ duyệt hoàn tiền
    CONFIRMED,   // Hoàn tiền đã được duyệt
    COMPLETED    // Đã hoàn tiền
}
