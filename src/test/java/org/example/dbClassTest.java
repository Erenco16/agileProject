package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;

public class dbClassTest {

    //(These are only used within the tests to search for a row in a list.
    private boolean containsValue(ArrayList<ArrayList<String>> rows, int columnIndex, String expected) {
        for (ArrayList<String> row : rows) {
            if (row.size() > columnIndex && row.get(columnIndex).equals(expected)) {
                return true;
            }
        }
        return false;
    }

    @Test
    void testGetConnection() {
        try (Connection conn = DBClass.getConnection()) {
            assertNotNull(conn, "getConnection() should return a non-null connection.");
        } catch (SQLException e) {
            fail("getConnection() threw SQLException: " + e.getMessage());
        }
    }

    @Test
    void testGetValues() {
        try (Connection conn = DBClass.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1 AS col")) {
            ArrayList<ArrayList<String>> values = DBClass.getValues(rs);
            assertEquals(1, values.size(), "There should be exactly one row.");
            assertEquals("1", values.get(0).get(0), "The value in the first column should be '1'.");
        } catch (SQLException e) {
            fail("Exception during testGetValues: " + e.getMessage());
        }
    }

    // JUnit functions for Customer table operations
    @Test
    void testInsertCustomerAndSelect() {
        // Use an existing DeliveryArea id (assumed to be 1)
        String uniqueName = "UniqueCustomer_" + System.currentTimeMillis();
        String email = uniqueName + "@example.com";
        DBClass.insertCustomer(uniqueName, email, "Test Address", "1234567890", 1, "EIR1234");

        // Search in all customers for our unique customer name (column order assumed: id, name, email, ...)
        ArrayList<ArrayList<String>> customers = DBClass.selectAllCustomers();
        assertTrue(containsValue(customers, 1, uniqueName), "Inserted customer should be found in selectAllCustomers.");
    }

    @Test
    void testSelectNonExistentCustomer() {
        ArrayList<ArrayList<String>> customers = DBClass.selectCustomers(999999);
        assertEquals(0, customers.size(), "Selecting a non-existent customer id should return an empty list.");
    }

    @Test
    void testSelectAllCustomers() {
        String uniqueName = "AllCustomer_" + System.currentTimeMillis();
        String email = uniqueName + "@example.com";
        DBClass.insertCustomer(uniqueName, email, "All Address", "3334445555", 1, "EIRALL");

        ArrayList<ArrayList<String>> customers = DBClass.selectAllCustomers();
        assertTrue(containsValue(customers, 1, uniqueName), "selectAllCustomers should return the inserted customer.");
    }

    // JUnit functions for Address table operations

    @Test
    void testInsertAddressAndSelect() {
        String uniqueAddress = "UniqueAddress_" + System.currentTimeMillis();
        DBClass.insertAddress(uniqueAddress, 1);

        ArrayList<ArrayList<String>> addresses = DBClass.selectAllAddress();
        // Assuming column order: id, address, delivery_area_id.
        assertTrue(containsValue(addresses, 1, uniqueAddress), "Inserted address should be found in selectAllAddress.");
    }

    @Test
    void testSelectNonExistentAddress() {
        ArrayList<ArrayList<String>> addresses = DBClass.selectAddress(999999);
        assertEquals(0, addresses.size(), "Selecting a non-existent address id should return an empty list.");
    }

    @Test
    void testSelectAllAddress() {
        String uniqueAddress1 = "AllAddress1_" + System.currentTimeMillis();
        String uniqueAddress2 = "AllAddress2_" + System.currentTimeMillis();
        DBClass.insertAddress(uniqueAddress1, 1);
        DBClass.insertAddress(uniqueAddress2, 1);

        ArrayList<ArrayList<String>> addresses = DBClass.selectAllAddress();
        assertTrue(containsValue(addresses, 1, uniqueAddress1)
                        && containsValue(addresses, 1, uniqueAddress2),
                "Both inserted addresses should be found in selectAllAddress.");
    }

