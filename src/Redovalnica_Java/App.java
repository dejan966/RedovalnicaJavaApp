package Redovalnica_Java;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    private JPanel panelApp;
    private JTabbedPane tabbedPane1;
    private JComboBox SolskoLetoComboBoxP;
    private JComboBox RazredComboBoxP;
    private JComboBox PredmetComboBoxP;
    private JComboBox VrstaUrComboBox;
    private JTextField textField1;
    private JButton potrdiPrisotnostButton;
    private JButton preveriPrisotnostZaNazajButton;
    private JTree tree1;
    private JPanel jCal;
    private JButton vnesiOcenoButton;
    private JButton statistikaZaOceneButton;
    private JComboBox SolskoLetoComboBoxO;
    private JComboBox RazredComboBoxO;
    private JComboBox PredmetComboBoxO;
    private JTree tree2;
    private JPanel jCal2;
    private JComboBox OcenaComboBox;

    JDateChooser chooser = new JDateChooser();
    JDateChooser chooser2 = new JDateChooser();

    public App() throws SQLException {
        JFrame jframe = new JFrame("Redovalnica");
        jframe.setContentPane(panelApp);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(700, 500);
        jframe.setResizable(false);
        jframe.setVisible(true);

        jCal.add(chooser);
        jCal2.add(chooser2);

        RedovalnicaDatabase rd = new RedovalnicaDatabase();
        for (Solsko_Leto item : rd.ReturnVsaSolskaLeta()){
            SolskoLetoComboBoxP.addItem(item.SLeto);
            SolskoLetoComboBoxO.addItem(item.SLeto);
        }

        RedovalnicaDatabase rd2 = new RedovalnicaDatabase();
        for (Razred item : rd2.ReturnVseRazrede()){
            RazredComboBoxP.addItem(item.ImeR);
            RazredComboBoxO.addItem(item.ImeR);
        }

        RedovalnicaDatabase rd3 = new RedovalnicaDatabase();
        for (RazredPredmet item : rd3.ReturnVsePredmete()){
            PredmetComboBoxP.addItem(item.ImeP);
            PredmetComboBoxO.addItem(item.ImeP);
        }

        RedovalnicaDatabase rd4 = new RedovalnicaDatabase();
        for(Vrsta_Ur item : rd4.ReturnVseVrsteUr())
            VrstaUrComboBox.addItem(item.Ura);

        RedovalnicaDatabase rd5 = new RedovalnicaDatabase();
        for(Ocena item : rd5.ReturnVseOcene())
            OcenaComboBox.addItem(item.StO);

        statistikaZaOceneButton.addActionListener(e -> {
            jframe.dispose();
            new Statistika();
        });
        potrdiPrisotnostButton.addActionListener(e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String Idate = dtf.format(now);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Sdate = sdf.format(chooser.getDate());
            JOptionPane.showMessageDialog(null, Sdate);
        });
    }
}
