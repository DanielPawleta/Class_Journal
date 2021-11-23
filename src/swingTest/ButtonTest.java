package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest extends JFrame implements ActionListener {

    JButton button;

    public ButtonTest(){

        this.setTitle("Swing button test");
        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        button = new JButton();
        button.setBounds(200,100,250,100);
        button.addActionListener(this);
        button.setText("elo");
        button.setFocusable(false);
        button.setFont(new Font("myFont",Font.BOLD,25));
        button.setForeground(Color.CYAN);
        button.setBackground(Color.RED);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //button.

        this.add(button);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        {
            System.out.println("a");
            button.setEnabled(false);
            this.add(displayLabel());

        }
    }

    public JLabel displayLabel(){
        JLabel label = new JLabel();
        label.setSize(100,100);
        label.setBackground(Color.BLUE);
        return label;
    }
}
