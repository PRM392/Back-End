-- ========================================
-- INSERT TOURS - TEST DATA (idempotent)
-- ========================================

INSERT INTO tours (tour_name, tour_description, duration, base_price_adult, base_price_child,
                   tour_type, tour_status, created_at, updated_at, is_customizable,
                   min_food_places, min_visit_places, total_custom_places)
VALUES
-- SÀI GÒN (5 tours)
('Foodie Tour Sai Gon - Kham Pha Am Thuc Duong Pho',
 'Kham pha am thuc duong pho Sai Gon voi cac mon dac trung nhu bun bo, pho, banh mi.',
 1, 500000, 250000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Cho Ben Thanh Food Adventure',
 'Trai nghiem am thuc tai cho Ben Thanh - trai tim am thuc cua Sai Gon.',
 0.5, 350000, 175000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 2, 0),
('Sai Gon Ve Dem - Food and Drink Tour',
 'Tour am thuc ve dem tai Sai Gon, kham pha cac quan ngon va food court.',
 0.5, 600000, 300000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Private Chef Experience - Saigon',
 'Trai nghiem am thuc cao cap voi dau bep rieng tai Sai Gon.',
 4, 1200000, 600000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 3, 2, 5),
('Cuoi Tuan Sai Gon - Foodie Day Trip',
 'Tour tron goi cuoi tuan kham pha am thuc Sai Gon.',
 1, 850000, 425000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 8, 5, 0),

