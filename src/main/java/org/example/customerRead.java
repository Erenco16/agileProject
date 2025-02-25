package org.example;

import java.util.ArrayList;

public class customerRead {

    public boolean selectCustomerMod(String id){
        try {
            // Assume selectCustomers returns a Customer object or null if not found
            ArrayList<ArrayList<String>> customer = DBClass.selectCustomers(Integer.parseInt(id));

            if (customer.isEmpty()) {
                throw new IllegalArgumentException("No customer found with ID: " + id);
            } else {
                System.out.println("Customer found: " + customer);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public boolean selectAllCustomerMod(){
        try{
            ArrayList<ArrayList<String>> allCustomers = DBClass.selectAllCustomers();
            System.out.println("Displaying all customers: " + allCustomers);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
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
