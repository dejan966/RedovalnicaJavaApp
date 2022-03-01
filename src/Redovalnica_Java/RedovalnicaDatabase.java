package Redovalnica_Java;

import java.sql.*;
import java.util.ArrayList;

public class RedovalnicaDatabase {
    Connection conn;
    String jdbcURL = "jdbc:postgresql://ella.db.elephantsql.com:5432/finomhzd";
    String username = "finomhzd";
    String password = "qDjavv-S5TXm78zV2dGfIti1PiZZlcer";
    final Connection newConn;

    public RedovalnicaDatabase() throws SQLException {
        conn = DriverManager.getConnection(jdbcURL, username, password);
        newConn = conn;
    }
    public Boolean PreveriPrijavo(Ucitelj uc)
    {
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT solski_email, geslo FROM ucitelji WHERE(solski_email = '" + uc.getSolskiEmail() + "') AND (geslo = '" + uc.getGeslo() + "');";
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next())
                return false;

            rs.close();
            stmt.close();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
    public String ReturnImePriimekUcitelja(Ucitelj email)
    {
        String ime_priimek = "";
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT o.ime || ' ' || o.priimek AS ucitelj FROM osebe o INNER JOIN ucitelji u ON u.id_osebe = o.id_osebe WHERE (u.solski_email = '" + email.getSolskiEmail() + "');";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                ime_priimek = rs.getString(1);

            rs.close();
            stmt.close();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return ime_priimek;
    }
    public ArrayList<Ucenec> ReturnVseUcence(){
        ArrayList<Ucenec> ucenci = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String ime = rs.getString("ime");
                String priimek = rs.getString("priimek");

                Ucenec uc = new Ucenec(ime, priimek);
                ucenci.add(uc);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ucenci;
    }
    public ArrayList<Razred> ReturnVseRazrede(){
        ArrayList<Razred> razredi = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT razred FROM razredi";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String razred = rs.getString("razred");

                Razred r = new Razred(razred);
                razredi.add(r);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return razredi;
    }
    public ArrayList<RazredPredmet> ReturnVsePredmete(){
        ArrayList<RazredPredmet> predmeti = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT predmet FROM predmeti";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String predmet = rs.getString("predmet");

                RazredPredmet uc = new RazredPredmet(predmet);
                predmeti.add(uc);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return predmeti;
    }
    public ArrayList<Ocena> ReturnVseOcene(){
        ArrayList<Ocena> ocene = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT ocena_st FROM ocene";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int stO = rs.getInt("ocena_st");

                Ocena o = new Ocena(stO);
                ocene.add(o);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ocene;
    }
    public ArrayList<Solsko_Leto> ReturnVsaSolskaLeta(){
        ArrayList<Solsko_Leto> solskaLeta = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT solsko_leto FROM solska_leta";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String solsko_leto = rs.getString("solsko_leto");

                Solsko_Leto sl = new Solsko_Leto(solsko_leto);
                solskaLeta.add(sl);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return solskaLeta;
    }
    public ArrayList<Vrsta_Ur> ReturnVseVrsteUr(){
        ArrayList<Vrsta_Ur> vrste_ur = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT vrsta_ure FROM vrste_ur";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String vrsta_ure = rs.getString("vrsta_ure");

                Vrsta_Ur vr = new Vrsta_Ur(vrsta_ure);
                vrste_ur.add(vr);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vrste_ur;
    }

