package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    //Test #1
    //Obj: to test valid customer name input
    //Excpected Output: true
    @org.junit.jupiter.api.Test
    void testValidName() {
        Customer customer = new Customer();
        boolean result = customer.validateName("John Doe");
        assertTrue(result);
    }
    //Test successful

    //Test 2
    //Obj: to test invalid customer name field is empty
    //Excpected Output: Name connot be empty
    @org.junit.jupiter.api.Test
    void testEmptyNameThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateName("");
        });
        assertEquals("Name cannot be empty", exception.getMessage());
    }
    //Test succesful

    @org.junit.jupiter.api.Test
    void testNameExcessLimitThrowsException() {
        Customer customer = new Customer();
        String test = "a".repeat(256);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateName(test);
        });
        assertEquals("Name cannot exceed 255 characters", exception.getMessage());
    }

    //Test 3
    //Obj: to test invalid email input
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @org.junit.jupiter.api.Test
    void testInvalidEmailAtThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateEmail("johnexample.com");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }
    //test successful

    //Test 4
    //Obj: to test invalid email includes .com
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @org.junit.jupiter.api.Test
    void testInvalidEmailDotComThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateEmail("john@example");
        });
        assertEquals("Invalid email format, must include '@' and '.com'", exception.getMessage());
    }
    //Test Successful

    //Test 5
    //Obj : to test invalid blank email input
    //Excpected Output: Invalid email format, must include '@' and '.com'
    @org.junit.jupiter.api.Test
    void testInvalidEmailEmptyThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateEmail("");
        });
        assertEquals("Email cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 6
    //Obj: to test valid email input
    //Excpected Output: true
    @org.junit.jupiter.api.Test
    void testValidEmail() {
        Customer customer = new Customer();
        boolean result = customer.validateEmail("john@example.com");
        assertTrue(result);
    }
    //Test successful

    //Test 7
    //Obj: to test empty address field input
    //Expected Output: Address cannot be empty
    @org.junit.jupiter.api.Test
    void testEmptyAddressThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateAddress("");
        });
        assertEquals("Address cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 8
    //Obj: test input characters limits
    //Expected Output: Address cannot exceed 255 characters
    @org.junit.jupiter.api.Test
    void testCharLimitAddressThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateAddress("W".repeat(256));
        });
        assertEquals("Address cannot exceed 255 characters", exception.getMessage());
    }

    //Test 9
    //Obj: to test valid address input
    //Expected Output: true
    @org.junit.jupiter.api.Test
    void testValidAddress() {
        Customer customer = new Customer();
        boolean result = customer.validateAddress("123 Main St");
        assertTrue(result);
    }
    //Test successful

    //Test 10
    //Obj: to test invalid phone number input
    //Expected Output: Invalid phone number format, must be 10 digits
    @org.junit.jupiter.api.Test
    void testInvalidPhoneNumberThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validatePhoneNumber("12345");
        });
        assertEquals("Invalid phone number format, must be 10 digits", exception.getMessage());
    }
    //Test successful

    //Test 11
    //Obj: to test valid phone number input
    //Expected Output: true
    @org.junit.jupiter.api.Test
    void testValidPhoneNumber() {
        Customer customer = new Customer();
        boolean result = customer.validatePhoneNumber("1234567890");
        assertTrue(result);
    }
    //Test successful

    //Test 12
    //Obj: to test invalid empty delivery area field
    //Expected Output: Delivery area cannot be empty
    @org.junit.jupiter.api.Test
    void testEmptyDeliveryAreaThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("");
        });
        assertEquals("Delivery area cannot be empty", exception.getMessage());
    }
    //Test successful

    //test 12
    //Obj: test negative delivery area field
    //expected output: Delivery area must be a positive number
    @org.junit.jupiter.api.Test
    void testNegativeDeliveryAreaThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("-1");
        });
        assertEquals("Delivery area must be a positive number", exception.getMessage());
    }

    //Test 13
    //Obj: test non-numeric user input
    //Expected output: Input must be a number
    @org.junit.jupiter.api.Test
    void testInvalidDeliveryAreaThrowsException() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.validateDeliveryArea("Willow Park");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //Test successful


    //Test 14
    //Obj: to test valid delivery area input
    //Expected Output: Pass
    @org.junit.jupiter.api.Test
    void testValidDeliveryArea() {
        Customer customer = new Customer();
        boolean result = customer.validateDeliveryArea("1");
        assertTrue(result);
    }
    //Test successful

    //Test 15
    //Obj: to test invalid eircode input
    //Expected Output: Invalid Eircode format, must be 7 characters
    @org.junit.jupiter.api.Test
    void testInvalidEircodeThrowsException() {
        Customer customer = new Customer();
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
        Customer customer = new Customer();
        boolean result = customer.validateEircode("D01AB2C");
        assertTrue(result);
    }
    //Test successful

    // Test 15
    // obj: valid read from customer
    // expected output: true
    @Test
    void customerReadValid() {
        Customer customer = new Customer();
        boolean result = customer.selectCustomerMod("1");
        assertTrue(result);
    }
    // success

    //Test 16
    //obj: test non existed row from Customer table
    // expected output: Error occurred: No customer found with ID: (ID)
    @Test
    void customerReadInvalid() {
        Customer customer = new Customer();
        String invalidInput = "99";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.selectCustomerMod(invalidInput);
        });
        assertEquals("Error occurred: " + "No customer found with ID: " + invalidInput, exception.getMessage());
    }
    // success

    //test 17
    //obj: test empty input
    //obj:
    @Test
    void customerReadInvalidEmpty() {
        Customer customer = new Customer();
        String invalidInput = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.selectCustomerMod(invalidInput);
        });
        assertEquals("Input must be a number" + invalidInput, exception.getMessage());
    }

    //Test 18
    //obj: test display all customer function
    //expected output: true
    @Test
    void customerReadAllValid() {
        Customer customer = new Customer();
        boolean result = customer.selectAllCustomerMod();
        assertTrue(result);
    }
    // success

    //Test 19
    //obj: non number inputs
    //expected output: "Input must be a number"
    @Test
    void customerReadNonNumericalInvalid() {
        Customer customer = new Customer();
        String invalidInput = "six";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.selectCustomerMod(invalidInput);
        });
        assertEquals("Input must be a number", exception.getMessage());
    }

    //Test 20
    //obj: valid in range number for Update
    //expected output: true
    @Test
    void customerUpdateValid() {
        Customer customer = new Customer();
        boolean result = customer.selectCustomerMod("1");
        assertTrue(result);
    }
    //success

    //Test 21
    //obj: invalid range number for Update
    //expected output: true
}