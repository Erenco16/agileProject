package org.example;

import java.util.ArrayList;

public class deliveryAreaRead {
    
    public boolean deliveryReadByID(String id) {
        try {
            ArrayList<ArrayList<String>> deliveryArea = DBClass.selectDeliveryArea(Integer.parseInt(id));
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
            ArrayList<ArrayList<String>> allDeliveryArea = DBClass.selectAllDeliveryArea();
            System.out.println("Displaying all delivery areas: " + allDeliveryArea);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }
}
