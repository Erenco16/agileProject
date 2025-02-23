package org.example;

import java.util.Scanner;

public class customerInputValidation {


    // DELIVERYAREA MUST BE CHANGED TO INT

    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String deliveryArea;
    private String eircode;


    customerCreate c = new customerCreate();
    Scanner input = new Scanner(System.in);


    public void checkName(){

        while(true){

            System.out.println("Enter Customer Name: ");
            name = input.nextLine().trim();
            try{
                c.validateName(name);
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
                c.validateEmail(email);
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
                c.validateAddress(address);
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
                c.validatePhoneNumber(phoneNumber);
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
            deliveryArea = input.nextLine().trim();
            try {
                //int deliveryAreaInt = Integer.parseInt(inputValue);
                c.validateDeliveryArea(deliveryArea); // assuming setDeliveryArea now accepts an int
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
                c.validateEircode(eircode);
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
            db.insertCustomer(name, email, address, phoneNumber, Integer.parseInt(deliveryArea), eircode);
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
