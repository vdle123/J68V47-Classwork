package Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;

public class Main_test extends JFrame implements ActionListener {
    ImageIcon mainicon = new ImageIcon(("src/Project/Configs/Images/logo.png"));
    static Date_Manager date_manager = new Date_Manager(); //access date manager class
    static double saveprotein = 0;
    static int savecalories = 0;
    static JButton add_food_button, set_goals, see_logs;
    static JFrame f, logs_frame;
    static JLabel l, logs_label,calorie_label,protein_label, space_label, date_label;
    static JTable log_table;
    static String food_name = "";
    String calories_input = "";
    String protein_input = "";
    static int total_calories = 0;
    static double total_protein = 0;
    static int calorie_goal = 0;
    static double protein_goal = 0;

    static String[]goalsdata = {"",""};
    static String[]loggeddata = {"",""};
    static Color verydarkgray = new Color(27,27,27);

    Boolean show_food_label = false; //if true it will show a label that food has been added.
    JPanel buttons = new JPanel();
    JPanel MainLabels = new JPanel();
    Main_test() {
        //create_logs();
        if(Files.exists(Paths.get("src/Project/Configs/Data/"+String.valueOf(LocalDate.now())+".txt"))){
            System.out.println("does exist");
        }else{
            create_logs();
        }
        goals_reader();
        logged_data();
        log_data();
        //save_data();
        total_calories = Integer.parseInt(loggeddata[0]);
        total_protein = Double.parseDouble(loggeddata[1]);

        f = new JFrame("Calorie Tracker");
        f.setIconImage(mainicon.getImage());
        l = new JLabel("");
        calorie_label = new JLabel("");
        protein_label = new JLabel("");
        space_label = new JLabel("");
        date_label = new JLabel("");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add_food_button = new JButton("Add Food");
        add_food_button.setFont(new Font("Verdana",Font.BOLD,15));

        set_goals = new JButton("Goals");
        set_goals.setFont(new Font("Verdana",Font.BOLD,15));

        see_logs = new JButton("Logs");
        see_logs.setFont(new Font("Verdana",Font.BOLD,15));

        hovereffect(add_food_button);
        hovereffect(set_goals);
        hovereffect(see_logs);

        add_food_button.addActionListener(new ActionListener() {
            @Override
            //This will ask for food name, calories and protein once the button is pressed
            public void actionPerformed(ActionEvent e) {
                food_name =(String) JOptionPane.showInputDialog(f,"Enter name of the food","",JOptionPane.PLAIN_MESSAGE);
                calories_input =JOptionPane.showInputDialog(f,"Enter the amount of calories","",JOptionPane.PLAIN_MESSAGE);

                while(true){
                    try{
                        int calories = Integer.parseInt(calories_input);
                        System.out.println(calories);
                        if(calories>= 0){               //this is put for 0 because water might be logged, which has no calories nor protein
                            total_calories+=calories;
                            savecalories=calories;
                            //update_calories();
                            break;
                        }else{
                            System.out.println("Please enter a positive number");
                        }
                        System.out.println(calories);
                        break;
                    } catch (NumberFormatException eInt) {
                        calories_input =JOptionPane.showInputDialog(f,"Error, please re-enter the number (int)","",JOptionPane.PLAIN_MESSAGE);
                    }}

                protein_input =JOptionPane.showInputDialog(f,"Enter the amount of protein","",JOptionPane.PLAIN_MESSAGE);
                while(true){
                    try{
                        double protein = Double.parseDouble(protein_input);
                        if(protein>=0.0){
                            System.out.println(protein);
                            total_protein+=protein;
                            saveprotein = protein;
                            update_calories();
                            save_data();
                            show_food_label = true;
                            break;

                        }else {
                            System.out.println("Please enter a positive number");
                        }
                        System.out.println(protein);
                        break;
                    } catch (NumberFormatException eDouble) {
                        protein_input =JOptionPane.showInputDialog(f,"Error, please re-enter the number","",JOptionPane.PLAIN_MESSAGE);
                    }
                }if(show_food_label==true){
                    JOptionPane.showMessageDialog(f,"The food has been added.","",JOptionPane.PLAIN_MESSAGE);}
            }
        });








        set_goals.addActionListener(new ActionListener() {

            @Override
            //This will allow to set goals of Calories and Protein once set goals button is pressed
            public void actionPerformed(ActionEvent e) {
                calories_input =JOptionPane.showInputDialog(f,"Enter the goal of calories","",JOptionPane.PLAIN_MESSAGE);

                while(true){
                    try{
                        int calories = Integer.parseInt(calories_input);
                        System.out.println(calories);
                        if(calories> 0){
                            calorie_goal=calories;
                            break;
                        }else{
                            System.out.println("Please enter a positive number");
                        }
                        System.out.println(calories);
                    } catch (NumberFormatException eInt) {
                        calories_input =JOptionPane.showInputDialog(f,"Error, please re-enter the number (int)","",JOptionPane.PLAIN_MESSAGE);
                    }}

                protein_input =JOptionPane.showInputDialog(f,"Enter the amount of protein","",JOptionPane.PLAIN_MESSAGE);
                while(true){
                    try{
                        double protein = Double.parseDouble(protein_input);
                        if(protein>0.0){
                            protein_goal=protein;
                            save_goals();
                            break;
                        }else{
                            System.out.println("Please enter a positive number");
                        }
                        System.out.println(protein);
                    } catch (NumberFormatException eDouble) {
                        protein_input =JOptionPane.showInputDialog(f,"Error, please re-enter the number","",JOptionPane.PLAIN_MESSAGE);
                    }

                }
            }});
        see_logs.addActionListener(new ActionListener() {
            //This will handle the logs
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame logs_frame = new JFrame("Data Log - "+String.valueOf(LocalDate.now()));
                logs_frame.setIconImage(mainicon.getImage());
                logs_frame.setBackground(verydarkgray);

                // Create a table model with column names
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Food");
                tableModel.addColumn("Calories");
                tableModel.addColumn("Protein");


                // Create a JTable with the table model
                JTable table = new JTable(tableModel){
                      public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };

                // Create a JScrollPane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBackground(verydarkgray);


                // Add the scroll pane to the frame
                logs_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JViewport viewport = scrollPane.getViewport();
                viewport.setBackground(verydarkgray);
                logs_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
                logs_frame.getContentPane().setBackground(verydarkgray);

                logs_frame.getContentPane().setBackground(verydarkgray);

                // Read data from the file and populate the table
                read_data(tableModel);
                table.setRowSelectionAllowed(false);

                table.setBackground(verydarkgray);
                table.setForeground(Color.LIGHT_GRAY);

                table.getTableHeader().setBackground(Color.DARK_GRAY);
                table.getTableHeader().setForeground(Color.LIGHT_GRAY);

                logs_frame.setBackground(verydarkgray);
                scrollPane.setBackground(verydarkgray);
                scrollPane.setForeground(verydarkgray);

                logs_frame.setSize(400, 300);
                logs_frame.setBackground(verydarkgray);
                logs_frame.setResizable(false);
                logs_frame.setLocationRelativeTo(null);
                logs_frame.setVisible(true);




            }
        });
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(new EmptyBorder(new Insets(150, 100, 115, 100)));
        buttons.setBackground(verydarkgray);


