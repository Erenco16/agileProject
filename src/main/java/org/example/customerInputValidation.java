package org.example;

import java.util.Scanner;

public class customerInputValidation {


    // DELIVERYAREA MUST BE CHANGED TO INT

    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private int deliveryArea;
    private String eircode;


    customerCreate c = new customerCreate();
    Scanner input = new Scanner(System.in);


    public void checkName(){

        while(true){

            System.out.println("Enter Customer Name: ");
            name = input.nextLine().trim();
            try{
                c.setName(name);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }

    }

    public void checkEmail(){

        while(true){

            System.out.println("Enter Customer Email: ");
            email = input.nextLine().trim();
            try{
                c.setEmail(email);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }

    public void checkAddress(){

        while(true){

            System.out.println("Enter Customer Address: ");
            address = input.nextLine().trim();
            try{
                c.setAddress(address);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }

    public void checkPhoneNumber(){

        while(true){

            System.out.println("Enter Customer Phone Number: ");
            phoneNumber = input.nextLine().trim();
            try{
                c.setPhoneNumber(phoneNumber);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }


    // Eren is updating this function as its gonna accept int values
    public void checkDeliveryArea() {
        while (true) {
            System.out.println("Enter Customer Delivery Area: ");
            String inputValue = input.nextLine().trim();
            try {
                int deliveryAreaInt = Integer.parseInt(inputValue);
                c.setDeliveryArea(deliveryAreaInt); // assuming setDeliveryArea now accepts an int
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
            eircode = input.nextLine().trim();
            try{
                c.setEircode(eircode);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }
    }

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertCustomer(){
        DBClass db = new DBClass();
        try{
            db.insertCustomer(name, email, address, phoneNumber, deliveryArea, eircode);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
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
