package org.example;

import java.util.ArrayList;

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

    // Setters and Getters (same as you have)

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
    public boolean validPublication(int pub_id) {
        ArrayList<ArrayList<String>> selectPublication = databaseConnection.selectPublication(pub_id);
        if (selectPublication == null || selectPublication.isEmpty()) {
            throw new IllegalArgumentException("No publication found with ID: " + pub_id);
        }
        System.out.println(selectPublication);
        return true;
    }
    public static void main(String[] args) {
        Order order = new Order();
        System.out.println(order.validPublication(1));
    }
}
