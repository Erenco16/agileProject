package org.example;

public class customerCreate {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String deliveryArea;
    private String eircode;

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@") || !email.contains(".com")) {
            throw new IllegalArgumentException("Invalid email format, must include '@' and '.com'");
        }
        this.email = email;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number format, must be 10 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setDeliveryArea(String deliveryArea) {
        if (deliveryArea == null || deliveryArea.isEmpty()) {
            throw new IllegalArgumentException("Delivery area cannot be empty");
        }
        this.deliveryArea = deliveryArea;
    }

    public void setEircode(String eircode) {
        if (eircode == null || eircode.isEmpty()) {
            throw new IllegalArgumentException("Eircode cannot be empty");
        }
        if (eircode.length() != 7) {
            throw new IllegalArgumentException("Invalid Eircode format, must be 7 characters");
        }
        this.eircode = eircode;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDeliveryArea() { return deliveryArea; }
    public String getEircode() { return eircode; }
}
