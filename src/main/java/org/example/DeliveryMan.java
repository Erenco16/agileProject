package org.example;

public class DeliveryMan {

    public String name;
    public String employmentStatus;

    public DeliveryMan(String employmentStatus, String name) {
        this.employmentStatus = employmentStatus;
        this.name = name;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean validateName(){
        return true;
    }

    public boolean validateSetEmployeeStatus(){
        return true;
    }
}
