package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class DeliveryArea {
    private String name;
    private String description;
    private DatabaseConnection databaseConnection;

    // Constructor to initialize the DatabaseConnection
    public DeliveryArea() {
        this.databaseConnection = new DatabaseConnection();
    }


    /**
     * 构造方法
     * @param name 配送区域名称，不能为空且长度在1-255个字符之间
     */

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    // Getter 方法
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean validName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Delivery area name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid delivery area name, valid name is between 1-255 characters");
        }
        return true;
    }



    public boolean validDescription(String description){
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Delivery area description cannot be empty");
        }
        if (description.length() > 1024) {
            throw new IllegalArgumentException("Invalid delivery area description, valid description is between 1-1024 characters");
        }

        return true;
    }

    public boolean deliveryReadByID(String id) {
        try {
            ArrayList<ArrayList<String>> deliveryArea = databaseConnection.selectDeliveryArea(Integer.parseInt(id));
            if (deliveryArea.isEmpty()) {
                throw new IllegalArgumentException("No delivery area found with ID: " + id);
            } else {
                System.out.println("Delivery area found: " + deliveryArea);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public boolean selectAllDeliveryArea() {
        try{
            ArrayList<ArrayList<String>> allDeliveryArea = databaseConnection.selectAllDeliveryArea();
            System.out.println("Displaying all delivery areas: " + allDeliveryArea);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    private String areaName;
    private String areaDescription;
    Scanner input = new Scanner(System.in);

    public void checkAreaName(){

        while(true){

            System.out.println("Enter Delivery Area: ");
            areaName = input.nextLine().trim();
            try{
                validName(areaName);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }

    public void checkAreaDescription(){

        while(true){

            System.out.println("Enter Delivery Area Description: ");
            areaDescription = input.nextLine().trim();
            try{
                validDescription(areaDescription);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }


    public void deliveryAreaReadID() {
        while(true){
            System.out.println("Enter Delivery Area ID: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                deliveryReadByID(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    public void deliveryAreaReadAll() {selectAllDeliveryArea();
    }

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertDeliveryArea() {
        try{
            databaseConnection.insertDeliveryArea(areaName, areaDescription);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