    // JUnit functions for DeliverArea table operations

    @Test
    void testInsertDeliveryAreaAndSelect() {
        String uniqueArea = "UniqueArea_" + System.currentTimeMillis();
        DBClass.insertDeliveryArea(uniqueArea, "Test Description");

        ArrayList<ArrayList<String>> areas = DBClass.selectAllDeliveryArea();
        // Assuming column order: id, name, description.
        assertTrue(containsValue(areas, 1, uniqueArea), "Inserted delivery area should be found in selectAllDeliveryArea.");
    }

    @Test
    void testDuplicateDeliveryArea() {
        String uniqueArea = "DuplicateArea_" + System.currentTimeMillis();
        DBClass.insertDeliveryArea(uniqueArea, "First Description");
        // Attempt duplicate insertion.
        DBClass.insertDeliveryArea(uniqueArea, "Second Description");

        ArrayList<ArrayList<String>> areas = DBClass.selectAllDeliveryArea();
        int count = 0;
        for (ArrayList<String> row : areas) {
            if (row.size() >= 2 && row.get(1).equals(uniqueArea)) {
                count++;
            }
        }
        assertEquals(1, count, "Duplicate delivery area insertion should not create multiple records.");
    }

    @Test
    void testSelectNonExistentDeliveryArea() {
        ArrayList<ArrayList<String>> areas = DBClass.selectDeliveryArea(999999);
        assertEquals(0, areas.size(), "Selecting a non-existent delivery area id should return an empty list.");
    }

    // JUnit functions for NewsAgent table operations

    @Test
    void testInsertNewsAgentAndSelect() {
        String uniqueAgent = "UniqueAgent_" + System.currentTimeMillis();
        DBClass.insertNewsAgent(uniqueAgent);

        ArrayList<ArrayList<String>> agents = DBClass.selectAllNewsAgent();
        // Assuming column order: id, name.
        assertTrue(containsValue(agents, 1, uniqueAgent), "Inserted news agent should be found in selectAllNewsAgent.");
    }

    @Test
    void testDuplicateNewsAgent() {
        String uniqueAgent = "DuplicateAgent_" + System.currentTimeMillis();
        DBClass.insertNewsAgent(uniqueAgent);
        // Attempt duplicate insertion.
        DBClass.insertNewsAgent(uniqueAgent);

        ArrayList<ArrayList<String>> agents = DBClass.selectAllNewsAgent();
        int count = 0;
        for (ArrayList<String> row : agents) {
            if (row.size() >= 2 && row.get(1).equals(uniqueAgent)) {
                count++;
            }
        }
        assertEquals(1, count, "Duplicate news agent insertion should not create multiple records.");
    }

    @Test
    void testSelectNonExistentNewsAgent() {
        ArrayList<ArrayList<String>> agents = DBClass.selectNewsAgent(999999);
        assertEquals(0, agents.size(), "Selecting a non-existent news agent id should return an empty list.");
    }

    // JUnit functions for Publication table operations
    // In order to test this, we first must insert a customer,
    // and then insert a publication

