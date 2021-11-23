package swingTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerTest {
    public static void main(String[] args) {
        MouseListenerDemo mouseListenerDemo = new MouseListenerDemo();
    }
}


class MouseListenerDemo extends JFrame implements MouseListener {


    JLabel label;

    public MouseListenerDemo() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        label = new JLabel();
        label.setBounds(0,0,100,100);
        label.setBackground(Color.RED);
        label.setOpaque(true);
        this.addMouseListener(this);


        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
        label.setBackground(Color.YELLOW);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release");
        label.setBackground(Color.GREEN);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("enter the area");
        label.setBackground(Color.BLUE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exit area");
        label.setBackground(Color.RED);

    }
}



