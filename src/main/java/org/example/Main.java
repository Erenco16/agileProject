package org.example;

public class Main {
    public static void main(String[] args) {
        // 假设用户输入的数据
        String inputName = "Dublin Delivery";
        String inputDescription = "Delivery area covering Dublin and its surroundings.";

        // 创建 deliveryCreate 对象
        deliveryCreate delivery = new deliveryCreate(inputName, inputDescription);
        System.out.println("Delivery area created successfully!");
        System.out.println("Name: " + delivery.getName());
        System.out.println("Description: " + delivery.getDescription());
    }
}
