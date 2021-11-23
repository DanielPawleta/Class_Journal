package swingTest;

import javax.swing.*;
import java.awt.*;

public class GridLayoutTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,150);
        frame.setLayout(new GridLayout(3,3,10,10));

        for (int i=1;i<19;i++){
            frame.add(new JButton(String.valueOf(i)));
        }

        frame.setVisible(true);
    }
}
