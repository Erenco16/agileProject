package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private int cust_id;
    private int pub_id;
    private int quantity;
    private String status;
    private DatabaseConnection databaseConnection;  // <== Store connection here

    // Initialize DatabaseConnection in the constructor
    public Order() {
        this.databaseConnection = new DatabaseConnection();
    }

    // Setters and Getters
    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public void setPub_id(int pub_id) {
        this.pub_id = pub_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCust_id() {
        return cust_id;
    }

    public int getPub_id() {
        return pub_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    // checking if the customer ID exists
    public boolean validCustomer(int customer_id) {
        ArrayList<ArrayList<String>> selectCustomers = databaseConnection.selectCustomers(customer_id);
        if (selectCustomers == null || selectCustomers.isEmpty()) {
            throw new IllegalArgumentException("No customer found with ID: " + customer_id);
        }
        return true;
    }

    // validations for publications if the publication exists already or
    public boolean validatePublicationAndStock(int pub_id) {
        ArrayList<ArrayList<String>> publicationData = databaseConnection.selectPublication(pub_id);

        if (publicationData == null || publicationData.isEmpty()) {
            throw new IllegalArgumentException("No publication found with ID: " + pub_id);
        }

        // Get first (and only) publication record
        ArrayList<String> publication = publicationData.get(0);
        int stock = Integer.parseInt(publication.get(publication.size() - 1));

        if (this.quantity > stock) {
            throw new IllegalArgumentException("Not enough stock for publication ID: " + pub_id +
                    ". Requested: " + this.quantity + ", Available: " + stock);
        }

        return true;
    }

    // CLI logic starts here:
    Scanner input = new Scanner(System.in);

    public void checkCustId() {
        while (true) {
            System.out.println("Enter Customer ID: ");
            String custIdInput = input.nextLine().trim();
            try {
                int custIdParsed = Integer.parseInt(custIdInput);
                validCustomer(custIdParsed);
                setCust_id(custIdParsed);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkPubId() {
        while (true) {
            System.out.println("Enter Publication ID: ");
            String pubIdInput = input.nextLine().trim();
            try {
                int pubIdParsed = Integer.parseInt(pubIdInput);
                setPub_id(pubIdParsed);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number");
            }
        }
    }

    public void checkQuantity() {
        while (true) {
            System.out.println("Enter Quantity: ");
            String quantityInput = input.nextLine().trim();
            try {
                int quantityParsed = Integer.parseInt(quantityInput);
                if (quantityParsed <= 0) {
                    throw new IllegalArgumentException("Quantity must be a positive number");
                }
                setQuantity(quantityParsed);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkStatus() {
        while (true) {
            System.out.println("Enter Order Status (Pending, Shipped, Delivered, Cancelled): ");
            String statusInput = input.nextLine().trim();
            if (statusInput.equalsIgnoreCase("Pending") ||
                    statusInput.equalsIgnoreCase("Shipped") ||
                    statusInput.equalsIgnoreCase("Delivered") ||
                    statusInput.equalsIgnoreCase("Cancelled")) {
                setStatus(statusInput);
                break;
            } else {
                System.out.println("Invalid status. Please enter one of the following: Pending, Shipped, Delivered, or Cancelled.");
            }
        }
    }


    public void orderReadById() {
        while (true) {
            System.out.println("Enter Order ID: ");
            try {
                int id = input.nextInt();
                input.nextLine(); // consume leftover newline
                ArrayList<ArrayList<String>> order = databaseConnection.selectOrdersStatus(id);
                if (order.isEmpty()) {
                    System.out.println("No order found with ID: " + id);
                } else {
                    System.out.println("Order found: " + order);
                }
                break;
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                input.nextLine();
            }
        }
    }

    public void orderReadAll() {
        try {
            ArrayList<ArrayList<String>> allOrders = databaseConnection.selectAllOrdersStatus();
            System.out.println("Displaying all orders: " + allOrders);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void insertOrder() {
        try {
            // Validate publication and stock before inserting
            validatePublicationAndStock(pub_id);
            databaseConnection.insertOrderStatus(cust_id, pub_id, quantity, status);
            System.out.println("Order inserted successfully!");
        } catch (Exception e) {
            System.out.println("Error occurred while inserting order: " + e.getMessage());
        }
    }

    public void updateOrder() {
        while (true) {
            System.out.println("Enter Order ID: ");
            try {
                int input_id = input.nextInt();
                input.nextLine(); // consume leftover newline

                System.out.println("Enter new status (Pending, Shipped, Delivered, Cancelled): ");
                String newStatus = input.nextLine().trim();

                databaseConnection.updateOrder(input_id, newStatus);
                System.out.println("Order updated successfully!");
                break;
            } catch (Exception e) {
                System.out.println("Error occurred while updating order: " + e.getMessage());
                input.nextLine(); // Clear buffer
            }
        }
    }


    public void deleteOrder() {
        System.out.println("Enter Order ID: ");
        try {
            int input_id = input.nextInt();
            input.nextLine(); // consume leftover newline
            databaseConnection.deleteOrder(input_id);
            System.out.println("Order deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error occurred while deleting order: " + e.getMessage());
        }
    }
}