        Dimension buttonSize = new Dimension(90,5);
        add_food_button.setPreferredSize(buttonSize);
        set_goals.setPreferredSize(buttonSize);
        see_logs.setPreferredSize(buttonSize);


        JPanel panel = new JPanel(new GridLayout(1,3, 20,0));
        panel.setBackground(verydarkgray);
        panel.add(add_food_button);
        panel.add(set_goals);
        panel.add(see_logs);


        MainLabels.setLayout(new BoxLayout(MainLabels, BoxLayout.Y_AXIS));
        MainLabels.setBorder(new EmptyBorder(new Insets(30, 130, 5, 10)));

        l.setText("CALORIE TRACKER");
        l.setFont(new Font("Verdana",Font.BOLD,20));
        l.setForeground(Color.LIGHT_GRAY);

        calorie_label.setText("CALORIES: "+ loggeddata[0]+ "/"+goalsdata[0]);
        if (loggeddata[0]==null){
            calorie_label.setText("CALORIES: 0/"+goalsdata[0]);
        }
        calorie_label.setFont(new Font("Verdana",Font.BOLD,20));
        calorie_label.setForeground(Color.LIGHT_GRAY);

        space_label.setText(" ");
        space_label.setFont(new Font("Verdana",Font.BOLD,1));
        space_label.setForeground(Color.LIGHT_GRAY);

        protein_label.setText("PROTEIN: "+loggeddata[1]+"/"+goalsdata[1]);
        protein_label.setFont(new Font("Verdana", Font.BOLD,20));
        protein_label.setForeground(Color.LIGHT_GRAY);

