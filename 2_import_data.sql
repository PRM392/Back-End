-- ============================================================
-- IMPORT TEST DATA - FIX ĐẦY ĐỦ
-- Chạy: psql -U postgres -d foodie_tour -f 2_import_data.sql
-- ============================================================

-- -------------------------------------------
-- 1. SYSTEM CONFIG
-- -------------------------------------------
INSERT INTO system_configs (config_key, config_value, description) VALUES
('CANCEL_ALLOW_HOURS', '24', 'Số giờ cho phép hủy trước khởi hành'),
('RELOCATE_ALLOW_HOURS', '8', 'Số giờ cho phép dời lịch'),
('DEPOSIT_PERCENTAGE', '30', 'Phần trăm tiền cọc'),
('MIN_BOOKING_ADULT', '1', 'Số người lớn tối thiểu'),
('MAX_BOOKING_PAX', '50', 'Số khách tối đa mỗi booking');

-- -------------------------------------------
-- 2. PERMISSIONS
-- -------------------------------------------
INSERT INTO permission (name, description, status) VALUES
('CREATE_TOUR', 'Tạo tour mới', 'ACTIVE'),
('UPDATE_TOUR', 'Cập nhật tour', 'ACTIVE'),
('DELETE_TOUR', 'Xóa tour', 'ACTIVE'),
('VIEW_TOUR', 'Xem tour', 'ACTIVE'),
('CREATE_SCHEDULE', 'Tạo lịch trình', 'ACTIVE'),
('UPDATE_SCHEDULE', 'Cập nhật lịch trình', 'ACTIVE'),
('DELETE_SCHEDULE', 'Xóa lịch trình', 'ACTIVE'),
('VIEW_SCHEDULE', 'Xem lịch trình', 'ACTIVE'),
('CREATE_BOOKING', 'Tạo booking', 'ACTIVE'),
('UPDATE_BOOKING', 'Cập nhật booking', 'ACTIVE'),
('DELETE_BOOKING', 'Xóa booking', 'ACTIVE'),
('VIEW_BOOKING', 'Xem booking', 'ACTIVE'),
('PROCESS_RELOCATE', 'Xử lý dời lịch', 'ACTIVE'),
('ADMIN_REFUND', 'Hoàn tiền admin', 'ACTIVE'),
('MANAGE_EMPLOYEE', 'Quản lý nhân viên', 'ACTIVE'),
('VIEW_REVENUE', 'Xem doanh thu', 'ACTIVE'),
('MANAGE_ROUTE', 'Quản lý tuyến đường', 'ACTIVE');

-- -------------------------------------------
-- 3. ROLES
-- -------------------------------------------
INSERT INTO role (name, status) VALUES
('ADMIN', 'ACTIVE'),
('GUIDE', 'ACTIVE'),
('MANAGER', 'ACTIVE');

-- -------------------------------------------
-- 4. ROLE PERMISSION
-- -------------------------------------------
INSERT INTO role_permission (role_id, permission_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8),
(1, 9), (1, 10), (1, 11), (1, 12), (1, 13), (1, 14), (1, 15), (1, 16), (1, 17),
(3, 4), (3, 8), (3, 12), (3, 16),
(2, 4), (2, 8), (2, 12);

