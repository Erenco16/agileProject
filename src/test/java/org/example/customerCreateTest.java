package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class customerCreateTest {

    //Test #1
    //Obj: to test valid customer name input
    @Test
    void testValidName() {
        customerCreate customer = new customerCreate();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }

    //Test 2
    //Obj: to test invalid customer name field is empty
    @Test
    void testEmptyNameThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setName("");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }

    //Test 3
    //Obj: to test invalid email input
    @Test
    void testInvalidEmailAtThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("johnexample.com");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }

    //Test 4
    //Obj: to test invalid email includes .com
    @Test
    void testInvalidEmailDotComThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("john@example");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }

    //Test 5
    //Obj : to test invalid blank email input
    @Test
    void testInvalidEmailEmptyThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }

    //Test 6
    //Obj: to test valid email input
    @Test
    void testValidEmail() {
        customerCreate customer = new customerCreate();
        customer.setEmail("john@example.com");
        assertEquals("john@example.com", customer.getEmail());
    }

    //Test 7
    //Obj: to test empty address field input
    @Test
    void testEmptyAddressThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setAddress("");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }

    //Test 8
    //Obj: to test valid address input
    @Test
    void testValidAddress() {
        customerCreate customer = new customerCreate();
        customer.setAddress("123 Main St");
        assertEquals("123 Main St", customer.getAddress());
    }

    //Test 9
    //Obj: to test invalid phone number input
    @Test
    void testInvalidPhoneNumberThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setPhoneNumber("12345");
        });
        assertEquals("Invalid phone number format, must be 10 digits", exception.getMessage());
    }

    //Test 10
    //Obj: to test valid phone number input
    @Test
    void testValidPhoneNumber() {
        customerCreate customer = new customerCreate();
        customer.setPhoneNumber("1234567890");
        assertEquals("1234567890", customer.getPhoneNumber());
    }

    //Test 11
    //Obj: to test invalid empty delivery area field
    @Test
    void testEmptyDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setDeliveryArea("");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }



    //Test 12
    //Obj: to test valid delivery area input
    @Test
    void testValidDeliveryArea() {
        customerCreate customer = new customerCreate();
        customer.setDeliveryArea("1");
        assertEquals("1", customer.getDeliveryArea());
    }

    //Test 13
    //Obj: to test invalid eircode input
    @Test
    void testInvalidEircodeThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEircode("D0123");
        });
        assertEquals("Invalid Eircode format, must be 7 characters", exception.getMessage());
    }

    //Test 14
    //Obj: to test valid eircode input
    @Test
    void testValidEircode() {
        customerCreate customer = new customerCreate();
        customer.setEircode("D01AB2C");
        assertEquals("D01AB2C", customer.getEircode());
    }
}
