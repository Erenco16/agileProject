-- so the logic in here related to the foreign keys in order to increase the simplicity in the db is:
-- if a foreign key is stated as ON DELETE CASCADE, it means that the relevant record will be deleted in the table when the corresponding value in the main table is deleted
-- if a foreign key is stated as ON DELETE SET NULL, then the relevant record will just be set to null when the corresponding record in the main table is deleted
PRAGMA foreign_keys = ON; -- Ensure foreign key enforcement

-- Drop tables if they exist
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS DeliveryArea;
DROP TABLE IF EXISTS Publication;
DROP TABLE IF EXISTS NewsAgent;
DROP TABLE IF EXISTS OrdersStatus;

-- Customers Table
CREATE TABLE IF NOT EXISTS Customers (
     id INTEGER PRIMARY KEY AUTOINCREMENT,
     name TEXT NOT NULL,
     email TEXT UNIQUE,
     address_id INTEGER NOT NULL,
     FOREIGN KEY (address_id) REFERENCES Address(id) ON DELETE CASCADE
);

-- Address Table
CREATE TABLE IF NOT EXISTS Address (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    address TEXT NOT NULL,
    delivery_area_id INTEGER,
    FOREIGN KEY (delivery_area_id) REFERENCES DeliveryArea(id) ON DELETE SET NULL
);

-- Delivery Area Table
CREATE TABLE IF NOT EXISTS DeliveryArea (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Publications Table
CREATE TABLE IF NOT EXISTS Publication (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cust_id INTEGER,
    price REAL NOT NULL,  -- SQLite uses REAL instead of DOUBLE
    FOREIGN KEY (cust_id) REFERENCES Customers(id) ON DELETE CASCADE
);

-- News Agent Table
CREATE TABLE IF NOT EXISTS NewsAgent (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);

-- Orders Status Table
CREATE TABLE IF NOT EXISTS OrdersStatus (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cust_id INTEGER NOT NULL,
    pub_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0), -- Ensuring quantity is always positive
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP, -- Auto-fill with current date & time
    status TEXT CHECK (status IN ('Pending', 'Shipped', 'Delivered', 'Cancelled')) NOT NULL, -- Status validation
    FOREIGN KEY (cust_id) REFERENCES Customers(id) ON DELETE CASCADE,
    FOREIGN KEY (pub_id) REFERENCES Publication(id) ON DELETE CASCADE
);

