-- Delivery Area Table (creating first so it can be referenced)
CREATE TABLE IF NOT EXISTS DeliveryArea (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
description TEXT NOT NULL
);

-- Customers Table
CREATE TABLE IF NOT EXISTS Customers (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
email TEXT UNIQUE,
address TEXT NOT NULL,
phone_number TEXT NOT NULL,
delivery_area_id INTEGER NOT NULL,
eircode TEXT NOT NULL,
FOREIGN KEY (delivery_area_id) REFERENCES DeliveryArea(id) ON DELETE CASCADE
);

-- Address Table
CREATE TABLE IF NOT EXISTS Address (
id INTEGER PRIMARY KEY AUTOINCREMENT,
address TEXT NOT NULL,
delivery_area_id INTEGER,
FOREIGN KEY (delivery_area_id) REFERENCES DeliveryArea(id) ON DELETE SET NULL
);

-- News Agent Table
CREATE TABLE IF NOT EXISTS NewsAgent (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL
);

-- Publications Table
CREATE TABLE IF NOT EXISTS Publication (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
description TEXT NOT NULL,
price REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS OrdersStatus (
id INTEGER PRIMARY KEY AUTOINCREMENT,
cust_id INTEGER NOT NULL,
pub_id INTEGER NOT NULL,
quantity INTEGER NOT NULL CHECK (quantity > 0),
order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
status TEXT NOT NULL CHECK (status IN ('Pending', 'Shipped', 'Delivered', 'Cancelled')),
FOREIGN KEY (cust_id) REFERENCES Customers(id) ON DELETE CASCADE,
FOREIGN KEY (pub_id) REFERENCES Publication(id) ON DELETE CASCADE
);