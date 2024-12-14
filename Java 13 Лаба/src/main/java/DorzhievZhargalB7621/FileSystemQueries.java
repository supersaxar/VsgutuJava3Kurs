package DorzhievZhargalB7621;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileSystemQueries {

    private final Connection connection;

    public FileSystemQueries(Connection connection) {
        this.connection = connection;
    }

    public String getFullPath(String fileName) throws SQLException {
        String query = "WITH RECURSIVE paths AS (" +
                " SELECT id, parent_id, name FROM files WHERE name = ?" +
                " UNION ALL" +
                " SELECT d.id, d.parent_id, d.name FROM directories d" +
                " INNER JOIN paths p ON d.id = p.parent_id)" +
                " SELECT string_agg(name, '/' ORDER BY id DESC) AS full_path FROM paths;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, fileName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("full_path");
                }
            }
        }
        return null;
    }

    public int countFiles(String directory) throws SQLException {
        String query = "WITH RECURSIVE dir_tree AS (" +
                " SELECT id FROM directories WHERE name = ?" +
                " UNION ALL" +
                " SELECT d.id FROM directories d" +
                " INNER JOIN dir_tree dt ON d.parent_id = dt.id)" +
                " SELECT COUNT(*) FROM files WHERE parent_id IN (SELECT id FROM dir_tree);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, directory);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public long getDiskUsage(String directory) throws SQLException {
        String query = "WITH RECURSIVE dir_tree AS (" +
                " SELECT id FROM directories WHERE name = ?" +
                " UNION ALL" +
                " SELECT d.id FROM directories d" +
                " INNER JOIN dir_tree dt ON d.parent_id = dt.id)" +
                " SELECT SUM(size) FROM files WHERE parent_id IN (SELECT id FROM dir_tree);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, directory);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return 0;
    }
}