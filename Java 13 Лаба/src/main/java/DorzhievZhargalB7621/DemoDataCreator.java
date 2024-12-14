package DorzhievZhargalB7621;

import java.sql.*;

public class DemoDataCreator {

    public static void createDemoData(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO directories (id, parent_id, name) VALUES (1, NULL, 'root') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO directories (id, parent_id, name) VALUES (2, 1, 'subdir1') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO directories (id, parent_id, name) VALUES (3, 1, 'subdir2') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO directories (id, parent_id, name) VALUES (4, 2, 'subsubdir1') ON CONFLICT DO NOTHING;");

            stmt.executeUpdate("INSERT INTO files (id, parent_id, name, size) VALUES (1, 1, 'file1.txt', 1024) ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO files (id, parent_id, name, size) VALUES (2, 2, 'file2.txt', 2048) ON CONFLICT DO NOTHING;");
            stmt.executeUpdate("INSERT INTO files (id, parent_id, name, size) VALUES (3, 4, 'file3.txt', 512) ON CONFLICT DO NOTHING;");
        }
    }

    public static void printHierarchy(Connection connection) throws SQLException {
        String query = "WITH RECURSIVE dir_tree AS (" +
                " SELECT id, parent_id, name, 0 AS level FROM directories WHERE parent_id IS NULL" +
                " UNION ALL" +
                " SELECT d.id, d.parent_id, d.name, dt.level + 1 FROM directories d" +
                " INNER JOIN dir_tree dt ON d.parent_id = dt.id)" +
                " SELECT id, parent_id, name, level FROM dir_tree ORDER BY level, name;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int level = rs.getInt("level");
                String name = rs.getString("name");
                System.out.println("  ".repeat(level) + "- " + name);
            }
        }

        String fileQuery = "SELECT f.name AS file_name, d.name AS dir_name FROM files f " +
                "INNER JOIN directories d ON f.parent_id = d.id ORDER BY dir_name, file_name;";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(fileQuery)) {
            while (rs.next()) {
                String fileName = rs.getString("file_name");
                String dirName = rs.getString("dir_name");
                System.out.println("  - " + dirName + "/" + fileName);
            }
        }
    }

}
