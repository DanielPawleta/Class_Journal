package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JColorChooserTest {
    public static void main(String[] args) {
        MouseListenerDemo colorChooserDemo = new MouseListenerDemo();
    }
}


class JColorChooserDemo extends JFrame implements ActionListener {

    JButton button;
    JLabel label;

    public JColorChooserDemo() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());

        button = new JButton("Choose a color");
        button.addActionListener(this);

        label = new JLabel();
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setText("This is some text");
        label.setFont(new Font("MV Boli", Font.PLAIN, 100));

        this.add(button);
        this.add(label);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            //JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Pick color test", Color.BLACK);
            label.setForeground(color);
        }
    }
}



