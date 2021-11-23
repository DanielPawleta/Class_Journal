package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TogglePanel {
    public static void main(String[] args) {
        final CardLayout cardlayout = new CardLayout();
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container contentPane = frame.getContentPane();
        contentPane.setLayout(cardlayout);

        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        JButton button1 = new JButton("previous frame!");
        JButton button2 = new JButton("next frame");
        contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.add(panel1, "Panel 1");
        contentPane.add(panel2, "Panel 2");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);

        panel1.add(button2);
        panel2.add(button1);

        ActionListener btnListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardlayout.next(contentPane);
            }
        };

        button1.addActionListener(btnListener);
        button2.addActionListener(btnListener);

    }
}