package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        new NewsAgentCreate().mainPage();
//        try (Connection conn = DatabaseConnection.getConnection()) {
//            System.out.println("Connected to SQLite successfully!");
//            new NewsAgentCreate().mainPage();
//        } catch (SQLException e) {
//            System.out.println("Connection failed: " + e.getMessage());
//        }
    }

}
