package org.example;
import java.util.Scanner;
public class NewsAgentCreate {

    Scanner input = new Scanner(System.in);
    private int option;

    public void mainPage(){
        mainPageOptions();

        if (option == 1){
            System.out.println("taking you to customer page...");
            customerPage();
        } else if (option == 2) {
            System.out.println("taking you to publication page");
            publicationPage(option);

        }
    }

    public Integer mainPageOptions(){
        System.out.println("Welcome Newsagent!");
        System.out.println("Please select an option below:");
        System.out.println("1. Customer page");
        System.out.println("2. Publication page");
        System.out.println("3. Delivery Area");
        System.out.println("4. Order");
        option = input.nextInt();
        return option;
    }

    public void customerPage(){

        System.out.println("Welcome to Create Customer!");

        System.out.println("Enter Customer Name: ");
        String name = input.nextLine();

        System.out.println("Enter Customer Email: ");
        String email = input.nextLine();

        System.out.println("Enter Customer Address: ");
        String address = input.nextLine();

        System.out.println("Enter Customer Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Enter Customer Delivery Area: ");
        String deliveryArea = input.nextLine();

        System.out.println("Enter Customer Eircode: ");
        String eircode = input.nextLine();



    }

    public void publicationPage(int option){
        System.out.println("You chose " + option);
    }

//    public void deliveryArea(){
//
//    }

//    public void order(){
//
//    }

}


