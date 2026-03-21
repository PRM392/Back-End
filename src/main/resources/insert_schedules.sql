-- ========================================
-- INSERT SCHEDULES FOR TOURS
-- Each tour gets 3-5 schedules with different dates
-- ========================================

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 1, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 1 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Tuần này', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 1, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 1 AND departure_at = NOW() + INTERVAL '7' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Cuối tháng', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '14' DAY, NOW(), NOW(), 1, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 1 AND departure_at = NOW() + INTERVAL '14' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 9AM at Ben Thanh', 'Chợ Bến Thành - Sáng nay', 15, 3, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 2, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 2 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 9AM at Ben Thanh', 'Chợ Bến Thành - Cuối tuần', 15, 3, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 2, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 2 AND departure_at = NOW() + INTERVAL '5' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 6PM at Ben Thanh', 'Sài Gòn về đêm - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 3, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 3 AND departure_at = NOW() + INTERVAL '3' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 6PM at Ben Thanh', 'Sài Gòn về đêm - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '10' DAY, NOW(), NOW(), 3, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 3 AND departure_at = NOW() + INTERVAL '10' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for pickup time', 'Private Chef Experience - Hôm nay', 8, 2, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 4, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 4 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for pickup time', 'Private Chef Experience - Tuần sau', 8, 2, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 4, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 4 AND departure_at = NOW() + INTERVAL '8' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 7AM', 'Cuối tuần Sài Gòn - Chủ nhật', 30, 10, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 5, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 5 AND departure_at = NOW() + INTERVAL '6' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hà Nội cổ kính - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 6, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 6 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hà Nội cổ kính - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '4' DAY, NOW(), NOW(), 6, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 6 AND departure_at = NOW() + INTERVAL '4' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 6PM at Old Quarter', 'Hà Nội về đêm - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 7, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 7 AND departure_at = NOW() + INTERVAL '3' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 6PM at Old Quarter', 'Hà Nội về đêm - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '10' DAY, NOW(), NOW(), 7, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 7 AND departure_at = NOW() + INTERVAL '10' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 9AM at Hoan Kiem', 'Hanoi Old Quarter - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 8, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 8 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for pickup time', 'Private Hanoi Food - Tuần này', 10, 3, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 9, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 9 AND departure_at = NOW() + INTERVAL '5' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 4PM', 'Múa rối nước và ẩm thực - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 10, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 10 AND departure_at = NOW() + INTERVAL '6' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Da Nang Foodie - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 11, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 11 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Da Nang Foodie - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 11, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 11 AND departure_at = NOW() + INTERVAL '7' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hội An - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 12, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 12 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hội An - Cuối tháng', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '14' DAY, NOW(), NOW(), 12, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 12 AND departure_at = NOW() + INTERVAL '14' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 9AM', 'Da Nang Beach - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 13, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 13 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for pickup time', 'Private Da Nang - Tuần này', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 14, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 14 AND departure_at = NOW() + INTERVAL '5' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 7PM at Son Tra', 'Da Nang Night Market - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 15, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 15 AND departure_at = NOW() + INTERVAL '3' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Nha Trang Seafood - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 16, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 16 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Nha Trang Seafood - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 16, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 16 AND departure_at = NOW() + INTERVAL '8' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 9AM', 'Nha Trang Local - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 17, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 17 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for pickup time', 'Private Nha Trang - Tuần này', 12, 3, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 18, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 18 AND departure_at = NOW() + INTERVAL '6' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Phú Quốc Food - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 19, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 19 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Phú Quốc Food - Cuối tháng', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '15' DAY, NOW(), NOW(), 19, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 19 AND departure_at = NOW() + INTERVAL '15' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 7PM at Dinh Cau', 'Phú Quốc Night - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '4' DAY, NOW(), NOW(), 20, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 20 AND departure_at = NOW() + INTERVAL '4' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 6AM', 'Cần Thơ Foodie - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 21, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 21 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 6AM', 'Cần Thơ Foodie - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '9' DAY, NOW(), NOW(), 21, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 21 AND departure_at = NOW() + INTERVAL '9' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 6AM', 'Mekong Delta - Ngày mai', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 22, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 22 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hội An Cooking - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 23, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 23 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hội An Cooking - Tuần này', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 23, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 23 AND departure_at = NOW() + INTERVAL '6' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 6PM at Japanese Bridge', 'Hội An Night - Thứ 6', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 24, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 24 AND departure_at = NOW() + INTERVAL '3' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 7AM', 'Sapa Mountain - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 25, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 25 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 6AM', 'Sapa Market - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 26, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 26 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Huế Imperial - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 27, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 27 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Huế Imperial - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 27, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 27 AND departure_at = NOW() + INTERVAL '7' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 9AM at Dong Ba', 'Huế Street - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 28, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 28 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Vũng Tàu Beach - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 29, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 29 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Vũng Tàu Beach - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 29, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 29 AND departure_at = NOW() + INTERVAL '8' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 7AM at Front Beach', 'Vũng Tàu Seafood - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 30, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 30 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Quy Nhơn Coastal - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 31, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 31 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 9AM', 'Quy Nhơn Local - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 32, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 32 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Đà Lạt Coffee - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 33, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 33 AND departure_at = NOW() + INTERVAL '1' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Đà Lạt Coffee - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 33, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 33 AND departure_at = NOW() + INTERVAL '7' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Meeting point 7AM at Da Lat Market', 'Dalat Market - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 34, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 34 AND departure_at = NOW() + INTERVAL '2' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Pickup at hotel 8AM', 'Hidden Gems - Sắp ra mắt', 15, 4, 'DRAFT', false, NOW() + INTERVAL '30' DAY, NOW(), NOW(), 35, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 35 AND departure_at = NOW() + INTERVAL '30' DAY LIMIT 1);

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT 'Contact for details', 'Private Food Experience - Tạm ngưng', 10, 3, 'INACTIVE', false, NOW() + INTERVAL '60' DAY, NOW(), NOW(), 36, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE tour_id = 36 AND departure_at = NOW() + INTERVAL '60' DAY LIMIT 1);

-- Template schedule for customizable tours
INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
SELECT NULL, 'Template schedule for customizable tours', 20, 5, 'ACTIVE', true, NULL, NOW(), NOW(), NULL, NULL
WHERE NOT EXISTS (SELECT 1 FROM schedules WHERE is_template = true LIMIT 1);
