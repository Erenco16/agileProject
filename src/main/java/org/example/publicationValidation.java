package org.example;

import java.util.Scanner;

public class publicationValidation {

    private String publicationName;
    private String publicationDescription;
    private String publicationPrice;
    Scanner input = new Scanner(System.in);
    publicationCreate p = new publicationCreate();
    publicationRead pr = new publicationRead();

    public void checkPublicationName(){
        while(true){
            System.out.println("Enter Publication Name: ");
            publicationName = input.nextLine().trim();
            try{
                p.validName(publicationName);
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
                p.validDescription(publicationDescription);
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
                p.validPrice(publicationPrice);
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
                pr.publicationReadByID(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    // select all
    public void publicationReadAll() {
        pr.selectAllPublication();
    }

}
