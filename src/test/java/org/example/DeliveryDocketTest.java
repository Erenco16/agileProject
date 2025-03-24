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
            deliveryDocket.docketStatusValidation("5");
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
    //testing Read valid ID
    //expecting result: true
    @Test
    void testValidIdRead() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.selectDocketID("2");
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
    //success

    //test 13
    //testing valid delivery man
    //expecting result: true
    @Test
    void testValidDeliveryManId() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.deliveryManIdValidation("2");
        assertTrue(result);
    }
    //success

    //Test 14
    //testing out of range delivery man id
    //expecting result: No Delivery Man found with ID: + deliveryManIdInt
    @Test
    void testNonExistedDeliveryManId() {
        deliveryDocket = new DeliveryDocket();
        String id = "999";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation(id);
        });
        assertEquals("No Delivery Man found with ID: " + id , exception.getMessage());
    }
    //success

    //test 15
    //testing empty input for delivery man id
    //expecting result: delivery Man ID cannot be empty
    @Test
    void testEmptyInputDeliveryManId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation(null);
        });
        assertEquals("delivery Man ID cannot be empty", exception.getMessage());
    }
    //success

    //test 16
    //testing non numerical input for delivery man id
    //expecting result: Input must be a number
    @Test
    void testNonNumericalInputDeliveryManId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

    //test 17
    //testing negative numerical input for delivery man id
    //expecting result: Please enter a valid deliveryMan ID
    @Test
    void testNegativeInputDeliveryManId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.deliveryManIdValidation("-1");
        });
        assertEquals("Please enter a valid deliveryMan ID", exception.getMessage());
    }
    //success

    //test 18
    //testing empty read id input
    //expecting result: Please make a selection
    @Test
    void testEmptyReadID() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("");
        });
        assertEquals("Please make a selection", exception.getMessage());
    }
    //success

    //test 19
    //testing non-existed read id input
    //expecting result: Error occurred: No delivery docket ID found
    @Test
    void testNonExistedReadID() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("999");
        });
        assertEquals("Error occurred: No delivery docket ID found", exception.getMessage());
    }
    //success

    //test 20
    //testing non-numerical read id input
    //expecting result: Input must be a number
    @Test
    void testNonNumericalReadID() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketID("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

    //test 21
    //testing valid input for docket ID for Update
    //expecting result: true
    @Test
    void testValidUpdateId() {
        deliveryDocket = new DeliveryDocket();
        boolean result = deliveryDocket.selectDocketIdValidation("2");
        assertTrue(result);
    }
    //success

    //test 22
    //testing empty input for docket ID for Update
    //expecting result: Please make a selection
    @Test
    void testEmptyUpdateId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketIdValidation("");
        });
        assertEquals("Please make a selection", exception.getMessage());
    }
    //success

    //test 23
    //testing non existed input for docket ID for Update
    //expecting result: No delivery docket ID found
    @Test
    void testNonExistedUpdateId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketIdValidation("9999");
        });
        assertEquals("Error occurred: No delivery docket ID found", exception.getMessage());
    }
    //success

    //test 24
    //testing non existed input for docket ID for Update
    //expecting result: No delivery docket ID found
    @Test
    void testNonNumericalUpdateId() {
        deliveryDocket = new DeliveryDocket();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryDocket.selectDocketIdValidation("ABC");
        });
        assertEquals("Input must be a number", exception.getMessage());
    }
    //success

}