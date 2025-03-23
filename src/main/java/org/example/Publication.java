package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Publication {
    private String name;
    private String description;
    private String price;
    private String stock;
    private DatabaseConnection databaseConnection;
    private String inputID;

    // Constructor to initialize the DatabaseConnection
    public Publication() {
        this.databaseConnection = new DatabaseConnection();
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void stock(String stock){
        this.stock = stock;
    }

    // Getter 方法
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return price;
    }

    public String getStock(){
        return stock;
    }

    public boolean validName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Publication name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid publication name, valid name is between 1-255 characters");
        }
        return true;
    }

    public boolean validDescription(String description){
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Publication description cannot be empty");
        }
        if (description.length() > 1024) {
            throw new IllegalArgumentException("Invalid publication description, valid description is between 1-1024 characters");
        }
        return true;

    }

    public boolean validPrice(String price){

        try{
            double dnum = Double.parseDouble(price);
            if (dnum < 0){
                throw new IllegalArgumentException("Price can not be a negative number!");
            }
//            if(price.isEmpty() || price == null){
//                throw new IllegalArgumentException("Price must not be empty");
//            }
            if(price.length() > 255 ){
                throw new IllegalArgumentException("Price must be between 1-255 digits long");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Price must be a digit value");
        }
        return true;
    }

    public boolean validStock(String stock){

        try{
            int dnum = Integer.parseInt(stock);
            if (dnum < 0){
                throw new IllegalArgumentException("Stock can not be a negative number!");
            }
//            if(price.isEmpty() || price == null){
//                throw new IllegalArgumentException("Price must not be empty");
//            }
            if(stock.length() > 255 ){
                throw new IllegalArgumentException("Stock must be between 1-255 digits long");
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Stock must be an integer value");
        }
        return true;
    }

    public boolean publicationReadByID(String id) {
        try {
            ArrayList<ArrayList<String>> publication = databaseConnection.selectPublication(Integer.parseInt(id));
            if (publication.isEmpty()) {
                throw new IllegalArgumentException("No publication found with ID: " + id);
            } else {
                System.out.println("Publication found: " + publication);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public boolean selectAllPublication() {
        try{
            ArrayList<ArrayList<String>> allPublication = databaseConnection.selectAllPublication();
            System.out.println("Displaying all Publications: " + allPublication);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    private String publicationName;
    private String publicationDescription;
    private String publicationPrice;
    private String publicationStock;

    Scanner input = new Scanner(System.in);

    public void checkPublicationName(){
        while(true){
            System.out.println("Enter Publication Name: ");
            publicationName = input.nextLine().trim();
            try{
                validName(publicationName);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkPublicationDescription(){
        while(true){
            System.out.println("Enter Publication Description: ");
            publicationDescription = input.nextLine().trim();
            try{
                validDescription(publicationDescription);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkPublicationPrice(){
        while(true){
            System.out.println("Enter Publication Price: ");
            publicationPrice = input.nextLine();
            try {
                validPrice(publicationPrice);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkPublicationStock(){
        while(true){
            System.out.println("Enter Publication Stock: ");
            publicationStock = input.nextLine();
            try {
                validStock(publicationStock);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertPublication(){
        try{
            databaseConnection.insertPublication(publicationName, publicationDescription, Double.parseDouble(publicationPrice), Integer.parseInt(publicationStock));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // read by id function
    public void publicationReadID() {
        while(true){
            System.out.println("Enter Publication ID: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                publicationReadByID(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    // select all
    public void publicationReadAll() {
        selectAllPublication();
    }


    public void publicationUpdate() {
        while(true){
            System.out.println("Enter publication ID to update: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                publicationReadByID(String.valueOf(id));
                inputID = String.valueOf(id);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    public void updatePublicationDB() {
        try {
            databaseConnection.updatePublication(Integer.parseInt(inputID), publicationName,  publicationDescription, Double.parseDouble(publicationPrice), Integer.parseInt(publicationStock)) ;
            System.out.println("Publication update successful, returning to main page");
        } catch (IllegalArgumentException e)    {
            System.out.println(e.getMessage());
        }
    }

    //--------------------- delete ---------------------
    public void publicationDelete() {
        while(true){
            System.out.println("Enter deliveryArea ID to delete: ");
            try {
                int id = input.nextInt();
                input.nextLine();
                publicationReadByID(String.valueOf(id));
                System.out.println("Deleting data related to ID: " + id);
                databaseConnection.deletePublication(id);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }


}
