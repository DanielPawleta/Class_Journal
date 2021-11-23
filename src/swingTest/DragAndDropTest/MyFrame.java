package swingTest.DragAndDropTest;

import javax.swing.*;

public class MyFrame extends JFrame {

    DragPanel dragPanel = new DragPanel();

    public MyFrame() {
        this.setTitle("Drag drop test");
        this.setSize(600,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);



        this.add(dragPanel);
        this.setVisible(true);
    }
}
