package Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class Main_test extends JFrame implements ActionListener {
    //this declares where to use the logo
    ImageIcon mainicon = new ImageIcon(("Config/Images/logo.png"));

    //this declares stuff that will be used in the program
    static double saveprotein = 0;
    static int savecalories = 0;
    static JButton add_food_button, set_goals, see_logs;
    static JFrame f;
    static JLabel l, calorie_label, protein_label, space_label, date_label;
    static String food_name = "";
    String calories_input = "";
    String protein_input = "";
    static int total_calories = 0;
    static double total_protein = 0;
    static int calorie_goal = 0;
    static double protein_goal = 0;

    static String[] goalsdata = {"", ""};
    static String[] loggeddata = {"", ""};
    static Color verydarkgray = new Color(27, 27, 27); //custom color to be used later on

    Boolean show_food_label = false; //if true it will show a label that food has been added.
    JPanel buttons = new JPanel();
    JPanel MainLabels = new JPanel();

    Main_test() {
        //if the data file does not exist it should create a new one in data folder with name of current date (2024-01-16 < example) and make it a text file
        if (!Files.exists(Paths.get("Config/Data/" + LocalDate.now() + ".txt"))) {
            create_logs();
        }
        //run these to get the data from files
        goals_reader();
        logged_data();
        log_data();
        //this will be used to add saved data to new data
        total_calories = Integer.parseInt(loggeddata[0]);
        total_protein = Double.parseDouble(loggeddata[1]);

        //declare data for the menus
        f = new JFrame("Calorie Tracker");
        f.setIconImage(mainicon.getImage());
        l = new JLabel("");
        calorie_label = new JLabel("");
        protein_label = new JLabel("");
        space_label = new JLabel("");
        date_label = new JLabel("");

        //make main program close if the X button is pressed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add buttons
        add_food_button = new JButton("Add Food");
        add_food_button.setFont(new Font("Verdana", Font.BOLD, 15));

        set_goals = new JButton("Goals");
        set_goals.setFont(new Font("Verdana", Font.BOLD, 15));

        see_logs = new JButton("Logs");
        see_logs.setFont(new Font("Verdana", Font.BOLD, 15));

        //make the buttons change color (glow) once the cursor is over the button
        hovereffect(add_food_button);
        hovereffect(set_goals);
        hovereffect(see_logs);

        add_food_button.addActionListener(new ActionListener() {
            @Override
            //This will ask for food name, calories and protein once the button is pressed then add it to files
            public void actionPerformed(ActionEvent e) {
                food_name = (String) JOptionPane.showInputDialog(f, "Enter name of the food", "", JOptionPane.PLAIN_MESSAGE, null,null,null);
                calories_input = (String) JOptionPane.showInputDialog(f, "Enter the amount of calories", "", JOptionPane.PLAIN_MESSAGE, null, null, null);

                while (true) {
                    try {
                        int calories = Integer.parseInt(calories_input);
                        if (calories >= 0) {               //this is put for 0 because water might be logged, which has no calories nor protein
                            total_calories += calories;
                            savecalories = calories;
                            break;
                        }
                        break;
                    } catch (NumberFormatException eInt) {
                        calories_input = (String) JOptionPane.showInputDialog(f, "Error, please re-enter the number", "", JOptionPane.PLAIN_MESSAGE, null, null, 0);
                    }
                }

                protein_input = (String) JOptionPane.showInputDialog(f, "Enter the amount of protein", "", JOptionPane.PLAIN_MESSAGE, null, null, 0.0);
                while (true) {
                    try {
                        double protein = Double.parseDouble(protein_input);
                        if (protein >= 0.0) {
                            total_protein += protein;
                            saveprotein = protein;
                            update_calories();
                            save_data();
                            show_food_label = true;
                            break;

                        }
                        break;
                    } catch (NumberFormatException eDouble) {
                        protein_input = (String) JOptionPane.showInputDialog(f, "Error, please re-enter the number", "", JOptionPane.PLAIN_MESSAGE, null, null, null);
                    }
                }
                if (show_food_label) {
                    JOptionPane.showMessageDialog(f, "The food has been added.", "", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        set_goals.addActionListener(new ActionListener() {

            @Override
            //This will allow to set goals of Calories and Protein once set goals button is pressed
            public void actionPerformed(ActionEvent e) {
                calories_input = (String) JOptionPane.showInputDialog(f, "Enter the goal of calories", "", JOptionPane.PLAIN_MESSAGE, null, null, null);

                while (true) {
                    try {
                        int calories = Integer.parseInt(calories_input);
                        if (calories > 0) {
                            calorie_goal = calories;
                            break;
                        }
                    } catch (NumberFormatException eInt) {
                        calories_input = (String) JOptionPane.showInputDialog(f, "Error, please re-enter the number (int)", "", JOptionPane.PLAIN_MESSAGE, null, null, null);
                    }
                }

                protein_input = (String) JOptionPane.showInputDialog(f, "Enter the amount of protein", "", JOptionPane.PLAIN_MESSAGE, null, null, null);
                while (true) {
                    try {
                        double protein = Double.parseDouble(protein_input);
                        if (protein > 0.0) {
                            protein_goal = protein;
                            save_goals();
                            break;
                        }
                    } catch (NumberFormatException eDouble) {
                        protein_input = (String) JOptionPane.showInputDialog(f, "Error, please re-enter the number", "", JOptionPane.PLAIN_MESSAGE, null, null, null);
                    }

                }
            }
        });
        see_logs.addActionListener(new ActionListener() {
            //This will handle the logs
            @Override
            public void actionPerformed(ActionEvent e) {
                //create logs pane and give it icon + color
                JFrame logs_frame = new JFrame("Data Log - " + LocalDate.now());
                logs_frame.setIconImage(mainicon.getImage());
                logs_frame.setBackground(verydarkgray);

                // Create a table with column names
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Food");
                tableModel.addColumn("Calories");
                tableModel.addColumn("Protein");


                // Create a JTable with the table model
                JTable table = new JTable(tableModel) {
                    //this will disable editing of the logs table
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                //JScrollPane to display all data and make it scrollable
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBackground(verydarkgray);


                // Add the scroll pane to the frame
                logs_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //this is used to add the background color to scrollpane
                JViewport viewport = scrollPane.getViewport();
                viewport.setBackground(verydarkgray);

                logs_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                logs_frame.getContentPane().setBackground(verydarkgray);

                logs_frame.getContentPane().setBackground(verydarkgray);

                // Read data from the file and populate the table
                read_data(tableModel);

                //disable row selection
                table.setRowSelectionAllowed(false);

                //table entries colors
                table.setBackground(verydarkgray);
                table.setForeground(Color.LIGHT_GRAY);

                //table headings colors
                table.getTableHeader().setBackground(Color.DARK_GRAY);
                table.getTableHeader().setForeground(Color.LIGHT_GRAY);



                logs_frame.setSize(400, 300);
                logs_frame.setResizable(false);
                logs_frame.setLocationRelativeTo(null);
                logs_frame.setVisible(true);


            }
        });
        //border for buttons
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(new EmptyBorder(new Insets(150, 100, 115, 100)));
        buttons.setBackground(verydarkgray);

        //make all buttons the same size
        Dimension buttonSize = new Dimension(90, 5);
        add_food_button.setPreferredSize(buttonSize);
        set_goals.setPreferredSize(buttonSize);
        see_logs.setPreferredSize(buttonSize);

        //make the buttons in 1 line with gaps inbetween them
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setBackground(verydarkgray);
        panel.add(add_food_button);
        panel.add(set_goals);
        panel.add(see_logs);

        //layout and border for main labels
        MainLabels.setLayout(new BoxLayout(MainLabels, BoxLayout.Y_AXIS));
        MainLabels.setBorder(new EmptyBorder(new Insets(30, 130, 5, 10)));

        //set text for the labels
        l.setText("CALORIE TRACKER");
        l.setFont(new Font("Verdana", Font.BOLD, 20));
        l.setForeground(Color.LIGHT_GRAY);

        calorie_label.setText("CALORIES: " + loggeddata[0] + "/" + goalsdata[0]);
        if (loggeddata[0] == null) { //if the logged data is none then set calories to 0
            calorie_label.setText("CALORIES: 0/" + goalsdata[0]);
        }
        calorie_label.setFont(new Font("Verdana", Font.BOLD, 20));
        calorie_label.setForeground(Color.LIGHT_GRAY);

        space_label.setText(" ");
        space_label.setFont(new Font("Verdana", Font.BOLD, 1));
        space_label.setForeground(Color.LIGHT_GRAY);

        protein_label.setText("PROTEIN: " + loggeddata[1] + "/" + goalsdata[1]);
        protein_label.setFont(new Font("Verdana", Font.BOLD, 20));
        protein_label.setForeground(Color.LIGHT_GRAY);

        date_label.setText(String.valueOf(LocalDate.now()));
        date_label.setFont(new Font("Verdana", Font.BOLD, 15));
        date_label.setForeground(Color.GRAY);

        //this label will add the Title
        MainLabels.add(l);
        //this will add space between info
        MainLabels.add(space_label);
        //this will display calories label
        MainLabels.add(calorie_label);
        MainLabels.add(space_label);
        //this will display protein label
        MainLabels.add(protein_label);
        //this will show a label of current day
        MainLabels.add(date_label);

        MainLabels.setBackground(verydarkgray);
        //add buttons
        buttons.add(panel);

        //add buttons to main frame
        f.add(buttons, BorderLayout.EAST);
        //add labels to main frame
        f.add(MainLabels, BorderLayout.NORTH);
        //set program size
        f.setSize(500, 500);
        //make the program unable to be resized
        f.setResizable(false);
        f.show();

    }

    public static void main(String[] args) {
        new Main_test();
    }

    public static void save_goals() {
        try {
            FileWriter myWriter = new FileWriter("Config/Goals/goals.txt");
            myWriter.write(String.valueOf(calorie_goal));
            myWriter.write("\n");
            myWriter.write(String.valueOf(protein_goal));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //save data and update the labels
        goals_reader();
        logged_data();
        calorie_label.setText("CALORIES: " + total_calories + "/" + goalsdata[0]);
        protein_label.setText("PROTEIN: " + total_protein + "/" + goalsdata[1]);

    }

    public static void update_calories() {
        //log the data and update labels
        log_data();
        calorie_label.setText("CALORIES: " + total_calories + "/" + goalsdata[0]);
        protein_label.setText("PROTEIN: " + total_protein + "/" + goalsdata[1]);
    }

    private static void hovereffect(JButton button) {
        button.setBorder(new LineBorder(Color.GRAY, 2)); //create borders around buttons
        button.setForeground(Color.LIGHT_GRAY); //set button text color
        button.setBackground(Color.DARK_GRAY); //set button background color
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change border color when mouse enters the button
                button.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore initial border color when mouse exits the button
                button.setBorder(new LineBorder(Color.GRAY, 2));
            }
        });
    }

    public static void read_data(DefaultTableModel model) {
        String filePath = "Config/Logs/" + LocalDate.now() + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of strings using the commas
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goals_reader() { //this will read the goals file and then set it as goalsdata array for use in program
        try {
            int i = 0;
            File myObj = new File("Config/Goals/goals.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                goalsdata[i] = data;
                i++;

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logged_data() { //this will create new file in Data folder with name as the date then log it into data
        try {
            int i = 0;
            File myObj = new File("Config/Data/" + LocalDate.now() + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                loggeddata[i] = data;
                i++;

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save_data() { //this will save the data when you add food

        // Specify the file path
        String filePath = "Config/Logs/" + LocalDate.now() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Concatenate the variables and write to the file
            writer.write(food_name + "," + savecalories + "," + saveprotein);
            writer.newLine(); // Add a newline for the next set of variables

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static void log_data() { //this will log calories and protein

        // Specify the file path
        String filePath = "Config/Data/" + LocalDate.now() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Concatenate the variables and write to the file
            writer.write(String.valueOf(total_calories));
            writer.write("\n");
            writer.write(String.valueOf(total_protein));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create_logs() { //this is used for new file creation, it will need to have data as 0 so if its ran on new day it wouldn't crash
        String filePath = "Config/Data/" + LocalDate.now() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("0");
            writer.write("\n");
            writer.write("0.0");
            writer.newLine(); // Add a newline for the next set of variables


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) { //this is used to catch action

    }
}
