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

    // Properly check if customer ID exists
    public boolean validCustomer(int customer_id){
        ArrayList<ArrayList<String>> selectCustomers = databaseConnection.selectCustomers(customer_id);
        return selectCustomers != null && !selectCustomers.isEmpty();
    }
}