    public ArrayList<Ucenec> ReturnUcenci_Razred(Razred ru){
        ArrayList<Ucenec> ucenci = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN razredi r ON u.id_razredi = r.id_razredi INNER JOIN solska_leta sl on sl.id_solska_leta = r.id_solska_leta WHERE(r.razred = '" + ru.getImeR()+ "') AND (sl.solsko_leto = '" + ru.getSLeto() + "')";
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()){
                Ucenec u = new Ucenec("", "");
                ucenci.add(u);
            }
            else{
                do {
                    String ime = rs.getString("ime");
                    String priimek = rs.getString("priimek");

                    Ucenec uc = new Ucenec(ime, priimek);
                    ucenci.add(uc);
                }while(rs.next());
            }

            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ucenci;
    }
    public ArrayList<Razred> ReturnRazred_SolskoLeto(Solsko_Leto s){
        ArrayList<Razred> razrediS = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT r.razred FROM razredi r INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta WHERE (sl.solsko_leto = '" + s.getSLeto() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String razred = rs.getString("razred");

                Razred r = new Razred(razred);
                razrediS.add(r);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return razrediS;
    }
    public ArrayList<Ocena> ReturnRazredUcenciOcena(RazredPredmet prs){
        ArrayList<Ocena> ocene = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT DISTINCT o.ime || ' ' || o.priimek AS ucenec, oc.ocena_st, p.predmet FROM osebe o INNER JOIN ucenci u on o.id_osebe = u.id_osebe INNER JOIN razredi r on r.id_razredi = u.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN razredi_predmeti rp on r.id_razredi = rp.id_razredi INNER JOIN ocene_ucenci ou on rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN ocene_ucenci ou2 on u.id_ucenci = ou2.id_ucenci INNER JOIN predmeti p on p.id_predmeti = rp.id_predmeti INNER JOIN ocene oc on oc.id_ocene = ou.id_ocene WHERE (sl.solsko_leto = '" + prs.getSLeto() + "') AND (r.razred = '" + prs.getImeR() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String ucenec = rs.getString(1);
                int stO = rs.getInt(2);

                Ocena oc = new Ocena(ucenec, stO);
                ocene.add(oc);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ocene;
    }
    public void InsertOcena_Ucenec(Ocena ocenaZaUcenca)
    {
        try (newConn)
        {
            Statement stmt = newConn.createStatement();
            String sql = "INSERT INTO ocene_ucenci(id_ucenci, id_ocene, datum, id_razredi_predmeti) VALUES ((SELECT id_ucenci FROM ucenci WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + ocenaZaUcenca.getUcenec() + "'))), (SELECT id_ocene FROM ocene WHERE ocena_st = '" + ocenaZaUcenca.getUOcena() + "'), '" + ocenaZaUcenca.getDatumOcena() + "', (SELECT rp.id_razredi_predmeti FROM razredi_predmeti rp INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti INNER JOIN ucitelji u ON u.id_ucitelji = rp.id_ucitelji INNER JOIN osebe o ON o.id_osebe = u.id_osebe WHERE (r.razred = '" + ocenaZaUcenca.getImeR() + "') AND (p.predmet = '" + ocenaZaUcenca.getImeP() + "') AND (o.ime || ' ' || o.priimek = '" + ocenaZaUcenca.getUciteljP() + "')))";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int InsertRazrediPredmeti(RazredPredmet rp)
    {
        int id = 0;
        try(newConn){
            Statement stmtSelect = newConn.createStatement();
            String sqlSelect = "SELECT rp.id_razredi_predmeti FROM osebe o INNER JOIN ucitelji u on o.id_osebe = u.id_osebe INNER JOIN razredi_predmeti rp on u.id_ucitelji = rp.id_ucitelji INNER JOIN razredi r on rp.id_razredi = r.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN predmeti p on rp.id_predmeti = p.id_predmeti WHERE (p.predmet = '" + rp.getImeP() + "') AND (r.razred = '" + rp.getImeR() + "') AND (sl.solsko_leto = '" + rp.getSLeto() + "') AND (ime || ' ' || priimek = '" + rp.getUciteljP() + "')";
            ResultSet rs = stmtSelect.executeQuery(sqlSelect);
            if(rs.next()){
                id = rs.getInt(1);
            }
            else{
                Statement stmtInsert = newConn.createStatement();
                String sqlInsert = "INSERT INTO razredi_predmeti (id_predmeti, id_razredi, id_ucitelji) VALUES ((SELECT id_predmeti FROM predmeti WHERE predmet = '" + rp.getImeP() + "'), (SELECT r.id_razredi FROM razredi r INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta WHERE (r.razred = '" + rp.getImeR() + "') AND (sl.solsko_leto = '" + rp.getSLeto() + "')), (SELECT id_ucitelji FROM ucitelji WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + rp.getUciteljP() + "'))));";
                stmtInsert.executeUpdate(sqlInsert);
                stmtInsert.close();

                Statement stmtSelect2 = newConn.createStatement();
                String sqlSelect2 = "SELECT rp.id_razredi_predmeti FROM osebe o INNER JOIN ucitelji u on o.id_osebe = u.id_osebe INNER JOIN razredi_predmeti rp on u.id_ucitelji = rp.id_ucitelji INNER JOIN razredi r on rp.id_razredi = r.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN predmeti p on rp.id_predmeti = p.id_predmeti WHERE (p.predmet = '" + rp.getImeP() + "') AND (r.razred = '" + rp.getImeR() + "') AND (sl.solsko_leto = '" + rp.getSLeto() + "') AND (ime || ' ' || priimek = '" + rp.getUciteljP() + "')";
                ResultSet rs2 = stmtSelect2.executeQuery(sqlSelect2);
                while(rs2.next())
                    id = rs2.getInt(1);

                rs2.close();
                stmtSelect2.close();
            }
            rs.close();
            stmtSelect.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
    public int InsertUreIzvedb(UreIzvedbe ure)
    {
        int id = 0;
        try(newConn){
            Statement stmtSelect = newConn.createStatement();
            String sqlSelect = "SELECT id_ure_izvedb FROM ure_izvedb WHERE (id_razredi_predmeti = '" + ure.Id_R_P_U + "') AND (id_vrste_ur = (SELECT id_vrste_ur FROM vrste_ur WHERE vrsta_ure = '" + ure.getVrstaUre() + "')) AND (datum_cas LIKE '%" + ure.getDatum() + "%')";
            ResultSet rsSelect = stmtSelect.executeQuery(sqlSelect);
            if(rsSelect.next()){
                id = rsSelect.getInt(1);
            }
            else{
                Statement stmtInsert = newConn.createStatement();
                String sqlInsert = "INSERT INTO ure_izvedb(id_razredi_predmeti, id_vrste_ur, datum_cas) VALUES ('" + ure.Id_R_P_U + "', (SELECT id_vrste_ur FROM vrste_ur WHERE vrsta_ure = '" + ure.getVrstaUre() + "'), '" + ure.getDatumCas() + "')";

                stmtInsert.executeUpdate(sqlInsert);
                stmtInsert.close();

                Statement stmtSelect2 = newConn.createStatement();
                String sqlSelect2 = "SELECT id_ure_izvedb FROM ure_izvedb WHERE (id_razredi_predmeti = '" + ure.Id_R_P_U + "') AND (id_vrste_ur = (SELECT id_vrste_ur FROM vrste_ur WHERE vrsta_ure = '" + ure.getVrstaUre() + "')) AND (datum_cas LIKE '%" + ure.getDatum() + "%')";
                ResultSet rsSelect2 = stmtSelect2.executeQuery(sqlSelect2);
                while(rsSelect2.next())
                    id = rsSelect2.getInt(1);

                rsSelect2.close();
                stmtSelect2.close();
            }
            rsSelect.close();
            stmtSelect.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
    public void InsertPrisotnosti(Prisotnost dPrisotnost)
    {
        try(newConn)
        {
            Statement stmt = newConn.createStatement();
            String sql = "INSERT INTO prisotnosti(id_ucenci, id_ure_izvedb, opomba) VALUES((SELECT id_ucenci FROM ucenci WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + dPrisotnost.getUcenecN() + "'))), '" + dPrisotnost.IdUr + "', '" + dPrisotnost.getOpomba() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public ArrayList<Ucenec> ReturnUcenci_Razred_Predmet_Vrsta_Ure_SolskoLeto_Datum(Prisotnost pZaNazaj){
        ArrayList<Ucenec> ucenci = new ArrayList<>();
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "(SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN prisotnosti p on p.id_ucenci = u.id_ucenci INNER JOIN razredi r ON u.id_razredi = r.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN razredi_predmeti rp on r.id_razredi = rp.id_razredi INNER JOIN predmeti pr on pr.id_predmeti = rp.id_predmeti WHERE (r.razred = '" + pZaNazaj.getImeR() + "') AND (pr.predmet = '" + pZaNazaj.getImeP() + "') AND (sl.solsko_leto = '" + pZaNazaj.getSLeto() + "'))" +
                    "UNION (SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN prisotnosti p on p.id_ucenci = u.id_ucenci INNER JOIN ure_izvedb ui on ui.id_ure_izvedb = p.id_ure_izvedb INNER JOIN vrste_ur vu on vu.id_vrste_ur = ui.id_vrste_ur INNER JOIN razredi_predmeti rp on rp.id_razredi_predmeti = ui.id_razredi_predmeti INNER JOIN razredi r on r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN predmeti pr on pr.id_predmeti = rp.id_predmeti " +
                    "WHERE(ui.datum_cas LIKE '%" + pZaNazaj.getDatumCas() + "%') AND(r.razred = '" + pZaNazaj.getImeR() + "') AND(pr.predmet = '" + pZaNazaj.getImeP() + "') AND(vu.vrsta_ure = '" + pZaNazaj.getVrstaUre() + "') AND (sl.solsko_leto = '" + pZaNazaj.getSLeto() + "'))";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()){
                Ucenec u = new Ucenec("", "");
                ucenci.add(u);
            }
            else{
                while(rs.next()){
                    String ime = rs.getString(1);
                    String priimek = rs.getString(2);
                    Ucenec u = new Ucenec(ime, priimek);
                    ucenci.add(u);
                }
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ucenci;
    }

    public int Return_StUcenci_Razred(Razred r){
        int st_ucencevR = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN razredi r ON r.id_razredi = u.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta WHERE(sl.solsko_leto = '" + r.getSLeto() + "') AND (r.razred = '" + r.getImeR() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                st_ucencevR = rs.getInt(1);
            rs.close();
            stmt.close();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencevR;
    }
    public int Return_Ucenci_Ocena1(RazredPredmet r1)
    {
        int st_ucencev1 = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 1) AND (sl.solsko_leto = '" + r1.getSLeto() + "') AND (r.razred = '" + r1.getImeR() + "') AND (p.predmet = '" + r1.getImeP() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next())
                    st_ucencev1 = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencev1;
    }
    public int Return_Ucenci_Ocena2(RazredPredmet r2)
    {
        int st_ucencev2 = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 2) AND (sl.solsko_leto = '" + r2.getSLeto() + "') AND (r.razred = '" + r2.getImeR() + "') AND (p.predmet = '" + r2.getImeP() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next())
                    st_ucencev2 = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencev2;
    }
    public int Return_Ucenci_Ocena3(RazredPredmet r3)
    {
        int st_ucencev3 = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 3) AND (sl.solsko_leto = '" + r3.getSLeto() + "') AND (r.razred = '" + r3.getImeR() + "') AND (p.predmet = '" + r3.getImeP() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next())
                    st_ucencev3 = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencev3;
    }
    public int Return_Ucenci_Ocena4(RazredPredmet r4)
    {
        int st_ucencev4 = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 4) AND (sl.solsko_leto = '" + r4.getSLeto() + "') AND (r.razred = '" + r4.getImeR() + "') AND (p.predmet = '" + r4.getImeP() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next())
                    st_ucencev4 = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencev4;
    }
    public int Return_Ucenci_Ocena5(RazredPredmet r5)
    {
        int st_ucencev5 = 0;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 5) AND (sl.solsko_leto = '" + r5.getSLeto() + "') AND (r.razred = '" + r5.getImeR() + "') AND (p.predmet = '" + r5.getImeP() + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next())
                    st_ucencev5 = rs.getInt(1);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return st_ucencev5;
    }
}
