package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class customerRead {
    private int option;

    Scanner input = new Scanner(System.in);

    public int customerReadOption() {
        System.out.println("Welcome to the customer read!");
        System.out.println("Please select an option below:");
        System.out.println("1.  Read a specific customer");
        System.out.println("2.  All customers");
        System.out.println("3.  Return");
        option = input.nextInt();
        input.nextLine();
        return option;
    }

    public void customerReadSelection() {
        customerReadOption();

        switch (option) {
            case 1:
                System.out.println("Searching for specific customer");
                break;
            case 2:
                System.out.println("Searching for all customers");
                break;
        }
    }

    public void specificCustomerRead() {
        System.out.println("Enter customer ID:");
        int customerID = input.nextInt();
        String nullChecker = String.valueOf(customerID);

        if (nullChecker == null || nullChecker.isEmpty()) {
            throw new IllegalArgumentException("Field cannot be empty");
        }

        if (customerID < 0 ) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
    }

    public void selectCustomerMod(int id){
        try {
            // Assume selectCustomers returns a Customer object or null if not found
            ArrayList<ArrayList<String>> customer = DBClass.selectCustomers(id);

            // Check if the customer is null (i.e., no matching customer was found)
            if (customer == null) {
                System.out.println("No customer found with ID: " + id);
            } else {
                // Proceed with displaying the customer details or other logic
                System.out.println("Customer found: " + customer);
            }
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues)
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // select customer function for an id
    public void selectCustomer(int id){
        try{
            DBClass.selectCustomers(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // select all customer function
    public void selectAllCustomer(){
        try{
            DBClass.selectAllCustomers();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
