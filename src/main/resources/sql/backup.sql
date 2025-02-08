DROP DATABASE IF EXISTS agile_project;

CREATE DATABASE IF NOT EXISTS agile_project;

USE agile_project;

-- Create MySQL user 'agile_user' with NO password (for local development)
DROP USER IF EXISTS 'agile_user'@'localhost';
CREATE USER 'agile_user'@'localhost' IDENTIFIED WITH mysql_native_password BY '';
GRANT ALL PRIVILEGES ON agile_project.* TO 'agile_user'@'localhost';
FLUSH PRIVILEGES;

USE agile_project;

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create MySQL user 'guest' with password '12345' dynamically
DROP USER IF EXISTS 'guest'@'localhost';
CREATE USER 'guest'@'localhost' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON agile_project.* TO 'guest'@'localhost';
FLUSH PRIVILEGES;