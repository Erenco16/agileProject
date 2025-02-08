package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

class MainTest {
    // 保存原始的 System.in 和 System.out
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    // 用于捕获输出的 ByteArrayOutputStream
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    // 公共辅助方法：根据传入的字符串设置 System.in 并调用 Main.main()
    private void runMainWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
    }

    /**
     * 测试有效配送区域的创建
     * 模拟输入一个新的配送区域（名称不重复且符合要求），验证输出中包含成功提示及新配送区域的详细信息
     */
    @Test
    void testValidDeliveryAreaCreation() {
        // 预先创建的区域为 "Dublin"，因此使用 "NewYork" 测试
        String simulatedInput = "NewYork\nGreat city description\n";
        runMainWithInput(simulatedInput);
        String output = outContent.toString();

        assertTrue(output.contains("create delivery area successfully！"),
                "应显示配送区域创建成功的提示");
        assertTrue(output.contains("Name：NewYork"),
                "输出中应包含新配送区域名称");
        assertTrue(output.contains("Description：Great city description"),
                "输出中应包含新配送区域描述");
    }

    /**
     * 测试重复配送区域的情况
     * 模拟输入名称为 "Dublin"（已预先创建），应抛出异常提示区域已存在
     */
    @Test
    void testDuplicateDeliveryArea() {
        String simulatedInput = "Dublin\nSome description\n";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> runMainWithInput(simulatedInput));
        assertTrue(ex.getMessage().contains("the delivery area already exist，dont create。"),
                "应抛出重复配送区域的异常提示");
    }

    /**
     * 测试空名称输入
     * 模拟输入空的配送区域名称，验证抛出异常提示名称不能为空
     */
    @Test
    void testEmptyName() {
        String simulatedInput = "\nValid description\n";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> runMainWithInput(simulatedInput));
        assertTrue(ex.getMessage().contains("Name cannot be empty"),
                "应抛出名称不能为空的异常提示");
    }

    /**
     * 测试名称长度超过限制
     * 构造一个超过 255 个字符的名称，验证抛出异常
     */
    @Test
    void testInvalidNameLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            sb.append("a");
        }
        String longName = sb.toString();
        String simulatedInput = longName + "\nValid description\n";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> runMainWithInput(simulatedInput));
        assertTrue(ex.getMessage().contains("invalid name， valid  name is between 1-255 characters"),
                "应抛出名称长度不合法的异常提示");
    }

    /**
     * 测试空描述输入
     * 模拟输入空的配送区域描述，验证抛出异常提示描述不能为空
     */
    @Test
    void testEmptyDescription() {
        String simulatedInput = "UniqueName\n\n";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> runMainWithInput(simulatedInput));
        assertTrue(ex.getMessage().contains("invalid description cannot be empty"),
                "应抛出描述不能为空的异常提示");
    }

    /**
     * 测试描述长度超过限制
     * 构造一个超过 1024 个字符的描述，验证抛出异常
     */
    @Test
    void testInvalidDescriptionLength() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1030; i++) {
            sb.append("d");
        }
        String longDescription = sb.toString();
        String simulatedInput = "UniqueName\n" + longDescription + "\n";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> runMainWithInput(simulatedInput));
        assertTrue(ex.getMessage().contains("invalid description，valid inputs is between 1-1024 characters"),
                "应抛出描述长度不合法的异常提示");
    }
}
