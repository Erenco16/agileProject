package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class deliveryCreateTest {


    //Test 1
    //Obj: to test valid delivery area name input
    //Expected Output: true
    @Test
    void validTestDeliveryAreaName() {
        // 当所有输入都合法时，构造方法应正常返回对象
        deliveryCreate area = new deliveryCreate();
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
        deliveryCreate area = new deliveryCreate();

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
        deliveryCreate area = new deliveryCreate();
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
        deliveryCreate area = new deliveryCreate();
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
        deliveryCreate area = new deliveryCreate();
        String longDescription = "a".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    area.validDescription(longDescription);
                });
        assertEquals("Invalid delivery area description, valid description is between 1-1024 characters", exception.getMessage());
    }
    //Test successful

    //Test 6
    //Obj: to test valid delivery area description
    //Expected Output: True
    @Test
    void validTestDeliveryAreaDescription(){
        deliveryCreate area = new deliveryCreate();
        boolean check2 = area.validDescription("The capital of Dublin");
        assertTrue(check2);
    }
    //Test successful
}
