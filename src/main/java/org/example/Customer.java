package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    // --------------------- Create ---------------------
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String deliveryArea;
    private String eircode;
    private DatabaseConnection databaseConnection;

    // Constructor to initialize the DatabaseConnection
    public Customer() {
        this.databaseConnection = new DatabaseConnection();
    }


    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() > 255 ) {
            throw new IllegalArgumentException("Name cannot exceed 255 characters");
        }
        return true;
    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@") || !email.contains(".com")) {
            throw new IllegalArgumentException("Invalid email format, must include '@' and '.com'");
        }
        return true;
    }

    public boolean validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (address.length() > 255) {
            throw new IllegalArgumentException("Address cannot exceed 255 characters");
        }
        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number format, must be 10 digits");
        }
        return true;
    }

    public boolean validateDeliveryArea(String deliveryArea) {
        if (deliveryArea == null || deliveryArea.isEmpty()) {
            throw new IllegalArgumentException("Delivery area cannot be empty");
        }
        try {
            int deliveryAreaInt = Integer.parseInt(deliveryArea);
            if (deliveryAreaInt <= 0) {
                throw new IllegalArgumentException("Delivery area must be a positive number");
            }
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
    }

    public boolean validateEircode(String eircode) {
        if (eircode == null || eircode.isEmpty()) {
            throw new IllegalArgumentException("Eircode cannot be empty");
        }
        if (eircode.length() != 7) {
            throw new IllegalArgumentException("Invalid Eircode format, must be 7 characters");
        }
        return true;
    }

    public boolean selectCustomerMod(String id){
        try {
            // Assume selectCustomers returns a Customer object or null if not found
            ArrayList<ArrayList<String>> customer = databaseConnection.selectCustomers(Integer.parseInt(id));

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
            ArrayList<ArrayList<String>> allCustomers = databaseConnection.selectAllCustomers();
            System.out.println("Displaying all customers: " + allCustomers);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    // input validation


    // --------------------- Read&Validation ---------------------
    // DELIVERYAREA MUST BE CHANGED TO INT
    // not necessary, we parse the string to int during insertion (timmy)

    private String inputName;
    private String inputEmail;
    private String inputAddress;
    private String inputPhoneNumber;
    private String inputDeliveryArea;
    private String inputEircode;
    private String inputID;

    Scanner input = new Scanner(System.in);


    public void checkName(){

        while(true){

            System.out.println("Enter Customer Name: ");
            inputName = input.nextLine().trim();
            try{
                validateName(inputName);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }

    }

    public void checkEmail(){

        while(true){

            System.out.println("Enter Customer Email: ");
            inputEmail = input.nextLine().trim();
            try{
                validateEmail(inputEmail);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }

    public void checkAddress(){

        while(true){

            System.out.println("Enter Customer Address: ");
            inputAddress = input.nextLine().trim();
            try{
                validateAddress(inputAddress);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }

    public void checkPhoneNumber(){

        while(true){

            System.out.println("Enter Customer Phone Number: ");
            inputPhoneNumber = input.nextLine().trim();
            try{
                validatePhoneNumber(inputPhoneNumber);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }


    public void checkDeliveryArea() {
        while (true) {
            System.out.println("Enter Customer Delivery Area: ");
            inputDeliveryArea = input.nextLine().trim();
            try {
                validateDeliveryArea(inputDeliveryArea); // assuming setDeliveryArea now accepts an int
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer value.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void checkEircode(){

        while(true){

            System.out.println("Enter Customer Eircode: ");
            inputEircode = input.nextLine().trim();
            try{
                validateEircode(inputEircode);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }
    }

    public void customerReadID() {
        while(true){
            System.out.println("Enter Customer ID: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                selectCustomerMod(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    public void customerReadAll() {
        selectAllCustomerMod();
    }

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertCustomer(){
        try{
            databaseConnection.insertCustomer(inputName, inputEmail, inputAddress, inputPhoneNumber, Integer.parseInt(inputDeliveryArea), inputEircode);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //--------------------- Update ---------------------

    public void customerUpdate() {
        while(true){
            System.out.println("Enter Customer ID to update: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                selectCustomerMod(String.valueOf(id));
                inputID = String.valueOf(id);
                break;
            } catch (IllegalArgumentException e)    {
                System.out.println(e.getMessage());            }
        }
    }

    public void updateCustomerDB() {
        try {
            databaseConnection.updateCustomer(Integer.parseInt(inputID), inputName, inputEmail, inputAddress, inputPhoneNumber, Integer.parseInt(inputDeliveryArea), inputEircode);
            System.out.println("Customer update successful, returning to main page");
        } catch (IllegalArgumentException e)    {
            System.out.println(e.getMessage());
        }
    }

    //--------------------- delete ---------------------
    public void customerDelete() {
        while(true){
            selectAllCustomerMod();
            System.out.println("Enter Customer ID to delete: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                selectCustomerMod(String.valueOf(id));
                System.out.println("Deleting data related to ID: " + id);
                databaseConnection.deleteCustomer(id);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

//    public void customerDeleteDB() {
//        int id = Integer.parseInt(inputID);
//        databaseConnection.deleteCustomer(id);
//    }

}
