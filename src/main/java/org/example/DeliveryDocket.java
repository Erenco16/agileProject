package org.example;

public class DeliveryDocket {
    public String date;
    public String deliveryManId;
    public String docketStatus;

    public DeliveryDocket(String date, String deliveryManId, String docketStatus) {
        this.date = date;
        this.deliveryManId = deliveryManId;
        this.docketStatus = docketStatus;
    }

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
