package org.example;

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




}
