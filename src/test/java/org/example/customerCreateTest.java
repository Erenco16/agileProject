package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class customerCreateTest {

    @Test
    void testValidName() {
        customerCreate customer = new customerCreate();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testEmptyNameThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setName("");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    @Test
    void testInvalidEmailAtThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("johnexample.com");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }

    @Test
    void testInvalidEmailDotComThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("john@example");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }

    @Test
    void testInvalidEmailEmptyThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("");
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }
    @Test
    void testValidEmail() {
        customerCreate customer = new customerCreate();
        customer.setEmail("john@example.com");
        assertEquals("john@example.com", customer.getEmail());
    }

    @Test
    void testEmptyAddressThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setAddress("");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }

    @Test
    void testValidAddress() {
        customerCreate customer = new customerCreate();
        customer.setAddress("123 Main St");
        assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    void testInvalidPhoneNumberThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setPhoneNumber("12345");
        });
        assertEquals("Invalid phone number format, must be 10 digits", exception.getMessage());
    }

    @Test
    void testValidPhoneNumber() {
        customerCreate customer = new customerCreate();
        customer.setPhoneNumber("1234567890");
        assertEquals("1234567890", customer.getPhoneNumber());
    }

    @Test
    void testEmptyDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setDeliveryArea("");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }

    @Test
    void testValidDeliveryArea() {
        customerCreate customer = new customerCreate();
        customer.setDeliveryArea("1");
        assertEquals("1", customer.getDeliveryArea());
    }

    @Test
    void testInvalidEircodeThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEircode("D0123");
        });
        assertEquals("Invalid Eircode format, must be 7 characters", exception.getMessage());
    }

    @Test
    void testValidEircode() {
        customerCreate customer = new customerCreate();
        customer.setEircode("D01AB2C");
        assertEquals("D01AB2C", customer.getEircode());
    }
}
