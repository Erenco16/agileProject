package org.example;

import java.sql.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Report {

    private static final String DB_PATH = "database_files/agile_project.db"; // Store inside project folder
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    private static final String OUTPUT_PATH = "src/main/resources/reports/";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void exportQueryResultToCSV(String sql, String outputFolder, String fileName) {
        Path outputPath = Paths.get(outputFolder, fileName);

        try (Connection conn = getConnection();
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

    public static void totalRevenueByMonthReport(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        exportQueryResultToCSV(
                "SELECT order_month, ROUND(SUM(total_payable_amount),2) AS total_amount FROM (" +
                        "SELECT strftime('%m', o.order_date) + 0 AS order_month, i.total_payable_amount " +
                        "FROM Orders o LEFT JOIN Invoice i ON o.id = i.order_id) " +
                        "WHERE order_month = " + month + " GROUP BY order_month",
                OUTPUT_PATH,
                "revenueByMonth.csv"
        );
    }

    public static void totalRevenueByDeliveryAreaReport(int deliveryArea) {
        if (!isValidDeliveryArea(deliveryArea)) {
            throw new IllegalArgumentException("Invalid delivery area: " + deliveryArea);
        }
        exportQueryResultToCSV(
                "SELECT\n" +
                        "delivery_area,\n" +
                        "ROUND(SUM(total_payable_amount), 2) AS total_revenue\n" +
                        "FROM (\n" +
                        "SELECT\n" +
                        "da.name AS delivery_area,\n" +
                        "i.total_payable_amount\n" +
                        "FROM Orders o\n" +
                        "LEFT JOIN Invoice i ON o.id = i.order_id\n" +
                        "LEFT JOIN Customers c ON o.cust_id = c.id\n" +
                        "LEFT JOIN DeliveryArea da ON c.delivery_area_id = da.id\n" +
                        "WHERE da.id = " + deliveryArea + ")\n" +
                        "GROUP BY delivery_area",
                OUTPUT_PATH,
                "revenueByDeliveryArea.csv"
        );
    }

    public static void totalRevenueByCustomerReport(int customerId) {
        if (!isValidCustomer(customerId)) {
            throw new IllegalArgumentException("Invalid customer ID: " + customerId);
        }
        exportQueryResultToCSV(
                "SELECT\n" +
                        "customer_name,\n" +
                        "ROUND(SUM(total_payable_amount), 2) AS total_revenue\n" +
                        "FROM (\n" +
                        "SELECT\n" +
                        "c.name AS customer_name,\n" +
                        "i.total_payable_amount\n" +
                        "FROM Orders o\n" +
                        "LEFT JOIN Invoice i ON o.id = i.order_id\n" +
                        "LEFT JOIN Customers c ON o.cust_id = c.id\n" +
                        "WHERE c.id = " + customerId + ")\n" +
                        "GROUP BY customer_name",
                OUTPUT_PATH,
                "revenueByCustomer.csv"
        );
    }

    public static boolean isValidDeliveryArea(int deliveryArea) {
        ArrayList<ArrayList<String>> values = selectDeliveryArea(deliveryArea);
        return values.stream().anyMatch(row -> row.get(0).equals(String.valueOf(deliveryArea)));
    }

    public static boolean isValidCustomer(int customerId) {
        ArrayList<ArrayList<String>> values = selectCustomers(customerId);
        return values.stream().anyMatch(row -> row.get(0).equals(String.valueOf(customerId)));
    }

    public static ArrayList<ArrayList<String>> getValues(ResultSet rs) throws SQLException {
        ArrayList<ArrayList<String>> values = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            ArrayList<String> rowValues = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                rowValues.add(rs.getString(i));
            }
            values.add(rowValues);
        }
        return values;
    }

    public static ArrayList<ArrayList<String>> selectCustomers(int id) {
        String sql = "SELECT * FROM Customers WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from Customers: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select a record from the DeliveryArea table by id.
    public static ArrayList<ArrayList<String>> selectDeliveryArea(int id) {
        String sql = "SELECT * FROM DeliveryArea WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from DeliveryArea: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        totalRevenueByMonthReport(3);
        totalRevenueByDeliveryAreaReport(2);
        totalRevenueByCustomerReport(2);
    }
}
