package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class customerCreateTest {

    //Test #1
    //Obj: to test valid customer name input
    //Excpected Output: true
    @Test
    void testValidName() {
        customerCreate customer = new customerCreate();
        boolean result = customer.validateName("John Doe");
        assertTrue(result);
    }
    //Test successful

    //Test 2
    //Obj: to test invalid customer name field is empty
    //Excpected Output: Name connot be empty
    @Test
    void testEmptyNameThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateName("");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }
    //Test succesful

    @Test
    void testNameExcessLimitThrowsException() {
        customerCreate customer = new customerCreate();
        String test = "a".repeat(256);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateName(test);
        });
        assertEquals("Name cannot exceed 255 characters", exception.getMessage());
    }

    //Test 3
    //Obj: to test invalid email input
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @Test
    void testInvalidEmailAtThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateEmail("johnexample.com");
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
            customer.validateEmail("john@example");
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
            customer.validateEmail("");
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 6
    //Obj: to test valid email input
    //Excpected Output: true
    @Test
    void testValidEmail() {
        customerCreate customer = new customerCreate();
        boolean result = customer.validateEmail("john@example.com");
        assertTrue(result);
    }
    //Test successful

    //Test 7
    //Obj: to test empty address field input
    //Expected Output: Address cannot be empty
    @Test
    void testEmptyAddressThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateAddress("");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 8
    //Obj: test input characters limits
    //Expected Output: Address cannot exceed 255 characters
    @Test
    void testCharLimitAddressThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateAddress("W".repeat(256));
        });
        assertEquals("Address cannot exceed 255 characters", exception.getMessage());
    }

    //Test 9
    //Obj: to test valid address input
    //Expected Output: true
    @Test
    void testValidAddress() {
        customerCreate customer = new customerCreate();
        boolean result = customer.validateAddress("123 Main St");
        assertTrue(result);
    }
    //Test successful

    //Test 10
    //Obj: to test invalid phone number input
    //Expected Output: Invalid phone number format, must be 10 digits
    @Test
    void testInvalidPhoneNumberThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validatePhoneNumber("12345");
        });
        assertEquals("Invalid phone number format, must be 10 digits", exception.getMessage());
    }
    //Test successful

    //Test 11
    //Obj: to test valid phone number input
    //Expected Output: true
    @Test
    void testValidPhoneNumber() {
        customerCreate customer = new customerCreate();
        boolean result = customer.validatePhoneNumber("1234567890");
        assertTrue(result);
    }
    //Test successful

    //Test 12
    //Obj: to test invalid empty delivery area field
    //Expected Output: Delivery area cannot be empty
    @Test
    void testEmptyDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }
    //Test successful

    //test 12
    //Obj: test negative delivery area field
    //expected output: Delivery area must be a positive number
    @Test
    void testNegativeDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("-1");
        });
        assertEquals("Delivery area must be a positive number", exception.getMessage());
    }

    //Test 13
    //Obj: test non-numeric user input
    //Expected output: Input must be a number
    @Test
    void testInvalidDeliveryAreaThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("Willow Park");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //Test successful


    //Test 14
    //Obj: to test valid delivery area input
    //Expected Output: Pass
    @Test
    void testValidDeliveryArea() {
        customerCreate customer = new customerCreate();
        boolean result = customer.validateDeliveryArea("1");
        assertTrue(result);
    }
    //Test successful

    //Test 15
    //Obj: to test invalid eircode input
    //Expected Output: Invalid Eircode format, must be 7 characters
    @Test
    void testInvalidEircodeThrowsException() {
        customerCreate customer = new customerCreate();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateEircode("D0123");
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
        boolean result = customer.validateEircode("D01AB2C");
        assertTrue(result);
    }
    //Test successful
}
