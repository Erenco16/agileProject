package org.example;

import java.sql.*;
import java.io.*;
import java.nio.file.*;

public class Report {

    private static final String DB_PATH = "database_files/agile_project.db"; // Store inside project folder
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    private static final String OUTPUT_PATH = "src/main/resources/reports/";

    // Use this method for connection inside Report.java
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void exportQueryResultToCSV(String sql, String outputFolder, String fileName) {
        Path outputPath = Paths.get(outputFolder, fileName);

        try (Connection conn = getConnection();  // <-- Now using local getConnection() method
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write CSV header
            for (int i = 1; i <= columnCount; i++) {
                writer.write(metaData.getColumnName(i));
                if (i < columnCount) writer.write(",");
            }
            writer.newLine();

            // Write each row
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    if (value != null) {
                        value = value.replace("\"", "\"\"");
                        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
                            value = "\"" + value + "\"";
                        }
                    }
                    writer.write(value != null ? value : "");
                    if (i < columnCount) writer.write(",");
                }
                writer.newLine();
            }

            System.out.println("CSV export completed: " + outputPath);

        } catch (SQLException | IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }

    public static void totalRevenueByMonthReport(String month) {
        exportQueryResultToCSV("SELECT * FROM Customers", OUTPUT_PATH, "customers.csv");
    }
    public static void totalRevenueByDeliveryAreaReport(String deliveryArea) {
        exportQueryResultToCSV("SELECT * FROM Customers", OUTPUT_PATH, "customers.csv");
    }
    public static void totalRevenueByCustomerReport(int customerId) {
        exportQueryResultToCSV("SELECT * FROM Customers", OUTPUT_PATH, "customers.csv");
    }

    public static void main(String[] args) {
        // Example usage (make sure to set a real folder):
        exportQueryResultToCSV("SELECT * FROM Customers", OUTPUT_PATH, "customers.csv");
    }

}
