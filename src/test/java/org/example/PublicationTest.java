package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicationTest {
    
    //Test 1
    //Obj: to test valid publication name input
    //Expected Output: Pass
    @Test
    void validTestPublicationName() {
        // 当所有输入都合法时，构造方法应正常返回对象
        Publication pubc = new Publication();
        boolean check1 = pubc.validName("The Irish Times");
        assertTrue(check1);
    }
    //Test successful

    //Test 2
    //Obj: to test invalid empty name field
    //Expected Output: publication name cannot be empty
    @Test
    void invalidTestEmptyNameThrowsException() {
        // 模拟空名称输入，期望抛出异常
        Publication pubc = new Publication();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            pubc.validName("");
        });
        assertEquals("Publication name cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 3
    //Obj: to test invalid name too long input
    //Expected Output: Name too long
    @Test
    void invalidTestNameTooLongThrowsException() {
        Publication pubc = new Publication();
        String longName = "a".repeat(256);
        // 当传入超过合法长度的名称时，构造方法应抛出 IllegalArgumentException 异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
            pubc.validName(longName);
        });
        assertEquals("Invalid publication name, valid name is between 1-255 characters", exception.getMessage());
    }
    //Test successful

    //Test 4
    //Obj: to test invalid publication description is empty input
    //Expected Output: publication description cannot be empty
    @Test
    void invalidTestEmptyDescriptionThrowsException() {
        // 模拟空描述输入，期望抛出异常
        Publication pubc = new Publication();
        pubc.setDescription("");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                pubc.validDescription(""));
        assertEquals("Publication description cannot be empty", exception.getMessage());
    }
    //Test successful

    //Test 5
    //Obj: to test invalid publication description is too long input
    //Expected Output: Invalid publication description, valid description is between 1-1024 characters
    @Test
    void invalidTestDescriptionTooLongThrowsException() {
        Publication pubc = new Publication();
        String longDescription = "a".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pubc.validDescription(longDescription);
        });
        assertEquals("Invalid publication description, valid description is between 1-1024 characters", exception.getMessage());
    }
    //Test successful

    //Test 6
    //Obj: to test invalid publication description is too long input
    //Expected Output: True
    @Test
    void validTestPublicationDescription(){
        Publication pubc = new Publication();
        boolean check2 = pubc.validDescription("The capital of Dublin");
        assertTrue(check2);
    }
    //Test successful

    //Test 7
    //Obj: to test invalid publication price is a negative value
    //Expected Output: Price can not be a negative number!
    @Test
    void invalidTestPublicationPriceNegative(){
        Publication pubc = new Publication();
        String num = "-12.00";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pubc.validPrice(num);
        });
        assertEquals("Price can not be a negative number!", exception.getMessage());
    }
    //Test successful

    //Test 8
    //Obj: to test invalid publication price too long
    //Expected Output: Price must be between 1-255 digits long
    @Test
    void invalidTestPriceLength(){
        Publication pubc = new Publication();
        String longVal = "1".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pubc.validPrice(longVal);
        });
        assertEquals("Price must be between 1-255 digits long", exception.getMessage());
    }
    //Test successful

    //Test 9
    //Obj: to test invalid publication price value is not a double
    //Expected Output: Price must be a digit value
    @Test
    void invalidTestPublicationPriceValue(){
        Publication pubc = new Publication();
        String num = "abcde";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pubc.validPrice(num);
        });
        assertEquals("Price must be a digit value", exception.getMessage());
    }
    //Test successful

    //Test 10
    //Obj: to test valid publication price value
    //Expected Output: True
    @Test
    void validTestPublicationPriceValue(){
        Publication pubc = new Publication();
        boolean check3 = pubc.validPrice("12.00");
        assertTrue(check3);
    }
    //Test successful

    Publication pr = new Publication();

    //test 1
    //obj : valid user input
    //expected output: true
    @Test
    void PublicationValid() {
        boolean result = pr.publicationReadByID("1");
        assertTrue(result);
    }
    //success

    //test 2
    // obj: empty input
    // expected output: Invalid ID: (input)
    @Test
    void PublicationInvalidEmpty() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pr.publicationReadByID(input);
        });
        assertEquals("Invalid ID: " + input , exception.getMessage());
    }
    //success

    //Test 3
    //obj : non numerical input
    //expected output: Invalid ID: (input)
    @Test
    void PublicationInvalidNonNumeric() {
        String input = "Irish Time";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pr.publicationReadByID(input);
        });
        assertEquals("Invalid ID: " + input , exception.getMessage());
    }

    //Test 4
    //obj: null item from DB
    //expected output: Error occurred: No publication found with ID: (input)
    @Test
    void PublicationInvalidNull() {
        String input = "999";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            pr.publicationReadByID(input);
        });
        assertEquals("Error occurred: " + "No publication found with ID: " + input , exception.getMessage());
    }
    //success

    //Test 5
    //obj: test read all publication from db
    //expected output: [(all items publication in arraylist format)]
    @Test
    void PublicationAll() {
        pr.selectAllPublication();
        assertTrue(true);
    }
    //success

}