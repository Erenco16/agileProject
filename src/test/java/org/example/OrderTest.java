package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

class OrderTest {

    // Manual mock of DatabaseConnection
    class MockDatabaseConnection extends DatabaseConnection {
        @Override
        public ArrayList<ArrayList<String>> selectCustomers(int customer_id) {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            if (customer_id == 1) { // valid customer
                ArrayList<String> row = new ArrayList<>();
                row.add("1");
                result.add(row);
            }
            return result;
        }

        @Override
        public ArrayList<ArrayList<String>> selectPublication(int pub_id) {
            ArrayList<ArrayList<String>> result = new ArrayList<>();
            if (pub_id == 1) { // valid publication
                ArrayList<String> row = new ArrayList<>();
                row.add("1");
                row.add("Test Publication");
                row.add("Test Description");
                row.add("9.99");
                row.add("10"); // stock available
                result.add(row);
            }
            return result;
        }
    }

    // Utility method to inject mock using reflection
    private void injectMockDatabaseConnection(Order order, DatabaseConnection mockDbConnection) {
        try {
            Field field = Order.class.getDeclaredField("databaseConnection");
            field.setAccessible(true);
            field.set(order, mockDbConnection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Test 1: Valid customer exists
    @Test
    void validCustomerTest() {
        Order order = new Order();
        injectMockDatabaseConnection(order, new MockDatabaseConnection());
        assertTrue(order.validCustomer(1));
    }

    // Test 2: Invalid customer should throw exception
    @Test
    void invalidCustomerThrowsException() {
        Order order = new Order();
        injectMockDatabaseConnection(order, new MockDatabaseConnection());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.validCustomer(999);
        });
        assertEquals("No customer found with ID: 999", exception.getMessage());
    }

    // Test 3: Valid publication and enough stock
    @Test
    void validPublicationAndStockTest() {
        Order order = new Order();
        order.setQuantity(5);
        injectMockDatabaseConnection(order, new MockDatabaseConnection());
        assertTrue(order.validatePublicationAndStock(1));
    }

    // Test 4: Publication not found should throw exception
    @Test
    void publicationNotFoundThrowsException() {
        Order order = new Order();
        order.setQuantity(5);
        injectMockDatabaseConnection(order, new MockDatabaseConnection());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.validatePublicationAndStock(999);
        });
        assertEquals("No publication found with ID: 999", exception.getMessage());
    }

    // Test 5: Insufficient stock should throw exception
    @Test
    void insufficientStockThrowsException() {
        class LowStockMockDatabaseConnection extends MockDatabaseConnection {
            @Override
            public ArrayList<ArrayList<String>> selectPublication(int pub_id) {
                ArrayList<ArrayList<String>> result = new ArrayList<>();
                if (pub_id == 1) {
                    ArrayList<String> row = new ArrayList<>();
                    row.add("1");
                    row.add("Test Publication");
                    row.add("Test Description");
                    row.add("9.99");
                    row.add("2"); // low stock value
                    result.add(row);
                }
                return result;
            }
        }

        Order order = new Order();
        order.setQuantity(5);
        injectMockDatabaseConnection(order, new LowStockMockDatabaseConnection());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.validatePublicationAndStock(1);
        });
        assertEquals("Not enough stock for publication ID: 1. Requested: 5, Available: 2", exception.getMessage());
    }
}
