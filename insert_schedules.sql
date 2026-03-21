-- ========================================
-- INSERT SCHEDULES FOR TOURS
-- Each tour gets 3-5 schedules with different dates
-- ========================================

INSERT INTO schedules (schedule_note, schedule_description, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at, tour_id, route_id)
VALUES
-- SAI GON TOURS (tour_id 1-5)
-- Tour 1: Foodie Tour Sai Gon
('Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 1, NULL),
('Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Tuần này', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 1, NULL),
('Pickup at hotel 8AM', 'Khám phá ẩm thực Sài Gòn - Cuối tháng', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '14' DAY, NOW(), NOW(), 1, NULL),

-- Tour 2: Cho Ben Thanh
('Meeting point 9AM at Ben Thanh', 'Chợ Bến Thành - Sáng nay', 15, 3, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 2, NULL),
('Meeting point 9AM at Ben Thanh', 'Chợ Bến Thành - Cuối tuần', 15, 3, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 2, NULL),

-- Tour 3: Sai Gon Ve Dem
('Meeting point 6PM at Ben Thanh', 'Sài Gòn về đêm - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 3, NULL),
('Meeting point 6PM at Ben Thanh', 'Sài Gòn về đêm - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '10' DAY, NOW(), NOW(), 3, NULL),

-- Tour 4: Private Chef
('Contact for pickup time', 'Private Chef Experience - Hôm nay', 8, 2, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 4, NULL),
('Contact for pickup time', 'Private Chef Experience - Tuần sau', 8, 2, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 4, NULL),

-- Tour 5: Cuoi Tuan
('Pickup at hotel 7AM', 'Cuối tuần Sài Gòn - Chủ nhật', 30, 10, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 5, NULL),

-- HA NOI TOURS (tour_id 6-10)
-- Tour 6: Ha Noi Co Kinh
('Pickup at hotel 8AM', 'Hà Nội cổ kính - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 6, NULL),
('Pickup at hotel 8AM', 'Hà Nội cổ kính - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '4' DAY, NOW(), NOW(), 6, NULL),

-- Tour 7: Ha Noi Ve Dem
('Meeting point 6PM at Old Quarter', 'Hà Nội về đêm - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 7, NULL),
('Meeting point 6PM at Old Quarter', 'Hà Nội về đêm - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '10' DAY, NOW(), NOW(), 7, NULL),

-- Tour 8: Hanoi Old Quarter
('Meeting point 9AM at Hoan Kiem', 'Hanoi Old Quarter - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 8, NULL),

-- Tour 9: Private Hanoi
('Contact for pickup time', 'Private Hanoi Food - Tuần này', 10, 3, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 9, NULL),

-- Tour 10: Water Puppet
('Pickup at hotel 4PM', 'Múa rối nước và ẩm thực - Thứ 7', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 10, NULL),

-- DA NANG TOURS (tour_id 11-15)
-- Tour 11: Da Nang Foodie
('Pickup at hotel 8AM', 'Da Nang Foodie - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 11, NULL),
('Pickup at hotel 8AM', 'Da Nang Foodie - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 11, NULL),

-- Tour 12: Hoi An Food
('Pickup at hotel 8AM', 'Hội An - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 12, NULL),
('Pickup at hotel 8AM', 'Hội An - Cuối tháng', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '14' DAY, NOW(), NOW(), 12, NULL),

-- Tour 13: Da Nang Beach
('Pickup at hotel 9AM', 'Da Nang Beach - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 13, NULL),

-- Tour 14: Private Da Nang
('Contact for pickup time', 'Private Da Nang - Tuần này', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '5' DAY, NOW(), NOW(), 14, NULL),

-- Tour 15: Da Nang Night Market
('Meeting point 7PM at Son Tra', 'Da Nang Night Market - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 15, NULL),

-- NHA TRANG TOURS (tour_id 16-18)
-- Tour 16: Nha Trang Seafood
('Pickup at hotel 8AM', 'Nha Trang Seafood - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 16, NULL),
('Pickup at hotel 8AM', 'Nha Trang Seafood - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 16, NULL),

-- Tour 17: Nha Trang Local
('Pickup at hotel 9AM', 'Nha Trang Local - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 17, NULL),

-- Tour 18: Private Nha Trang
('Contact for pickup time', 'Private Nha Trang - Tuần này', 12, 3, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 18, NULL),

-- PHU QUOC TOURS (tour_id 19-20)
-- Tour 19: Phu Quoc Food
('Pickup at hotel 8AM', 'Phú Quốc Food - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 19, NULL),
('Pickup at hotel 8AM', 'Phú Quốc Food - Cuối tháng', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '15' DAY, NOW(), NOW(), 19, NULL),

-- Tour 20: Phu Quoc Night
('Meeting point 7PM at Dinh Cau', 'Phú Quốc Night - Thứ 6', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '4' DAY, NOW(), NOW(), 20, NULL),

-- CAN THO TOURS (tour_id 21-22)
-- Tour 21: Can Tho Foodie
('Pickup at hotel 6AM', 'Cần Thơ Foodie - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 21, NULL),
('Pickup at hotel 6AM', 'Cần Thơ Foodie - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '9' DAY, NOW(), NOW(), 21, NULL),

-- Tour 22: Mekong Delta
('Pickup at hotel 6AM', 'Mekong Delta - Ngày mai', 25, 8, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 22, NULL),

-- HOI AN TOURS (tour_id 23-24)
-- Tour 23: Hoi An Cooking
('Pickup at hotel 8AM', 'Hội An Cooking - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 23, NULL),
('Pickup at hotel 8AM', 'Hội An Cooking - Tuần này', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '6' DAY, NOW(), NOW(), 23, NULL),

-- Tour 24: Hoi An Night
('Meeting point 6PM at Japanese Bridge', 'Hội An Night - Thứ 6', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '3' DAY, NOW(), NOW(), 24, NULL),

-- SAPA TOURS (tour_id 25-26)
-- Tour 25: Sapa Mountain
('Pickup at hotel 7AM', 'Sapa Mountain - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 25, NULL),

-- Tour 26: Sapa Market
('Pickup at hotel 6AM', 'Sapa Market - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 26, NULL),

-- HUE TOURS (tour_id 27-28)
-- Tour 27: Hue Imperial
('Pickup at hotel 8AM', 'Huế Imperial - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 27, NULL),
('Pickup at hotel 8AM', 'Huế Imperial - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 27, NULL),

-- Tour 28: Hue Street
('Meeting point 9AM at Dong Ba', 'Huế Street - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 28, NULL),

-- VUNG TAU TOURS (tour_id 29-30)
-- Tour 29: Vung Tau Beach
('Pickup at hotel 8AM', 'Vũng Tàu Beach - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 29, NULL),
('Pickup at hotel 8AM', 'Vũng Tàu Beach - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '8' DAY, NOW(), NOW(), 29, NULL),

-- Tour 30: Vung Tau Seafood
('Meeting point 7AM at Front Beach', 'Vũng Tàu Seafood - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 30, NULL),

-- QUY NHON TOURS (tour_id 31-32)
-- Tour 31: Quy Nhon Coastal
('Pickup at hotel 8AM', 'Quy Nhơn Coastal - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 31, NULL),

-- Tour 32: Quy Nhon Local
('Pickup at hotel 9AM', 'Quy Nhơn Local - Ngày mai', 15, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 32, NULL),

-- DA LAT TOURS (tour_id 33-34)
-- Tour 33: Da Lat Coffee
('Pickup at hotel 8AM', 'Đà Lạt Coffee - Ngày mai', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '1' DAY, NOW(), NOW(), 33, NULL),
('Pickup at hotel 8AM', 'Đà Lạt Coffee - Cuối tuần', 20, 5, 'ACTIVE', false, NOW() + INTERVAL '7' DAY, NOW(), NOW(), 33, NULL),

-- Tour 34: Dalat Market
('Meeting point 7AM at Da Lat Market', 'Dalat Market - Ngày mai', 18, 4, 'ACTIVE', false, NOW() + INTERVAL '2' DAY, NOW(), NOW(), 34, NULL),

-- DRAFT TOURS (tour_id 35-36)
-- Tour 35: Hidden Gems (DRAFT)
('Pickup at hotel 8AM', 'Hidden Gems - Sắp ra mắt', 15, 4, 'DRAFT', false, NOW() + INTERVAL '30' DAY, NOW(), NOW(), 35, NULL),

-- Tour 36: Private Food Experience (INACTIVE)
('Contact for details', 'Private Food Experience - Tạm ngưng', 10, 3, 'INACTIVE', false, NOW() + INTERVAL '60' DAY, NOW(), NOW(), 36, NULL),

-- TEMPLATE SCHEDULES (for customizable tours)
(NULL, 'Template schedule for customizable tours', 20, 5, 'ACTIVE', true, NULL, NOW(), NOW(), NULL, NULL);

-- ========================================
-- VERIFY
-- ========================================
SELECT COUNT(*) AS total_schedules FROM schedules;
SELECT s.schedule_id, s.departure_at, s.schedule_status, s.is_template, t.tour_name 
FROM schedules s 
JOIN tours t ON s.tour_id = t.tour_id 
ORDER BY s.schedule_id;
