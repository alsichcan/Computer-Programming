import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

public class StorageManager {

    /* Save string lines into as a file */
    public static void writeLines(String fileName, List<String> strings) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for (String string : strings) {
                fileWriter.write(string + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            // TODO: Practice 3
            e.printStackTrace();
        }
    }

    /* Private methods. Today, you don't need to read them. */
    private static List<String> readLines(File file) {
        List<String> strings = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                strings.add(line);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    /*
     * Read string lines of files in a specified directory.
     * The files are sorted by their names.
     */
    public static List<List<String>> directoryChildrenLines(String directoryName) {
        List<List<String>> childrenLines = new LinkedList<>();
        for (File childFile : nameSortedDirectoryFiles(directoryName)) {
            List<String> lines = readLines(childFile);
            childrenLines.add(lines);
        }
        return childrenLines;
    }

    /*
     * Delete a file
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        return file.delete();
    }

    private static File[] nameSortedDirectoryFiles(String directoryName) {
        // TODO: Practice 2
        File[] directoryFiles = directoryFiles(directoryName);
        Arrays.sort(directoryFiles, Comparator.comparing(File::getName));
        return directoryFiles;
    }

    private static File[] directoryFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
}
