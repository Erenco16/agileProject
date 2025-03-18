package org.example;

public class Invoice {

    public String orderid;
    public String total_payable_amount_due;

    public Invoice(String orderid, String total_payable_amount_due) {
        this.orderid = orderid;
        this.total_payable_amount_due = total_payable_amount_due;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTotal_payable_amount_due() {
        return total_payable_amount_due;
    }

    public void setTotal_payable_amount_due(String total_payable_amount_due) {
        this.total_payable_amount_due = total_payable_amount_due;
    }

    public boolean validateOrderId(){
        return true;
    }

    public boolean validateTotalPayableAmountDue(){
        return true;
    }
}
