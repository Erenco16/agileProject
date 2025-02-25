package org.example;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBClass {
    private static final String DB_PATH = "database_files/agile_project.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;

    // Returns a connection to the SQLite database.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // General method to convert a ResultSet into an ArrayList of rows.
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


    // Insert a record into the Customers table.
    public static void insertCustomer(String name, String email, String address, String phone_number, int deliveryAreaID, String eircode) {
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

    // Select all records from the Customers table.
    public static ArrayList<ArrayList<String>> selectAllCustomers() {
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


    // Insert a record into the Address table.
    public static void insertAddress(String address, Integer deliveryAreaId) {
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
    public static ArrayList<ArrayList<String>> selectAddress(int id) {
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
    public static ArrayList<ArrayList<String>> selectAllAddress() {
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


    // Insert a record into the DeliveryArea table.
    public static void insertDeliveryArea(String name, String description) {
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

    // Select all records from the DeliveryArea table.
    public static ArrayList<ArrayList<String>> selectAllDeliveryArea() {
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


    // Insert a record into the NewsAgent table.
    public static void insertNewsAgent(String name) {
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
    public static ArrayList<ArrayList<String>> selectNewsAgent(int id) {
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
    public static ArrayList<ArrayList<String>> selectAllNewsAgent() {
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


    // Insert a record into the Publication table.
    public static void insertPublication(String name, String description, double price) {
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
    public static ArrayList<ArrayList<String>> selectPublication(int id) {
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
    public static ArrayList<ArrayList<String>> selectAllPublication() {
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

    // Insert a record into the OrdersStatus table.
    public static void insertOrderStatus(int custId, int pubId, int quantity, String status) {
        String sql = "INSERT INTO OrdersStatus (cust_id, pub_id, quantity, status) VALUES (?, ?, ?, ?)";
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

    // Select a record from the OrdersStatus table by id.
    public static ArrayList<ArrayList<String>> selectOrdersStatus(int id) {
        String sql = "SELECT * FROM OrdersStatus WHERE id = " + id;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting from OrdersStatus: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // Select all records from the OrdersStatus table.
    public static ArrayList<ArrayList<String>> selectAllOrdersStatus() {
        String sql = "SELECT * FROM OrdersStatus";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return getValues(rs);
        } catch (SQLException e) {
            System.err.println("Error selecting all OrdersStatus: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // -------------------- Main Method --------------------

    public static void main(String[] args) {
        // Ensure the directory for the database exists.
        File dbDirectory = new File("database_files");
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();
        }

        // Insert sample data.
        // Note: The order of insertion matters due to foreign key constraints.
        insertDeliveryArea("Downtown", "Test description");
        insertAddress("123 Main St", 1);     // Assuming delivery_area_id 1 exists.
        insertCustomer("John Doe", "john@example.com", "Some address", "12123123", 1, "N37 ASD");
        insertNewsAgent("Jane Reporter");
        insertPublication("test", "test", 9.99);            // Assuming customer id 1 exists.
        insertOrderStatus(1, 1, 2, "Pending"); // Assuming cust_id 1 and pub_id 1 exist.

        // Display data from each table.
        System.out.println("Select Customer with id 1: ");
        System.out.println(selectCustomers(1));
        System.out.println("Select All Customers: ");
        System.out.println(selectAllCustomers());

        System.out.println("Select Address with id 1: ");
        System.out.println(selectAddress(123));
        System.out.println("Select All Addresses: ");
        System.out.println(selectAllAddress());

        System.out.println("Select DeliveryArea with id 1: ");
        System.out.println(selectDeliveryArea(1));
        System.out.println("Select All DeliveryAreas: ");
        System.out.println(selectAllDeliveryArea());

        System.out.println("Select NewsAgent with id 1: ");
        System.out.println(selectNewsAgent(1));
        System.out.println("Select All NewsAgents: ");
        System.out.println(selectAllNewsAgent());

        System.out.println("Select Publication with id 1: ");
        System.out.println(selectPublication(1));
        System.out.println("Select All Publications: ");
        System.out.println(selectAllPublication());

        System.out.println("Select OrdersStatus with id 1: ");
        System.out.println(selectOrdersStatus(1));
        System.out.println("Select All OrdersStatus: ");
        System.out.println(selectAllOrdersStatus());
    }
}
