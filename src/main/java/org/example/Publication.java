package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Publication {
    private String name;
    private String description;
    private String price;

    public void setPrice(String price) {
        this.price = price;
    }

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

    public String getPrice(){
        return price;
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

    public boolean publicationReadByID(String id) {
        try {
            ArrayList<ArrayList<String>> publication = DBClass.selectPublication(Integer.parseInt(id));
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
            ArrayList<ArrayList<String>> allPublication = DBClass.selectAllPublication();
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

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertPublication(){
        DBClass db = new DBClass();
        try{
            db.insertPublication(publicationName, publicationDescription, Double.parseDouble(publicationPrice));
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
}
