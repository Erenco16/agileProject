package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_PATH = "database_files/agile_project.db"; // Store inside project folder
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    private static final String SQL_FILE_PATH = "src/main/resources/sql/setup.sql"; // Path to setup SQL file

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

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
                System.out.println("✅ SQLite database initialized in: " + DB_PATH);
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to initialize SQLite: " + e.getMessage());
        }
    }

    public static void runSQLFile(Connection connection, String filePath) {
        File sqlFile = new File(filePath);

        // Check if the SQL file exists
        if (!sqlFile.exists()) {
            System.out.println("❌ SQL file not found: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFile));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("--")) { // Ignore comments and empty lines
                    sql.append(line).append(" ");
                    if (line.endsWith(";")) { // Execute when a full SQL command is read
                        statement.execute(sql.toString());
                        System.out.println("✅ Executed: " + sql);
                        sql.setLength(0); // Reset the StringBuilder
                    }
                }
            }

            System.out.println("✅ SQL setup file executed successfully!");

        } catch (IOException | SQLException e) {
            System.out.println("❌ Failed to execute SQL file: " + e.getMessage());
        }
    }
}
