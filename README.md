*Embeded-Insurance-Project*

Eureka server = http://localhost:8761/
===========================================================
*MySQL Database Queries*

CREATE TABLE policy (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_number VARCHAR(50) NOT NULL UNIQUE,
    policy_type VARCHAR(50) NOT NULL,
    premium_amount DECIMAL(10,2) NOT NULL,
    effective_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    customer_reference VARCHAR(50),  -- a business reference to customer (could be email or customer id as defined in customer service)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_policy_type (policy_type)
);

----------------------------------------------
CREATE TABLE customer_360 (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(255),
    date_of_birth DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_email (email)
);

----------------------------------------------
CREATE TABLE claim (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    claim_number VARCHAR(50) NOT NULL UNIQUE,
    policy_reference VARCHAR(50) NOT NULL,   -- reference to Policy (e.g. policy number)
    customer_reference VARCHAR(50) NOT NULL, -- reference to Customer (could be email or customer id)
    claim_status ENUM('FILED', 'APPROVED', 'REJECTED') NOT NULL,
    claim_amount DECIMAL(10,2) NOT NULL,
    date_filed DATE NOT NULL,
    date_resolved DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_claim_status (claim_status)
);

----------------------------------------------
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_reference VARCHAR(50) NOT NULL,  -- reference to Policy
    customer_reference VARCHAR(50) NOT NULL,  -- reference to Customer
    transaction_id VARCHAR(100) UNIQUE,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('SUCCESS', 'PENDING', 'FAILED') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_status (status)
);

----------------------------------------------
CREATE TABLE notification_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    notification_type VARCHAR(50) NOT NULL,  -- e.g., EMAIL, SMS
    recipient VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    status ENUM('SENT', 'FAILED') NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_notification_type (notification_type)
);

===========================================================
-- Insert dummy policies
INSERT INTO policy_360 (policy_number, policy_type, premium_amount, effective_date, expiration_date, customer_reference)
VALUES 
('POL123456', 'Auto', 1000.50, '2023-01-01', '2024-01-01', 'john.doe@example.com'),
('POL654321', 'Health', 1500.75, '2023-03-15', '2024-03-15', 'jane.smith@example.com'),
('POL987654', 'Home', 2000.00, '2023-05-01', '2024-05-01', 'alice.brown@example.com');

----------------------------------------------
-- Insert dummy customers
INSERT INTO customer_360 (name, email, phone, address, date_of_birth)
VALUES 
('John Doe', 'john.doe@example.com', '+1234567890', '123 Main St, Cityville, USA', '1980-05-15'),
('Jane Smith', 'jane.smith@example.com', '+1987654321', '456 Elm St, Townsville, USA', '1990-10-25'),
('Alice Brown', 'alice.brown@example.com', '+1122334455', '789 Oak St, Villageville, USA', '1985-07-20');

----------------------------------------------
-- Insert dummy claims
INSERT INTO claim_360 (claim_number, policy_reference, customer_reference, claim_status, claim_amount, date_filed, date_resolved)
VALUES 
('CLM001', 'POL123456', 'john.doe@example.com', 'FILED', 500.00, '2023-02-20', NULL),
('CLM002', 'POL654321', 'jane.smith@example.com', 'APPROVED', 1200.00, '2023-04-01', '2023-04-10'),
('CLM003', 'POL987654', 'alice.brown@example.com', 'REJECTED', 750.00, '2023-06-05', '2023-06-12');

----------------------------------------------
-- Insert dummy payments
INSERT INTO payment (policy_reference, customer_reference, transaction_id, amount, payment_date, status)
VALUES 
('POL123456', 'john.doe@example.com', 'TXN1001', 1000.50, '2023-01-01 10:00:00', 'SUCCESS'),
('POL654321', 'jane.smith@example.com', 'TXN1002', 1500.75, '2023-03-15 11:30:00', 'SUCCESS'),
('POL987654', 'alice.brown@example.com', 'TXN1003', 2000.00, '2023-05-01 09:45:00', 'PENDING');

----------------------------------------------
-- Insert dummy notification logs
INSERT INTO notification_log (notification_type, recipient, message, status)
VALUES 
('EMAIL', 'john.doe@example.com', 'Your policy POL123456 has been created and activated successfully.', 'SENT'),
('EMAIL', 'jane.smith@example.com', 'Good news! Your claim CLM002 has been approved.', 'SENT'),
('SMS', '+1234567890', 'Payment of $1000.50 has been processed for policy POL123456.', 'SENT'),
('SMS', '+1987654321', 'Your claim CLM002 status updated: APPROVED. Check your email for details.', 'SENT');

===========================================================
