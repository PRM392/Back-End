-- INSERT 37 TOURS FOR TESTING

INSERT INTO tours (tour_name, tour_description, duration, base_price_adult, base_price_child, 
                   tour_type, tour_status, created_at, updated_at, is_customizable, 
                   min_food_places, min_visit_places, total_custom_places)
VALUES
-- SAI GON (5 tours)
('Foodie Tour Sai Gon - Kham Pha Am Thuc', 
 'Kham pha am thuc duong pho Sai Gon voi bun bo, pho, banh mi.',
 1, 500000, 250000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Cho Ben Thanh Food Adventure', 
 'Trai nghiem am thuc tai cho Ben Thanh.',
 0.5, 350000, 175000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 2, 0),
('Sai Gon Ve Dem - Food Tour', 
 'Tour am thuc ve dem tai Sai Gon.',
 0.5, 600000, 300000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Private Chef Experience - Saigon', 
 'Trai nghiem am thuc cao cap voi dau bep rieng.',
 4, 1200000, 600000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 3, 2, 5),
('Cuoi Tuan Sai Gon - Foodie Trip', 
 'Tour tron goi cuoi tuan kham pha am thuc.',
 1, 850000, 425000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 8, 5, 0),

-- HA NOI (5 tours)
('Ha Noi Co Kinh - Am Thuc Truyen Thong', 
 'Kham pha am thuc Ha Noi tai pho co, bun cha, pho bo.',
 1, 450000, 225000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Ha Noi Ve Dem - Street Food Tour', 
 'Tour am thuc duong pho Ha Noi ve dem.',
 0.5, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hanoi Old Quarter Food Adventure', 
 'Cuoc phieu luu am thuc qua 36 pho phuong Ha Noi.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 7, 4, 0),
('Private Hanoi Food Experience', 
 'Trai nghiem am thuc rieng tu tai Ha Noi.',
 4, 1100000, 550000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 4, 3, 5),
('Hanoi Water Puppet and Food Tour', 
 'Ket hop xem mua roi nuoc va kham pha am thuc Ha Noi.',
 1, 750000, 375000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),

-- DA NANG (5 tours)
('Da Nang Foodie Paradise', 
 'Kham pha am thuc Da Nang voi mi Quang, bun cha ca.',
 1, 520000, 260000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hoi An Ancient Town Food Tour', 
 'Kham pha am thuc Hoi An thanh pho co UNESCO.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Da Nang Beach and Seafood', 
 'Tour ket hop bai bien va hai san Da Nang.',
 1, 850000, 425000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Private Da Nang Culinary Tour', 
 'Trai nghiem am thuc rieng tu tai Da Nang.',
 8, 1500000, 750000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 6, 4, 8),
('Da Nang Night Market Food Tour', 
 'Kham pha cho dem va khu pho am thuc Da Nang.',
 0.5, 400000, 200000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- NHA TRANG (3 tours)
('Nha Trang Seafood Paradise', 
 'Tour am thuc hai san tai Nha Trang.',
 1, 720000, 360000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Nha Trang Local Food Discovery', 
 'Kham pha am thuc dia phuong Nha Trang.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),
('Private Nha Trang Food and Beach', 
 'Ket hop tham quan bai bien va am thuc Nha Trang.',
 6, 1300000, 650000, 'PRIVATE', 'ACTIVE', NOW(), NOW(), true, 4, 3, 6),

-- PHU QUOC (2 tours)
('Phu Quoc Food and Nature Tour', 
 'Kham pha am thuc Phu Quoc dao ngoc.',
 1, 680000, 340000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Phu Quoc Night Food Market', 
 'Tour cho dem Phu Quoc.',
 0.5, 450000, 225000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- CAN THO (2 tours)
('Can Tho Foodie - Cho Noi Cai Rang', 
 'Kham pha am thuc mien Tay tai Can Tho.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Mekong Delta Culinary Adventure', 
 'Tour am thuc mien Tay Nam Bo.',
 1, 620000, 310000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 7, 5, 0),

-- HOI AN (2 tours)
('Hoi An Cooking Class and Food Tour', 
 'Ket hop lop nau an va tham quan am thuc Hoi An.',
 4, 950000, 475000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 2, 0),
('Hoi An Ancient Town at Night', 
 'Tour am thuc Hoi An ve dem.',
 0.5, 420000, 210000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- SAPA (2 tours)
('Sapa Mountain Food Experience', 
 'Kham pha am thuc vung cao tai Sapa.',
 1, 650000, 325000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Sapa Local Market and Food Tour', 
 'Tham cho phiên Sapa va kham pha am thuc.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- HUE (2 tours)
('Hue Imperial Food Tour', 
 'Kham pha am thuc cung dinh Hue.',
 1, 600000, 300000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Hue Street Food Adventure', 
 'Tour am thuc duong pho Hue.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- VUNG TAU (2 tours)
('Vung Tau Beach Food Tour', 
 'Ket hop tham quan bien Vung Tau.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Vung Tau Seafood Market Tour', 
 'Tour cho hai san Vung Tau.',
 0.5, 420000, 210000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 4, 3, 0),

-- QUY NHON (2 tours)
('Quy Nhon Coastal Food Tour', 
 'Kham pha am thuc bien tai Quy Nhon.',
 1, 580000, 290000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Quy Nhon Local Cuisine Experience', 
 'Tour am thuc dia phuong Quy Nhon.',
 1, 520000, 260000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- DA LAT (2 tours)
('Da Lat Coffee and Food Tour', 
 'Kham pha am thuc va ca phe Da Lat.',
 1, 550000, 275000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 6, 4, 0),
('Dalat Local Market Adventure', 
 'Tour cho Da Lat va kham pha am thuc.',
 1, 480000, 240000, 'GROUP', 'ACTIVE', NOW(), NOW(), false, 5, 3, 0),

-- DRAFT INACTIVE (2 tours)
('Hidden Gems Food Tour - Draft', 
 'Tour am thuc bi mat - chi VIP.',
 1, 750000, 375000, 'GROUP', 'DRAFT', NOW(), NOW(), false, 6, 4, 0),
('Private Food Experience - Inactive', 
 'Trai nghiem am thuc rieng tu cao cap.',
 4, 2000000, 1000000, 'PRIVATE', 'INACTIVE', NOW(), NOW(), true, 4, 3, 8);

-- VERIFY
SELECT COUNT(*) AS total_tours FROM tours;
SELECT tour_id, tour_name, tour_type, base_price_adult, tour_status FROM tours ORDER BY tour_id;