-- -------------------------------------------
-- 5. EMPLOYEE (số ít, column: employee_name)
-- -------------------------------------------
INSERT INTO employee (email, password, employee_name, phone, image, status) VALUES
('admin@foodie.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'Nguyễn Văn Admin', '0901234567', NULL, 'ACTIVE'),
('manager@foodie.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'Trần Thị Manager', '0901234568', NULL, 'ACTIVE'),
('guide1@foodie.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'Lê Văn Guide 1', '0901234569', NULL, 'ACTIVE'),
('guide2@foodie.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'Phạm Thị Guide 2', '0901234570', NULL, 'ACTIVE'),
('admin2@foodie.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'Hoàng Văn Admin 2', '0901234571', NULL, 'ACTIVE');

-- -------------------------------------------
-- 6. TOURS
-- -------------------------------------------
INSERT INTO tours (tour_name, tour_description, duration, base_price_adult, base_price_child, tour_type, tour_status, is_customizable, min_food_places, min_visit_places, total_custom_places) VALUES
('Khám Phá Ẩm Thực Sài Gòn', 'Tour trải nghiệm ẩm thực đường phố Sài Gòn', 4, 350000, 250000, 'CITY_TOUR', 'ACTIVE', true, 3, 2, 8),
('Sài Gòn Về Đêm', 'Tour khám phá ẩm thực về đêm', 3, 450000, 300000, 'NIGHT_TOUR', 'ACTIVE', false, 4, 2, 6),
('Chợ Lớn - Di Sản Ẩm Thực', 'Tour đặc biệt khám phá khu Chợ Lớn', 5, 550000, 400000, 'CULTURAL', 'ACTIVE', true, 5, 3, 10),
('Ẩm Thực Miền Tây', 'Tour 1 ngày khám phá ẩm thực miền Tây Nam Bộ', 8, 650000, 450000, 'DAY_TRIP', 'ACTIVE', false, 4, 2, 6),
('Street Food Master', 'Tour dành cho food blogger', 6, 500000, 350000, 'CITY_TOUR', 'ACTIVE', true, 6, 3, 12),
('Gastronomy Deluxe', 'Tour cao cấp fine dining', 6, 1200000, 900000, 'DELUXE', 'ACTIVE', false, 4, 2, 8);

-- -------------------------------------------
-- 7. ROUTES
-- -------------------------------------------
INSERT INTO routes (route_name, route_status, tour_id) VALUES
('Tuyến Bến Thành - Quận 1', 'ACTIVE', 1),
('Tuyến Chợ Lớn', 'ACTIVE', 1),
('Tuyến Đêm Q.1', 'ACTIVE', 2),
('Tuyến Đêm Q.3', 'ACTIVE', 2),
('Tuyến Hoa Kiềng', 'ACTIVE', 3),
('Tuyến Cần Giờ', 'ACTIVE', 4),
('Tuyến Full Day', 'ACTIVE', 5),
('Tuyến Deluxe', 'ACTIVE', 6);

-- -------------------------------------------
-- 8. CHECKPOINTS (theo entity: location_name, duration_at_location, ggmap_url, checkpoint_type)
-- -------------------------------------------
INSERT INTO checkpoints (location_name, description, duration_at_location, ggmap_url, checkpoint_type) VALUES
('Phở Thìn Bò Nhừ', 'Phở Hà Nội ngon nổi tiếng - 93 Bis Lê Thị Riêng, Q.1', 30, 'https://maps.google.com/?q=10.7725,106.6980', 'FOOD'),
('Bánh Mì Huỳnh Hoa', 'Bánh mì ngon nhất - 26 Lê Thị Riêng, Q.1', 20, 'https://maps.google.com/?q=10.7723,106.6975', 'FOOD'),
('Cơm Tấm Kiều Giang', 'Cơm tấm sườn bì chả - 333 Võ Văn Tần, Q.3', 40, 'https://maps.google.com/?q=10.7867,106.6890', 'FOOD'),
('Hủ Tiếu Mãng Cầu', 'Hủ tiếu nam vang - 64 Lê Quốc Sơn, Q.4', 30, 'https://maps.google.com/?q=10.7520,106.7080', 'FOOD'),
('Bún Bò Huế Ông Háo', 'Bún bò Huế chính hiệu - 135 Nguyễn Cư Trinh, Q.1', 35, 'https://maps.google.com/?q=10.7680,106.6950', 'FOOD'),
('Bánh Pía Sơn Hiệp', 'Bánh pía huyền thoại - 255 Hùng Vương, Q.5', 40, 'https://maps.google.com/?q=10.7498,106.7150', 'VISIT'),
('Chợ Nhiếp Thành', 'Chợ nổi đặc sản - QL50, Cần Giờ', 60, 'https://maps.google.com/?q=10.5512,106.8920', 'VISIT'),
('Nhà Hàng Ngon', 'Fine dining - 5 Đồng Khởi, Q.1', 90, 'https://maps.google.com/?q=10.7798,106.7020', 'FOOD');

-- -------------------------------------------
-- 8.1 ROUTE CHECKPOINTS (liên kết checkpoint với route)
-- -------------------------------------------
INSERT INTO route_checkpoints (route_id, checkpoint_id, "order") VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(3, 4, 1),
(3, 5, 2),
(5, 6, 1),
(6, 7, 1),
(8, 8, 1);

-- -------------------------------------------
-- 9. SCHEDULES
-- -------------------------------------------
INSERT INTO schedules (tour_id, route_id, schedule_note, schedule_description, min_pax, max_pax, schedule_status, is_template, departure_at) VALUES
(1, 1, 'Đón khách 7h30', 'Tour Bến Thành', 5, 20, 'ACTIVE', true, '2026-04-01 07:30:00'),
(1, 2, 'Gặp guide 8h', 'Tour Chợ Lớn', 5, 20, 'ACTIVE', true, '2026-04-05 08:00:00'),
(1, 1, 'Đón khách 14h', 'Tour chiều', 3, 15, 'ACTIVE', true, '2026-04-10 14:00:00'),
(2, 3, 'Gặp guide 18h', 'Tour đêm Q.1', 5, 25, 'ACTIVE', true, '2026-04-03 18:00:00'),
(2, 4, 'Gặp guide 19h', 'Tour đêm Q.3', 5, 25, 'ACTIVE', true, '2026-04-08 19:00:00'),
(3, 5, 'Gặp guide 8h30', 'Tour Chợ Lớn', 8, 30, 'ACTIVE', true, '2026-04-06 08:30:00'),
(3, 5, 'Gặp guide 13h', 'Tour chiều', 6, 25, 'ACTIVE', true, '2026-04-12 13:00:00'),
(4, 6, 'Đón khách 6h', 'Tour Cần Giờ', 10, 40, 'ACTIVE', true, '2026-04-04 06:00:00'),
(5, 7, 'Bắt đầu 8h', 'Tour full day', 4, 20, 'ACTIVE', true, '2026-04-07 08:00:00'),
(5, 7, 'Bắt đầu 14h', 'Tour chiều', 3, 15, 'ACTIVE', true, '2026-04-11 14:00:00'),
(6, 8, 'Bắt đầu 18h', 'Tour deluxe', 2, 12, 'ACTIVE', true, '2026-04-09 18:00:00');

-- -------------------------------------------
-- 10. CUSTOMERS
-- -------------------------------------------
INSERT INTO customers (email, customer_name, phone, customer_status) VALUES
('test@gmail.com', 'Test User', '0912345678', 'PENDING'),
('customer1@example.com', 'Nguyễn Văn A', '0901234567', 'COMPLETED'),
('customer2@example.com', 'Trần Thị B', '0901234568', 'COMPLETED'),
('tourist@email.com', 'John Smith', '0909876543', 'PENDING'),
('vietnamese@email.com', 'Lê Minh Tuấn', '0933888123', 'PENDING'),
('foodie@email.com', 'Ngọc Trinh', '0977889456', 'COMPLETED');

-- -------------------------------------------
-- 11. BOOKINGS
-- -------------------------------------------
INSERT INTO bookings (booking_code, customer_name, email, phone, adult_count, children_count, pickup_location, customer_note, total_price, is_deposit, payment_method, booking_status, refund_status, departure_time, tour_id, route_id, schedule_id) VALUES
('BK2026032201', 'Test User', 'test@gmail.com', '0912345678', 2, 1, 'Khách sạn Rex, Q.1', 'Test', 950000, true, 'VNPAY', 'PENDING', 'INACTIVE', '2026-04-01 07:30:00', 1, 1, 1),
('BK2026032202', 'Nguyễn Văn A', 'customer1@example.com', '0901234567', 3, 0, 'Bến xe Miền Tây', 'Đoàn công ty', 1350000, true, 'VNPAY', 'DEPOSIT_PAID', 'INACTIVE', '2026-04-03 18:00:00', 2, 3, 4),
('BK2026032203', 'John Smith', 'tourist@email.com', '0909876543', 1, 2, 'Hotel Continental', 'Family', 1250000, false, 'CASH', 'COMPLETED', 'INACTIVE', '2026-04-07 08:00:00', 5, 7, 9),
('BK2026032204', 'Lê Minh Tuấn', 'vietnamese@email.com', '0933888123', 4, 2, 'Vingroup Center', 'Gia đình', 2050000, true, 'ONEPAY', 'PENDING', 'INACTIVE', '2026-04-06 08:30:00', 3, 5, 6),
('BK2026032205', 'Ngọc Trinh', 'foodie@email.com', '0977889456', 2, 0, 'Khách sạn Majestic', 'VIP', 2400000, false, 'VNPAY', 'COMPLETED', 'INACTIVE', '2026-04-09 18:00:00', 6, 8, 11);

-- -------------------------------------------
-- 12. BOOKING LOG (số ít, column: create_at)
-- -------------------------------------------
INSERT INTO booking_log (booking_id, employee_id, description, booking_status) VALUES
(1, NULL, 'Đặt tour thành công', 'PENDING'),
(2, NULL, 'Đặt tour thành công', 'PENDING'),
(2, NULL, 'Thanh toán cọc 30% thành công', 'DEPOSIT_PAID'),
(3, NULL, 'Đặt tour thành công', 'PENDING'),
(3, NULL, 'Thanh toán đủ thành công', 'COMPLETED'),
(4, NULL, 'Đặt tour thành công', 'PENDING'),
(5, NULL, 'Đặt tour thành công', 'PENDING'),
(5, NULL, 'Thanh toán đủ - VIP', 'COMPLETED');

-- -------------------------------------------
-- 13. TRANSACTIONS
-- -------------------------------------------
INSERT INTO transactions (booking_id, amount, payment_method, cash_flow, status, gateway_transaction_id) VALUES
(2, 405000, 'VNPAY', 'INCOME', 'SUCCESS', 987654321),
(3, 1250000, 'CASH', 'INCOME', 'SUCCESS', NULL),
(5, 2400000, 'VNPAY', 'INCOME', 'SUCCESS', 555666777);

-- -------------------------------------------
-- 14. CUSTOMER BOOKINGS
-- -------------------------------------------
INSERT INTO customer_bookings (customer_id, booking_id, is_main) VALUES
(1, 1, true),
(2, 2, true),
(4, 3, true),
(5, 4, true),
(6, 5, true);

-- ============================================================
-- DONE!
-- ============================================================
