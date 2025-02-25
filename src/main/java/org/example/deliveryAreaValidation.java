package org.example;
import java.util.Scanner;

public class deliveryAreaValidation {

    private String areaName;
    private String areaDescription;
    Scanner input = new Scanner(System.in);
    deliveryCreate d = new deliveryCreate();
    deliveryAreaRead dr = new deliveryAreaRead();

    public void checkAreaName(){

        while(true){

            System.out.println("Enter Delivery Area: ");
            areaName = input.nextLine().trim();
            try{
                d.validName(areaName);
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
                d.validDescription(areaDescription);
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
                dr.deliveryReadByID(String.valueOf(id));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());            }
        }
    }

    public void deliveryAreaReadAll() {
        dr.selectAllDeliveryArea();
    }

    // currently the deliveryArea is a string value but both the db and its
    // insert function are accepting int values
    public void insertDeliveryArea() {
        DBClass db = new DBClass();
        try{
            db.insertDeliveryArea(areaName, areaDescription);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
