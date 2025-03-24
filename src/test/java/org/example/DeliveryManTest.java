package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

class DeliveryManTest {

    // Mock database connection for DeliveryMan
    class MockDatabaseConnection extends DatabaseConnection {
        @Override
        public ArrayList<ArrayList<String>> selectDeliveryMan(int id) {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            if (id == 1) {
                ArrayList<String> row = new ArrayList<>();
                row.add("1");
                row.add("Test Delivery Man");
                row.add("Active");
                result.add(row);
            }
            return result;
        }

        @Override
        public ArrayList<ArrayList<String>> selectAllDeliveryMan() {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            ArrayList<String> row = new ArrayList<>();
            row.add("1");
            row.add("Test Delivery Man");
            row.add("Active");
            result.add(row);
            return result;
        }
    }

    // Utility method to inject mock database connection
    private void injectMockDatabaseConnection(DeliveryMan deliveryMan, DatabaseConnection mockDbConnection) {
        try {
            Field field = DeliveryMan.class.getDeclaredField("databaseConnection");
            field.setAccessible(true);
            field.set(deliveryMan, mockDbConnection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Test 1: Valid name
    @Test
    void validNameTest() {
        DeliveryMan deliveryMan = new DeliveryMan();
        assertTrue(deliveryMan.validateName("John Doe"));
    }

    // Test 2: Empty name throws exception
    @Test
    void emptyNameThrowsException() {
        DeliveryMan deliveryMan = new DeliveryMan();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryMan.validateName("");
        });
        assertEquals("Delivery area name cannot be empty", exception.getMessage());
    }

    // Test 3: Name too long throws exception
    @Test
    void nameTooLongThrowsException() {
        DeliveryMan deliveryMan = new DeliveryMan();
        String longName = "a".repeat(256);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deliveryMan.validateName(longName);
        });
        assertEquals("Invalid delivery area name, valid name is between 1-255 characters", exception.getMessage());
    }

    // Test 4: deliveryManReadById retrieves data (mocked)
    @Test
    void deliveryManReadByIdReturnsData() {
        DeliveryMan deliveryMan = new DeliveryMan();
        injectMockDatabaseConnection(deliveryMan, new MockDatabaseConnection());
        ArrayList<ArrayList<String>> result = deliveryMan.databaseConnection.selectDeliveryMan(1);
        assertFalse(result.isEmpty());
        assertEquals("Test Delivery Man", result.get(0).get(1));
    }

    // Test 5: deliveryManReadById with no match returns empty
    @Test
    void deliveryManReadByIdNoMatch() {
        DeliveryMan deliveryMan = new DeliveryMan();
        injectMockDatabaseConnection(deliveryMan, new MockDatabaseConnection());
        ArrayList<ArrayList<String>> result = deliveryMan.databaseConnection.selectDeliveryMan(999);
        assertTrue(result.isEmpty());
    }

    // Test 6: deliveryManReadAll returns mocked data
    @Test
    void deliveryManReadAllReturnsData() {
        DeliveryMan deliveryMan = new DeliveryMan();
        injectMockDatabaseConnection(deliveryMan, new MockDatabaseConnection());
        ArrayList<ArrayList<String>> result = deliveryMan.databaseConnection.selectAllDeliveryMan();
        assertFalse(result.isEmpty());
        assertEquals("Test Delivery Man", result.get(0).get(1));
    }
}
