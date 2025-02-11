package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class customerCreateTest {

    //Test #1
    //Obj: to test valid customer name input
    //Excpected Output: Pass
    @Test
    void testValidName() {
        customerCreate customer = new customerCreate();
        customer.setName("John Doe");
        assertEquals("John Doe", customer.getName());
    }
    //Test successful

    //Test 2
    //Obj: to test invalid customer name field is empty
    //Excpected Output: Name connot be empty
    @Test
    void testEmptyNameThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setName("");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }
    //Test succesful

    //Test 3
    //Obj: to test invalid email input
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @Test
    void testInvalidEmailAtThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("johnexample.com");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }
    //test successful

    //Test 4
    //Obj: to test invalid email includes .com
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @Test
    void testInvalidEmailDotComThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("john@example");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }
    //Test Successful

    //Test 5
    //Obj : to test invalid blank email input
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @Test
    void testInvalidEmailEmptyThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEmail("");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }
    //Test successful

    //Test 6
    //Obj: to test valid email input
    //Excpected Output: Pass
    @Test
    void testValidEmail() {
        customerCreate customer = new customerCreate();
        customer.setEmail("john@example.com");
        assertEquals("john@example.com", customer.getEmail());
    }
    //Test successful

    //Test 7
    //Obj: to test empty address field input
    //Expected Output: Address cannot be empty
    @Test
    void testEmptyAddressThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setAddress("");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 8
    //Obj: to test valid address input
    //Expected Output: Pass
    @Test
    void testValidAddress() {
        customerCreate customer = new customerCreate();
        customer.setAddress("123 Main St");
        assertEquals("123 Main St", customer.getAddress());
    }
    //Test successful

    //Test 9
    //Obj: to test invalid phone number input
    //Expected Output: Invalid phone number format, must be 10 digits
    @Test
    void testInvalidPhoneNumberThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setPhoneNumber("12345");
        });
        assertEquals("Invalid phone number format, must be 10 digits", exception.getMessage());
    }
    //Test successful

    //Test 10
    //Obj: to test valid phone number input
    //Expected Output: Pass
    @Test
    void testValidPhoneNumber() {
        customerCreate customer = new customerCreate();
        customer.setPhoneNumber("1234567890");
        assertEquals("1234567890", customer.getPhoneNumber());
    }
    //Test successful

    //Test 11
    //Obj: to test invalid empty delivery area field
    //Expected Output: Delivery area cannot be empty
    @Test
    void testEmptyDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setDeliveryArea("");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 12
    //Obj: to test valid delivery area input
    //Expected Output: Pass
    @Test
    void testValidDeliveryArea() {
        customerCreate customer = new customerCreate();
        customer.setDeliveryArea("1");
        assertEquals("1", customer.getDeliveryArea());
    }
    //Test successful

    //Test 13
    //Obj: to test invalid eircode input
    //Expected Output: Invalid Eircode format, must be 7 characters
    @Test
    void testInvalidEircodeThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.setEircode("D0123");
        });
        assertEquals("Invalid Eircode format, must be 7 characters", exception.getMessage());
    }
    //Test successful

    //Test 14
    //Obj: to test valid eircode input
    //Expected Output: Pass
    @Test
    void testValidEircode() {
        customerCreate customer = new customerCreate();
        customer.setEircode("D01AB2C");
        assertEquals("D01AB2C", customer.getEircode());
    }
    //Test successful
}
