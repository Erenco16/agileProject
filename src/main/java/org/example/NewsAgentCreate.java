package org.example;
import java.util.Scanner;
public class NewsAgentCreate {

    Scanner input = new Scanner(System.in);
    private int option;

    public void mainPage(){
        while (true) {
            mainPageOptions();

            switch (option) {
                case 1:
                    System.out.println("taking you to customer page...");
                    customerPage();
                    break;
                case 2:
                    System.out.println("taking you to publication page");
                    publicationPage();
                    break;
                case 3:
                    System.out.println("taking you to delivery area page.....");
                    deliveryAreaPage();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
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
        while (true) {
            System.out.println("Welcome to Create Customer!");
            System.out.println("1.Create a Customer");
            System.out.println("2.Read a Customer");
            option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                createCustomerCLI();
                break;
            } else if (option == 2) {
                readCustomerCLI();
                break;
            } else {
                System.out.println("Please enter a valid option 1 or 2!");
                //add while loop for this
            }

        }

    }

    public void publicationPage(){
        while (true) {
            System.out.println("Welcome to the Publication Page!");
            System.out.println("1.Create a Publication");
            System.out.println("2.Read a Publication");
            option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                createPublicationCLI();
                break;
            } else if (option == 2) {
                readPublicationCLI();
                break;
            } else {
                System.out.println("Please enter a valid option 1 or 2!");
                //add while loop for this
            }

        }
    }

    public void deliveryAreaPage(){
        while (true) {
            System.out.println("Welcome to the Delivery Area Page!");
            System.out.println("1.Create a Delivery Area");
            System.out.println("2.Read a Delivery Area");
            option = input.nextInt();
            input.nextLine();

            if (option == 1) {
                createDeliveryAreaCLI();
                break;
            } else if (option == 2) {
                readDeliveryAreaCLI();
                break;
            } else {
                System.out.println("Please enter a valid option 1 or 2!");
                //add while loop for this
            }

        }
    }

    public void createDeliveryAreaCLI(){
        deliveryCreate d = new deliveryCreate();
        deliveryAreaValidation dv = new deliveryAreaValidation();
        System.out.println("Welcome to Create Delivery Area Page!");
        dv.checkAreaName();
        dv.checkAreaDescription();
        System.out.println("Delivery Area Added successfully!");
        System.out.println("Taking you back to the main page!");
        mainPage();
    }

    public void readDeliveryAreaCLI(){
        deliveryAreaRead dr = new deliveryAreaRead();

        System.out.println("Welcome to Delivery Area Read!");
        System.out.println("1.Find a Specific Delivery Area");
        System.out.println("2.Display all Delivery Areas");
        option = input.nextInt();
        input.nextLine();

        if (option == 1){
            System.out.println("Enter a Delivery Area ID");
            option = input.nextInt();
            input.nextLine();
            //dr.selectCustomerMod(String.valueOf(option));
        }
    }

    public void createPublicationCLI(){
        publicationCreate p = new publicationCreate();
        publicationValidation pv = new publicationValidation();
        System.out.println("Welcome to Create Delivery Area Page!");
        pv.checkPublicationName();
        pv.checkPublicationDescription();
        pv.checkPublicationPrice();
        mainPage();
    }

    public void readPublicationCLI(){
        publicationRead pr = new publicationRead();

        System.out.println("Welcome to Publication Read!");
        System.out.println("1.Find a Specific Publication");
        System.out.println("2.Display all Publications");
        option = input.nextInt();
        input.nextLine();

        if (option == 1){
            System.out.println("Enter a Publication ID");
            option = input.nextInt();
            input.nextLine();
            //dr.selectCustomerMod(String.valueOf(option));
        }
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

    public void readCustomerCLI() {
        customerInputValidation v = new customerInputValidation();

        while (true) {
            System.out.println("Welcome to Customer Read!");
            System.out.println("1.Find a Specific ID");
            System.out.println("2.Display all Customers");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        v.customerReadID();
                        return;
                    case 2:
                        v.customerReadAll();
                        return;
                    default:
                        System.out.println("Please enter a valid option 1 or 2!");
                        break;
                }
            } else {
                // If input is not an integer
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
            }
        }
    }



}


