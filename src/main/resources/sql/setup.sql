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
price REAL NOT NULL,
stock_available INTEGER
);

CREATE TABLE IF NOT EXISTS Orders (
id INTEGER PRIMARY KEY AUTOINCREMENT,
cust_id INTEGER NOT NULL,
pub_id INTEGER NOT NULL,
quantity INTEGER NOT NULL CHECK (quantity > 0),
order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
status TEXT NOT NULL CHECK (status IN ('Pending', 'Shipped', 'Delivered', 'Cancelled')),
FOREIGN KEY (cust_id) REFERENCES Customers(id),
FOREIGN KEY (pub_id) REFERENCES Publication(id)
);

-- Delivery Man Table
CREATE TABLE IF NOT EXISTS DeliveryMan (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
employment_status TEXT NOT NULL CHECK (employment_status IN ('Active', 'Inactive'))
);

-- DeliveryDocket Table
CREATE TABLE IF NOT EXISTS DeliveryDocket (
id INTEGER PRIMARY KEY AUTOINCREMENT,
date DATETIME DEFAULT CURRENT_TIMESTAMP,
delivery_man_id INTEGER,
docket_status TEXT NOT NULL CHECK (docket_status IN ('Completed', 'On Delivery', 'Pending', 'Cancelled')),
FOREIGN KEY (delivery_man_id) REFERENCES DeliveryMan(id)
);

-- Invoice Table
CREATE TABLE IF NOT EXISTS Invoice (
id INTEGER PRIMARY KEY AUTOINCREMENT,
order_id INTEGER NOT NULL,
total_payable_amount REAL,
FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE
);

-- trigger to create an Invoice for the order automatically
CREATE TRIGGER IF NOT EXISTS create_invoice_after_order
    AFTER INSERT ON Orders
    FOR EACH ROW
BEGIN
    INSERT INTO Invoice (order_id, total_payable_amount)
    VALUES (
               NEW.id,
               (SELECT NEW.quantity * p.price
                FROM Publication p
                WHERE p.id = NEW.pub_id)
           );
END;


-- Trigger to calculate invoice's price automatically
CREATE TRIGGER IF NOT EXISTS calculate_invoice_amount
    AFTER INSERT ON Invoice
    FOR EACH ROW
BEGIN
    UPDATE Invoice
    SET total_payable_amount = (
        SELECT o.quantity * p.price
        FROM Orders o
                 JOIN Publication p ON o.pub_id = p.id
        WHERE o.id = NEW.order_id
    )
    WHERE id = NEW.id;
END;

