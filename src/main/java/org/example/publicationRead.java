package org.example;

import java.util.ArrayList;

public class publicationRead {

    public boolean publicationReadByID(String id) {
        try {
            ArrayList<ArrayList<String>> publication = DBClass.selectPublication(Integer.parseInt(id));
            if (publication.isEmpty()) {
                throw new IllegalArgumentException("No publication found with ID: " + id);
            } else {
                System.out.println("Publication found: " + publication);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        } catch (Exception e) {
            // Handle other exceptions (e.g., database connection issues, item no exist in DB)
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }

    public boolean selectAllPublication() {
        try{
            ArrayList<ArrayList<String>> allPublication = DBClass.selectAllPublication();
            System.out.println("Displaying all Publications: " + allPublication);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error occurred: " + e.getMessage());
        }
        return true;
    }
}


