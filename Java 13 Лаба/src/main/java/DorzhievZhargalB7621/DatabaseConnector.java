package DorzhievZhargalB7621;

import java.sql.*;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/JavaLab13Ver2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}