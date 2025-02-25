package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class NewsAgentCreateTest {

    private NewsAgentCreate newsAgent;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        newsAgent = new NewsAgentCreate();
        System.setOut(new PrintStream(outputStreamCaptor)); // Capture console output
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes())); // Simulate user input
        newsAgent.input = new Scanner(System.in); // Reinitialize scanner with new input
    }

    @Test
    void testValidInput() {
        provideInput("2\n"); // Simulating user entering "2"
        int result = newsAgent.getValidIntegerInput(1, 3);
        assertEquals(2, result, "Valid input should be returned correctly");
    }

    @Test
    void testOutOfRangeInput() {
        provideInput("4\n2\n"); // First input is out of range (4), second is valid (2)
        newsAgent.getValidIntegerInput(1, 3);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid option! Please enter a number between 1 and 3"),
                "Should display out-of-range message");
    }

    @Test
    void testEmptyInput() {
        provideInput("\n2\n"); // First input is empty, second input is valid
        newsAgent.getValidIntegerInput(1, 3);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Input cannot be empty. Please enter a number."),
                "Should display empty input message");
    }

    @Test
    void testNonNumericInput() {
        provideInput("abcd\n2\n"); // First input is invalid, second input is valid
        newsAgent.getValidIntegerInput(1, 3);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid input! Please enter a valid number."),
                "Should display invalid input message");
    }
}