-- HÀ NỘI (5 tours)
('Ha Noi Co Kinh - Am Thuc Truyen Thong',
 'Kham pha am thuc Ha Noi tai pho co, thuong thuc bun cha, pho bo, banh cuon va cac mon an vat.',
 1, 450000, 225000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Ha Noi Ve Dem - Street Food Tour',
 'Tour am thuc duong pho Ha Noi ve dem, kham pha cac quan bun dau, nem chua ran.',
 0.5, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hanoi Old Quarter Food Adventure',
 'Cuoc phieu luu am thuc qua 36 pho phuong Ha Noi. Thu cac mon dac sac tu Bac vao Nam.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 7, 4, 0),
('Private Hanoi Food Experience',
 'Trai nghiem am thuc rieng tu tai Ha Noi voi huong dan vien dia phuong.',
 4, 1100000, 550000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 4, 3, 5),
('Hanoi Water Puppet and Food Tour',
 'Ket hop xem mua roi nuoc va kham pha am thuc Ha Noi. Tour tron goi van hoa va am thuc.',
 1, 750000, 375000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),

-- ĐÀ NẴNG (5 tours)
('Da Nang Foodie Paradise',
 'Kham pha am thuc Da Nang voi cac mon dac trung nhu mi Quang, bun cha ca, banh xeo.',
 1, 520000, 260000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hoi An Ancient Town Food Tour',
 'Kham pha am thuc Hoi An - thanh pho co UNESCO. Thuong thuc cao lau, banh vac.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Da Nang Beach and Seafood',
 'Tour ket hop bai bien va hai san Da Nang. Thuong thuc tom hum, cua, ca chien.',
 1, 850000, 425000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Private Da Nang Culinary Tour',
 'Trai nghiem am thuc rieng tu tai Da Nang va Hoi An. Duoc thiet ke rieng theo so thich.',
 8, 1500000, 750000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 6, 4, 8),
('Da Nang Night Market Food Tour',
 'Kham pha cac cho dem va khu pho am thuc Da Nang. Thu dac sac dia phuong va hai san nuong.',
 0.5, 400000, 200000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- NHA TRANG (3 tours)
('Nha Trang Seafood Paradise',
 'Tour am thuc hai san tai Nha Trang. Thuong thuc cac loai ca, tom, cua, muc tuoi song.',
 1, 720000, 360000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Nha Trang Local Food Discovery',
 'Kham pha am thuc dia phuong Nha Trang: bun cha ca, banh canh, nem nuong.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Private Nha Trang Food and Beach',
 'Ket hop tham quan bai bien va trai nghiem am thuc rieng tu tai Nha Trang.',
 6, 1300000, 650000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 4, 3, 6),

-- PHÚ QUỐC (2 tours)
('Phu Quoc Food and Nature Tour',
 'Kham pha am thuc Phu Quoc - dao ngoc voi cac dac sac nhu ghichu, nam tram, tieu.',
 1, 680000, 340000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Phu Quoc Night Food Market',
 'Tour cho dem Phu Quoc, thuong thuc hai san nuong, cac mon an duong pho.',
 0.5, 450000, 225000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- CẦN THƠ (2 tours)
('Can Tho Foodie - Cho Noi Cai Rang',
 'Kham pha am thuc mien Tay tai Can Tho, tham cho noi Cai Rang, thuong thuc dac sac.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Mekong Delta Culinary Adventure',
 'Tour am thuc mien Tay Nam Bo, kham pha cac mon an dac trung cua vung dong bang Cuu Long.',
 1, 620000, 310000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 7, 5, 0),

-- HỘI AN (2 tours)
('Hoi An Cooking Class and Food Tour',
 'Ket hop lop nau an va tham quan am thuc Hoi An. Hoc cach lam cac mon dac trung.',
 4, 950000, 475000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 2, 0),
('Hoi An Ancient Town at Night',
 'Tour am thuc Hoi An ve dem, kham pha cac quan an lung linh trong pho co.',
 0.5, 420000, 210000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- SAPA (2 tours)
('Sapa Mountain Food Experience',
 'Kham pha am thuc vung cao tai Sapa, thuong thuc cac mon dac sac Tay Bac nhu thang co.',
 1, 650000, 325000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Sapa Local Market and Food Tour',
 'Tham cho phien Sapa va kham pha am thuc dia phuong cua dong bao dan toc vung Tay Bac.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- HUẾ (2 tours)
('Hue Imperial Food Tour',
 'Kham pha am thuc cung dinh Hue va dac sac xu Hue. Thuong thuc cac mon che bien tinh te.',
 1, 600000, 300000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hue Street Food Adventure',
 'Tour am thuc duong pho Hue, thu cac mon binh dan nhung dam chat Hue.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- VŨNG TÀU (2 tours)
('Vung Tau Beach Food Tour',
 'Ket hop tham quan bien Vung Tau va thuong thuc hai san tuoi song.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Vung Tau Seafood Market Tour',
 'Tour cho hai san Vung Tau, trai nghiem mua hai san va thuong thuc tai cho.',
 0.5, 420000, 210000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 3, 0),

-- QUY NHƠN (2 tours)
('Quy Nhon Coastal Food Tour',
 'Kham pha am thuc bien tai Quy Nhon, thuong thuc hai san tuoi song va dac sac Binh Dinh.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Quy Nhon Local Cuisine Experience',
 'Tour am thuc dia phuong Quy Nhon, thu cac mon dac trung cua Binh Dinh.',
 1, 520000, 260000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- ĐÀ LẠT (2 tours)
('Da Lat Coffee and Food Tour',
 'Kham pha am thuc va ca phe Da Lat. Thu ca phe lua nep, sua dau nanh, banh mi xiu.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Dalat Local Market Adventure',
 'Tour cho Da Lat va kham pha am thuc dia phuong. Thuong thuc rau cu tuoi, trai cay.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- INACTIVE / DRAFT (2 tours - cho test filter)
('Hidden Gems Food Tour - Draft',
 'Tour am thuc bi mat tai Sai Gon - chi danh cho thanh vien VIP.',
 1, 750000, 375000, 'GROUP', 'DRAFT', NOW(), NOW(), false, 6, 4, 0),
('Private Food Experience - Inactive',
 'Trai nghiem am thuc rieng tu cao cap.',
 4, 2000000, 1000000, 'PRIVATE', 'INACTIVE', NOW(), NOW(), true, 4, 3, 8)
ON CONFLICT DO NOTHING;

-- ========================================
-- VERIFY
-- ========================================
SELECT COUNT(*) AS total_tours FROM tours;
