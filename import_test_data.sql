-- =============================================
-- TEST DATA FOR VIETVIBE / FOODIE TOUR
-- Includes: Roles, Employees, Tours, Routes, Checkpoints, Schedules, and Tracking Sessions
-- =============================================

-- 1. ROLES
INSERT INTO public.role (name, status, created_at, updated_at)
SELECT 'TOURGUIDE', 'ACTIVE', NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM public.role WHERE name = 'TOURGUIDE');

-- 2. EMPLOYEE (Guide)
-- Password: '123456'
INSERT INTO public.employee (role_id, employee_name, email, password, status, created_at, updated_at)
SELECT r.role_id, 'Hanoi Guide', 'guide@test.com', '$2a$10$IXBBliXi8Xs73uioBUnJIeeDECtPhi2nM6yUE8JWPYqSsEEu3cKbS', 'ACTIVE', NOW(), NOW()
FROM public.role r
WHERE r.name = 'TOURGUIDE'
  AND NOT EXISTS (SELECT 1 FROM public.employee WHERE email = 'guide@test.com');

UPDATE public.employee
SET password = (SELECT password FROM public.employee WHERE email = 'felixiter.travel@gmail.com' LIMIT 1)
WHERE email IN ('guide@test.com');

-- 3. TOURS
INSERT INTO public.tours (tour_name, tour_description, duration, base_price_adult, base_price_child, tour_type, tour_status, is_customizable, created_at, updated_at)
SELECT 'Hanoi Street Food Tour', 'Explore the best hidden gems of Hanoi old quarter', 4, 350000, 200000, 'GROUP', 'ACTIVE', false, NOW(), NOW()
WHERE NOT EXISTS (SELECT 1 FROM public.tours WHERE tour_name = 'Hanoi Street Food Tour');

-- 4. ROUTES
INSERT INTO public.routes (route_name, tour_id, route_status, created_at, updated_at)
SELECT 'Old Quarter Food Loop', tour_id, 'ACTIVE', NOW(), NOW()
FROM public.tours WHERE tour_name = 'Hanoi Street Food Tour'
  AND NOT EXISTS (SELECT 1 FROM public.routes WHERE route_name = 'Old Quarter Food Loop');

-- 5. CHECKPOINTS
INSERT INTO public.checkpoints (tour_id, location_name, description, duration_at_location, checkpoint_type, created_at, updated_at)
SELECT tour_id, 'Dong Xuan Market', 'Start point and snack time', 30, 'FOOD', NOW(), NOW() 
FROM public.tours WHERE tour_name = 'Hanoi Street Food Tour'
  AND NOT EXISTS (SELECT 1 FROM public.checkpoints WHERE location_name = 'Dong Xuan Market');

-- 6. Add more Checkpoints
INSERT INTO public.checkpoints (tour_id, location_name, description, duration_at_location, checkpoint_type, created_at, updated_at)
SELECT tour_id, 'Long Bien Bridge', 'Sightseeing and photos', 20, 'VISIT', NOW(), NOW() 
FROM public.tours WHERE tour_name = 'Hanoi Street Food Tour'
  AND NOT EXISTS (SELECT 1 FROM public.checkpoints WHERE location_name = 'Long Bien Bridge');

INSERT INTO public.checkpoints (tour_id, location_name, description, duration_at_location, checkpoint_type, created_at, updated_at)
SELECT tour_id, 'Beer Street', 'Dinner and local drinks', 60, 'FOOD', NOW(), NOW() 
FROM public.tours WHERE tour_name = 'Hanoi Street Food Tour'
  AND NOT EXISTS (SELECT 1 FROM public.checkpoints WHERE location_name = 'Beer Street');

-- 7. LINK CHECKPOINTS (RouteCheckpoint)
INSERT INTO public.route_checkpoints (route_id, checkpoint_id, "order", route_checkpoint_status, created_at, updated_at)
SELECT r.route_id, c.checkpoint_Id, 1, 'ACTIVE', NOW(), NOW()
FROM public.routes r, public.checkpoints c 
WHERE r.route_name = 'Old Quarter Food Loop' AND c.location_name = 'Dong Xuan Market'
  AND NOT EXISTS (SELECT 1 FROM public.route_checkpoints rc WHERE rc.route_id = r.route_id AND rc.checkpoint_id = c.checkpoint_Id);

