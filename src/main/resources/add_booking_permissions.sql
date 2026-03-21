-- Thêm các permission booking cho admin
INSERT INTO public.permission (name, description, status, created_at, updated_at)
SELECT v.name, v.description, v.status, NOW(), NOW()
FROM (VALUES
    ('ADMIN_VIEW_BOOKING', 'Cho phép admin xem danh sách tất cả booking', 'ACTIVE'),
    ('ADMIN_CONFIRM_PAYMENT', 'Cho phép admin xác nhận thanh toán', 'ACTIVE'),
    ('ADMIN_REFUND', 'Cho phép admin hoàn tiền booking', 'ACTIVE')
) AS v(name, description, status)
WHERE NOT EXISTS (
    SELECT 1 FROM public.permission p WHERE p.name = v.name
);

-- Gán quyền cho ADMIN role
INSERT INTO public.role_permission (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM public.role r
CROSS JOIN public.permission p
WHERE r.name = 'ADMIN'
AND p.name IN ('ADMIN_VIEW_BOOKING', 'ADMIN_CONFIRM_PAYMENT', 'ADMIN_REFUND')
AND NOT EXISTS (
    SELECT 1 FROM public.role_permission rp 
    WHERE rp.role_id = r.role_id AND rp.permission_id = p.permission_id
);