    @Test
    void testInsertPublicationAndSelect() {
        // Insert a unique customer for the publication.
        String uniqueCustomer = "PubCustomer_" + System.currentTimeMillis();
        String email = uniqueCustomer + "@example.com";
        DBClass.insertCustomer(uniqueCustomer, email, "Pub Address", "1112223333", 1, "EIRPUB");

        // Retrieve the customer id by searching in selectAllCustomers.
        ArrayList<ArrayList<String>> customers = DBClass.selectAllCustomers();
        int custId = -1;
        for (ArrayList<String> row : customers) {
            if (row.size() >= 2 && row.get(1).equals(uniqueCustomer)) {
                try {
                    custId = Integer.parseInt(row.get(0));
                } catch (NumberFormatException e) {
                    // ignore parsing error
                }
                break;
            }
        }
        assertTrue(custId != -1, "Inserted customer should have a valid id.");

        double uniquePrice = (System.currentTimeMillis() % 1000) + 0.99;
        DBClass.insertPublication(custId, uniquePrice);

        ArrayList<ArrayList<String>> pubs = DBClass.selectAllPublication();
        boolean found = false;
        // Assuming column order: id, cust_id, price.
        for (ArrayList<String> row : pubs) {
            if (row.size() >= 3) {
                try {
                    int rowCustId = Integer.parseInt(row.get(1));
                    double rowPrice = Double.parseDouble(row.get(2));
                    if (rowCustId == custId && Math.abs(rowPrice - uniquePrice) < 0.001) {
                        found = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    // ignore parse errors
                }
            }
        }
        assertTrue(found, "Inserted publication should be found in selectAllPublication.");
    }

    @Test
    void testSelectNonExistentPublication() {
        ArrayList<ArrayList<String>> pubs = DBClass.selectPublication(999999);
        assertEquals(0, pubs.size(), "Selecting a non-existent publication id should return an empty list.");
    }

    // JUnit functions for OrdersStatus table operations
    // In order to test this, we first must insert a publication,
    // and then insert a OrdersStatus

    @Test
    void testInsertOrderStatusAndSelect() {
        String uniqueCustomer = "OrderCustomer_" + System.currentTimeMillis();
        String email = uniqueCustomer + "@example.com";
        DBClass.insertCustomer(uniqueCustomer, email, "Order Address", "2223334444", 1, "EIRORD");

        // Retrieve the customer id.
        ArrayList<ArrayList<String>> customers = DBClass.selectAllCustomers();
        int custId = -1;
        for (ArrayList<String> row : customers) {
            if (row.size() >= 2 && row.get(1).equals(uniqueCustomer)) {
                try {
                    custId = Integer.parseInt(row.get(0));
                } catch (NumberFormatException e) {}
                break;
            }
        }
        assertTrue(custId != -1, "Inserted customer should have a valid id.");

        double uniquePrice = (System.currentTimeMillis() % 1000) + 1.99;
        DBClass.insertPublication(custId, uniquePrice);

        // Retrieve publication id.
        ArrayList<ArrayList<String>> pubs = DBClass.selectAllPublication();
        int pubId = -1;
        for (ArrayList<String> row : pubs) {
            if (row.size() >= 3) {
                try {
                    int rowCustId = Integer.parseInt(row.get(1));
                    double rowPrice = Double.parseDouble(row.get(2));
                    if (rowCustId == custId && Math.abs(rowPrice - uniquePrice) < 0.001) {
                        pubId = Integer.parseInt(row.get(0));
                        break;
                    }
                } catch (NumberFormatException e) {}
            }
        }
        assertTrue(pubId != -1, "Inserted publication should have a valid id.");

        int quantity = 5;
        String status = "Completed_" + System.currentTimeMillis();
        DBClass.insertOrderStatus(custId, pubId, quantity, status);

        ArrayList<ArrayList<String>> orders = DBClass.selectAllOrdersStatus();
        boolean found = false;
        // Assuming column order: id, cust_id, pub_id, quantity, status.
        for (ArrayList<String> row : orders) {
            if (row.size() >= 5) {
                try {
                    int rowCustId = Integer.parseInt(row.get(1));
                    int rowPubId = Integer.parseInt(row.get(2));
                    int rowQuantity = Integer.parseInt(row.get(3));
                    String rowStatus = row.get(4);
                    if (rowCustId == custId && rowPubId == pubId && rowQuantity == quantity && rowStatus.equals(status)) {
                        found = true;
                        break;
                    }
                } catch (NumberFormatException e) {}
            }
        }
        assertTrue(found, "Inserted order status should be found in selectAllOrdersStatus.");
    }

    @Test
    void testSelectNonExistentOrderStatus() {
        ArrayList<ArrayList<String>> orders = DBClass.selectOrdersStatus(999999);
        assertEquals(0, orders.size(), "Selecting a non-existent order status id should return an empty list.");
    }

}
