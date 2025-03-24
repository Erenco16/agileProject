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
            System.out.println("Enter Delivery Man Name: ");
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
                ArrayList<ArrayList<String>> deliveryMan = databaseConnection.selectDeliveryMan(id);
                if (deliveryMan.isEmpty()) {
                    System.out.println("No Delivery Man found with ID: " + id);
                } else {
                    System.out.println("Delivery Man found: " + deliveryMan);
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
            ArrayList<ArrayList<String>> allDeliveryMan = databaseConnection.selectAllDeliveryMan();
            System.out.println("Displaying all Deliver Men: " + allDeliveryMan);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void insertDeliveryMan() {
        while (true) {
            try {
                // Validate publication and stock before inserting
                validateName(name);
                databaseConnection.insertDeliveryMan(name, employmentStatus);
                System.out.println("Delivery Man inserted successfully!");
                break; // Exit the loop if insertion succeeds
            } catch (Exception e) {
                System.out.println("Error occurred while inserting Delivery Man: " + e.getMessage());
                break; // Break out on unexpected errors
            }
        }
    }



    public void updateDeliveryMan() {
        while (true) {
            System.out.println("Enter Delivery Man ID: ");
            try {
                int input_id = input.nextInt();
                input.nextLine(); // consume leftover newline

                System.out.println("Enter new status (Active, Inactive): ");
                String newStatus = input.nextLine().trim();

                databaseConnection.updateDeliveryMan(input_id, newStatus);
                System.out.println("Delivery Man updated successfully!");
                break;
            } catch (Exception e) {
                System.out.println("Error occurred while updating Delivery man: " + e.getMessage());
                input.nextLine(); // Clear buffer
            }
        }
    }


    public void deleteDeliveryMan() {
        System.out.println("Enter Delivery Man ID: ");
        try {
            int input_id = input.nextInt();
            input.nextLine(); // consume leftover newline
            databaseConnection.deleteDeliveryMan(input_id);
            System.out.println("Delivery Man deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error occurred while deleting order: " + e.getMessage());
        }
    }


}
