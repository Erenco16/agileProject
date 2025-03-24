package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryMan {

    public String name;
    public String employmentStatus;
    private DatabaseConnection databaseConnection;

    // Initialize DatabaseConnection in the constructor
    public DeliveryMan() { this.databaseConnection = new DatabaseConnection();}

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Scanner input = new Scanner(System.in);

    public boolean validateName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Delivery area name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid delivery area name, valid name is between 1-255 characters");
        }
        return true;
    }

    public void checkName(){
        while(true){
            System.out.println("Enter Customer Name: ");
            this.name = input.nextLine().trim();
            try{
                validateName(this.name);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkEmploymentStatus() {
        while (true) {
            System.out.println("Enter Employment Status (Active, Inactive): ");
            String statusInput = input.nextLine().trim();
            if (statusInput.equalsIgnoreCase("Active") ||
                    statusInput.equalsIgnoreCase("Inactive"))
                     {
                setEmploymentStatus(statusInput);
                break;
            } else {
                System.out.println("Invalid status. Please enter one of the following: Active, Inactive");
            }
        }
    }

    //Integrate this part with DB

    public void deliveryManReadById() {
        while (true) {
            System.out.println("Enter Delivery Man ID: ");
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

    public void deliveryManReadAll() {
        try {
            ArrayList<ArrayList<String>> allOrders = databaseConnection.selectAllOrdersStatus();
            System.out.println("Displaying all orders: " + allOrders);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void insertDeliveryMan() {
        while (true) {
            try {
                // Validate publication and stock before inserting
                validatePublicationAndStock(pub_id);
                databaseConnection.insertOrderStatus(cust_id, pub_id, quantity, status);
                System.out.println("Order inserted successfully!");
                break; // Exit the loop if insertion succeeds
            } catch (IllegalArgumentException e) {
                System.out.println("Insufficient stock: " + e.getMessage());
                System.out.println("Please enter a valid quantity:");
                checkQuantity(); // Re-prompt for quantity and retry
            } catch (Exception e) {
                System.out.println("Error occurred while inserting order: " + e.getMessage());
                break; // Break out on unexpected errors
            }
        }
    }



    public void updateDeliveryMan() {
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


    public void deleteDeliveryMan() {
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
