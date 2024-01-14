package Project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Date_Manager {
    public static void check_and_write_date(){
        //This will write the date for the day of use or ignore if it is the same day
        try {
            File myObj = new File("goals.txt");
            BufferedReader brTest = new BufferedReader(new FileReader(myObj));

            String line;
            boolean currentDateExists = false;

            // Check if the current date is already in the file
            while ((line = brTest.readLine()) != null) {
                if (line.equals(String.valueOf(LocalDate.now()))) {
                    currentDateExists = true;
                    break;
                }
            }

            brTest.close(); // Close the BufferedReader

            if (!currentDateExists) {
                FileWriter myWriter = new FileWriter("goals.txt", true);
                myWriter.append(String.valueOf(LocalDate.now())).append("\n"); // Writes current date to the file
                myWriter.close();
                System.out.println("Wrote");
            } else {
                System.out.println("Same date, not writing again");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void readAndRemoveOldRecords() {
        try {
            String fileName = "dates.txt";
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

    private static Date truncateTime(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }
    public static void main(String[] args) {
        check_and_write_date();
    }
}
