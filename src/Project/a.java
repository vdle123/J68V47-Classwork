package Project;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;


public class a {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void readAndRemoveOldRecords() {
        try {
            String fileName = "goals.txt";
            List<String> records = readRecordsFromFile(fileName);

            if (records.isEmpty()) {
                System.out.println("File is empty.");
                return;
            }

            System.out.println("All Records:");
            for (String record : records) {
                System.out.println(record);
            }

            // Check if the list has more than 7 records
            if (records.size() > 7) {
                // Remove the oldest records until the size is 7
                int recordsToRemove = records.size() - 7;
                for (int i = 0; i < recordsToRemove; i++) {
                    records.remove(0); // Remove the first (oldest) record
                }

                // Write the updated records back to the file
                writeRecordsToFile(fileName, records);
                System.out.println("Old records removed.");
            } else {
                System.out.println("No records removed.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static List<String> readRecordsFromFile(String fileName) throws IOException {
        List<String> records = new ArrayList<>();
        File myObj = new File(fileName);

        if (myObj.exists()) {
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    records.add(myReader.nextLine());
                }
            }
        }

        return records;
    }

    private static void writeRecordsToFile(String fileName, List<String> records) throws IOException {
        Files.write(Paths.get(fileName), records);
    }

    private static boolean isOlderThanSevenDays(Path filePath, LocalDate currentDate) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            LocalDate fileCreationDate = attrs.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return fileCreationDate.isBefore(currentDate.minusDays(7));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete files older than seven days in the specified directory
    private static void deleteOldFiles(String directoryPath) {
        LocalDate currentDate = LocalDate.now();

        try (Stream<Path> paths = Files.list(Paths.get(directoryPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> isOlderThanSevenDays(path, currentDate))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            System.out.println("Deleted file: " + path.getFileName());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // readAndRemoveOldRecords();
        deleteOldFiles("Logs/");


    }
}
