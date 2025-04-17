-- Insert data user
INSERT INTO users (id, username, email, phone, password, status, created_at, updated_at)
VALUES 
(1, 'huyanh', 'huyanh@example.com', '0901234567', 'hashed_password_1',  1, NOW(), NOW()),
(2, 'thaison', 'thaison@example.com', '0902345678', 'hashed_password_2', 1, NOW(), NOW());

INSERT INTO roles (id, role, created_at, updated_at)
VALUES 
(1, 'admin', NOW(), NOW()),
(2, 'staff', NOW(), NOW()),
(3, 'customer', NOW(), NOW());

INSERT INTO permissions (id, name, created_at)
VALUES 
(1, 'create_order', NOW()),
(2, 'view_orders', NOW()),
(3, 'manage_users', NOW()),
(4, 'manage_inventory', NOW());


INSERT INTO users_roles (user_id, role_id)
VALUES 
(1, 3),  -- john_doe là customer
(2, 1);  -- jane_smith là admin


-- Admin có toàn quyền
INSERT INTO roles_permissions (role_id, permission_id)
VALUES 
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 1), (2, 2),
(3, 1);




INSERT INTO staff_positions (id, name, created_at)
VALUES (1, 'Barista', NOW()), (2, 'Manager', NOW());

INSERT INTO staff (staff_id, fullname, phone, email, salary, status, created_at, position_id)
VALUES 
(1, 'Nguyen Van A', '0903456789', 'a@cafe.com', 5000000, 1, NOW(), 1),
(2, 'Le Thi B', '0904567890', 'b@cafe.com', 7000000, 1, NOW(), 2);


INSERT INTO categories (id, name, created_at)
VALUES (1, 'Coffee', NOW()), (2, 'Tea', NOW());

INSERT INTO drinks (id, name, thumbnail, description, price, stock, created_at, category_id)
VALUES 
(1, 'Latte', 'latte.png', 'Creamy milk coffee', 45000, 100, NOW(), 1),
(2, 'Green Tea', 'green_tea.png', 'Refreshing green tea', 30000, 80, NOW(), 2);


INSERT INTO orders (id, status, created_at, total, user_id, staff_id)
VALUES 
(1, 1, NOW(), 120000, 1, 1);

INSERT INTO order_details (drink_id, order_id, quantity, price, total)
VALUES 
(1, 1, 2, 45000, 90000),
(2, 1, 1, 30000, 30000);


INSERT INTO suppliers (id, supplier, created_at)
VALUES (1, 'Nguyen Coffee Supply', NOW());

INSERT INTO materials (id, name, quantity, status, date_entry, supplier_id)
VALUES 
(1, 'Coffee Beans', 50, 1, NOW(), 1),
(2, 'Milk', 30, 1, NOW(), 1);

INSERT INTO drinks_components (drink_id, material_id)
VALUES 
(1, 1),
(1, 2);


INSERT INTO purchase (purchase_id, date, total, status, created_at, supplier_id, staff_id)
VALUES 
(1, NOW(), 200000, 1, NOW(), 1, 2);

INSERT INTO purchase_details (material_id, purchase_id, quantity, price, total)
VALUES 
(1, 1, 5, 20000, 100000),
(2, 1, 10, 10000, 100000);
