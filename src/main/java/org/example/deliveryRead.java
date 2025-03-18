package org.example;

import java.util.ArrayList;

public class deliveryRead {

    /**
     * Reads a specific delivery area based on the provided ID.
     * Verifies that the delivery area exists and displays its details.
     *
     * @param id the delivery area ID as a string.
     * @return true if the delivery area is successfully retrieved.
     */
    public boolean selectDeliveryAreaMod(String id) {
        try {
            // Assume DBClass.selectDeliveryArea returns an ArrayList of ArrayLists of Strings
            // representing delivery area details, or an empty list if not found.
            ArrayList<ArrayList<String>> deliveryArea = DBClass.selectDeliveryArea(Integer.parseInt(id));

            if (deliveryArea == null || deliveryArea.isEmpty()) {
                throw new IllegalArgumentException("No delivery area found with ID: " + id);
            } else {
                System.out.println("Delivery area found: " + deliveryArea);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        } catch (Exception e) {
            // Handle other exceptions such as database connection issues
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    /**
     * Reads all delivery areas.
     * Verifies that a valid delivery area list is provided (i.e., is not null)
     * and displays the delivery area details (which should match the database records).
     *
     * @return true if the operation is successful.
     */
    public boolean selectAllDeliveryAreaMod() {
        try {
            ArrayList<ArrayList<String>> allDeliveryAreas = DBClass.selectAllDeliveryAreas();

            if (allDeliveryAreas == null) {
                throw new IllegalArgumentException("Delivery area list is null");
            } else {
                System.out.println("Displaying all delivery areas: " + allDeliveryAreas);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    /**
     * Retrieves a specific delivery area by its ID without returning a status.
     *
     * @param id the delivery area ID.
     */
    public void selectDeliveryArea(int id) {
        try {
            DBClass.selectDeliveryArea(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves all delivery areas without returning a status.
     */
    public void selectAllDeliveryArea() {
        try {
            DBClass.selectAllDeliveryAreas();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
