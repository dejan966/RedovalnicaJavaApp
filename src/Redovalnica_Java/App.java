package Redovalnica_Java;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    ArrayList<String> manjkajociUcenci = new ArrayList<>();
    ArrayList<String> oceneUcenci = new ArrayList<>();
    ArrayList<String> ocene = new ArrayList<>();
    String[] mU;

    JDateChooser chooserPrisotnost = new JDateChooser();
    JDateChooser chooserOcene = new JDateChooser();

    public static void MailUcitelja(String mail)
    {
        sMail = mail;
    }

    public void UpdatePrisotnostJTree(String ime, String priimek){
        DefaultTreeModel modelP = (DefaultTreeModel)PrisotnostTree.getModel();
        DefaultMutableTreeNode rootP = (DefaultMutableTreeNode)modelP.getRoot();
        rootP.add(new DefaultMutableTreeNode(ime + " " + priimek));
        manjkajociUcenci.add(ime + " " + priimek);
        mU = manjkajociUcenci.toArray(new String[0]);
        modelP.reload(rootP);
    }
    public void UpdateOcenaJTree(String ime, String priimek){
        DefaultTreeModel modelO = (DefaultTreeModel)OcenaTree.getModel();
        DefaultMutableTreeNode rootO = (DefaultMutableTreeNode)modelO.getRoot();
        rootO.add(new DefaultMutableTreeNode(ime + " " + priimek));
        modelO.reload(rootO);
    }
    public App() throws SQLException {
        JFrame jframe = new JFrame("Redovalnica");
        jframe.setContentPane(panelApp);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setSize(700, 500);
        jframe.setResizable(false);
        jframe.setVisible(true);

        jCal.add(chooserPrisotnost);
        jCal2.add(chooserOcene);

        //removing the default child nodes from PrisotnostJtree
        DefaultTreeModel modelP = (DefaultTreeModel) PrisotnostTree.getModel();
        DefaultMutableTreeNode rootP = (DefaultMutableTreeNode) modelP.getRoot();
        rootP.removeAllChildren();
        modelP.reload();
        DefaultMutableTreeNode newRootNodeP = new DefaultMutableTreeNode("Učenec");
        modelP.setRoot(newRootNodeP);

        //removing the default child nodes from OcenaJtree
        DefaultTreeModel modelO = (DefaultTreeModel) OcenaTree.getModel();
        DefaultMutableTreeNode rootO = (DefaultMutableTreeNode) modelO.getRoot();
        rootO.removeAllChildren();
        modelO.reload();
        DefaultMutableTreeNode newRootNodeO = new DefaultMutableTreeNode("Učenec");
        modelO.setRoot(newRootNodeO);

        RedovalnicaDatabase rb = new RedovalnicaDatabase();
        Ucitelj mail = new Ucitelj(sMail);
        imePriimekUcitelja = rb.ReturnImePriimekUcitelja(mail);
        jLabelPrijava.setText("Prijavljeni ste kot " + imePriimekUcitelja);
        jLabelPrijava2.setText("Prijavljeni ste kot " + imePriimekUcitelja);

        RedovalnicaDatabase rd = new RedovalnicaDatabase();
        for (Solsko_Leto item : rd.ReturnVsaSolskaLeta()){
            SolskoLetoComboBoxP.addItem(item.getSLeto());
            SolskoLetoComboBoxO.addItem(item.getSLeto());
        }

        RedovalnicaDatabase s = new RedovalnicaDatabase();
        Solsko_Leto sl = new Solsko_Leto(SolskoLetoComboBoxP.getSelectedItem().toString());
        for(Razred item : s.ReturnRazred_SolskoLeto(sl))
        {
            RazredComboBoxP.addItem(item.getImeR());
            RazredComboBoxO.addItem(item.getImeR());
        }

        RedovalnicaDatabase rd3 = new RedovalnicaDatabase();
        for (RazredPredmet item : rd3.ReturnVsePredmete()){
            PredmetComboBoxP.addItem(item.getImeP());
            PredmetComboBoxO.addItem(item.getImeP());
        }

        RedovalnicaDatabase rd4 = new RedovalnicaDatabase();
        for(Vrsta_Ur item : rd4.ReturnVseVrsteUr())
            VrstaUrComboBox.addItem(item.getUra());

        RedovalnicaDatabase rd5 = new RedovalnicaDatabase();
        for(Ocena item : rd5.ReturnVseOcene())
            OcenaComboBox.addItem(item.getStO());

        ocene.add(OcenaComboBox.getSelectedItem().toString());

        RedovalnicaDatabase rt = new RedovalnicaDatabase();
        Razred r = new Razred(RazredComboBoxP.getSelectedItem().toString(), SolskoLetoComboBoxP.getSelectedItem().toString());
        for(Ucenec item: rt.ReturnUcenci_Razred(r)){
            DefaultTreeModel modelP2 = (DefaultTreeModel)PrisotnostTree.getModel();
            DefaultMutableTreeNode rootPrisotnost = (DefaultMutableTreeNode)modelP2.getRoot();
            rootPrisotnost.add(new DefaultMutableTreeNode(item.getIme() + " " + item.getPriimek()));
            manjkajociUcenci.add(item.getIme() + " " + item.getPriimek());
            modelP2.reload(rootPrisotnost);

            DefaultTreeModel modelO2 = (DefaultTreeModel)OcenaTree.getModel();
            DefaultMutableTreeNode rootO2 = (DefaultMutableTreeNode)modelO2.getRoot();
            rootO2.add(new DefaultMutableTreeNode(item.getIme() + " " + item.getPriimek()));
            modelO2.reload(rootO2);
        }

        Date date = new Date();
        chooserPrisotnost.setDate(date);
        chooserOcene.setDate(date);

        OcenaTree.setCellRenderer(new SelectOcenaTreeCellRenderer());
        PrisotnostTree.setCellRenderer(new SelectPrisotnostTreeCellRenderer());

        statistikaZaOceneButton.addActionListener(e -> {
            jframe.dispose();
            new Statistika();
        });
        potrdiPrisotnostButton.addActionListener(e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String datumCas = dtf.format(now);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String datum = sdf.format(chooserPrisotnost.getDate());

            int idUreIzvedbR = 0, idRazredPredmetR = 0;
            String opomba = textField1.getText();
            try {
                RedovalnicaDatabase rp = new RedovalnicaDatabase();
                RazredPredmet razredPredmet = new RazredPredmet(PredmetComboBoxP.getSelectedItem().toString(), RazredComboBoxP.getSelectedItem().toString(), imePriimekUcitelja, SolskoLetoComboBoxP.getSelectedItem().toString());
                idRazredPredmetR = rp.InsertSelectRazrediPredmeti(razredPredmet);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            try {
                RedovalnicaDatabase ru = new RedovalnicaDatabase();
                UreIzvedbe ure = new UreIzvedbe(idRazredPredmetR, VrstaUrComboBox.getSelectedItem().toString(), datumCas, datum);
                idUreIzvedbR = ru.InsertSelectUreIzvedb(ure);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            for (int i = 0; i < mU.length; i++)
            {
                try {
                    Prisotnost danasnjaPrisotnost = new Prisotnost(mU[i], idUreIzvedbR, opomba);
                    RedovalnicaDatabase re = new RedovalnicaDatabase();
                    re.InsertPrisotnosti(danasnjaPrisotnost);
                    JOptionPane.showMessageDialog(null, "Uspešno dodana prisotnost za ta dan.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            PrisotnostTree.clearSelection();
        });
        SolskoLetoComboBoxP.addItemListener(e -> {
            //RazredComboBoxP.removeAllItems();
            //RazredComboBoxP.removeAll();
            //RazredComboBoxP.getEditor().setItem("");
            for(int i=0;i<RazredComboBoxP.getItemCount();i++)
                RazredComboBoxP.removeItemAt(i);


//            DefaultComboBoxModel model = (DefaultComboBoxModel) RazredComboBoxP.getModel();
//            model.removeAllElements();
            try {
                RedovalnicaDatabase s2 = new RedovalnicaDatabase();
                Solsko_Leto sl2 = new Solsko_Leto(SolskoLetoComboBoxP.getSelectedItem().toString());
                for(Razred item : s2.ReturnRazred_SolskoLeto(sl2))
                    RazredComboBoxP.addItem(item.getImeR());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        SolskoLetoComboBoxO.addItemListener(e -> {
            //RazredComboBoxO.removeAllItems();
            try {
                RedovalnicaDatabase s2 = new RedovalnicaDatabase();
                Solsko_Leto sl2 = new Solsko_Leto(SolskoLetoComboBoxO.getSelectedItem().toString());
                for(Razred item : s2.ReturnRazred_SolskoLeto(sl2))
                    RazredComboBoxO.addItem(item.getImeR());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        RazredComboBoxP.addItemListener(e -> {
            try{
                RedovalnicaDatabase rc = new RedovalnicaDatabase();
                DefaultTreeModel modelP2 = (DefaultTreeModel)PrisotnostTree.getModel();
                DefaultMutableTreeNode rootP2 = (DefaultMutableTreeNode)modelP2.getRoot();
                rootP2.removeAllChildren();
                modelP2.reload();
                Razred r2 = new Razred(RazredComboBoxP.getSelectedItem().toString(), SolskoLetoComboBoxP.getSelectedItem().toString());
                for(Ucenec item: rc.ReturnUcenci_Razred(r2)) {
                    if (!item.getIme().equals("")  && !item.getPriimek().equals("")) {
                        if(!manjkajociUcenci.isEmpty()){
                            for(int i = 0; i<manjkajociUcenci.size(); i++)
                                manjkajociUcenci.remove(i);
                        }
                        UpdatePrisotnostJTree(item.getIme(), item.getPriimek());
                    }
                }
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Interna napaka.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        RazredComboBoxO.addItemListener(e -> {
            try{
                RedovalnicaDatabase rc = new RedovalnicaDatabase();
                Razred r2 = new Razred(RazredComboBoxO.getSelectedItem().toString(), SolskoLetoComboBoxO.getSelectedItem().toString());
                DefaultTreeModel modelO2 = (DefaultTreeModel)OcenaTree.getModel();
                DefaultMutableTreeNode rootO2 = (DefaultMutableTreeNode)modelO2.getRoot();
                rootO2.removeAllChildren();
                modelO2.reload();
                for(Ucenec item: rc.ReturnUcenci_Razred(r2))
                    if(!item.getIme().equals("")  && !item.getPriimek().equals(""))
                        UpdateOcenaJTree(item.getIme(), item.getPriimek());
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        vnesiOcenoButton.addActionListener(e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Sdate = sdf.format(chooserOcene.getDate());
            try {
                RedovalnicaDatabase rp = new RedovalnicaDatabase();
                RazredPredmet razredPredmet = new RazredPredmet(PredmetComboBoxO.getSelectedItem().toString(), RazredComboBoxO.getSelectedItem().toString(), imePriimekUcitelja, SolskoLetoComboBoxO.getSelectedItem().toString());
                rp.InsertSelectRazrediPredmeti(razredPredmet);

                for(int i = 0; i<oceneUcenci.size(); i++){
                    RedovalnicaDatabase rs = new RedovalnicaDatabase();
                    Ocena ocena = new Ocena(oceneUcenci.get(i), ocene.get(i), Sdate, PredmetComboBoxO.getSelectedItem().toString(), RazredComboBoxO.getSelectedItem().toString(), SolskoLetoComboBoxO.getSelectedItem().toString(), imePriimekUcitelja);
                    rs.InsertOcena_Ucenec(ocena);
                }

                JOptionPane.showMessageDialog(null, "Uspešno vnešene ocene.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Interna napaka.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            OcenaTree.clearSelection();
            });
        preveriPrisotnostZaNazajButton.addActionListener(e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Sdate = sdf.format(chooserPrisotnost.getDate());

            Prisotnost prisotnostZaNazaj = new Prisotnost(PredmetComboBoxP.getSelectedItem().toString(), RazredComboBoxP.getSelectedItem().toString(), SolskoLetoComboBoxP.getSelectedItem().toString(), VrstaUrComboBox.getSelectedItem().toString(), Sdate);
            try {
                    /*PrisotnostTreeView.Nodes.Clear();
                    PrisotnostTreeView.Nodes.Add("Učenci");*/
                RedovalnicaDatabase rrp = new RedovalnicaDatabase();
                    /*for (Ucenec item : rrp.ReturnUcenci_Razred_Predmet_Vrsta_Ure_SolskoLeto_Datum(prisotnostZaNazaj))
                    {
                        if (item.getIme() != "" && item.getPriimek() != "")
                            PrisotnostTreeView.Nodes[0].Nodes.Add(item.getIme() + ' ' + item.getPriimek());
                    }*/
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            });
        PrisotnostTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                potrdiPrisotnostButton.setEnabled(true);
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)PrisotnostTree.getSelectionPath().getLastPathComponent();
                manjkajociUcenci.remove(selectedNode.getUserObject().toString());
                mU = manjkajociUcenci.toArray(new String[0]);

                TreePath path = PrisotnostTree.getPathForLocation(e.getX(), e.getY());
                if (path == null){
                    PrisotnostTree.clearSelection();
                    potrdiPrisotnostButton.setEnabled(false);
                }
            }
        });
        OcenaTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                vnesiOcenoButton.setEnabled(true);
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)OcenaTree.getSelectionPath().getLastPathComponent();
                oceneUcenci.add(selectedNode.getUserObject().toString());

                TreePath path = OcenaTree.getPathForLocation(e.getX(), e.getY());
                //OcenaTree.getSelectionModel().setSelectionPath(path);
                if (path == null){
                    OcenaTree.clearSelection();
                    vnesiOcenoButton.setEnabled(false);
                }

            }
        });
        OcenaComboBox.addItemListener(e -> {
            ocene.add(OcenaComboBox.getSelectedItem().toString());
        });

    }
//    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {
//        @Override
//        public Component getTreeCellRendererComponent(JTree tree, Object value,
//            boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
//            super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
//
//            // Assuming you have a tree of Strings
//            String node = (String) ((DefaultMutableTreeNode) value).getUserObject();
//
//            // If the node is a leaf and ends with "xxx"
//            if (leaf && !node.endsWith(" ")) {
//                // nastavljanje barve noda v rdečo
//                setForeground(new Color(255, 0,0));
//            }
//            return this;
//        }
//    }
    public class SelectPrisotnostTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            JComponent c = (JComponent)super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
            c.setOpaque(true);
    //            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    //            MyData data = (MyData)node.getUserObject();
    //            if(data.isX()){
    //                c.setForeground(Color.blue);
    //                c.setBackground(Color.gray);
    //            }
            String node = (String) ((DefaultMutableTreeNode) value).getUserObject();

            if (leaf && hasFocus || isSelected)
                c.setForeground(new Color(255, 0,0));

            return c;
        }
    }
    public class SelectOcenaTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            JComponent c = (JComponent)super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);
            c.setOpaque(true);

            String node = (String) ((DefaultMutableTreeNode) value).getUserObject();

            if (leaf && hasFocus || isSelected)
                c.setForeground(new Color(255, 0,0));

            return c;
        }
    }
}
