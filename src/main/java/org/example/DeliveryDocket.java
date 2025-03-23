package org.example;

public class DeliveryDocket {
    public String date;
    public String deliveryManId;
    public String docketStatus;
    private DatabaseConnection databaseConnection;

    public DeliveryDocket() {
        this.databaseConnection = new DatabaseConnection();
    }

    // ------------------ validation ----------------

    public boolean deliveryManIdValidation(String deliveryManId) {
        if (deliveryManId == null || deliveryManId.isEmpty()) {
            throw new IllegalArgumentException("delivery Man ID cannot be empty");
        }

        try{
            int deliveryManIdInt = Integer.parseInt(deliveryManId);
            if (deliveryManIdInt < 1 ) {
                throw new IllegalArgumentException("Please enter a valid deliveryMan ID");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
        return true;
    }

    public boolean docketStatusValidation(String docketStatus) {
        if (docketStatus == null || docketStatus.isEmpty()) {
            throw new IllegalArgumentException("Please make a selection");
        }
        try {
            int docketStatusInt = Integer.parseInt(docketStatus);
            if (docketStatusInt != 1 && docketStatusInt != 2 ) {
                throw new IllegalArgumentException("Please enter a valid docket status");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
        return true;
    }

    //

    // -------------------- setters&getters------------------
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(String deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public String getDocketStatus() {
        return docketStatus;
    }

    public void setDocketStatus(String docketStatus) {
        this.docketStatus = docketStatus;
    }

    public boolean validateDate(){
        return true;
    }

    public boolean validateDeliveryManId(){
        return true;
    }

    public boolean validateDocketStatus(){
        return true;
    }
}
