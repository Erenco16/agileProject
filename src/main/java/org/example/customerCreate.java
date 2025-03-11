package org.example;

public class customerCreate {
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String deliveryArea;
    private String eircode;

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() > 255 ) {
            throw new IllegalArgumentException("Name cannot exceed 255 characters");
        }
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@") || !email.contains(".com")) {
            throw new IllegalArgumentException("Invalid email format, must include '@' and '.com'");
        }
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (address.length() > 255) {
            throw new IllegalArgumentException("Address cannot exceed 255 characters");
        }
        return true;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number format, must be 10 digits");
        }
        return true;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean validateDeliveryArea(String deliveryArea) {
            if (deliveryArea == null || deliveryArea.isEmpty()) {
                throw new IllegalArgumentException("Delivery area cannot be empty");
            }
        try {
            int deliveryAreaInt = Integer.parseInt(deliveryArea);
            if (deliveryAreaInt <= 0) {
                throw new IllegalArgumentException("Delivery area must be a positive number");
            }
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number");
        }
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public boolean validateEircode(String eircode) {
        if (eircode == null || eircode.isEmpty()) {
            throw new IllegalArgumentException("Eircode cannot be empty");
        }
        if (eircode.length() != 7) {
            throw new IllegalArgumentException("Invalid Eircode format, must be 7 characters");
        }
        return true;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDeliveryArea() { return deliveryArea; }
    public String getEircode() { return eircode; }
}


