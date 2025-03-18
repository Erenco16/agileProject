package org.example;

public class Order {

    public String custId;
    public String pubId;
    public String quantity;
    public String date;
    public String orderStatus;

    public Order(String custId, String date, String orderStatus, String pubId, String quantity) {
        this.custId = custId;
        this.date = date;
        this.orderStatus = orderStatus;
        this.pubId = pubId;
        this.quantity = quantity;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean validateCustId(){
        return true;
    }

    public boolean validatePubId(){
        return true;
    }

    public boolean validateQuantity(){
        return true;
    }

    public boolean validateDate(){
        return true;
    }

    public boolean validateOrderStatus(){
        return true;
    }
}
