package org.example;

public class publicationCreate {
    private String name;
    private String description;
    private double price;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    // Getter 方法
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice(){
        return price;
    }

    public boolean ValidName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Publication name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid publication name, valid name is between 1-255 characters");
        }
        return true;
    }

    public boolean ValidDescription(String description){
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Publication description cannot be empty");
        }
        if (description.length() > 1024) {
            throw new IllegalArgumentException("Invalid publication description, valid description is between 1-1024 characters");
        }
        return true;

    }

    public boolean ValidPrice(double price){
        String priceStr = String.valueOf(price).replace(".", "");
        if (priceStr.isEmpty() || priceStr.length() > 255) {
            throw new IllegalArgumentException("Price must be between 1-255 digits long");
        }
        return true;
    }

}


