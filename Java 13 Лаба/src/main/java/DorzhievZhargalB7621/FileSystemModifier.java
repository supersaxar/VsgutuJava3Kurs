package DorzhievZhargalB7621;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileSystemModifier {

    private final Connection connection;

    public FileSystemModifier(Connection connection) {
        this.connection = connection;
    }

    public void moveFilesAndDirectories(String sourceDir, String targetDir) throws SQLException {
        String updateQuery = "UPDATE files SET parent_id = (SELECT id FROM directories WHERE name = ?) " +
                "WHERE parent_id = (SELECT id FROM directories WHERE name = ?);";
        String updateDirsQuery = "UPDATE directories SET parent_id = (SELECT id FROM directories WHERE name = ?) " +
                "WHERE parent_id = (SELECT id FROM directories WHERE name = ?);";
        try (PreparedStatement stmt1 = connection.prepareStatement(updateQuery);
             PreparedStatement stmt2 = connection.prepareStatement(updateDirsQuery)) {
            stmt1.setString(1, targetDir);
            stmt1.setString(2, sourceDir);
            stmt1.executeUpdate();

            stmt2.setString(1, targetDir);
            stmt2.setString(2, sourceDir);
            stmt2.executeUpdate();
        }
    }

    public void deleteDirectory(String directory) throws SQLException {
        String deleteFiles = "DELETE FROM files WHERE parent_id = (SELECT id FROM directories WHERE name = ?);";
        String deleteDirectory = "DELETE FROM directories WHERE name = ?;";
        try (PreparedStatement stmt1 = connection.prepareStatement(deleteFiles);
             PreparedStatement stmt2 = connection.prepareStatement(deleteDirectory)) {
            stmt1.setString(1, directory);
            stmt1.executeUpdate();

            stmt2.setString(1, directory);
            stmt2.executeUpdate();
        }
    }
}
