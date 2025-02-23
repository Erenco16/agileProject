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


    // inserting a delivery area into the db
    public void insertDeliveryArea(){
        try{
            DBClass.insertDeliveryArea(areaName, areaDescription);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // select delivery area function
    public void selectDeliveryArea(int id){
        try{
            DBClass.selectDeliveryArea(id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // select delivery area function
    public void selectAllDeliveryArea(){
        try{
            DBClass.selectAllDeliveryArea();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
