package Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;

public class Main_test extends JFrame implements ActionListener {
    ImageIcon mainicon = new ImageIcon(("Images/logo.png"));
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
    static float total_protein = 0;
    static int calorie_goal = 0;
    static double protein_goal = 0;

    static String[]goalsdata = {"",""};
    static Color verydarkgray = new Color(27,27,27);

    Boolean show_food_label = false; //if true it will show a label that food has been added.
    JPanel buttons = new JPanel();
    JPanel MainLabels = new JPanel();
    Main_test() {
        //System.out.println("Resource URL: " + "../../Images/logo.png");
        goals_reader();
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
                food_name =JOptionPane.showInputDialog(f,"Enter name of the food","",JOptionPane.PLAIN_MESSAGE);
                calories_input =JOptionPane.showInputDialog(f,"Enter the amount of calories","",JOptionPane.PLAIN_MESSAGE);

                while(true){
                    try{
                        int calories = Integer.parseInt(calories_input);
                        System.out.println(calories);
                        if(calories>= 0){               //this is put for 0 because water might be logged, which has no calories nor protein
                            total_calories+=calories;
                            savecalories=calories;
                            update_calories();
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
                f.setVisible(false);
                logs_frame = new JFrame("Logs");
                logs_label = new JLabel("blablabla");

                logs_frame.setBackground(Color.RED);
                logs_frame.setSize(500,500);

                String[][] data = {
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"apple","300","2.4"},
                        {"","",""},
                        {"apple","300","2.4"},

                        {"onion","200","1.0"}
                };
                String[] columnNames = {"Food", "Calories", "Protein"};

                log_table = new JTable(data,columnNames){
                    public boolean isCellEditable(int row, int column){
                        return false;
                    }
                };
                log_table.setBounds(30,40,200,300);
                log_table.setRowSelectionAllowed(false);
                JScrollPane sp = new JScrollPane(log_table);
                logs_frame.add(sp);


                logs_frame.setResizable(false);
                logs_frame.show();

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
        MainLabels.setBorder(new EmptyBorder(new Insets(30, 130, 5, 100)));

        l.setText("CALORIE TRACKER");
        l.setFont(new Font("Verdana",Font.BOLD,20));
        l.setForeground(Color.LIGHT_GRAY);

        calorie_label.setText("CALORIES: 0/"+goalsdata[0]);
        calorie_label.setFont(new Font("Verdana",Font.BOLD,20));
        calorie_label.setForeground(Color.LIGHT_GRAY);

        space_label.setText(" ");
        space_label.setFont(new Font("Verdana",Font.BOLD,1));
        space_label.setForeground(Color.LIGHT_GRAY);

        protein_label.setText("PROTEIN: 0.0/"+goalsdata[1]);
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
            FileWriter myWriter = new FileWriter("goals.txt");
            myWriter.write(String.valueOf(calorie_goal));
            myWriter.write("\n");
            myWriter.write(String.valueOf(protein_goal));
            myWriter.close();
            System.out.println("Wrote");
        } catch (IOException e) {
            System.out.println("error");
        }
        goals_reader();
        calorie_label.setText("CALORIES: "+total_calories+"/"+goalsdata[0]);
        protein_label.setText("PROTEIN: "+total_protein+"/"+goalsdata[1]);

    }
    public static void update_calories(){
        System.out.println("calories updated");

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
    public static void goals_reader(){
        try{
            int i = 0;
            File myObj = new File("goals.txt");
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

    public static void save_data() {

        // Specify the file path
        String filePath = "Logs/"+String.valueOf(LocalDate.now()+".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Concatenate the variables and write to the file
            writer.write(food_name + "," + savecalories + "," + saveprotein);
            writer.newLine(); // Add a newline for the next set of variables
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("error");
        }
        System.out.println("the data has been saved");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
