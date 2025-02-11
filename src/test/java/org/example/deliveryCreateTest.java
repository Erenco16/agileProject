package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class deliveryCreateTest {


    //Test 1
    //Obj: to test valid delivery area name input
    @Test
    void testValidDeliveryAreaName() {
        // 当所有输入都合法时，构造方法应正常返回对象
        deliveryCreate area = new deliveryCreate();
        area.setName("shanghai");
        assertEquals("shanghai", area.getName());
    }

    //Test 2
    //Obj: to test invalid empty name field
    @Test
    void testEmptyNameThrowsException() {
        // 模拟空名称输入，期望抛出异常
        deliveryCreate area = new deliveryCreate();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
                area.setName("");
    });
        assertEquals("Delivery area name cannot be empty", exception.getMessage());
    }

    //Test 3
    //Obj: to test invalid name too long input
    @Test
    void testNameTooLongThrowsException() {
        deliveryCreate area = new deliveryCreate();
        String longName = "a".repeat(256);
        // 当传入超过合法长度的名称时，构造方法应抛出 IllegalArgumentException 异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->{
                area.setName(longName);
    });
      assertEquals("Name too long", exception.getMessage());
    }

    //Test 4
    //Obj: to test invalid delivery area description is empty input
    @Test
    void testEmptyDescriptionThrowsException() {
        // 模拟空描述输入，期望抛出异常
        deliveryCreate area = new deliveryCreate();
        area.setDescription("");
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                area.setDescription(""));
        assertEquals("Delivery area description cannot be empty", exception.getMessage());
    }

    //Test 5
    //Obj: to test invalid delivery area description is too long input
    @Test
    void testDescriptionTooLongThrowsException() {
        deliveryCreate area = new deliveryCreate();
        String longDescription = "a".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                    area.setDescription(longDescription);
                });
        assertEquals("Invalid delivery area description, valid description is between 1-1024 characters", exception.getMessage());
    }
}