        date_label.setText(String.valueOf(LocalDate.now()));
        date_label.setFont(new Font("Verdana", Font.BOLD,15));
        date_label.setForeground(Color.GRAY);




        MainLabels.add(l);
        MainLabels.add(space_label);
        MainLabels.add(calorie_label);
        MainLabels.add(space_label);
        MainLabels.add(protein_label);
        MainLabels.add(date_label);

        MainLabels.setBackground(verydarkgray);

        buttons.add(panel);


        f.add(buttons, BorderLayout.EAST);
        f.add(MainLabels, BorderLayout.NORTH);

        f.setSize(500, 500);
        f.setResizable(false);
        f.show();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_test());
        //date_manager.check_and_write_date();
        //date_manager.readAndRemoveOldRecords();

    }
    public static void save_goals(){
        System.out.println("The goals have been saved");
        try {
            FileWriter myWriter = new FileWriter("src/Project/Configs/Goals/goals.txt");
            myWriter.write(String.valueOf(calorie_goal));
            myWriter.write("\n");
            myWriter.write(String.valueOf(protein_goal));
            myWriter.close();
            System.out.println("Wrote");
        } catch (IOException e) {
            System.out.println("error");
        }

        goals_reader();
        logged_data();
        calorie_label.setText("CALORIES: "+total_calories+"/"+goalsdata[0]);
        protein_label.setText("PROTEIN: "+total_protein+"/"+goalsdata[1]);

    }
    public static void update_calories(){
        System.out.println("calories updated");
        log_data();
        calorie_label.setText("CALORIES: "+total_calories+"/"+goalsdata[0]);
        protein_label.setText("PROTEIN: "+total_protein+"/"+goalsdata[1]);
    }
    private static void hovereffect(JButton button){
        button.setBorder(new LineBorder(Color.GRAY, 2));
        button.setForeground(Color.LIGHT_GRAY);
        button.setBackground(Color.DARK_GRAY);
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
        String filePath = "src/Project/Configs/Logs/" + String.valueOf(LocalDate.now() + ".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of strings using the comma as a delimiter
                String[] data = line.split(",");
                model.addRow(data);
            }
            System.out.println("Data read from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading data from file.");
        }
    }
    public static void goals_reader(){
        try{
            int i = 0;
            File myObj = new File("src/Project/Configs/Goals/goals.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){

                String data = myReader.nextLine();
                goalsdata[i]=data;
                System.out.println(goalsdata[i]);
                i++;

            }
            myReader.close();

        } catch (FileNotFoundException e){
            System.out.println("no goals have been set");
        }
    }
    public static void logged_data(){
        try{
            int i = 0;
            File myObj = new File("src/Project/Configs/Data/"+String.valueOf(LocalDate.now())+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){

                String data = myReader.nextLine();
                loggeddata[i]=data;
                System.out.println(loggeddata[i]);
                i++;

            }
            myReader.close();

        } catch (FileNotFoundException e){
            System.out.println("");
        }
    }

    public static void save_data() {

        // Specify the file path
        String filePath = "src/Project/Configs/Logs/"+String.valueOf(LocalDate.now()+".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                System.out.println(food_name);
                // Concatenate the variables and write to the file
                writer.write(food_name + "," + savecalories + "," + saveprotein);
                writer.newLine(); // Add a newline for the next set of variables
                System.out.println("Data written to file successfully.");

        } catch (IOException e) {
            System.out.println("error");
        }
        System.out.println("the data has been saved");
    }

    public static void log_data() {

        // Specify the file path
        String filePath = "src/Project/Configs/Data/"+String.valueOf(LocalDate.now()+".txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Concatenate the variables and write to the file
                writer.write(String.valueOf(total_calories));
                writer.write("\n");
                writer.write(String.valueOf(total_protein));
                writer.newLine(); // Add a newline for the next set of variables
                System.out.println("Data written to file successfully.");
            } catch (IOException e) {
                System.out.println("error");
            }
            System.out.println("the data has been saved");
        }
        public static void create_logs(){
            String filePath = "src/Project/Configs/Data/"+String.valueOf(LocalDate.now()+".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write("0");
                    writer.write("\n");
                    writer.write("0.0");
                    writer.newLine(); // Add a newline for the next set of variables


            } catch (IOException e) {
                System.out.println("error");
            }
            System.out.println("the data has been created");

        }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
