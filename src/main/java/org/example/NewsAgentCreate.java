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
                    case 4:
                        System.out.println("taking you to order page.....");
                        orderPage();
                        break;
                    case 5:
                        System.out.println("taking you to invoice page.....");
                        invoicePage();
                        break;
                    case 6:
                        System.out.println("taking you to delivery man page......");
                        deliveryManPage();
                        break;
                    case 7:
                        System.out.println("taking you to delivery docket page.....");
                        deliveryDocketPage();
                        break;
                    case 8:
                        System.out.println("taking you to reports page.....");
                        reportPage();
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }

    public void checkForQuit(String userInput) {
        if (userInput.equalsIgnoreCase("!q")) {
            System.out.println("Returning to the main page...");
            mainPage();
        }
    }

    public int getValidIntegerInput(int min, int max) {
        while (true) {
            System.out.print("Enter your option: ");
            String userInput = input.nextLine().trim(); // Read input as a string

            checkForQuit(userInput);

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
        System.out.println("3. Delivery Area page");
        System.out.println("4. Order page");
        System.out.println("5. Invoice page");
        System.out.println("6. Delivery Man page");
        System.out.println("7. Delivery Docket page");
        System.out.println("8. Report page ");
        option = getValidIntegerInput(1, 8);

        return option;
    }

    public void customerPage(){
        while (true) {
            System.out.println("Welcome to Create Customer!");
            System.out.println("1.Create a Customer");
            System.out.println("2.Read a Customer");
            System.out.println("3.Update a Customer");
            System.out.println("4.Delete a Customer");
            option = getValidIntegerInput(1, 4); // Allows only 1 to 4

            if (option == 1) {
                createCustomerCLI();
            } else if (option==2) {
                readCustomerCLI();
            } else if (option==3) {
                updateCustomerCLI();
            } else if (option==4) {
                deleteCustomerCLI();
            } else {
                System.out.println("Invalid option");
            }
        }

    }

    public void createCustomerCLI(){
        Customer v = new Customer();

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
        Customer v = new Customer();

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
                        mainPage();
                        return;
                    case 2:
                        v.customerReadAll();
                        mainPage();
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

    public void updateCustomerCLI(){
        Customer c =  new Customer();
        c.customerUpdate();
        c.checkName();
        c.checkEmail();
        c.checkAddress();
        c.checkPhoneNumber();
        c.checkDeliveryArea();
        c.checkEircode();
        c.updateCustomerDB();
        mainPage();
    }

    public void deleteCustomerCLI(){
        Customer c =  new Customer();
        c.customerDelete();
        mainPage();
    }

    public void updateDeliveryAreaCLI(){
        DeliveryArea da = new DeliveryArea();
        da.deliveryAreaUpdate();
        da.checkAreaName();
        da.checkAreaDescription();
        da.updatedeliveryAreaDB();
        mainPage();
    }

    public void deleteDeliveryAreaCLI(){
        DeliveryArea da = new DeliveryArea();
        da.deliveryAreaDelete();
        mainPage();
    }

    public void updatePublicationCLI(){
        Publication p = new Publication();
        p.publicationUpdate();
        p.checkPublicationName();
        p.checkPublicationDescription();
        p.checkPublicationPrice();
        p.checkPublicationStock();
        p.updatePublicationDB();
        mainPage();
    }

    public void deletePublicationCLI(){
        Publication p =new Publication();
        p.publicationDelete();
        mainPage();
    }

    public void publicationPage(){
        while (true) {
            System.out.println("Welcome to the Publication Page!");
            System.out.println("1.Create a Publication");
            System.out.println("2.Read a Publication");
            System.out.println("3.Update a Publication");
            System.out.println("4.Delete a Publication");
            option = getValidIntegerInput(1, 4); // Allows only 1 or 2

            if (option == 1) {
                createPublicationCLI();
            } else if (option == 2){
                readPublicationCLI();
            } else if (option == 3) {
                updatePublicationCLI();
            } else if (option == 4) {
                deletePublicationCLI();
            }

        }
    }

    public void deliveryAreaPage(){
        while (true) {
            System.out.println("Welcome to the Delivery Area Page!");
            System.out.println("1.Create a Delivery Area");
            System.out.println("2.Read a Delivery Area");
            System.out.println("3.Update a Delivery Area");
            System.out.println("4.Delete a Delivery Area");
            option = getValidIntegerInput(1, 4); // Allows only 1 or 2

            if (option == 1) {
                createDeliveryAreaCLI();
            } else if (option == 2){
                readDeliveryAreaCLI();
            } else if (option == 3){
                updateDeliveryAreaCLI();
            } else if (option == 4) {
                deleteDeliveryAreaCLI();
            }

        }
    }

    public void orderPage(){
        while (true) {
            System.out.println("Welcome to the Order Page!");
            System.out.println("1.Create an Order");
            System.out.println("2.Read an Order");
            System.out.println("3.Update an Order");
            System.out.println("4.Delete an Order");
            option = getValidIntegerInput(1, 4); // Allows only 1 or 2
            if (option == 1) {
                createOrderCLI();
            } else if(option==2) {
                readOrderCLI();
            }
            else if(option==3) {
                updateOrderCLI();
            }
            else if(option==4) {
                deleteOrderCLI();
            }
            else {
                // If input is not an integer
                System.out.println("Invalid input! Please enter a valid number (1-4).");
                input.nextLine();
            }
        }

    }

    private void deleteOrderCLI() {
        Order o = new Order();
        o.deleteOrder();
        mainPage();
    }

    private void updateOrderCLI() {
        Order o = new Order();
        o.updateOrder();
        mainPage();
    }

    private void readOrderCLI() {
        Order o = new Order();
        while (true) {
            System.out.println("Welcome to Order Read!");
            System.out.println("1.Find an Order with ID");
            System.out.println("2.Display all Orders");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        o.orderReadById();
                        mainPage();
                        return;
                    case 2:
                        o.orderReadAll();
                        mainPage();
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

    private void createOrderCLI() {
        Order o = new Order();
        o.checkCustId();
        o.checkPubId();
        o.checkQuantity();
        o.checkStatus();
        o.insertOrder();
        System.out.println("Taking you back to the main page.");
        mainPage();
    }

    public void invoicePage(){
        while (true) {
            System.out.println("Welcome to the Invoice Page!");
            System.out.println("1.Generate an Invoice for a Customer");
            option = getValidIntegerInput(1,1);
            if (option == 1){
                generateInvoiceCLI();
            }
        }

    }

    public void generateInvoiceCLI(){
        Invoice i = new Invoice();
        i.totalRevenueByCustomerReport();
    }


    //integrate this part as well, ensure all is working

    public void deliveryManPage(){
        while (true) {
            System.out.println("Welcome to the Delivery Man Page!");
            System.out.println("1.Create a Delivery Man");
            System.out.println("2.Read a Delivery Man");
            System.out.println("3.Update a Delivery Man");
            System.out.println("4.Delete a Delivery Man");
            option = getValidIntegerInput(1, 4); // Allows only 1 or 2

            if (option == 1){
                createDeliveryManCLI();
            } else if (option == 2){
                readDeliveryManCLI();
            } else if (option == 3){
                updateDeliveryManCLI();
            } else if (option == 4) {
                deleteDeliveryManCLI();
            } else {
                // If input is not an integer
                System.out.println("Invalid input! Please enter a valid number (1-4).");
                input.nextLine();
            }

        }

    }

    public void createDeliveryManCLI(){
        DeliveryMan dm = new DeliveryMan();
        dm.checkName();
        dm.checkEmploymentStatus();
        dm.insertDeliveryMan();
        System.out.println("Taking you back to the main page.");
        mainPage();

    }

    public void readDeliveryManCLI(){
        DeliveryMan dm = new DeliveryMan();
        while (true) {
            System.out.println("Welcome to Delivery Man Read!");
            System.out.println("1.Find a Delivery Man with ID");
            System.out.println("2.Display all Delivery Man");
            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        dm.deliveryManReadById();
                        mainPage();
                        return;
                    case 2:
                        dm.deliveryManReadAll();
                        mainPage();
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

    public void updateDeliveryManCLI(){
        DeliveryMan dm = new DeliveryMan();
        dm.updateDeliveryMan();
        mainPage();
    }

    public void deleteDeliveryManCLI(){
        DeliveryMan dm = new DeliveryMan();
        dm.deleteDeliveryMan();
        mainPage();
    }

    public void deliveryDocketPage(){
        while (true) {
            System.out.println("Welcome to the Delivery Docket Page!");
            System.out.println("1.Create a Delivery Docket");
            System.out.println("2.Read a Delivery Docket");
            System.out.println("3.Update a Delivery Docket");
            System.out.println("4.Delete a Delivery Docket");
            option = getValidIntegerInput(1, 4); // Allows only 1 or 2

            if (option == 1){
                createDeliveryDocketCLI();
            } else if (option == 2){
                readDeliveryDocketCLI();
            }else if (option == 3){
                updateDeliveryDocketCLI();
            }else if (option == 4){
                deleteDeliveryDocketCLI();
            }else {
                System.out.println("Invalid input! Please enter a valid number (1-4).");
            }


        }

    }

    public void createDeliveryDocketCLI(){
        DeliveryDocket dd = new DeliveryDocket();

    }

    public void readDeliveryDocketCLI(){}

    public void updateDeliveryDocketCLI(){}

    public void deleteDeliveryDocketCLI(){}


    public void reportPage(){
        while (true) {
            System.out.println("Welcome to the Report Page!");
            System.out.println("1.View total amount of revenue by month");
            System.out.println("2.View total amount of revenue by delivery area");
            System.out.println("3.View total amount of revenue by publication");
            option = getValidIntegerInput(1, 3);

            if (option == 1){
                revenueByMonthCLI();
            } else if (option == 2){
                revenueByDeliveryAreaCLI();
            } else if (option == 3) {
                revenueByPublicationCLI();
            } else {
                System.out.println("Invalid input! Please enter a valid number (1-3).");
            }

        }

    }

    public void revenueByMonthCLI(){
        Report r = new Report();
        r.totalRevenueByMonthReport();
        mainPage();
    }

    public void revenueByDeliveryAreaCLI(){
        Report r = new Report();
        r.totalRevenueByDeliveryAreaReport();
        mainPage();
    }

    public void revenueByPublicationCLI(){
        Report r = new Report();
        r.totalRevenueByPublicationReport();
        mainPage();
    }

    public void createDeliveryAreaCLI(){
        DeliveryArea dv = new DeliveryArea();
        System.out.println("Welcome to Create Delivery Area Page!");
        dv.checkAreaName();
        dv.checkAreaDescription();
        dv.insertDeliveryArea();
        System.out.println("Delivery Area Added successfully!");
        System.out.println("Taking you back to the main page!");
        mainPage();
    }

    public void readDeliveryAreaCLI(){
        DeliveryArea da = new DeliveryArea();

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
                        mainPage();
                        return;
                    case 2:
                        da.deliveryAreaReadAll();
                        mainPage();
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
        Publication pv = new Publication();
        System.out.println("Welcome to Create Publication Page!");
        pv.checkPublicationName();
        pv.checkPublicationDescription();
        pv.checkPublicationPrice();
        pv.checkPublicationStock();
        pv.insertPublication();
        mainPage();
    }

    public void readPublicationCLI(){
        Publication p = new Publication();

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
                        mainPage();
                        return;
                    case 2:
                        p.publicationReadAll();
                        mainPage();
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


