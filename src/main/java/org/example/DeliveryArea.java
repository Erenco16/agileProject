package org.example;

public class DeliveryArea {
    public String name ;
    public String description;

    public boolean testName(){
        if (name.length()>255|| name.isEmpty()){
            return false;
        }
        else
       return true;
    }
    public boolean testDescription(){
        if (description.length()>255|| description.isEmpty()){
            return false;
        }
        else
            return true;
    }




}
