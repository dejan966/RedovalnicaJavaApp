package Redovalnica_Java;

import javax.swing.*;

public class Statistika {
    private JPanel jpanel;

    public Statistika(){
        JFrame jframe = new JFrame("Statistika za ocene");
        jframe.setContentPane(jpanel);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(700, 500);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
}
