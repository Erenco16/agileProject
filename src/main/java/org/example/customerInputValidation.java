package org.example;

import java.util.Scanner;

public class customerInputValidation {

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

    public void checkDeliveryArea(){

        while(true){

            System.out.println("Enter Customer Delivery Area: ");
            deliveryArea = input.nextLine().trim();
            try{
                c.setDeliveryArea(deliveryArea);
                break;
            }catch (IllegalArgumentException e){
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




}
