package Redovalnica_Java;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTree PrisotnostTree;
    private JPanel jCal;
    private JButton vnesiOcenoButton;
    private JButton statistikaZaOceneButton;
    private JComboBox SolskoLetoComboBoxO;
    private JComboBox RazredComboBoxO;
    private JComboBox PredmetComboBoxO;
    private JTree OcenaTree;
    private JPanel jCal2;
    private JComboBox OcenaComboBox;
    private JLabel jLabelPrijava;
    private JLabel jLabelPrijava2;

    static String sMail;
    static String imePriimekUcitelja;

    JDateChooser chooser = new JDateChooser();
    JDateChooser chooser2 = new JDateChooser();

    public static void MailUcitelja(String mail)
    {
        sMail = mail;
    }

    public void UpdatePrisotnostJTree(final String nodeToAdd){
        DefaultTreeModel model = (DefaultTreeModel) PrisotnostTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) PrisotnostTree.getModel().getRoot();
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeToAdd);
        model.insertNodeInto(child, root, root.getChildCount());
        PrisotnostTree.scrollPathToVisible(new TreePath(child.getPath()));
        model.reload();
    }
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

        //removing the default items from PrisotnostJtree
        DefaultTreeModel modelP = (DefaultTreeModel) PrisotnostTree.getModel();
        DefaultMutableTreeNode rootP = (DefaultMutableTreeNode) modelP.getRoot();
        rootP.removeAllChildren();
        modelP.reload();
        modelP.setRoot(null);

        //removing the default items from OcenaJtree
        DefaultTreeModel modelO = (DefaultTreeModel) OcenaTree.getModel();
        DefaultMutableTreeNode rootO = (DefaultMutableTreeNode) modelO.getRoot();
        rootO.removeAllChildren();
        modelO.reload();
        modelO.setRoot(null);

        RedovalnicaDatabase rb = new RedovalnicaDatabase();
        Ucitelj mail = new Ucitelj(sMail);
        imePriimekUcitelja = rb.ReturnImePriimekUcitelja(mail);
        jLabelPrijava.setText("Prijavljeni ste kot " + imePriimekUcitelja);
        jLabelPrijava2.setText("Prijavljeni ste kot " + imePriimekUcitelja);

        RedovalnicaDatabase rd = new RedovalnicaDatabase();
        for (Solsko_Leto item : rd.ReturnVsaSolskaLeta()){
            SolskoLetoComboBoxP.addItem(item.SLeto);
            SolskoLetoComboBoxO.addItem(item.SLeto);
        }

        /*RedovalnicaDatabase rd2 = new RedovalnicaDatabase();
        for (Razred item : rd2.ReturnVseRazrede()){
            RazredComboBoxP.addItem(item.ImeR);
            RazredComboBoxO.addItem(item.ImeR);
        }*/

        try {
            RedovalnicaDatabase s = new RedovalnicaDatabase();
            Solsko_Leto sl = new Solsko_Leto(SolskoLetoComboBoxP.getSelectedItem().toString());
            for(Razred item : s.ReturnRazred_SolskoLeto(sl)){
                RazredComboBoxP.addItem(item.ImeR);
                RazredComboBoxO.addItem(item.ImeR);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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

        RedovalnicaDatabase rt = new RedovalnicaDatabase();
        Razred r = new Razred(RazredComboBoxP.getSelectedItem().toString());
        for(Ucenec item: rt.ReturnUcenci_Razred(r)){
            /*rootP.add(new DefaultMutableTreeNode("Učenci"));
            rootP.add(new DefaultMutableTreeNode(item.Ime + " " + item.Priimek));
            modelP.reload();*/
            //UpdatePrisotnostJTree(item.Ime + " " + item.Priimek);
            /* DefaultTreeModel model = (DefaultTreeModel)PrisotnostTree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
            root.add(new DefaultMutableTreeNode("Učenci"));
            root.add(new DefaultMutableTreeNode(item.Ime + " " + item.Priimek));
            model.reload(root);*/
            /* Dodaš nov node v ttmu nodu, ki je selectan
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)OcenaTree.getSelectionPath().getLastPathComponent();
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(item.Ime + " " + item.Priimek);
            selectedNode.add(newNode);

            DefaultTreeModel model = (DefaultTreeModel)PrisotnostTree.getModel();
            model.reload(); */
            /* DefaultTreeModel model = (DefaultTreeModel)PrisotnostTree.getModel();
            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Učenec");
            DefaultMutableTreeNode childNodes = new DefaultMutableTreeNode(item.Ime + " " + item.Priimek);
            rootNode.add(childNodes);
            PrisotnostTree = new JTree(rootNode);*/
            DefaultTreeModel model = (DefaultTreeModel)PrisotnostTree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
            root.add(new DefaultMutableTreeNode("another_child"));
            model.reload(root);
        }
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
            if(SolskoLetoComboBoxP.getSelectedItem().toString() != "" && RazredComboBoxP.getSelectedItem().toString() != "" && PredmetComboBoxP.getSelectedItem().toString() != "" && VrstaUrComboBox.getSelectedItem().toString() != ""){
                String opomba = textField1.getText();
                try {
                    RedovalnicaDatabase rp = new RedovalnicaDatabase();
                    RazredPredmet razredPredmet = new RazredPredmet(PredmetComboBoxP.getSelectedItem().toString(), RazredComboBoxP.getSelectedItem().toString(), imePriimekUcitelja, SolskoLetoComboBoxP.getSelectedItem().toString());
                    rp.InsertRazrediPredmeti(razredPredmet);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int idRazredPredmetR = 0;
                try {
                    RedovalnicaDatabase rp2 = new RedovalnicaDatabase();
                    RazredPredmet idrazredPredmet = new RazredPredmet(PredmetComboBoxP.getSelectedItem().toString(), RazredComboBoxP.getSelectedItem().toString(), imePriimekUcitelja, SolskoLetoComboBoxP.getSelectedItem().toString());
                    idRazredPredmetR = rp2.IDRazrediPredmeti(idrazredPredmet);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    RedovalnicaDatabase ru = new RedovalnicaDatabase();
                    UreIzvedbe ure = new UreIzvedbe(idRazredPredmetR, VrstaUrComboBox.getSelectedItem().toString(), Idate);
                    ru.InsertUreIzvedb(ure);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int idUreIzvedbR = 0;
                try {
                    RedovalnicaDatabase ru2 = new RedovalnicaDatabase();
                    UreIzvedbe idure = new UreIzvedbe(idRazredPredmetR, VrstaUrComboBox.getSelectedItem().toString(), Sdate);
                    idUreIzvedbR = ru2.IDUreIzvedb(idure);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                /*for (int i = 0; i < mU.Length; i++)
                {
                    Prisotnost danasnjaPrisotnost = new Prisotnost(mU[i], idUreIzvedbR, opomba);
                    try {
                        RedovalnicaDatabase re = new RedovalnicaDatabase();
                        re.InsertPrisotnosti(danasnjaPrisotnost);
                        JOptionPane.showMessageDialog(null, "Uspešno dodana prisotnost za ta dan.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }*/
            }
        });
        SolskoLetoComboBoxP.addItemListener(e -> {
            RazredComboBoxP.removeAllItems();

            try {
                RedovalnicaDatabase s = new RedovalnicaDatabase();
                Solsko_Leto sl = new Solsko_Leto(SolskoLetoComboBoxP.getSelectedItem().toString());
                for(Razred item : s.ReturnRazred_SolskoLeto(sl))
                    RazredComboBoxP.addItem(item.ImeR);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        SolskoLetoComboBoxO.addItemListener(e -> {
            RazredComboBoxO.removeAllItems();
            try {
                RedovalnicaDatabase s = new RedovalnicaDatabase();
                Solsko_Leto sl = new Solsko_Leto(SolskoLetoComboBoxO.getSelectedItem().toString());
                for(Razred item : s.ReturnRazred_SolskoLeto(sl))
                    RazredComboBoxO.addItem(item.ImeR);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        RazredComboBoxP.addItemListener(e -> {
            //PrisotnostJtree
        });
        RazredComboBoxO.addItemListener(e -> {
            //OcenaJtree
        });
        vnesiOcenoButton.addActionListener(e -> {
            if(SolskoLetoComboBoxP.getSelectedItem().toString() != "" && RazredComboBoxP.getSelectedItem().toString() != "" && PredmetComboBoxP.getSelectedItem().toString() != "" && VrstaUrComboBox.getSelectedItem().toString() != ""){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String Sdate = sdf.format(chooser.getDate());
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)OcenaTree.getSelectionPath().getLastPathComponent();
                String ucenec = selectedNode.getUserObject().toString();
                try {
                    RedovalnicaDatabase rs = new RedovalnicaDatabase();
                    Ocena ocena = new Ocena(ucenec, OcenaComboBox.getSelectedItem().toString(), Sdate, PredmetComboBoxO.getSelectedItem().toString(), RazredComboBoxO.getSelectedItem().toString(), SolskoLetoComboBoxO.getSelectedItem().toString(), imePriimekUcitelja);
                    //funkcija
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ocene ni bilo mogoče dodati za ucenca " + ucenec + ".", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
            });
        preveriPrisotnostZaNazajButton.addActionListener(e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Sdate = sdf.format(chooser.getDate());

            if(SolskoLetoComboBoxP.getSelectedItem().toString() != "" && RazredComboBoxP.getSelectedItem().toString() != "" && PredmetComboBoxP.getSelectedItem().toString() != "" && VrstaUrComboBox.getSelectedItem().toString() != ""){
                Prisotnost prisotnostZaNazaj = new Prisotnost(PredmetComboBoxP.getSelectedItem().toString(), RazredComboBoxP.getSelectedItem().toString(), SolskoLetoComboBoxP.getSelectedItem().toString(), VrstaUrComboBox.getSelectedItem().toString(), Sdate);
                try {
                    /*PrisotnostTreeView.Nodes.Clear();
                    PrisotnostTreeView.Nodes.Add("Učenci");*/
                    RedovalnicaDatabase rrp = new RedovalnicaDatabase();
                    /*for (Ucenec item : rrp.ReturnUcenci_Razred_Predmet_Vrsta_Ure_SolskoLeto_Datum(prisotnostZaNazaj))
                    {
                        if (item.Ime != "" && item.Priimek != "")
                            PrisotnostTreeView.Nodes[0].Nodes.Add(item.Ime + ' ' + item.Priimek);
                    }*/
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            });
    }
}
