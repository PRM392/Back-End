-- Fix: Thêm DEPOSIT_PAID vào booking_status check constraint
-- Chạy câu lệnh này trong PostgreSQL

-- Xóa constraint cũ và tạo mới với DEPOSIT_PAID
ALTER TABLE bookings DROP CONSTRAINT IF EXISTS bookings_booking_status_check;

ALTER TABLE bookings ADD CONSTRAINT bookings_booking_status_check 
    CHECK (booking_status IN ('PENDING', 'CONFIRMED', 'DEPOSIT_PAID', 'COMPLETED', 'CANCELLED', 'REFUNDED'));
