package Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    JMenuBar menubar;
    JMenu home_menu;
    JMenu about_menu;
    Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setTitle("Demo");
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        menubar = new JMenuBar();
        JMenu home_menu = new JMenu("HOME");
        JMenu about_menu = new JMenu("ABOUT");

        this.setJMenuBar(menubar);
        menubar.add(home_menu);
        menubar.add(about_menu);
        home_menu.addActionListener(this);
        about_menu.addActionListener(this);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource()==home_menu){
            System.out.println("Home button");
        }
        else if(evt.getSource()==about_menu){
            System.out.println("About button");
        }

    }
}
