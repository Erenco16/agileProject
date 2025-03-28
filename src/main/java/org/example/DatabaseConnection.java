package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private static final String DB_PATH = "database_files/agile_project.db"; // Store inside project folder
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    private static final String SQL_FILE_PATH = "src/main/resources/sql/setup.sql"; // Path to setup SQL file

    // Returns a connection to the SQLite database.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Initialize the database when the class is loaded
    static {
        try {
            // Ensure the database directory exists
            File dbDirectory = new File("database_files");
            if (!dbDirectory.exists()) {
                dbDirectory.mkdirs();
            }

            // Initialize database and run setup script
            try (Connection conn = getConnection()) {
                runSQLFile(conn, SQL_FILE_PATH);
                System.out.println("SQLite database initialized in: " + DB_PATH);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize SQLite: " + e.getMessage());
        }
    }

    // Run SQL file to set up the database schema
    public static void runSQLFile(Connection connection, String filePath) {
        File sqlFile = new File(filePath);

        // Check if the SQL file exists
        if (!sqlFile.exists()) {
            System.out.println("SQL file not found: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("--")) {
                    sql.append(line).append(" ");
                    // Check if we reached the end of a full statement
                    String currentSQL = sql.toString().trim();
                    if (currentSQL.endsWith(";") && (currentSQL.toUpperCase().endsWith("END;") || !currentSQL.toUpperCase().contains("CREATE TRIGGER"))) {
                        statement.execute(currentSQL);
                        sql.setLength(0);
                    }
                }
            }

            // Execute any remaining SQL that doesn't end with a semicolon (just in case)
            if (sql.length() > 0) {
                statement.execute(sql.toString());
            }

            System.out.println("SQL setup file executed successfully!");

        } catch (IOException | SQLException e) {
            System.out.println("Failed to execute SQL file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // General method to convert a ResultSet into an ArrayList of rows.
    public ArrayList<ArrayList<String>> getValues(ResultSet rs) throws SQLException {
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

    // --------------------- Customers Table Methods ---------------------

    // Insert a record into the Customers table.
    public void insertCustomer(String name, String email, String address, String phone_number, int deliveryAreaID, String eircode) {
        String sql = "INSERT INTO Customers (name, email, address, phone_number, delivery_area_id, eircode) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, address);
            pstmt.setString(4, phone_number);
            pstmt.setInt(5, deliveryAreaID);
            pstmt.setString(6, eircode);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted customer, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into Customers: " + e.getMessage());
        }
    }

    // Select a record from the Customers table by id.
    public ArrayList<ArrayList<String>> selectCustomers(int id) {
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

    // Select all records from the Customers table.
    public ArrayList<ArrayList<String>> selectAllCustomers() {
        String sql = "SELECT * FROM Customers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all Customers: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void updateCustomer(int id, String name, String email, String address, String phone_number, int deliveryAreaID, String eircode) {
        String sql = "UPDATE Customers SET name = ?, email = ?, address = ?, phone_number = ?, delivery_area_id = ?, eircode = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, address);
            pstmt.setString(4, phone_number);
            pstmt.setInt(5, deliveryAreaID);
            pstmt.setString(6, eircode);
            pstmt.setInt(7, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM Customers WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0; // returns true if the customer was deleted
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
        return false; // returns false if the deletion failed
    }

    // --------------------- Address Table Methods ---------------------

    // Insert a record into the Address table.
    public void insertAddress(String address, Integer deliveryAreaId) {
        String sql = "INSERT INTO Address (address, delivery_area_id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, address);
            if (deliveryAreaId != null) {
                pstmt.setInt(2, deliveryAreaId);
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted address, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into Address: " + e.getMessage());
        }
    }

    // Select a record from the Address table by id.
    public ArrayList<ArrayList<String>> selectAddress(int id) {
        String sql = "SELECT * FROM Address WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from Address: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select all records from the Address table.
    public ArrayList<ArrayList<String>> selectAllAddress() {
        String sql = "SELECT * FROM Address";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all Address: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- DeliveryArea Table Methods ---------------------

    // Insert a record into the DeliveryArea table.
    public void insertDeliveryArea(String name, String description) {
        String sql = "INSERT INTO DeliveryArea (name, description) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted delivery area, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into DeliveryArea: " + e.getMessage());
        }
    }

    // Select a record from the DeliveryArea table by id.
    public ArrayList<ArrayList<String>> selectDeliveryArea(int id) {
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

    // Select all records from the DeliveryArea table.
    public ArrayList<ArrayList<String>> selectAllDeliveryArea() {
        String sql = "SELECT * FROM DeliveryArea";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all DeliveryArea: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- NewsAgent Table Methods ---------------------

    // Insert a record into the NewsAgent table.
    public void insertNewsAgent(String name) {
        String sql = "INSERT INTO NewsAgent (name) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted news agent, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into NewsAgent: " + e.getMessage());
        }
    }

    // Select a record from the NewsAgent table by id.
    public ArrayList<ArrayList<String>> selectNewsAgent(int id) {
        String sql = "SELECT * FROM NewsAgent WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from NewsAgent: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select all records from the NewsAgent table.
    public ArrayList<ArrayList<String>> selectAllNewsAgent() {
        String sql = "SELECT * FROM NewsAgent";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all NewsAgent: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- Publication Table Methods ---------------------

    // Insert a record into the Publication table.
    public void insertPublication(String name, String description, double price, int stock) {
        String sql = "INSERT INTO Publication (name, description, price, stock_available) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted publication, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into Publication: " + e.getMessage());
        }
    }

    // Select a record from the Publication table by id.
    public ArrayList<ArrayList<String>> selectPublication(int id) {
        String sql = "SELECT * FROM Publication WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from Publication: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select all records from the Publication table.
    public ArrayList<ArrayList<String>> selectAllPublication() {
        String sql = "SELECT * FROM Publication";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all Publication: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- Orders Table Methods ---------------------

    // Insert a record into the Orders table.
    public void insertOrderStatus(int custId, int pubId, int quantity, String status) {
        String sql = "INSERT INTO Orders (cust_id, pub_id, quantity, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, custId);
            pstmt.setInt(2, pubId);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, status);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted order status, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into OrdersStatus: " + e.getMessage());
        }
    }

    // Select a record from the Orders table by id.
    public ArrayList<ArrayList<String>> selectOrdersStatus(int id) {
        String sql = "SELECT * FROM Orders WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting FROM Orders: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select all records from the Orders table.
    public ArrayList<ArrayList<String>> selectAllOrdersStatus() {
        String sql = "SELECT * FROM Orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all OrdersStatus: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- DeliveryMan Table Methods ---------------------
    public void insertDeliveryMan(String name, String employment_status) {
        String sql = "INSERT INTO DeliveryMan (name, employment_status) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, employment_status);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted delivery man, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into DeliveryMan: " + e.getMessage());
        }
    }

    public ArrayList<ArrayList<String>> selectDeliveryMan(int id) {
        String sql = "SELECT * FROM DeliveryMan WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from DeliveryMan: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> selectAllDeliveryMan() {
        String sql = "SELECT * FROM DeliveryMan";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all DeliveryMan: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // --------------------- DeliveryDocket Table Methods ---------------------
    public void insertDeliveryDocket(Integer delivery_man_id, String docket_status) {
        String sql = "INSERT INTO DeliveryDocket (delivery_man_id, docket_status) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, delivery_man_id, Types.INTEGER);
            pstmt.setString(2, docket_status);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted delivery docket, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into DeliveryDocket: " + e.getMessage());
        }
    }

    public ArrayList<ArrayList<String>> selectAllDeliveryDocket() {
        String sql = "SELECT * FROM DeliveryDocket";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting DeliveryDocket: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> selectDeliveryDocket(int id) {
        String sql = "SELECT * FROM DeliveryDocket WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all DeliveryDocket: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public boolean DeliveryDocketRead(int docketId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM DeliveryDocket WHERE id = ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the docketId parameter for the query
            pstmt.setInt(1, docketId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // The query returns '1' if the row exists, '0' if not
                    return rs.getInt(1) == 1;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if DeliveryDocket exists: " + e.getMessage());
        }

        // Return false if any exception occurs or no result found
        return false;
    }

    public void deliveryDocketToCsv(int docketId) {
        // Define the SQL query to select rows where the status is 'Pending'
        String sql = "SELECT o.id AS order_id, " +
                "c.name AS cust_name, " +
                "da.name AS delivery_area_name, " +
                "c.address, " +
                "p.name AS pub_name, " +
                "o.quantity, " +
                "o.status AS order_status " +
                "FROM Orders o " +
                "JOIN Customers c ON o.cust_id = c.id " +
                "JOIN DeliveryArea da ON c.delivery_area_id = da.id " +
                "JOIN Publication p ON o.pub_id = p.id " +
                "WHERE o.status = 'Pending'";

        // Path for the CSV file, named based on docketId
        String csvFilePath = "src/main/resources/docket/docket_" + docketId + ".csv";

        // Check if the file already exists
        File file = new File(csvFilePath);
        if (file.exists()) {
            System.out.println("The file " + csvFilePath + " already exists. Aborting the creation of a duplicate.");
            return; // Exit the method to avoid creating the file
        }

        // Try-with-resources to handle resources automatically
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {

            // Write the CSV header (column names)
            String header = "order_id,cust_name,delivery_area_name,address,pub_name,quantity,order_status";
            writer.write(header);
            writer.newLine();

            // Check if the ResultSet contains data
            boolean hasData = false;

            // Write the rows to CSV
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String custName = rs.getString("cust_name");
                String deliveryAreaName = rs.getString("delivery_area_name");
                String address = rs.getString("address");
                String pubName = rs.getString("pub_name");
                int quantity = rs.getInt("quantity");
                String orderStatus = rs.getString("order_status");

                // Debugging: Print each row to the console
                System.out.println("Row: " + orderId + ", " + custName + ", " + deliveryAreaName + ", " + address + ", " + pubName + ", " + quantity + ", " + orderStatus);

                // Write the data for each row into the CSV file
                String row = orderId + "," + custName + "," + deliveryAreaName + "," + address + "," + pubName + "," + quantity + "," + orderStatus;
                writer.write(row);
                writer.newLine();

                // Mark that we have data
                hasData = true;
            }

            // If no data was found, print a message to the console
            if (!hasData) {
                System.out.println("No rows found with status 'Pending'");
            } else {
                System.out.println("CSV file created successfully at: " + csvFilePath);
            }

        } catch (SQLException | IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void deliveryDocketToCsvDeliveryMan() {
        // Query to get the latest (highest) docket ID from the DeliveryDocket table
        String latestDocketSql = "SELECT MAX(id) AS latestDocket FROM DeliveryDocket";
        int latestDocket = 0;

        // Get the latest docket ID
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(latestDocketSql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                latestDocket = rs.getInt("latestDocket");
            }

        } catch (SQLException e) {
            System.err.println("Error fetching latest docket ID: " + e.getMessage());
        }

        // Define the SQL query to select rows where the status is 'Pending'
        String sql = "SELECT o.id AS order_id, " +
                "c.name AS cust_name, " +
                "da.name AS delivery_area_name, " +
                "c.address, " +
                "p.name AS pub_name, " +
                "o.quantity, " +
                "o.status AS order_status " +
                "FROM Orders o " +
                "JOIN Customers c ON o.cust_id = c.id " +
                "JOIN DeliveryArea da ON c.delivery_area_id = da.id " +
                "JOIN Publication p ON o.pub_id = p.id " +
                "WHERE o.status = 'Pending'";

        // Path for the CSV file, named based on the latest docket ID
        String csvFilePath = "src/main/resources/docket/docket_" + latestDocket + ".csv";

        // Check if the file already exists
        File file = new File(csvFilePath);
        if (file.exists()) {
            System.out.println("The file " + csvFilePath + " already exists. Aborting the creation of a duplicate.");
            return; // Exit the method to avoid creating the file
        }

        // Try-with-resources to handle resources automatically
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {

            // Write the CSV header (column names)
            String header = "order_id,cust_name,delivery_area_name,address,pub_name,quantity,order_status";
            writer.write(header);
            writer.newLine();

            // Check if the ResultSet contains data
            boolean hasData = false;

            // Write the rows to CSV
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                String custName = rs.getString("cust_name");
                String deliveryAreaName = rs.getString("delivery_area_name");
                String address = rs.getString("address");
                String pubName = rs.getString("pub_name");
                int quantity = rs.getInt("quantity");
                String orderStatus = rs.getString("order_status");

                // Debugging: Print each row to the console
                System.out.println("Row: " + orderId + ", " + custName + ", " + deliveryAreaName + ", " + address + ", " + pubName + ", " + quantity + ", " + orderStatus);

                // Write the data for each row into the CSV file
                String row = orderId + "," + custName + "," + deliveryAreaName + "," + address + "," + pubName + "," + quantity + "," + orderStatus;
                writer.write(row);
                writer.newLine();

                // Mark that we have data
                hasData = true;
            }

            // If no data was found, print a message to the console
            if (!hasData) {
                System.out.println("No rows found with status 'Pending'");
            } else {
                System.out.println("CSV file created successfully at: " + csvFilePath);
            }

        } catch (SQLException | IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }


    public void updateDocket(int id, int deliveryManID, String status) {
        String sql = "UPDATE DeliveryDocket SET delivery_man_id = ?, docket_status = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, deliveryManID);
            pstmt.setString(2, status);
            pstmt.setInt(3, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Docket updated successfully.");
            } else {
                System.out.println("No Docket found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating Docket: " + e.getMessage());
        }
    }

    public void deleteDocket(int id) {
        String sql = "DELETE FROM DeliveryDocket WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from DeliveryArea: " + e.getMessage());
        }
    }

    // --------------------- Invoice Table Methods ---------------------
    public void insertInvoice(Integer order_id, Double total_payable_amount) {
        String sql = "INSERT INTO Invoice (order_id, total_payable_amount) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order_id);
            pstmt.setDouble(2, total_payable_amount);
            int affectedRows = pstmt.executeUpdate();
            System.out.println("Inserted invoice, affected rows: " + affectedRows);
        } catch (SQLException e) {
            System.out.println("Error inserting into Invoice: " + e.getMessage());
        }
    }

    public ArrayList<ArrayList<String>> selectAllInvoice() {
        String sql = "SELECT * FROM Invoice";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all Invoice: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Updates and deletes for sprint 1 entities

    // --------------------- Update and Delete Methods ---------------------


    public void updateAddress(int id, String address, Integer deliveryAreaId) {
        String sql = "UPDATE Address SET address = ?, delivery_area_id = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, address);
            pstmt.setObject(2, deliveryAreaId, Types.INTEGER);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating Address: " + e.getMessage());
        }
    }

    public void deleteAddress(int id) {
        String sql = "DELETE FROM Address WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from Address: " + e.getMessage());
        }
    }

    public void updateDeliveryArea(int id, String name, String description) {
        String sql = "UPDATE DeliveryArea SET name = ?, description = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating DeliveryArea: " + e.getMessage());
        }
    }

    public void deleteDeliveryArea(int id) {
        String sql = "DELETE FROM DeliveryArea WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from DeliveryArea: " + e.getMessage());
        }
    }

    public void updateNewsAgent(int id, String name) {
        String sql = "UPDATE NewsAgent SET name = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating NewsAgent: " + e.getMessage());
        }
    }

    public void deleteNewsAgent(int id) {
        String sql = "DELETE FROM NewsAgent WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from NewsAgent: " + e.getMessage());
        }
    }

    // --------------------- DeliveryMan Update and Delete Methods ---------------------
    public void updateDeliveryMan(int id, String employmentStatus) {
        String sql = "UPDATE DeliveryMan SET employment_status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employmentStatus);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating DeliveryMan: " + e.getMessage());
        }
    }

    public void deleteDeliveryMan(int id) {
        String sql = "DELETE FROM DeliveryMan WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from DeliveryMan: " + e.getMessage());
        }
    }

    // --------------------- Invoice Update and Delete Methods ---------------------
    public void updateInvoice(int id, int orderId, double totalPayableAmount) {
        String sql = "UPDATE Invoice SET order_id = ?, total_payable_amount = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setDouble(2, totalPayableAmount);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating Invoice: " + e.getMessage());
        }
    }

    public void deleteInvoice(int id) {
        String sql = "DELETE FROM Invoice WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from Invoice: " + e.getMessage());
        }
    }

    // --------------------- Orders Update and Delete Methods ---------------------
    public void updateOrder(int id, String status) {
        String sql = "UPDATE Orders SET status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating Order: " + e.getMessage());
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM Orders WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from Orders: " + e.getMessage());
        }
    }

    // --------------------- DeliveryDocket Update and Delete Methods ---------------------
    public void updateDeliveryDocket(int id, Integer deliveryManId, String docketStatus) {
        String sql = "UPDATE DeliveryDocket SET delivery_man_id = ?, docket_status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, deliveryManId, Types.INTEGER);
            pstmt.setString(2, docketStatus);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating DeliveryDocket: " + e.getMessage());
        }
    }

    public void deleteDeliveryDocket(int id) {
        String sql = "DELETE FROM DeliveryDocket WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from DeliveryDocket: " + e.getMessage());
        }
    }

    public void updatePublication(int id, String name, String description, double price, int stock) {
        String sql = "UPDATE Publication SET name = ?, description = ?, price = ?, stock_available = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stock);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating Publication: " + e.getMessage());
        }
    }

    public void deletePublication(int id) {
        String sql = "DELETE FROM Publication WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting from Publication: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        // Insert sample data.
        // Note: The order of insertion matters due to foreign key constraints.

        DatabaseConnection db = new DatabaseConnection();
        db.insertDeliveryArea("Downtown", "Test description");
        db.insertAddress("123 Main St", 1);     // Assuming delivery_area_id 1 exists.
        db.insertCustomer("John Doe", "john@example.com", "Some address", "12123123", 2, "N37 ASD");
        db.insertNewsAgent("Jane Reporter");
        db.insertPublication("test", "test", 9.99, 10);            // Assuming customer id 1 exists.
        db.insertOrderStatus(1, 1, 2, "Pending"); // Assuming cust_id 1 and pub_id 1 exist.

        // Display data from each table.
        System.out.println("Select Customer with id 1: ");
        System.out.println(db.selectCustomers(1));
        System.out.println("Select All Customers: ");
        System.out.println(db.selectAllCustomers());

        System.out.println("Select Address with id 1: ");
        System.out.println(db.selectAddress(123));
        System.out.println("Select All Addresses: ");
        System.out.println(db.selectAllAddress());

        System.out.println("Select DeliveryArea with id 1: ");
        System.out.println(db.selectDeliveryArea(1));
        System.out.println("Select All DeliveryAreas: ");
        System.out.println(db.selectAllDeliveryArea());

        System.out.println("Select NewsAgent with id 1: ");
        System.out.println(db.selectNewsAgent(1));
        System.out.println("Select All NewsAgents: ");
        System.out.println(db.selectAllNewsAgent());

        System.out.println("Select Publication with id 1: ");
        System.out.println(db.selectPublication(1));
        System.out.println("Select All Publications: ");
        System.out.println(db.selectAllPublication());

        System.out.println("Select OrdersStatus with id 1: ");
        System.out.println(db.selectOrdersStatus(1));
        System.out.println("Select All OrdersStatus: ");
        System.out.println(db.selectAllOrdersStatus());

        db.insertDeliveryMan("Mike Johnson", "Active");
        db.insertDeliveryDocket(1, "Pending");
        db.insertInvoice(1, 49.99);

        System.out.println("Select All Delivery Men: ");
        System.out.println(db.selectAllDeliveryMan());
        System.out.println("Select All Delivery Dockets: ");
        System.out.println(db.selectAllDeliveryDocket());
        System.out.println("Select All Invoices: ");
        System.out.println(db.selectAllInvoice());

        // updates and deletes for the sprint 1
        System.out.println("Updating customer...");
        db.updateCustomer(3, "John Updated", "john_updated@example.com", "Updated Address", "9876543210", 2, "EIR999");
        System.out.println(db.selectCustomers(1));

        System.out.println("Deleting customer...");
        db.deleteCustomer(1);
        System.out.println(db.selectAllCustomers());

        System.out.println("Updating address...");
        db.updateAddress(1, "456 New St", 1);
        System.out.println(db.selectAddress(1));

        System.out.println("Deleting address...");
        db.deleteAddress(1);
        System.out.println(db.selectAllAddress());

        System.out.println("Updating delivery area...");
        db.updateDeliveryArea(1, "Uptown", "Updated description");
        System.out.println(db.selectDeliveryArea(1));

        System.out.println("Deleting delivery area...");
        db.deleteDeliveryArea(1);
        System.out.println(db.selectAllDeliveryArea());

        System.out.println("Updating news agent...");
        db.updateNewsAgent(1, "Updated Reporter");
        System.out.println(db.selectNewsAgent(1));

        System.out.println("Deleting news agent...");
        db.deleteNewsAgent(1);
        System.out.println(db.selectAllNewsAgent());
    }

}

