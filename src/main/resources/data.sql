
-- Tạo bảng departments (phòng ban)
CREATE TABLE IF NOT EXISTS departments (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           department_name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Tạo bảng users (người dùng)
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100),
    department_id BIGINT,
    is_active BOOLEAN DEFAULT TRUE,
    created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments(id)
    );

-- Tạo bảng roles (vai trò)
CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     role_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Tạo bảng permissions (quyền)
CREATE TABLE IF NOT EXISTS permissions (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           permission_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Tạo bảng liên kết user_roles (người dùng - vai trò)
CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role_id BIGINT NOT NULL,
                                          created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
    );

-- Tạo bảng liên kết role_permissions (vai trò - quyền)
CREATE TABLE IF NOT EXISTS role_permissions (
                                                role_id BIGINT NOT NULL,
                                                permission_id BIGINT NOT NULL,
                                                created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
    );

-- Tạo bảng user_permissions (người dùng - quyền) (Tùy chọn)
CREATE TABLE IF NOT EXISTS user_permissions (
                                                user_id BIGINT NOT NULL,
                                                permission_id BIGINT NOT NULL,
                                                created_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, permission_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
    );

---------------------------------------------------

-- Chèn dữ liệu vào bảng departments (phòng ban)
INSERT INTO departments (department_name, description, created_by, updated_by)
VALUES ('HR', 'Human Resources', 'admin', 'admin');

INSERT INTO departments (department_name, description, created_by, updated_by)
VALUES ('IT', 'Information Technology', 'admin', 'admin');

INSERT INTO departments (department_name, description, created_by, updated_by)
VALUES ('Sales', 'Sales Department', 'admin', 'admin');

INSERT INTO departments (department_name, description, created_by, updated_by)
VALUES ('Finance', 'Finance Department', 'admin', 'admin');

INSERT INTO departments (department_name, description, created_by, updated_by)
VALUES ('Marketing', 'Marketing Department', 'admin', 'admin');

-- Chèn dữ liệu vào bảng users (người dùng)
INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('john_doe', 'password1', 'john.doe@example.com', 'John Doe', 1, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('jane_smith', 'password2', 'jane.smith@example.com', 'Jane Smith', 2, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('mike_brown', 'password3', 'mike.brown@example.com', 'Mike Brown', 3, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('linda_white', 'password4', 'linda.white@example.com', 'Linda White', 4, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('susan_clark', 'password5', 'susan.clark@example.com', 'Susan Clark', 5, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('paul_jones', 'password6', 'paul.jones@example.com', 'Paul Jones', 2, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('alice_davis', 'password7', 'alice.davis@example.com', 'Alice Davis', 3, TRUE, 'admin', 'admin');

INSERT INTO users (username, password, email, full_name, department_id, is_active, created_by, updated_by)
VALUES ('kevin_moore', 'password8', 'kevin.moore@example.com', 'Kevin Moore', 1, TRUE, 'admin', 'admin');

-- Chèn dữ liệu vào bảng roles (vai trò)
INSERT INTO roles (role_name, description, created_by, updated_by)
VALUES ('Admin', 'Administrator role', 'admin', 'admin');

INSERT INTO roles (role_name, description, created_by, updated_by)
VALUES ('User', 'Standard user role', 'admin', 'admin');

INSERT INTO roles (role_name, description, created_by, updated_by)
VALUES ('Manager', 'Manager role', 'admin', 'admin');

INSERT INTO roles (role_name, description, created_by, updated_by)
VALUES ('HR_Manager', 'HR Manager role', 'admin', 'admin');

INSERT INTO roles (role_name, description, created_by, updated_by)
VALUES ('IT_Admin', 'IT Administrator role', 'admin', 'admin');

-- Chèn dữ liệu vào bảng permissions (quyền)
INSERT INTO permissions (permission_name, description, created_by, updated_by)
VALUES ('READ', 'Read access', 'admin', 'admin');

INSERT INTO permissions (permission_name, description, created_by, updated_by)
VALUES ('WRITE', 'Write access', 'admin', 'admin');

INSERT INTO permissions (permission_name, description, created_by, updated_by)
VALUES ('DELETE', 'Delete access', 'admin', 'admin');

INSERT INTO permissions (permission_name, description, created_by, updated_by)
VALUES ('MANAGE_USERS', 'Manage users', 'admin', 'admin');

INSERT INTO permissions (permission_name, description, created_by, updated_by)
VALUES ('MANAGE_DEPARTMENTS', 'Manage departments', 'admin', 'admin');

-- Chèn dữ liệu vào bảng liên kết user_roles (người dùng - vai trò)
INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (1, 1, 'admin', 'admin'); -- John Doe là Admin

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (2, 2, 'admin', 'admin'); -- Jane Smith là User

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (3, 3, 'admin', 'admin'); -- Mike Brown là Manager

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (4, 4, 'admin', 'admin'); -- Linda White là HR Manager

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (5, 5, 'admin', 'admin'); -- Susan Clark là IT Admin

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (6, 2, 'admin', 'admin'); -- Paul Jones là User

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (7, 3, 'admin', 'admin'); -- Alice Davis là Manager

INSERT INTO user_roles (user_id, role_id, created_by, updated_by)
VALUES (8, 1, 'admin', 'admin'); -- Kevin Moore là Admin

-- Chèn dữ liệu vào bảng liên kết role_permissions (vai trò - quyền)
-- Admin có tất cả các quyền
INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (1, 1, 'admin', 'admin'); -- Admin có quyền READ

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (1, 2, 'admin', 'admin'); -- Admin có quyền WRITE

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (1, 3, 'admin', 'admin'); -- Admin có quyền DELETE

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (1, 4, 'admin', 'admin'); -- Admin có quyền MANAGE_USERS

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (1, 5, 'admin', 'admin'); -- Admin có quyền MANAGE_DEPARTMENTS

-- User chỉ có quyền READ
INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (2, 1, 'admin', 'admin'); -- User có quyền READ

-- Manager có quyền READ và WRITE
INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (3, 1, 'admin', 'admin'); -- Manager có quyền READ

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (3, 2, 'admin', 'admin'); -- Manager có quyền WRITE

-- HR_Manager có quyền MANAGE_USERS và READ
INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (4, 1, 'admin', 'admin'); -- HR_Manager có quyền READ

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (4, 4, 'admin', 'admin'); -- HR_Manager có quyền MANAGE_USERS

-- IT_Admin có quyền MANAGE_DEPARTMENTS, WRITE và DELETE
INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (5, 2, 'admin', 'admin'); -- IT_Admin có quyền WRITE

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (5, 3, 'admin', 'admin'); -- IT_Admin có quyền DELETE

INSERT INTO role_permissions (role_id, permission_id, created_by, updated_by)
VALUES (5, 5, 'admin', 'admin'); -- IT_Admin có quyền MANAGE_DEPARTMENTS

