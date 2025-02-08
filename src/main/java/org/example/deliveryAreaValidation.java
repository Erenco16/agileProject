package org.example;
import java.util.Scanner;

public class deliveryAreaValidation {

    private String areaName;
    private String areaDescription;
    Scanner input = new Scanner(System.in);
    deliveryCreate d = new deliveryCreate();

    public void checkAreaName(){

        while(true){

            System.out.println("Enter Delivery Area: ");
            areaName = input.nextLine().trim();
            try{
                d.setName(areaName);
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
                d.setDescription(areaDescription);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }


        }


    }


}
