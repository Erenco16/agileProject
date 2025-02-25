package org.example;
import java.util.Scanner;
public class NewsAgentCreate {

    Scanner input = new Scanner(System.in);
    private int option;

    public void mainPage(){
        while (true) {
            option = mainPageOptions();
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


    public int getValidIntegerInput(int min, int max) {
        while (true) {
            System.out.print("Enter your option: ");
            String userInput = input.nextLine().trim(); // Read input as a string

            if (userInput.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a number.");
                continue;
            }

            try {
                int num = Integer.parseInt(userInput); // Try converting to int

                if (num < min || num > max) {
                    System.out.println("Invalid option! Please enter a number between " + min + " and " + max);
                    continue;
                }

                return num; // Return valid number

            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }



    public Integer mainPageOptions(){
        System.out.println("Welcome Newsagent!");
        System.out.println("Please select an option below:");
        System.out.println("1. Customer page");
        System.out.println("2. Publication page");
        System.out.println("3. Delivery Area");
        option = getValidIntegerInput(1, 3);

        return option;
    }

    public void customerPage(){
        while (true) {
            System.out.println("Welcome to Create Customer!");
            System.out.println("1.Create a Customer");
            System.out.println("2.Read a Customer");
            option = getValidIntegerInput(1, 2); // Allows only 1 or 2


            if (option == 1) {
                createCustomerCLI();
            } else {
                readCustomerCLI();
            }
        }

    }

    public void publicationPage(){
        while (true) {
            System.out.println("Welcome to the Publication Page!");
            System.out.println("1.Create a Publication");
            System.out.println("2.Read a Publication");
            option = getValidIntegerInput(1, 2); // Allows only 1 or 2

            if (option == 1) {
                createPublicationCLI();
            } else {
                readPublicationCLI();
            }

        }
    }

    public void deliveryAreaPage(){
        while (true) {
            System.out.println("Welcome to the Delivery Area Page!");
            System.out.println("1.Create a Delivery Area");
            System.out.println("2.Read a Delivery Area");
            option = getValidIntegerInput(1, 2); // Allows only 1 or 2

            if (option == 1) {
                createDeliveryAreaCLI();
            } else{
                readDeliveryAreaCLI();
            }

        }
    }

    public void createDeliveryAreaCLI(){
        deliveryAreaValidation dv = new deliveryAreaValidation();
        System.out.println("Welcome to Create Delivery Area Page!");
        dv.checkAreaName();
        dv.checkAreaDescription();
        dv.insertDeliveryArea();
        System.out.println("Delivery Area Added successfully!");
        System.out.println("Taking you back to the main page!");
        mainPage();
    }

    public void readDeliveryAreaCLI(){
        deliveryAreaValidation da = new deliveryAreaValidation();

        while (true) {
            System.out.println("Welcome to Delivery Area Read!");
            System.out.println("1.Find a Specific ID");
            System.out.println("2.Display all Delivery Areas");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        da.deliveryAreaReadID();
                        return;
                    case 2:
                        da.deliveryAreaReadAll();
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

    public void createPublicationCLI(){
        publicationValidation pv = new publicationValidation();
        System.out.println("Welcome to Create Publication Page!");
        pv.checkPublicationName();
        pv.checkPublicationDescription();
        pv.checkPublicationPrice();
        pv.insertPublication();
        mainPage();
    }

    public void readPublicationCLI(){
        publicationValidation p = new publicationValidation();

        while (true) {
            System.out.println("Welcome to Publication Read!");
            System.out.println("1.Find a Specific ID");
            System.out.println("2.Display all Publications");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        p.publicationReadID();
                        return;
                    case 2:
                        p.publicationReadAll();
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


