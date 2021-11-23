package swingTest;

import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame(){

        this.setTitle("Swing test");
        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);

        ImageIcon imageIcon = new ImageIcon("C:/Users/dpawleta/Downloads/CodeGymTasks/4.JavaCollections/src/test/swingTest/logo.jpg");
        //this.setIconImage(imageIcon.getImage());
        //this.getContentPane().setBackground(Color.RED);
    }

}
