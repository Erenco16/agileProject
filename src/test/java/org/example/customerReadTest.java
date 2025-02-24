package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class customerReadTest {

    // Test 1
    // obj: valid read from customer
    // expected output: true
    @Test
    void customerReadValid() {
        customerRead customer = new customerRead();
        boolean result = customer.selectCustomerMod("1");
        assertTrue(result);
    }
    // success

    //Test 2
    //obj: test non existed row from Customer table
    // expected output: Error occurred: No customer found with ID: (ID)
    @Test
    void customerReadInvalid() {
        customerRead customer = new customerRead();
        String invalidInput = "99";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.selectCustomerMod(invalidInput);
        });
        assertEquals("Error occurred: " + "No customer found with ID: " + invalidInput, exception.getMessage());
    }
    // success

    //Test 3
    //obj: test display all customer function
    //expected output: true
    @Test
    void customerReadAllValid() {
        customerRead customer = new customerRead();
        boolean result = customer.selectAllCustomerMod();
        assertTrue(result);
    }
    // success

    //Test 4
    //obj: non number inputs
    //expected output: "Input must be a number"
    @Test
    void customerReadNonNumericalInvalid() {
        customerRead customer = new customerRead();
        String invalidInput = "six";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.selectCustomerMod(invalidInput);
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
}