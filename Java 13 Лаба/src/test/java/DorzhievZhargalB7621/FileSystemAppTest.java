package DorzhievZhargalB7621;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileSystemAppTest {

    private static Connection connection;
    private static FileSystemQueries queries;
    private static FileSystemModifier modifier;

    @BeforeAll
    public static void setup() throws SQLException {
        connection = DatabaseConnector.getConnection();
        DatabaseSetup.setupDatabase(connection);
        DemoDataCreator.createDemoData(connection);
        queries = new FileSystemQueries(connection);
        modifier = new FileSystemModifier(connection);
    }

    @AfterAll
    public static void teardown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    @Order(1)
    public void testGetFullPath() throws SQLException {
        String fullPath = queries.getFullPath("file1.txt");
        assertEquals("file1.txt/root", fullPath, "Full path for 'file1.txt' should be 'file1.txt/root'");
    }

    @Test
    @Order(2)
    public void testCountFiles() throws SQLException {
        int fileCount = queries.countFiles("root");
        assertEquals(3, fileCount, "Total files in 'root' should be 3");
    }

    @Test
    @Order(3)
    public void testGetDiskUsage() throws SQLException {
        long diskUsage = queries.getDiskUsage("root");
        assertEquals(3584, diskUsage, "Disk usage for 'root' should be 3584 bytes");
    }

    @Test
    @Order(4)
    public void testMoveFilesAndDirectories() throws SQLException {
        modifier.moveFilesAndDirectories("subdir1", "subdir2");

        String movedFilePath = queries.getFullPath("file3.txt");
        assertEquals("subsubdir1/file3.txt/subdir2/root", movedFilePath, "File 'file3.txt' should now be in 'subdir2/subsubdir1'");
    }
}
