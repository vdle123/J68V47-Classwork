package Project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class testing_stuff {

    public static void save_data(String foodName, String saveCalories, String saveProtein) {
        // Specify the file path
        String filePath = "Logs/" + String.valueOf(LocalDate.now() + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Concatenate the variables and write to the file
            writer.write(foodName + "," + saveCalories + "," + saveProtein);
            writer.newLine(); // Add a newline for the next set of variables
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing data to file.");
        }
        System.out.println("The data has been saved.");
    }



    public static void main(String[] args) {
        // Create a table model with column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Food");
        tableModel.addColumn("Calories");
        tableModel.addColumn("Protein");

        // Create a JTable with the table model
        JTable table = new JTable(tableModel);

        // Create a JScrollPane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        JFrame frame = new JFrame("Data Log Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Read data from the file and populate the table
        //

        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


