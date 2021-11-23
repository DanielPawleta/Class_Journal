package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JRadioButtonTest {
    public static void main(String[] args) {
        MyFrame4 myFrame = new MyFrame4();
    }
}

class MyFrame4 extends JFrame implements ActionListener {



    public MyFrame4() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        JRadioButton pizzaButton = new JRadioButton("pizza");
        JRadioButton hamburgerButton = new JRadioButton("hamburger");
        JRadioButton hotDogButton = new JRadioButton("hot dog");

        ButtonGroup group = new ButtonGroup();
        group.add(pizzaButton);
        group.add(hamburgerButton);
        group.add(hotDogButton);

        this.add(pizzaButton);
        this.add(hamburgerButton);
        this.add(hotDogButton);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}