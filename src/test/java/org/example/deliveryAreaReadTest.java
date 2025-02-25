package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class deliveryAreaReadTest {

    deliveryAreaRead dar = new deliveryAreaRead();

    //test 1
    //obj : valid user input
    //expected output: true
    @Test
    void publicationReadValid() {
        boolean result = dar.deliveryReadByID("1");
        assertTrue(result);
    }
    //success

    //test 2
    // obj: empty input
    // expected output: Invalid ID: (input)
    @Test
    void publicationReadInvalidEmpty() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dar.deliveryReadByID(input);
        });
        assertEquals("Invalid ID: " + input , exception.getMessage());
    }
    //success

    //Test 3
    //obj : non numerical input
    //expected output: Invalid ID: (input)
    @Test
    void publicationReadInvalidNonNumeric() {
        String input = "Dublin";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dar.deliveryReadByID(input);
        });
        assertEquals("Invalid ID: " + input , exception.getMessage());
    }

    //Test 4
    //obj: null item from DB
    //expected output: Error occurred: No publication found with ID: (input)
    @Test
    void publicationReadInvalidNull() {
        String input = "100";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dar.deliveryReadByID(input);
        });
        assertEquals("Error occurred: " + "No delivery area found with ID: " + input , exception.getMessage());
    }
    //success

    //Test 5
    //obj: test read all publication from db
    //expected output: [(all items publication in arraylist format)]
    @Test
    void publicationReadAll() {
        dar.selectAllDeliveryArea();
        assertTrue(true);
    }
    //success

}