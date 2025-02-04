package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class customerCreateTest {

    @Test
    void testValidCustomerCreation() {
        customerCreate customer = new customerCreate("John Doe", "john@example.com", "123 Main St",
                "0123456789", "1", "N12B123");
        assertEquals("John Doe", customer.getName());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    void testEmptyNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("", "john@example.com", "123 Main St",
                    "0123456789", "1", "N12B123");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void testInvalidEmailThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "johnexample.com", "123 Main St",
                    "0123456789", "1", "N12B123");
        });
        assertEquals("Invalid email format, must include @ and .com", exception.getMessage());
    }

    @Test
    void testInvalidEmailNoDotComThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "john@example", null,
                    "0123456789", "1", "N12B123");
        });
        assertEquals("Invalid email format, must include @ and .com", exception.getMessage());
    }

    @Test
    void testEmptyAddressThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "john@example.com", "",
                    "0123456789", "1", "N12B123");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }

    @Test
    void testEmptyPhoneNumThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    null, "1", "N12B123");
        });
        assertEquals("Phone number cannot be empty", exception.getMessage());
    }

    @Test
    void testEmptyPhoneNum2ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    "", "1", "N12B123");
        });
        assertEquals("Phone number cannot be empty", exception.getMessage());
    }

    @Test
    void testInvalidPhoneNumThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    "01234567890", "1", "N12B123");
        });
        assertEquals("Invalid phone number format, number must be 10 digits", exception.getMessage());
    }

    @Test
    void testEmptyAreaThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    "0123456789", null , "N12B123");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }

    @Test
    void testEmptyEircodeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    "0123456789", "1", null);
        });
        assertEquals("Eircode cannot be empty", exception.getMessage());
    }

    @Test
    void testInvalidEircodeThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new customerCreate("John Doe", "John@example.com", "123 Main St",
                    "0123456789", "1", "N12B123456");
        });
        assertEquals("Invalid Eircode format, must be 7 letters", exception.getMessage());
    }
}
