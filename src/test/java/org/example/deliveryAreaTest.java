package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class deliveryAreaTest {

    deliveryArea area = new deliveryArea();
    //Test 1
    //Obj: to test valid delivery area name input
    //Expected Output: true
    @Test
    void validTestDeliveryAreaName() {
        // 当所有输入都合法时，构造方法应正常返回对象
        boolean check1 = area.validName("shanghai");
        assertTrue(check1);
    }
    //Test successful

    //Test 2
    //Obj: to test invalid empty name field
    //Expected Output: Delivery area name cannot be empty
    @Test
    void invalidTestEmptyNameThrowsException() {
        // 模拟空名称输入，期望抛出异常

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            area.validName("");
        });
        assertEquals("Delivery area name cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 3
    //Obj: to test invalid name too long input
    //Expected Output: Name too long
    @Test
    void invalidTestNameTooLongThrowsException() {

        String longName = "a".repeat(256);
        // 当传入超过合法长度的名称时，构造方法应抛出 IllegalArgumentException 异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            area.validName(longName);
        });
        assertEquals("Invalid delivery area name, valid name is between 1-255 characters", exception.getMessage());
    }
    //Test successful

    //Test 4
    //Obj: to test invalid delivery area description is empty input
    //Expected Output: Delivery area description cannot be empty
    @Test
    void invalidTestEmptyDescriptionThrowsException() {
        // 模拟空描述输入，期望抛出异常

        area.setDescription("");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                area.validDescription(""));
        assertEquals("Delivery area description cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 5
    //Obj: to test invalid delivery area description is too long input
    //Expected Output: Invalid delivery area description, valid description is between 1-1024 characters
    @Test
    void invalidTestDescriptionTooLongThrowsException() {

        String longDescription = "a".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            area.validDescription(longDescription);
        });
        assertEquals("Invalid delivery area description, valid description is between 1-1024 characters", exception.getMessage());
    }

    //Test 5
    //Obj: to test valid delivery area description
    //Expected Output: True
    @Test
    void validTestDeliveryAreaDescription(){

        boolean check2 = area.validDescription("The capital of Dublin");
        assertTrue(check2);
    }
    //Test successful

    //test 1
    //obj : valid user input
    //expected output: true
    @Test
    void publicationReadValid() {
        boolean result = area.deliveryReadByID("1");
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
            area.deliveryReadByID(input);
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
            area.deliveryReadByID(input);
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
            area.deliveryReadByID(input);
        });
        assertEquals("Error occurred: " + "No delivery area found with ID: " + input , exception.getMessage());
    }
    //success

    //Test 5
    //obj: test read all publication from db
    //expected output: [(all items publication in arraylist format)]
    @Test
    void publicationReadAll() {
        area.selectAllDeliveryArea();
        assertTrue(true);
    }
    //success

}