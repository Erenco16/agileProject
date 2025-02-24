package org.example;

import java.util.Scanner;

public class publicationValidation {

    private String publicationName;
    private String publicationDescription;
    private String publicationPrice;
    Scanner input = new Scanner(System.in);
    publicationCreate p = new publicationCreate();

    public void checkPublicationName(){
        while(true){
            System.out.println("Enter Publication Name: ");
            publicationName = input.nextLine().trim();
            try{
                p.setName(publicationName);
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
                p.setDescription(publicationDescription);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void checkPublicationPrice(){
        while(true){
            System.out.println("Enter Publication Price: ");
            publicationDescription = input.nextLine().trim();
            try{
                p.setPrice(Double.parseDouble(publicationPrice));
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }


}
