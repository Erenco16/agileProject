-- Ensure we are using root to execute this setup
DROP DATABASE IF EXISTS agile_project;
CREATE DATABASE IF NOT EXISTS agile_project;

USE agile_project;

-- Manage users with root access
DROP USER IF EXISTS 'agile_user'@'localhost';
CREATE USER 'agile_user'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON agile_project.* TO 'agile_user'@'localhost';

DROP USER IF EXISTS 'guest'@'localhost';
CREATE USER 'guest'@'localhost' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON agile_project.* TO 'guest'@'localhost';

-- Ensure guest cannot manage users but has access to the DB
GRANT SELECT, INSERT, UPDATE, DELETE ON agile_project.* TO 'guest'@'localhost';
GRANT RELOAD ON *.* TO 'guest'@'localhost'; -- ✅ Allow guest to run FLUSH PRIVILEGES

-- Apply privilege changes
FLUSH PRIVILEGES;

USE agile_project;

-- Ensure guest has full access to the database
GRANT ALL PRIVILEGES ON agile_project.* TO 'guest'@'localhost';

-- Create users table
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     email VARCHAR(100) UNIQUE NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
