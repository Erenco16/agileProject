package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConnection {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String DB_NAME = "agile_project";
    private static final String ROOT_URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?serverTimezone=UTC";

    private static final String ROOT_USER = "root";
    private static final String ROOT_PASSWORD = dotenv.get("MYSQL_ROOT_PASSWORD", "12345"); // Default to '12345' if not set

    private static final String GUEST_USER = "guest";
    private static final String GUEST_PASSWORD = "12345";

    private static final String SQL_FILE_PATH = "src/main/resources/sql/SetupDB.sql";

    public static Connection getConnection() throws SQLException {
        // Ensure database setup is done using root first
        setupDatabaseWithRoot();

        // Now connect as guest
        return DriverManager.getConnection(DB_URL, GUEST_USER, GUEST_PASSWORD);
    }

    private static void setupDatabaseWithRoot() {
        if (ROOT_PASSWORD == null || ROOT_PASSWORD.isEmpty()) {
            throw new RuntimeException("MYSQL_ROOT_PASSWORD environment variable is not set.");
        }

        try (Connection rootConn = DriverManager.getConnection(ROOT_URL, ROOT_USER, ROOT_PASSWORD);
             Statement stmt = rootConn.createStatement()) {

            System.out.println("🔧 Setting up database as ROOT...");

            // Create database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("✅ Database '" + DB_NAME + "' is ready.");

            // Run SQL script as root
            runSQLFile(rootConn, SQL_FILE_PATH);

            System.out.println("✅ Database setup complete.");

        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to set up database: " + e.getMessage());
        }
    }

    public static void runSQLFile(Connection connection, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("--")) {
                    sql.append(line).append(" ");
                    if (line.endsWith(";")) {
                        try {
                            statement.execute(sql.toString());
                            System.out.println("Executed: " + sql);
                        } catch (SQLException e) {
                            System.out.println("⚠️ SQL Execution Failed: " + sql);
                            e.printStackTrace();
                        }
                        sql.setLength(0);
                    }
                }
            }
            System.out.println("✅ SQL script executed successfully!");

        } catch (IOException | SQLException e) {
            System.out.println("❌ Failed to execute SQL file: " + e.getMessage());
        }
    }
}
