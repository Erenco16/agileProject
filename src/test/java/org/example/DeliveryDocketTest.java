package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryDocketTest {

    private DeliveryDocket deliveryDocket;

    //test 1
    //testing user input for deliveryManId
    //expected result: true
    @Test
    void testValidDeliveryId() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.deliveryManIdValidation("1");
        assertTrue(result);
    }
    //success

    //test 2
    //testing user input empty
    //expecting result: delivery Man ID cannot be empty
    @Test
    void testInvalidDeliveryId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation(null);
        });
        assertEquals("delivery Man ID cannot be empty", exception.getMessage());
    }
    //success

    //test 3
    //testing user input non-numerical
    //expecting result: Input must be a number
    @Test
    void testInvalidDeliveryIdNonNumerical() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

    //test 4
    //testing negative numerical input
    //expecting result: Please enter a valid deliveryMan ID
    @Test
    void testInvalidDeliveryIdNonAlphanumeric() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation("-1");
        });
        assertEquals("Please enter a valid deliveryMan ID", exception.getMessage());
    }
    // success

    //test 5
    //testing valid input for docket status
    //expecting result: true
    @Test
    void testValidDeliveryName() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.docketStatusValidation("1");
        assertTrue(result);
    }
    //success

    //test 6
    //testing valid input for docket status
    //expecting result: true
    @Test
    void testValidDeliveryName1() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.docketStatusValidation("2");
        assertTrue(result);
    }
    //success

    //test 6
    //testing empty input for docket status
    //expecting result: Please make a selection
    @Test
    void testEmptyDeliveryName() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.docketStatusValidation("");
        });
        assertEquals("Please make a selection", exception.getMessage());
    }
    //success

    //test 7
    //testing empty input for docket status
    //expecting result: Please enter a valid docket status
    @Test
    void testInvalidDeliveryName() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.docketStatusValidation("3");
        });
        assertEquals("Please enter a valid docket status", exception.getMessage());
    }
    //success

    //test 8
    //testing empty input for docket status
    //expecting result: Please enter a valid docket status
    @Test
    void testInvalidDeliveryName1() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.docketStatusValidation("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

    //test 9
    //testing Read ID
    //expecting result: true
    @Test
    void testValidIdRead() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.selectDocketID("1");
        assertTrue(result);
    }
    //success

    //test 10
    //testing non-existed Read ID
    //expecting result: Error occurred: No delivery docket ID found
    @Test
    void testInvalidNonExistIdRead() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("999");
        });
        assertEquals("Error occurred: No delivery docket ID found", exception.getMessage());
    }
    //success

    //test11
    //testing non-numerical Read ID
    //expecting result: Error occurred: Input must be a number
    @Test
    void testInvalidNonNumericalIdRead() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

    //test 12
    //testing empty Read ID
    //expecting result: Please make a selection
    @Test
    void testInvalidEmptyIdRead() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("");
        });
        assertEquals("Please make a selection", exception.getMessage());
    }


}