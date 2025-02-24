package org.example;
import java.util.Scanner;
public class NewsAgentCreate {

    Scanner input = new Scanner(System.in);
    private int option;

    public void mainPage(){
        mainPageOptions();

        switch (option) {
            case 1:
                System.out.println("taking you to customer page...");
                customerPage();
            break;
            case 2:
                System.out.println("taking you to publication page");
                publicationPage(option);
            break;
            case 3:
                System.out.println("taking you to delivery area page.....");
                deliveryPage();
            break;
        }
    }

    public boolean ValidateOptionInput(Integer num){
        if(num > 3){
            throw new IllegalArgumentException("Option is out of bounds");
        }
        return true;
    }

    public Integer mainPageOptions(){
        System.out.println("Welcome Newsagent!");
        System.out.println("Please select an option below:");
        System.out.println("1. Customer page");
        System.out.println("2. Publication page");
        System.out.println("3. Delivery Area");
        System.out.println("4. Order");
        option = input.nextInt();
        input.nextLine();
        return option;
    }

    public void customerPage(){

        System.out.println("Welcome to Create Customer!");
        System.out.println("1.Create a Customer");
        System.out.println("2.Read a Customer");
        option = input.nextInt();
        input.nextLine();

        if(option == 1){
            createCustomerCLI();
        }else if (option == 2){
//            readCustomerCLI();
        }else {
            System.out.println("Please enter a valid option 1 or 2!");
            //add while loop for this
        }

    }

    public void publicationPage(int option){
        System.out.println("You chose " + option);
    }

    public void deliveryPage(){

        deliveryCreate d = new deliveryCreate();
        deliveryAreaValidation dv = new deliveryAreaValidation();
        System.out.println("Welcome to Create Delivery Area Page!");
        dv.checkAreaName();
        dv.checkAreaDescription();

    }

    public void createCustomerCLI(){
        customerCreate c = new customerCreate();
        customerInputValidation v = new customerInputValidation();

        v.checkName();
        v.checkEmail();
        v.checkAddress();
        v.checkPhoneNumber();
        v.checkDeliveryArea();
        v.checkEircode();
        v.insertCustomer();         // added to insert customer info to db
        System.out.println("Customer Added successfully");
        System.out.println("Taking you back to main page....");
        mainPage();
    }



}


