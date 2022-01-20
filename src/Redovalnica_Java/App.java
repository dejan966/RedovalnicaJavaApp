package Redovalnica_Java;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel panelApp;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JTextField textField1;
    private JButton potrdiPrisotnostButton;
    private JButton preveriPrisotnostZaNazajButton;
    private JTree tree1;
    private JPanel jCal;
    private JButton vnesiOcenoButton;
    private JButton statistikaZaOceneButton;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JTextField textField2;
    private JTree tree2;
    private JPanel jCal2;

    JDateChooser chooser = new JDateChooser();
    JDateChooser chooser2 = new JDateChooser();
    public App(){
        JFrame jframe = new JFrame("Redovalnica");
        jframe.setContentPane(panelApp);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(700, 500);
        jframe.setResizable(false);
        jframe.setVisible(true);

        jCal.add(chooser);
        jCal2.add(chooser2);
        statistikaZaOceneButton.addActionListener(e -> {
            jframe.dispose();
            new Statistika();
        });
    }
}
