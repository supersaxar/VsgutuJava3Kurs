//Доржиев Жаргал Группа Б-762-1 Вариант 1
package DorzhievZhargalB7621;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileSystemApp {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            DatabaseSetup.setupDatabase(connection);
            DemoDataCreator.createDemoData(connection);

            FileSystemQueries queries = new FileSystemQueries(connection);
            FileSystemModifier modifier = new FileSystemModifier(connection);

            System.out.println("--- Иерархия файловой системы ---");
            DemoDataCreator.printHierarchy(connection);

            System.out.println("\n--- Примеры использования методов ---");

            System.out.println("\nПервое задание: ");
            String filePath = queries.getFullPath("file1.txt");
            System.out.println("Полный путь до 'file1.txt': " + filePath);

            System.out.println("\nВторое задание: ");
            int fileCount = queries.countFiles("root");
            System.out.println("Общее количество файлов в 'root': " + fileCount);

            System.out.println("\nТретье задание: ");
            long diskUsage = queries.getDiskUsage("root");
            System.out.println("Занимаемое место каталогом 'root': " + diskUsage + " bytes");

            System.out.println("\nЧетвертое задание");
            System.out.println("Перемещение 'subdir1' в 'subdir2'...");
            modifier.moveFilesAndDirectories("subdir1", "subdir2");
            DemoDataCreator.printHierarchy(connection);

            System.out.println("\nПятое задание");
            System.out.println("Удаление 'subdir2'...");
            modifier.deleteDirectory("subdir2");
            DemoDataCreator.printHierarchy(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}