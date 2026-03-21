
package org.foodie_tour.modules.booking.enums;

public enum BookingStatus {
    PENDING,           // Chờ thanh toán (full payment)
    DEPOSIT_PAID,      // Đã cọc 30%, chờ thanh toán 70% còn lại
    COMPLETED,         // Thanh toán đủ 100%
    CANCELLED          // Đã hủy
}