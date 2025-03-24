package org.example;

import java.util.Scanner;

import static org.example.Report.exportQueryResultToCSV;
import static org.example.Report.isValidCustomer;


public class Invoice {
    private static final String DB_PATH = "database_files/agile_project.db"; // Store inside project folder
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    private static final String OUTPUT_PATH = "src/main/resources/invoices/";

    public String total_payable_amount_due;

    public String getTotal_payable_amount_due() {
        return total_payable_amount_due;
    }

    public void setTotal_payable_amount_due(String total_payable_amount_due) {
        this.total_payable_amount_due = total_payable_amount_due;
    }

    public boolean validateOrderId(){
        return true;
    }

    public boolean validateTotalPayableAmountDue(){
        return true;
    }


    // report query method
    public void totalRevenueByCustomerReport() {
        Scanner input = new Scanner(System.in);
        int customerId;
        while (true) {
            try {
                System.out.print("Enter the Customer ID for the revenue report: ");
                customerId = Integer.parseInt(input.nextLine().trim());
                if (!isValidCustomer(customerId)) {
                    System.out.println("Invalid customer ID. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid customer ID.");
            }
        }

        String fname = "invoiceByCustomerID"+customerId+".csv";
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
                fname
        );
        System.out.println("Revenue by customer report generated.");
    }
}
