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

-- News Agent Table (Create this before orders or publications)
CREATE TABLE IF NOT EXISTS NewsAgent (
                                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                                         name TEXT NOT NULL
);

-- Publications Table (Must be created before OrdersStatus since Orders references it)
CREATE TABLE IF NOT EXISTS Publication (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           cust_id INTEGER NOT NULL,
                                           price REAL NOT NULL,
                                           FOREIGN KEY (cust_id) REFERENCES Customers(id) ON DELETE CASCADE
);

-- Orders Status Table (Created last to ensure all referenced tables exist)
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
