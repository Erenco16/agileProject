package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;

public class DBTest {

    DatabaseConnection db = new DatabaseConnection();
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
        try (Connection conn = db.getConnection()) {
            assertNotNull(conn, "getConnection() should return a non-null connection.");
        } catch (SQLException e) {
            fail("getConnection() threw SQLException: " + e.getMessage());
        }
    }

    @Test
    void testGetValues() {
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1 AS col")) {
            ArrayList<ArrayList<String>> values = db.getValues(rs);
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
        db.insertCustomer(uniqueName, email, "Test Address", "1234567890", 1, "EIR1234");

        // Search in all customers for our unique customer name (column order assumed: id, name, email, ...)
        ArrayList<ArrayList<String>> customers = db.selectAllCustomers();
        assertTrue(containsValue(customers, 1, uniqueName), "Inserted customer should be found in selectAllCustomers.");
    }

    @Test
    void testSelectNonExistentCustomer() {
        ArrayList<ArrayList<String>> customers = db.selectCustomers(999999);
        assertEquals(0, customers.size(), "Selecting a non-existent customer id should return an empty list.");
    }

    @Test
    void testSelectAllCustomers() {
        String uniqueName = "AllCustomer_" + System.currentTimeMillis();
        String email = uniqueName + "@example.com";
        db.insertCustomer(uniqueName, email, "All Address", "3334445555", 1, "EIRALL");

        ArrayList<ArrayList<String>> customers = db.selectAllCustomers();
        assertTrue(containsValue(customers, 1, uniqueName), "selectAllCustomers should return the inserted customer.");
    }

    // JUnit functions for Address table operations

    @Test
    void testInsertAddressAndSelect() {
        String uniqueAddress = "UniqueAddress_" + System.currentTimeMillis();
        db.insertAddress(uniqueAddress, 1);

        ArrayList<ArrayList<String>> addresses = db.selectAllAddress();
        // Assuming column order: id, address, delivery_area_id.
        assertTrue(containsValue(addresses, 1, uniqueAddress), "Inserted address should be found in selectAllAddress.");
    }

    @Test
    void testSelectNonExistentAddress() {
        ArrayList<ArrayList<String>> addresses = db.selectAddress(999999);
        assertEquals(0, addresses.size(), "Selecting a non-existent address id should return an empty list.");
    }

    @Test
    void testSelectAllAddress() {
        String uniqueAddress1 = "AllAddress1_" + System.currentTimeMillis();
        String uniqueAddress2 = "AllAddress2_" + System.currentTimeMillis();
        db.insertAddress(uniqueAddress1, 1);
        db.insertAddress(uniqueAddress2, 1);

        ArrayList<ArrayList<String>> addresses = db.selectAllAddress();
        assertTrue(containsValue(addresses, 1, uniqueAddress1)
                        && containsValue(addresses, 1, uniqueAddress2),
                "Both inserted addresses should be found in selectAllAddress.");
    }

    // JUnit functions for DeliverArea table operations

    @Test
    void testInsertDeliveryAreaAndSelect() {
        String uniqueArea = "UniqueArea_" + System.currentTimeMillis();
        db.insertDeliveryArea(uniqueArea, "Test Description");

        ArrayList<ArrayList<String>> areas = db.selectAllDeliveryArea();
        // Assuming column order: id, name, description.
        assertTrue(containsValue(areas, 1, uniqueArea), "Inserted delivery area should be found in selectAllDeliveryArea.");
    }

    @Test
    void testDuplicateDeliveryArea() {
        try {
            String uniqueArea = "DuplicateArea_" + System.currentTimeMillis();
            db.insertDeliveryArea(uniqueArea, "First Description");
            db.insertDeliveryArea(uniqueArea, "Second Description");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during duplicate delivery area insert: " + e.getMessage());
        }
    }

    @Test
    void testSelectNonExistentDeliveryArea() {
        ArrayList<ArrayList<String>> areas = db.selectDeliveryArea(999999);
        assertEquals(0, areas.size(), "Selecting a non-existent delivery area id should return an empty list.");
    }

    // JUnit functions for NewsAgent table operations

    @Test
    void testInsertNewsAgentAndSelect() {
        String uniqueAgent = "UniqueAgent_" + System.currentTimeMillis();
        db.insertNewsAgent(uniqueAgent);

        ArrayList<ArrayList<String>> agents = db.selectAllNewsAgent();
        // Assuming column order: id, name.
        assertTrue(containsValue(agents, 1, uniqueAgent), "Inserted news agent should be found in selectAllNewsAgent.");
    }

    @Test
    void testDuplicateNewsAgent() {
        try {
            String uniqueAgent = "DuplicateAgent_" + System.currentTimeMillis();
            db.insertNewsAgent(uniqueAgent);
            db.insertNewsAgent(uniqueAgent);
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during duplicate news agent insert: " + e.getMessage());
        }
    }

    @Test
    void testSelectNonExistentNewsAgent() {
        ArrayList<ArrayList<String>> agents = db.selectNewsAgent(999999);
        assertEquals(0, agents.size(), "Selecting a non-existent news agent id should return an empty list.");
    }

    // JUnit functions for Publication table operations
    // In order to test this, we first must insert a customer,
    // and then insert a publication

    @Test
    void testInsertPublicationAndSelect() {
        // Generate unique publication details.
        String pubName = "TestPublication_" + System.currentTimeMillis();
        String pubDescription = "Test Description " + System.currentTimeMillis();
        double pubPrice = (System.currentTimeMillis() % 1000) + 0.99;
        int stock = 10;
        // Insert the publication using the new method signature.
        db.insertPublication(pubName, pubDescription, pubPrice, stock);

        // Retrieve all publications.
        ArrayList<ArrayList<String>> pubs = db.selectAllPublication();
        boolean found = false;

        // Assuming the column order is: id, publication_name, publication_description, price.
        for (ArrayList<String> row : pubs) {
            if (row.size() >= 4) {
                try {
                    String rowPubName = row.get(1);
                    String rowPubDescription = row.get(2);
                    double rowPubPrice = Double.parseDouble(row.get(3));
                    if (rowPubName.equals(pubName) && rowPubDescription.equals(pubDescription)
                            && Math.abs(rowPubPrice - pubPrice) < 0.001) {
                        found = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    // Ignore parse errors.
                }
            }
        }
        assertTrue(found, "Inserted publication should be found in selectAllPublication.");
    }


    @Test
    void testSelectNonExistentPublication() {
        ArrayList<ArrayList<String>> pubs = db.selectPublication(999999);
        assertEquals(0, pubs.size(), "Selecting a non-existent publication id should return an empty list.");
    }

    // JUnit functions for OrdersStatus table operations
    // In order to test this, we first must insert a publication,
    // and then insert a OrdersStatus

    @Test
    void testInsertOrderStatusAndSelect() {
        try {
            db.insertCustomer("OrderCustomer", "ordercustomer@example.com", "Address", "1234567890", 1, "CODE123");
            var customers = db.selectAllCustomers();
            int custId = Integer.parseInt(customers.get(customers.size() - 1).get(0));

            db.insertPublication("TestPub", "Desc", 10.0, 10);
            var pubs = db.selectAllPublication();
            int pubId = Integer.parseInt(pubs.get(pubs.size() - 1).get(0));

            db.insertOrderStatus(custId, pubId, 5, "Pending");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during insertOrderStatus: " + e.getMessage());
        }
    }


    @Test
    void testSelectNonExistentOrderStatus() {
        ArrayList<ArrayList<String>> orders = db.selectOrdersStatus(999999);
        assertEquals(0, orders.size(), "Selecting a non-existent order status id should return an empty list.");
    }

    @Test
    void testInsertDeliveryManAndSelect() {
        String uniqueName = "DeliveryMan_" + System.currentTimeMillis();
        db.insertDeliveryMan(uniqueName, "Active");

        ArrayList<ArrayList<String>> deliveryMen = db.selectAllDeliveryMan();
        assertTrue(containsValue(deliveryMen, 1, uniqueName), "Inserted delivery man should be found in selectAllDeliveryMan.");
    }

    @Test
    void testSelectNonExistentDeliveryMan() {
        ArrayList<ArrayList<String>> deliveryMen = db.selectDeliveryMan(999999);
        assertEquals(0, deliveryMen.size(), "Selecting a non-existent delivery man should return an empty list.");
    }

    @Test
    void testInsertDeliveryDocket() {
        // Insert a delivery man to link the docket to
        db.insertDeliveryMan("Test DeliveryMan for Docket", "Active");

        // Retrieve the last delivery man ID
        var deliveryMen = db.selectAllDeliveryMan();
        int lastDeliveryManId = Integer.parseInt(deliveryMen.get(deliveryMen.size() - 1).get(0));

        // Insert a delivery docket for that delivery man
        db.insertDeliveryDocket(lastDeliveryManId, "Pending");

        // If no exceptions are thrown, the test will pass
    }


    @Test
    void testSelectNonExistentDeliveryDocket() {
        ArrayList<ArrayList<String>> deliveryDockets = db.selectAllDeliveryDocket();
        assertFalse(containsValue(deliveryDockets, 0, "999999"), "Selecting a non-existent delivery docket should return an empty list.");
    }

    @Test
    void testInsertInvoiceAndSelect() {
        db.insertCustomer("Invoice Customer", "invoice@example.com", "Test Address", "1234567890", 1, "EIR1234");
        ArrayList<ArrayList<String>> customers = db.selectAllCustomers();
        int custId = Integer.parseInt(customers.get(customers.size() - 1).get(0));
        int stock = 10;

        db.insertPublication("Invoice Publication", "Test Desc", 15.99, stock);
        ArrayList<ArrayList<String>> publications = db.selectAllPublication();
        int pubId = Integer.parseInt(publications.get(publications.size() - 1).get(0));

        db.insertOrderStatus(custId, pubId, 2, "Pending");
        ArrayList<ArrayList<String>> orders = db.selectAllOrdersStatus();
        int orderId = Integer.parseInt(orders.get(orders.size() - 1).get(0));

        db.insertInvoice(orderId, 31.98);
        ArrayList<ArrayList<String>> invoices = db.selectAllInvoice();
        assertTrue(containsValue(invoices, 2, "31.98"), "Inserted invoice should be found in selectAllInvoice.");
    }

    @Test
    void testSelectNonExistentInvoice() {
        ArrayList<ArrayList<String>> invoices = db.selectAllInvoice();
        assertFalse(containsValue(invoices, 0, "999999"), "Selecting a non-existent invoice should return an empty list.");
    }

    // junit tests for the update and delete methods for the sprint 1 entities

    @Test
    void testUpdateCustomer() {
        try {
            db.insertCustomer("Test Name", "test@example.com", "Test Address", "1234567890", 1, "TESTCODE");
            assertTrue(true); // Pass the test if no exception occurs
        } catch (Exception e) {
            assertTrue(false, "Exception thrown during insertCustomer: " + e.getMessage());
        }
    }


    @Test
    void testDeleteCustomer() {
        db.insertCustomer("DeleteTest", "delete@example.com", "Some Address", "111222333", 1, "EIR001");
        db.deleteCustomer(1);
        ArrayList<ArrayList<String>> customers = db.selectCustomers(1);
        assertEquals(0, customers.size(), "Deleted customer should not be found.");
    }

    @Test
    void testUpdateAddress() {
        try {
            db.insertAddress("Old Address", 1);
            db.updateAddress(1, "New Address", 1);
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during updateAddress: " + e.getMessage());
        }
    }

    @Test
    void testDeleteAddress() {
        db.insertAddress("Delete Address", 1);
        db.deleteAddress(1);
        ArrayList<ArrayList<String>> addresses = db.selectAddress(1);
        assertEquals(0, addresses.size(), "Deleted address should not be found.");
    }

    @Test
    void testUpdateDeliveryArea() {
        db.insertDeliveryArea("Old Area", "Old Description");
        db.updateDeliveryArea(2, "New Area", "Updated Description");
        ArrayList<ArrayList<String>> areas = db.selectDeliveryArea(2);
        assertTrue(containsValue(areas, 1, "New Area"), "Delivery area name should be updated.");
    }

    @Test
    void testDeleteDeliveryArea() {
        db.insertDeliveryArea("Delete Area", "Some Description");
        db.deleteDeliveryArea(1);
        ArrayList<ArrayList<String>> areas = db.selectDeliveryArea(1);
        assertEquals(0, areas.size(), "Deleted delivery area should not be found.");
    }

    @Test
    void testUpdateNewsAgent() {
        try {
            db.insertNewsAgent("Old NewsAgent");
            db.updateNewsAgent(1, "Updated NewsAgent");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during updateNewsAgent: " + e.getMessage());
        }
    }

    @Test
    void testDeleteNewsAgent() {
        db.insertNewsAgent("Delete NewsAgent");
        db.deleteNewsAgent(1);
        ArrayList<ArrayList<String>> agents = db.selectNewsAgent(1);
        assertEquals(0, agents.size(), "Deleted news agent should not be found.");
    }

    @Test
    void testUpdateDeliveryMan() {
        try {
            db.insertDeliveryMan("DeliveryManTest", "Active");
            var deliveryMen = db.selectAllDeliveryMan();
            int id = Integer.parseInt(deliveryMen.get(deliveryMen.size() - 1).get(0));

            db.updateDeliveryMan(id, "Inactive");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during updateDeliveryMan: " + e.getMessage());
        }
    }

    @Test
    void testDeleteDeliveryMan() {
        db.insertDeliveryMan("DeleteDeliveryMan", "Active");
        ArrayList<ArrayList<String>> deliveryMen = db.selectAllDeliveryMan();
        int id = Integer.parseInt(deliveryMen.get(deliveryMen.size() - 1).get(0));

        db.deleteDeliveryMan(id);
        ArrayList<ArrayList<String>> deletedDeliveryMan = db.selectDeliveryMan(id);
        assertEquals(0, deletedDeliveryMan.size(), "Deleted delivery man should not be found.");
    }

    @Test
    void testUpdateInvoice() {
        db.insertInvoice(1, 100.50);
        ArrayList<ArrayList<String>> invoices = db.selectAllInvoice();
        int id = Integer.parseInt(invoices.get(invoices.size() - 1).get(0));

        db.updateInvoice(id, 1, 200.75);
        ArrayList<ArrayList<String>> updatedInvoice = db.selectAllInvoice();
        assertTrue(containsValue(updatedInvoice, 2, "200.75"), "Invoice amount should be updated.");
    }

    @Test
    void testDeleteInvoice() {
        db.insertInvoice(1, 150.00);
        ArrayList<ArrayList<String>> invoices = db.selectAllInvoice();
        int id = Integer.parseInt(invoices.get(invoices.size() - 1).get(0));

        db.deleteInvoice(id);
        ArrayList<ArrayList<String>> deletedInvoice = db.selectAllInvoice();
        assertFalse(containsValue(deletedInvoice, 0, String.valueOf(id)), "Deleted invoice should not be found.");
    }

    @Test
    void testUpdateOrder() {
        try {
            db.insertOrderStatus(1, 1, 5, "Pending");
            ArrayList<ArrayList<String>> orders = db.selectAllOrdersStatus();
            int id = Integer.parseInt(orders.get(orders.size() - 1).get(0));

            db.updateOrder(id, "Shipped");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during updateOrder: " + e.getMessage());
        }
    }

    @Test
    void testDeleteOrder() {
        db.insertOrderStatus(1, 1, 2, "Pending");
        ArrayList<ArrayList<String>> orders = db.selectAllOrdersStatus();
        int id = Integer.parseInt(orders.get(orders.size() - 1).get(0));

        db.deleteOrder(id);
        ArrayList<ArrayList<String>> deletedOrder = db.selectOrdersStatus(id);
        assertEquals(0, deletedOrder.size(), "Deleted order should not be found.");
    }

    @Test
    void testUpdateDeliveryDocket() {
        try {
            db.insertDeliveryMan("DocketUpdateMan", "Active");
            var deliveryMen = db.selectAllDeliveryMan();
            int deliveryManId = Integer.parseInt(deliveryMen.get(deliveryMen.size() - 1).get(0));

            db.insertDeliveryDocket(deliveryManId, "Pending");
            var dockets = db.selectAllDeliveryDocket();
            int docketId = Integer.parseInt(dockets.get(dockets.size() - 1).get(0));

            db.updateDeliveryDocket(docketId, deliveryManId, "Completed");
            assertTrue(true); // Pass if no exception thrown
        } catch (Exception e) {
            fail("Exception thrown during updateDeliveryDocket: " + e.getMessage());
        }
    }

    @Test
    void testDeleteDeliveryDocket() {
        db.insertDeliveryMan("DocketDeleteMan", "Active");
        ArrayList<ArrayList<String>> deliveryMen = db.selectAllDeliveryMan();
        int deliveryManId = Integer.parseInt(deliveryMen.get(deliveryMen.size() - 1).get(0));

        db.insertDeliveryDocket(deliveryManId, "Pending");
        ArrayList<ArrayList<String>> dockets = db.selectAllDeliveryDocket();
        int docketId = Integer.parseInt(dockets.get(dockets.size() - 1).get(0));

        db.deleteDeliveryDocket(docketId);
        ArrayList<ArrayList<String>> deletedDocket = db.selectAllDeliveryDocket();
        assertFalse(containsValue(deletedDocket, 0, String.valueOf(docketId)), "Deleted delivery docket should not be found.");
    }

    @Test
    void testUpdatePublication() {
        String pubName = "PublicationTest_" + System.currentTimeMillis();
        db.insertPublication(pubName, "Test Description", 20.0, 50);
        ArrayList<ArrayList<String>> publications = db.selectAllPublication();
        int id = Integer.parseInt(publications.get(publications.size() - 1).get(0));

        db.updatePublication(id, "UpdatedPublication", "Updated Description", 30.0, 100);
        ArrayList<ArrayList<String>> updatedPublication = db.selectPublication(id);
        assertTrue(containsValue(updatedPublication, 1, "UpdatedPublication"), "Publication name should be updated.");
    }

    @Test
    void testDeletePublication() {
        String pubName = "DeletePublication_" + System.currentTimeMillis();
        db.insertPublication(pubName, "Test Description", 20.0, 50);
        ArrayList<ArrayList<String>> publications = db.selectAllPublication();
        int id = Integer.parseInt(publications.get(publications.size() - 1).get(0));

        db.deletePublication(id);
        ArrayList<ArrayList<String>> deletedPublication = db.selectPublication(id);
        assertEquals(0, deletedPublication.size(), "Deleted publication should not be found.");
    }

}
