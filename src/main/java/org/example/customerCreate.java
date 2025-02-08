package org.example;

public class customerCreate {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String deliveryArea;
    private String eircode;

    public customerCreate(String name, String email, String address, String phoneNumber, String deliveryArea, String eircode) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email ==null || !email.contains("@") || !email.contains(".com")) {
            throw new IllegalArgumentException("Invalid email format, must include @ and .com");
        }

        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number format, number must be 10 digits");
        }
        if (deliveryArea == null || deliveryArea.isEmpty()) {
            throw new IllegalArgumentException("Delivery area cannot be empty");
        }
        if (eircode == null || eircode.isEmpty()) {
            throw new IllegalArgumentException("Eircode cannot be empty");
        }
        if(eircode.length() != 7) {
            throw new IllegalArgumentException("Invalid Eircode format, must be 7 letters");
        }

        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryArea = deliveryArea;
        this.eircode = eircode;
    }

    public customerCreate() {

    }

    public void test(){
        System.out.println("hello world");
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDeliverArea() { return deliveryArea; }
    public String getEircode() { return eircode; }
}
