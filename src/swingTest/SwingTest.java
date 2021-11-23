package swingTest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingTest {

    public static void main(String[] args) {
        /*
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Swing test");
        jFrame.setVisible(true);
        jFrame.setSize(500,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
f
        ImageIcon imageIcon = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.getContentPane().setBackground(Color.RED);

         */

        MyFrame myFrame = new MyFrame();
        JLabel label = new JLabel();
        label.setText("Macio");
        label.setIcon(new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg"));
        //myFrame.getContentPane().setBackground(Color.RED);
        myFrame.add(label);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.GREEN);
        label.setFont(new Font("My Font",Font.PLAIN,20));
        label.setIconTextGap(10);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        //label.setBounds(10,10,100,100);

        Border border = BorderFactory.createLineBorder(Color.green);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        //myFrame.setLayout(null);
        //myFrame.pack();

        myFrame.setLayout(null);
        myFrame.remove(label);

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(0,0,150,150);

        myFrame.add(redPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.GREEN);
        greenPanel.setBounds(150,0,150,150);

        myFrame.add(greenPanel);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setBounds(0,150,300,150);

        myFrame.add(bluePanel);

        JLabel label2 = new JLabel();
        label2.setText("elo");
        label2.setIcon(new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg"));
        label2.setBounds(10,10,200,200);
        label2.setOpaque(false);
        //label2.setSize(100,100);
        redPanel.add(label2);
        redPanel.setLayout(new BorderLayout());
        label2.setVerticalAlignment(SwingConstants.BOTTOM);
        label2.setHorizontalAlignment(SwingConstants.RIGHT);


        ButtonTest myFrame2 = new ButtonTest();















    }
}
