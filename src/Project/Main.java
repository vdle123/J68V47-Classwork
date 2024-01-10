package Project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.X_AXIS;

public class Main extends JFrame implements ActionListener {

    static JButton a, b, c;
    static JFrame f;
    static JLabel l;

    JPanel buttons = new JPanel();
    JPanel MainLabels = new JPanel();
    Main() {
        f = new JFrame("panel");
        l = new JLabel("");

        a = new JButton("Add Food");
        a.setFont(new Font("Verdana",Font.BOLD,12));
        a.setHorizontalTextPosition(SwingConstants.CENTER);
        a.setLocation(50,50);
        b = new JButton("Set Goals");
        b.setFont(new Font("Verdana",Font.BOLD,12));
        b.setHorizontalTextPosition(SwingConstants.CENTER);

        c = new JButton("Logs");
        c.setFont(new Font("Verdana",Font.BOLD,12));
        c.setHorizontalTextPosition(SwingConstants.CENTER);

        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons.setBackground(Color.RED);
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons.setBackground(Color.BLACK);
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons.setBackground(Color.BLUE);
            }
        });
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBorder(new EmptyBorder(new Insets(100, 250, 150, 200)));


        MainLabels.setLayout(new BoxLayout(MainLabels, BoxLayout.Y_AXIS));
        MainLabels.setBorder(new EmptyBorder(new Insets(10, 175, 5, 150)));

        l.setText("CALORIE TRACKER");
        l.setFont(new Font("Verdana",Font.BOLD,14));
        l.setForeground(Color.WHITE);

        MainLabels.add(l);
        MainLabels.setBackground(Color.BLACK);

        buttons.add(a);
        buttons.add(b);
        buttons.add(c);
        buttons.setBackground(Color.BLACK);

        f.add(buttons, BorderLayout.EAST);
        f.add(MainLabels, BorderLayout.NORTH);

        f.setSize(500, 500);
        f.setResizable(false);
        f.show();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
