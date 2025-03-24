package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryDocket {
    public String date;
    public String deliveryManId;
    public String docketStatus;
    private DatabaseConnection databaseConnection;

    public DeliveryDocket() {
        this.databaseConnection = new DatabaseConnection();
    }

    Scanner input = new Scanner(System.in);

    // ------------------ validation ----------------

    public boolean deliveryManIdValidation(String deliveryManId) {
        if (deliveryManId == null || deliveryManId.isEmpty()) {
            throw new IllegalArgumentException("delivery Man ID cannot be empty");
        }
        try{
            int deliveryManIdInt = Integer.parseInt(deliveryManId);
            ArrayList<ArrayList<String>> dm = databaseConnection.selectDeliveryMan(deliveryManIdInt);

            if (deliveryManIdInt < 1 ) {
                throw new IllegalArgumentException("Please enter a valid deliveryMan ID");
            } else if (dm.isEmpty()) {
                throw new IllegalArgumentException("No Delivery Man found with ID: " + deliveryManIdInt);
            } else {
                System.out.println("Delivery Man found with id: " + deliveryManIdInt);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
        return true;
    }

    public boolean docketStatusValidation(String docketStatus) {
        if (docketStatus == null || docketStatus.isEmpty()) {
            throw new IllegalArgumentException("Please make a selection");
        }
        try {
            int docketStatusInt = Integer.parseInt(docketStatus);
            if (docketStatusInt != 1 && docketStatusInt != 2 && docketStatusInt != 3 && docketStatusInt != 4 ) {
                throw new IllegalArgumentException("Please enter a valid docket status");
            } else {
                switch (docketStatusInt) {
                    case 1:
                        this.docketStatus = "Pending";
                        break;
                    case 2:
                        this.docketStatus = "Completed";
                        break;
                    case 3:
                        this.docketStatus = "On Delivery";
                        break;
                    case 4:
                        this.docketStatus = "Cancelled";
                        break;
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
        return true;
    }

    public boolean selectDocketID(String docketID) {
        if (docketID == null || docketID.isEmpty()) {
            throw new IllegalArgumentException("Please make a selection");
        }
        try {
            boolean id = databaseConnection.DeliveryDocketRead(Integer.parseInt(docketID));
            if (!id) {
                throw new IllegalArgumentException("No delivery docket ID found");
            } else {
                ArrayList<ArrayList<String>> Docket = databaseConnection.selectDeliveryDocket(Integer.parseInt(docketID));
                System.out.println("Displaying Docket: " + Docket);
                System.out.println("Docket found! Printing docket to CSV...");
                databaseConnection.deliveryDocketToCsv(Integer.parseInt(docketID));
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public boolean selectDocketIdValidation(String docketID) {
        if (docketID == null || docketID.isEmpty()) {
            throw new IllegalArgumentException("Please make a selection");
        }
        try {
            boolean id = databaseConnection.DeliveryDocketRead(Integer.parseInt(docketID));
            if (!id) {
                throw new IllegalArgumentException("No delivery docket ID found");
            } else {
                inputId = Integer.parseInt(docketID);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    // -------------------- Create --------------------
    String inputStatus;
    String inputDeliveryManId;

    public void checkDeliveryManId() {
        while (true) {
            System.out.println("Please enter a deliveryMan ID:");
            inputDeliveryManId = input.nextLine().trim();
            try {
                deliveryManIdValidation(inputDeliveryManId);
                break;
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkDocketStatus() {
            while (true) {
                System.out.println("Please select docket status:\n1. Pending\n2. Completed\n3. On Delivery\n4. Cancelled");
                inputStatus = input.nextLine().trim();
                try {
                    docketStatusValidation(inputStatus);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    //------------------Read-----------------
    public void deliveryDocketReadId() {
        while(true){
            System.out.println("Enter delivery docket ID: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                selectDocketID(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    //------------------ update --------------------
    int inputId;

    public void checkDocketId() {
        while (true) {
            selectAllDocket();
            System.out.println("Please docket ID:");
            try {
                int id = input.nextInt();
                selectDocketIdValidation(String.valueOf(id));
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //--------------------delete----------------------
    public void docketDelete() {
        while(true){
            selectAllDocket();
            System.out.println("Enter docket ID to delete: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                docketStatusValidation(String.valueOf(id));
                System.out.println("Deleting data related to ID: " + id);
                databaseConnection.deleteDocket(id);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //---------------------DB Method ------------------
    public void insertDocketDB() {
        try{
            databaseConnection.insertDeliveryDocket(Integer.parseInt(inputDeliveryManId), docketStatus);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateDocketDB() {
        try {
            databaseConnection.updateDocket(inputId, Integer.parseInt(inputDeliveryManId), docketStatus);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean selectAllDocket() {
        try{
            ArrayList<ArrayList<String>> allDockets = databaseConnection.selectAllDeliveryDocket();
            System.out.println("Displaying all customers: " + allDockets);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    // -------------------- setters&getters------------------
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(String deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public String getDocketStatus() {
        return docketStatus;
    }

    public void setDocketStatus(String docketStatus) {
        this.docketStatus = docketStatus;
    }

    public boolean validateDate(){
        return true;
    }

    public boolean validateDeliveryManId(){
        return true;
    }

    public boolean validateDocketStatus(){
        return true;
    }
}
