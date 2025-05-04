-- Xóa bảng nếu đã tồn tại để tránh lỗi khi chạy lại
DROP TABLE IF EXISTS drink_order_details;
DROP TABLE IF EXISTS drink_components;
DROP TABLE IF EXISTS material_purchase_details;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS drinks;
DROP TABLE IF EXISTS materials;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;

-- Tạo bảng users
CREATE TABLE users (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(11) NOT NULL,
    address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(5) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng roles
CREATE TABLE roles (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng permissions
CREATE TABLE permissions (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng user_roles (bảng trung gian giữa users và roles)
CREATE TABLE user_roles (
    user_id INTEGER(10) NOT NULL,
    role_id INTEGER(10) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Tạo bảng role_permissions (bảng trung gian giữa roles và permissions)
CREATE TABLE role_permissions (
    role_id INTEGER(10) NOT NULL,
    permission_id INTEGER(10) NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

-- Tạo bảng staff
CREATE TABLE staff (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(255) NOT NULL,
    salary INTEGER(10) NOT NULL,
    user_id INTEGER(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tạo bảng orders
CREATE TABLE orders (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER(10) NOT NULL,
    total INTEGER(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tạo bảng drinks
CREATE TABLE drinks (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    material VARCHAR(50),
    price INTEGER(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    category_id INTEGER(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);

-- Tạo bảng drink_order_details (bảng trung gian giữa drinks và orders)
CREATE TABLE drink_order_details (
    drink_id INTEGER(10) NOT NULL,
    order_id INTEGER(10) NOT NULL,
    quantity INTEGER(10) NOT NULL,
    price INTEGER(10) NOT NULL,
    PRIMARY KEY (drink_id, order_id),
    FOREIGN KEY (drink_id) REFERENCES drinks(id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

-- Tạo bảng drink_components (bảng trung gian giữa drinks và materials)
CREATE TABLE drink_components (
    drink_id INTEGER(10) NOT NULL,
    material_id INTEGER(10) NOT NULL,
    PRIMARY KEY (drink_id, material_id),
    FOREIGN KEY (drink_id) REFERENCES drinks(id) ON DELETE CASCADE,
    FOREIGN KEY (material_id) REFERENCES materials(id) ON DELETE CASCADE
);

-- Tạo bảng materials
CREATE TABLE materials (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    quantity INTEGER(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng material_purchase_details (bảng trung gian giữa materials và purchase)
CREATE TABLE material_purchase_details (
    material_id INTEGER(10) NOT NULL,
    purchase_id INTEGER(10) NOT NULL,
    quantity INTEGER(10) NOT NULL,
    price INTEGER(10) NOT NULL,
    PRIMARY KEY (material_id, purchase_id),
    FOREIGN KEY (material_id) REFERENCES materials(id) ON DELETE CASCADE,
    FOREIGN KEY (purchase_id) REFERENCES purchase(id) ON DELETE CASCADE
);

-- Tạo bảng suppliers
CREATE TABLE suppliers (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng category
CREATE TABLE category (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tạo bảng purchase
CREATE TABLE purchase (
    id INTEGER(10) PRIMARY KEY AUTO_INCREMENT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total INTEGER(10) NOT NULL,
    status VARCHAR(10) NOT NULL,
    supplier_id INTEGER(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE
);

-- Chèn dữ liệu mẫu

-- users
INSERT INTO users (username, email, phone, address, password, status) VALUES
('admin', 'admin@example.com', '0123456789', '123 Admin St', 'hashed_password_1', 'true'),
('staff1', 'staff1@example.com', '0987654321', '456 Staff St', 'hashed_password_2', 'true'),
('customer1', 'customer1@example.com', '0912345678', '789 Customer St', 'hashed_password_3', 'true');

-- roles
INSERT INTO roles (name) VALUES
('ADMIN'),
('STAFF'),
('CUSTOMER');

-- permissions
INSERT INTO permissions (name) VALUES
('MANAGE_USERS'),
('MANAGE_ORDERS'),
('VIEW_REPORTS'),
('MANAGE_DRINKS');

-- user_roles
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1), -- admin -> ADMIN
(2, 2), -- staff1 -> STAFF
(3, 3); -- customer1 -> CUSTOMER

-- role_permissions
INSERT INTO role_permissions (role_id, permission_id) VALUES
(1, 1), -- ADMIN -> MANAGE_USERS
(1, 2), -- ADMIN -> MANAGE_ORDERS
(1, 3), -- ADMIN -> VIEW_REPORTS
(1, 4), -- ADMIN -> MANAGE_DRINKS
(2, 2), -- STAFF -> MANAGE_ORDERS
(2, 4); -- STAFF -> MANAGE_DRINKS

-- staff
INSERT INTO staff (fullname, salary, user_id) VALUES
('John Doe', 5000000, 2);

-- category
INSERT INTO category (name) VALUES
('Coffee'),
('Tea'),
('Juice');

-- drinks
INSERT INTO drinks (name, material, price, status, category_id) VALUES
('Espresso', 'Coffee beans', 30000, 'active', 1),
('Green Tea', 'Tea leaves', 25000, 'active', 2),
('Orange Juice', 'Oranges', 35000, 'active', 3);

-- orders
INSERT INTO orders (user_id, total) VALUES
(3, 60000),
(3, 35000);

-- drink_order_details
INSERT INTO drink_order_details (drink_id, order_id, quantity, price) VALUES
(1, 1, 2, 30000), -- 2 Espresso
(2, 2, 1, 25000); -- 1 Green Tea

-- materials
INSERT INTO materials (name, quantity, status) VALUES
('Coffee beans', 100, 'active'),
('Tea leaves', 50, 'active'),
('Oranges', 200, 'active');

-- drink_components
INSERT INTO drink_components (drink_id, material_id) VALUES
(1, 1), -- Espresso -> Coffee beans
(2, 2), -- Green Tea -> Tea leaves
(3, 3); -- Orange Juice -> Oranges

-- suppliers
INSERT INTO suppliers (name, address, contact) VALUES
('Supplier A', '123 Supplier St', '0123456789'),
('Supplier B', '456 Supplier St', '0987654321');

-- purchase
INSERT INTO purchase (date, total, status, supplier_id) VALUES
('2025-04-25 10:00:00', 1000000, 'completed', 1),
('2025-04-25 11:00:00', 500000, 'pending', 2);

-- material_purchase_details
INSERT INTO material_purchase_details (material_id, purchase_id, quantity, price) VALUES
(1, 1, 50, 20000), -- 50 Coffee beans
(2, 2, 20, 25000); -- 20 Tea leaves