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
        // 生成一个长度为 256 的字符串，"a".repeat(256) 要求 Java 11 及以上版本
        String longName = "a".repeat(256);

        // 当传入超过合法长度的名称时，构造方法应抛出 IllegalArgumentException 异常
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate(longName, "A valid area description."));

        // 验证异常消息是否符合预期
        assertEquals("Invalid delivery area name, valid name is between 1-255 characters",
                exception.getMessage());
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


        String longDescription = "a".repeat(1025);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new deliveryCreate("shanghai", longDescription));
        assertEquals("Invalid delivery area description, valid description is between 1-1024 characters", exception.getMessage());
    }
}
