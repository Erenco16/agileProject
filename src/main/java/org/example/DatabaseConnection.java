package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
                if (!line.isEmpty() && !line.startsWith("--")) { // Ignore comments and empty lines
                    sql.append(line).append("\n");
                    if (line.endsWith(";")) { // Execute when a full SQL command is read
                        statement.execute(sql.toString());
                        System.out.println("Executed: " + sql);
                        sql.setLength(0); // Reset the StringBuilder
                    }
                }
            }

            System.out.println("SQL setup file executed successfully!");

        } catch (IOException | SQLException e) {
            System.out.println("Failed to execute SQL file: " + e.getMessage());
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
    public void insertPublication(String name, String description, double price) {
        String sql = "INSERT INTO Publication (name, description, price) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
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
            System.err.println("Error selecting all DeliveryDocket: " + e.getMessage());
        }
        return new ArrayList<>();
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


    public static void main(String[] args) {
        // Insert sample data.
        // Note: The order of insertion matters due to foreign key constraints.

        DatabaseConnection db = new DatabaseConnection();
        db.insertDeliveryArea("Downtown", "Test description");
        db.insertAddress("123 Main St", 1);     // Assuming delivery_area_id 1 exists.
        db.insertCustomer("John Doe", "john@example.com", "Some address", "12123123", 1, "N37 ASD");
        db.insertNewsAgent("Jane Reporter");
        db.insertPublication("test", "test", 9.99);            // Assuming customer id 1 exists.
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
    }

}