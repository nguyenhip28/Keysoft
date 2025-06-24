-- ========================================
-- DATABASE: quanlythuvien (QUẢN LÝ THƯ VIỆN)
-- Cập nhật: Tách role, thêm tính phí phạt trả sách trễ, thêm user_code vào members (không liên kết)
-- ========================================

-- 1. Tạo CSDL
CREATE DATABASE IF NOT EXISTS quanlythuvien;
USE quanlythuvien;

-- ========================================
-- 2. PHÂN QUYỀN NGƯỜI DÙNG (TÁCH RIÊNG)
-- ========================================

-- 2.1. Vai trò
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name ENUM('admin', 'user') UNIQUE NOT NULL
);

-- 2.2. Người dùng hệ thống
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_code VARCHAR(20) UNIQUE,
    full_name VARCHAR(100),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INT,
    gender ENUM('Nam', 'Nữ', 'Khác'),
    address TEXT,
    phone VARCHAR(20),
    password_hash VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2.3. Gán vai trò
CREATE TABLE user_roles (
    user_role_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    role_id INT,
    assigned_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
);

-- ========================================
-- 3. CHỨC NĂNG THƯ VIỆN (KHÔNG LIÊN KẾT USERS)
-- ========================================

-- 3.1. Hội viên
CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    member_code VARCHAR(20) UNIQUE NOT NULL,
    user_code VARCHAR(20), -- chỉ lưu để tham chiếu, không liên kết
    full_name VARCHAR(100),
    gender ENUM('Nam', 'Nữ', 'Khác'),
    age INT,
    address TEXT,
    phone VARCHAR(20)
);

-- 3.2. Sách
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    author VARCHAR(100),
    publish_year INT,
    category VARCHAR(100),
    quantity INT DEFAULT 0,
    image_url TEXT
);

-- 3.3. Mượn sách
CREATE TABLE borrows (
    borrow_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id INT,
    borrow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    expected_return_date DATE,
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE CASCADE
);

-- 3.4. Chi tiết mượn sách
CREATE TABLE borrow_details (
    borrow_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    borrow_id INT,
    book_id INT,
    quantity INT CHECK (quantity <= 2),
    actual_return_date DATE,
    late_fee DECIMAL(10,2) DEFAULT 0.00,
    total_fee DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (borrow_id) REFERENCES borrows(borrow_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

-- 3.5. Phiếu nhập sách
CREATE TABLE book_imports (
    import_id INT AUTO_INCREMENT PRIMARY KEY,
    import_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    member_id INT,
    FOREIGN KEY (member_id) REFERENCES members(member_id) ON DELETE SET NULL
);

-- 3.6. Chi tiết phiếu nhập
CREATE TABLE book_import_details (
    import_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    import_id INT,
    book_id INT,
    quantity INT,
    FOREIGN KEY (import_id) REFERENCES book_imports(import_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

-- ========================================
-- 4. QUY TẮC TÍNH PHÍ TRẢ SÁCH TRỄ
-- ========================================

-- 4.1. Bảng quy định phí trả muộn
CREATE TABLE late_fee_rules (
    rule_id INT AUTO_INCREMENT PRIMARY KEY,
    min_days INT NOT NULL,
    max_days INT,
    fee_per_day DECIMAL(10,2) NOT NULL
);

-- 4.2. (Tuỳ chọn) Bảng ghi nhận phạt (nếu cần log)
CREATE TABLE penalties (
    penalty_id INT AUTO_INCREMENT PRIMARY KEY,
    borrow_detail_id INT,
    calculated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    late_days INT,
    fee_applied DECIMAL(10,2),
    rule_id INT,
    FOREIGN KEY (borrow_detail_id) REFERENCES borrow_details(borrow_detail_id),
    FOREIGN KEY (rule_id) REFERENCES late_fee_rules(rule_id)
);
