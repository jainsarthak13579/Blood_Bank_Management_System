SHOW DATABASES;

CREATE DATABASE blood_bank;

USE blood_bank;

CREATE TABLE Donor (
    donor_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_name VARCHAR(100) NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    last_donation_date DATE
);

CREATE TABLE Inventory (
    donation_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT,
    donation_date DATE NOT NULL,
    blood_group VARCHAR(10) NOT NULL,
    quantity INT NOT NULL,
    expiry_date DATE NOT NULL,
    FOREIGN KEY (donor_id) REFERENCES Donor(donor_id)
);

CREATE TABLE Request (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    requester_name VARCHAR(100) NOT NULL,
    blood_group_requested VARCHAR(10) NOT NULL,
    request_date DATE NOT NULL,
    request_status VARCHAR(20) NOT NULL CHECK (request_status IN ('Pending', 'Fulfilled', 'Cancelled'))
);

SELECT * from Donor;
