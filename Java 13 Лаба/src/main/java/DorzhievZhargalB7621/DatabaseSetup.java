package DorzhievZhargalB7621;

import java.sql.*;

public class DatabaseSetup {

    public static void setupDatabase(Connection connection) throws SQLException {
        String createDirectoriesTable = "CREATE TABLE IF NOT EXISTS directories (" +
                "id SERIAL PRIMARY KEY," +
                "parent_id INTEGER REFERENCES directories(id) ON DELETE CASCADE," +
                "name VARCHAR(255) NOT NULL UNIQUE" +
                ");";

        String createFilesTable = "CREATE TABLE IF NOT EXISTS files (" +
                "id SERIAL PRIMARY KEY," +
                "parent_id INTEGER REFERENCES directories(id) ON DELETE CASCADE," +
                "name VARCHAR(255) NOT NULL," +
                "size BIGINT NOT NULL" +
                ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createDirectoriesTable);
            stmt.execute(createFilesTable);
        }
    }
}