INSERT INTO public.route_checkpoints (route_id, checkpoint_id, "order", route_checkpoint_status, created_at, updated_at)
SELECT r.route_id, c.checkpoint_Id, 2, 'ACTIVE', NOW(), NOW()
FROM public.routes r, public.checkpoints c 
WHERE r.route_name = 'Old Quarter Food Loop' AND c.location_name = 'Long Bien Bridge'
  AND NOT EXISTS (SELECT 1 FROM public.route_checkpoints rc WHERE rc.route_id = r.route_id AND rc.checkpoint_id = c.checkpoint_Id);

INSERT INTO public.route_checkpoints (route_id, checkpoint_id, "order", route_checkpoint_status, created_at, updated_at)
SELECT r.route_id, c.checkpoint_Id, 3, 'ACTIVE', NOW(), NOW()
FROM public.routes r, public.checkpoints c 
WHERE r.route_name = 'Old Quarter Food Loop' AND c.location_name = 'Beer Street'
  AND NOT EXISTS (SELECT 1 FROM public.route_checkpoints rc WHERE rc.route_id = r.route_id AND rc.checkpoint_id = c.checkpoint_Id);

-- 8. SCHEDULES
INSERT INTO public.schedules (tour_id, route_id, schedule_note, max_pax, min_pax, schedule_status, is_template, departure_at, created_at, updated_at)
SELECT r.tour_id, r.route_id, 'Evening Food Run', 10, 2, 'ACTIVE', false, NOW() + interval '2 hours', NOW(), NOW()
FROM public.routes r WHERE r.route_name = 'Old Quarter Food Loop'
  AND NOT EXISTS (SELECT 1 FROM public.schedules WHERE schedule_note = 'Evening Food Run');

-- 9. TOUR SESSION (Active)
INSERT INTO public.tour_sessions (schedule_id, guide_id, session_status, started_at, created_at, updated_at)
SELECT s.schedule_id, e.employee_id, 'IN_PROGRESS', NOW(), NOW(), NOW()
FROM public.schedules s, public.employee e 
WHERE s.schedule_note = 'Evening Food Run' AND e.email = 'guide@test.com'
  AND NOT EXISTS (SELECT 1 FROM public.tour_sessions ts WHERE ts.schedule_id = s.schedule_id AND ts.guide_id = e.employee_id AND ts.session_status = 'IN_PROGRESS');

-- 10. INITIAL GUIDE LOCATIONS (Optional - for immediate trail visualization)
-- Assuming session_id = 1 (or matching the one created above)
INSERT INTO public.guide_locations (session_id, latitude, longitude, recorded_at)
SELECT ts.session_id, 21.0278, 105.8342, NOW() - interval '5 minutes'
FROM public.tour_sessions ts WHERE ts.session_status = 'IN_PROGRESS' LIMIT 1;

INSERT INTO public.guide_locations (session_id, latitude, longitude, recorded_at)
SELECT ts.session_id, 21.0285, 105.8355, NOW() - interval '2 minutes'
FROM public.tour_sessions ts WHERE ts.session_status = 'IN_PROGRESS' LIMIT 1;

-- 11. INITIAL CHECKIN LOGS (Optional - marking first point as checked in)
INSERT INTO public.checkin_logs (session_id, route_checkpoint_id, status, latitude, longitude, note, checked_at)
SELECT ts.session_id, rc.route_checkpoint_id, 'CHECKED_IN', 21.0278, 105.8342, 'Arrived at market', NOW() - interval '5 minutes'
FROM public.tour_sessions ts, public.route_checkpoints rc, public.routes r, public.checkpoints cp
WHERE ts.session_status = 'IN_PROGRESS' 
  AND r.route_name = 'Old Quarter Food Loop' AND rc.route_id = r.route_id 
  AND cp.location_name = 'Dong Xuan Market' AND rc.checkpoint_id = cp.checkpoint_Id
LIMIT 1;






