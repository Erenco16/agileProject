package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class deliveryCreateTest {

    @Test
    void testValidDeliveryAreaCreation() {
        // 当所有输入都合法时，构造方法应正常返回对象
        deliveryCreate area = new deliveryCreate("shanghai", "magic city.");
        assertNotNull(area);
        assertEquals("shanghai", area.getName());
        assertEquals("magic city.", area.getDescription());
    }

    @Test
    void testEmptyNameThrowsException() {
        // 模拟空名称输入，期望抛出异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate("", "A valid area description."));
        assertEquals("Delivery area name cannot be empty", exception.getMessage());
    }

    @Test
    void testNameTooLongThrowsException() {
        // 构造一个超过 255 个字符的名称
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            sb.append("a");
        }
        String longName = sb.toString();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate(longName, "A valid area description."));
        assertEquals("Invalid delivery area name, valid name is between 1-255 characters", exception.getMessage());
    }

    @Test
    void testEmptyDescriptionThrowsException() {
        // 模拟空描述输入，期望抛出异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate("shanghai", ""));
        assertEquals("Delivery area description cannot be empty", exception.getMessage());
    }

    @Test
    void testDescriptionTooLongThrowsException() {
        // 构造一个超过 1024 个字符的描述
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1025; i++) {
            sb.append("d");
        }
        String longDescription = sb.toString();
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate("shanghai", longDescription));
        assertEquals("Invalid delivery area description, valid description is between 1-1024 characters", exception.getMessage());
    }
}
