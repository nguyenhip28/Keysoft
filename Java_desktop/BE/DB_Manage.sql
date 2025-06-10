CREATE DATABASE hospital_management;
USE hospital_management;

CREATE TABLE roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- Users
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_code VARCHAR(20) UNIQUE NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100),
    email VARCHAR(100),
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- Permissions (no longer dependent on a functions table)
CREATE TABLE user_permissions (
    permission_id INT PRIMARY KEY AUTO_INCREMENT,
    user_code VARCHAR(20) NOT NULL,
    feature_name VARCHAR(100) NOT NULL, -- e.g., manage_patients, manage_appointments
    description TEXT,
    FOREIGN KEY (user_code) REFERENCES users(user_code)
);

-- Patients
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_code VARCHAR(10) UNIQUE NOT NULL, 
    full_name VARCHAR(100),
    date_of_birth DATE,
    gender ENUM('Nam','Nữ'),
    address VARCHAR(200),
    phone_number VARCHAR(20),
    email VARCHAR(100)
);

-- Appointments
CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_code VARCHAR(10) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    symptoms TEXT,
    FOREIGN KEY (patient_code) REFERENCES patients(patient_code)
);

-- Medicines
CREATE TABLE medicines (
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    medicine_name VARCHAR(100),
    unit VARCHAR(50),
    quantity INT,
    price DECIMAL(10,2)
);

-- Prescriptions
CREATE TABLE prescriptions (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_code VARCHAR(10),
    prescription_date DATE NOT NULL,
    notes TEXT,
    total_amount DECIMAL(12,2),
    FOREIGN KEY (patient_code) REFERENCES patients(patient_code)
);

-- Prescription Details
CREATE TABLE prescription_details (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    prescription_id INT,
    medicine_id INT,
    quantity INT,
    dosage VARCHAR(100),
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);
