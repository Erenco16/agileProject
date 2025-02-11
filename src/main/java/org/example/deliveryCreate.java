package org.example;

public class deliveryCreate {
    private String name;
    private String description;

    /**
     * 构造方法
     * @param name 配送区域名称，不能为空且长度在1-255个字符之间
     * @param description 配送区域描述，不能为空且长度在1-1024个字符之间
     */
    public void setName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Delivery area name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid delivery area name, valid name is between 1-255 characters");
        }
        this.name = name;
    }

    public void setDescription(String description){
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Delivery area description cannot be empty");
        }
        if (description.length() > 1024) {
            throw new IllegalArgumentException("Invalid delivery area description, valid description is between 1-1024 characters");
        }

        this.description = description;
    }

    /*public deliveryCreate(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Delivery area name cannot be empty");
        }
        if (name.length() > 255) {
            throw new IllegalArgumentException("Invalid delivery area name, valid name is between 1-255 characters");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Delivery area description cannot be empty");
        }
        if (description.length() > 1024) {
            throw new IllegalArgumentException("Invalid delivery area description, valid description is between 1-1024 characters");
        }
        this.name = name;
        this.description = description;
    }
*/
    // Getter 方法
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}



