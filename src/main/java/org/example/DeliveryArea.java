package org.example;

public class DeliveryArea {
    public String name ="";
    public boolean testName(){
        if (name.length()>255|| name.isEmpty()){
            return false;

        }
        else
        return true;
    }

}
