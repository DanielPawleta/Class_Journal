package swingTest;

import javax.swing.*;
import java.awt.*;

public class ProgressBarTest {

    public static void main(String[] args) {
        ProgressBarDemo demo = new ProgressBarDemo();
    }
}
    class ProgressBarDemo {
        JFrame frame = new JFrame();
        JProgressBar bar = new JProgressBar();

        public ProgressBarDemo() {

            bar.setValue(0);
            bar.setBounds(0,0,500,50);
            bar.setStringPainted(true);
            bar.setFont(new Font("MV Boli",Font.BOLD,25));
            bar.setForeground(Color.RED);
            bar.setBackground(Color.BLACK);

            frame.add(bar);

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(500,500);
            frame.setLayout(null);

            frame.setVisible(true);

            fill();
            bar.setString("Done bitch!");



        }

        public void fill(){
            int counter = 20;

            while (counter>0){
                bar.setValue(counter);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter-=1;
            }
        }
    }

