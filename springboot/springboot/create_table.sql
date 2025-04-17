-- Bảng api_keys
CREATE TABLE api_keys (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    key VARCHAR(64) UNIQUE,
    status INT(10),
    created_at TIMESTAMP
);

-- Bảng users
CREATE TABLE users (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    email VARCHAR(20) UNIQUE,
    phone VARCHAR(11),
    password VARCHAR(255),
    status VARCHAR(5),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Bảng roles
CREATE TABLE roles (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(20) UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Bảng permissions
CREATE TABLE permissions (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE,
    created_at TIMESTAMP
);

-- users_roles (many-to-many)
CREATE TABLE users_roles (
    user_id INT(10),
    role_id INT(10),
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- roles_permissions (many-to-many)
CREATE TABLE roles_permissions (
    role_id INT(10),
    permission_id INT(10),
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

-- staff_positions
CREATE TABLE staff_positions (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    created_at TIMESTAMP
);

-- staff
CREATE TABLE staff (
    staff_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(50),
    phone VARCHAR(11) UNIQUE,
    email VARCHAR(30) UNIQUE,
    salary INT(10),
    status INT(10),
    position_id INT(10),
    created_at TIMESTAMP,
    FOREIGN KEY (position_id) REFERENCES staff_positions(id)
);

-- orders
CREATE TABLE orders (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    status INT(10),
    created_at TIMESTAMP,
    total INT(10),
    user_id INT(10),
    staff_id INT(10),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

-- order_details
CREATE TABLE order_details (
    drink_id INT(10),
    order_id INT(10),
    quantity INT(10),
    total INT(10),
    price INT(10),
    PRIMARY KEY (drink_id, order_id),
    FOREIGN KEY (drink_id) REFERENCES drinks(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- invoices
CREATE TABLE invoices (
    invoice_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    order_id INT(10),
    created_at TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- categories
CREATE TABLE categories (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE,
    created_at TIMESTAMP
);

-- drinks
CREATE TABLE drinks (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE,
    thumbnail VARCHAR(255),
    description VARCHAR(255),
    price VARCHAR(7),
    stock INT(10),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    category_id INT(10),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- drinks_components
CREATE TABLE drinks_components (
    drink_id INT(10),
    material_id INT(10),
    PRIMARY KEY (drink_id, material_id),
    FOREIGN KEY (drink_id) REFERENCES drinks(id),
    FOREIGN KEY (material_id) REFERENCES materials(id)
);

-- suppliers
CREATE TABLE suppliers (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    supplier VARCHAR(10) UNIQUE,
    created_at TIMESTAMP
);

-- materials
CREATE TABLE materials (
    id INT(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    quantity INT(10),
    status INT(10),
    date_entry TIMESTAMP,
    supplier_id INT(10),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

-- purchase
CREATE TABLE purchase (
    purchase_id INT(10) PRIMARY KEY AUTO_INCREMENT,
    date TIMESTAMP,
    total INT(10),
    status INT(10),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    supplier_id INT(10),
    staff_id INT(10),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

-- purchase_details
CREATE TABLE purchase_details (
    material_id INT(10),
    purchase_id INT(10),
    quantity INT(10),
    price INT(10),
    total INT(10),
    PRIMARY KEY (material_id, purchase_id),
    FOREIGN KEY (material_id) REFERENCES materials(id),
    FOREIGN KEY (purchase_id) REFERENCES purchase(purchase_id)
);