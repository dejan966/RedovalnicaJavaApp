package Redovalnica_Java;

import javax.swing.*;
import java.sql.SQLException;

public class LoginForm {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton prijavaButton;
    private JPanel panelPrijava;

    public LoginForm() {
        JFrame jframe = new JFrame("Prijava");
        jframe.setContentPane(panelPrijava);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(400, 300);
        jframe.setResizable(false);
        jframe.setVisible(true);

        prijavaButton.addActionListener(e -> {
            String sMail = textField1.getText();
            String geslo = passwordField1.getText();
            Ucitelj uc = new Ucitelj(sMail, geslo);
            try {
                RedovalnicaDatabase rd = new RedovalnicaDatabase();
                if(rd.PreveriPrijavo(uc)){
                    jframe.dispose();
                    new App();
                }
                else
                    JOptionPane.showMessageDialog(null, "Napačno geslo ali email.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
