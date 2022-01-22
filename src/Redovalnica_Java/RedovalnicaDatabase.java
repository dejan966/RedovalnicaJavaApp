package Redovalnica_Java;

import java.sql.*;
import java.util.ArrayList;

public class RedovalnicaDatabase {
    private Connection conn;
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
            String sql = "SELECT solski_email, geslo FROM ucitelji WHERE(solski_email = '" + uc.SolskiEmail + "') AND (geslo = '" + uc.Geslo + "');";
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
            String sql = "SELECT o.ime || ' ' || o.priimek AS ucitelj FROM osebe o INNER JOIN ucitelji u ON u.id_osebe = o.id_osebe WHERE (u.solski_email = '" + email.SolskiEmail + "');";
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
            String sql = "SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN razredi r ON u.id_razredi = r.id_razredi INNER JOIN solska_leta sl on sl.id_solska_leta = r.id_solska_leta WHERE(r.razred = '" + ru.ImeR + "') AND (sl.solsko_leto = '" + ru.SLeto + "')";
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
            String sql = "SELECT r.razred FROM razredi r INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta WHERE (sl.solsko_leto = '" + s.SLeto + "')";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                while(rs.next()) {
                    String razred = rs.getString("razred");

                    Razred r = new Razred(razred);
                    razrediS.add(r);
                }
            }
            else{
                Razred u = new Razred("");
                razrediS.add(u);
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
            String sql = "SELECT DISTINCT o.ime || ' ' || o.priimek AS ucenec, oc.ocena_st, p.predmet FROM osebe o INNER JOIN ucenci u on o.id_osebe = u.id_osebe INNER JOIN razredi r on r.id_razredi = u.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN razredi_predmeti rp on r.id_razredi = rp.id_razredi INNER JOIN ocene_ucenci ou on rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN ocene_ucenci ou2 on u.id_ucenci = ou2.id_ucenci INNER JOIN predmeti p on p.id_predmeti = rp.id_predmeti INNER JOIN ocene oc on oc.id_ocene = ou.id_ocene WHERE (sl.solsko_leto = '" + prs.SLeto + "') AND (r.razred = '" + prs.ImeR + "')";
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
            String sql = "INSERT INTO ocene_ucenci(id_ucenci, id_ocene, datum, id_razredi_predmeti) VALUES ((SELECT id_ucenci FROM ucenci WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + ocenaZaUcenca.Ucenec + "'))), (SELECT id_ocene FROM ocene WHERE ocena_st = '" + ocenaZaUcenca.UOcena + "'), '" + ocenaZaUcenca.DatumOcena + "', (SELECT rp.id_razredi_predmeti FROM razredi_predmeti rp INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti INNER JOIN ucitelji u ON u.id_ucitelji = rp.id_ucitelji INNER JOIN osebe o ON o.id_osebe = u.id_osebe WHERE (r.razred = '" + ocenaZaUcenca.ImeR + "') AND (p.predmet = '" + ocenaZaUcenca.ImeP + "') AND (o.ime || ' ' || o.priimek = '" + ocenaZaUcenca.UciteljP + "')))";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void InsertRazrediPredmeti(RazredPredmet rp)
    {
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT p.predmet, r.razred, o.ime || ' ' || o.priimek FROM osebe o INNER JOIN ucitelji u on o.id_osebe = u.id_osebe INNER JOIN razredi_predmeti rp on u.id_ucitelji = rp.id_ucitelji INNER JOIN razredi r on rp.id_razredi = r.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN predmeti p on rp.id_predmeti = p.id_predmeti WHERE (p.predmet = '\" + rp.ImeP + \"') AND (r.razred = '\" + rp.ImeR + \"') AND (sl.solsko_leto = '\" + rp.SLeto + \"') AND (ime || ' ' || priimek = '\" + rp.UciteljP + \"')";
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.next()){
                final Connection newConn2 = DriverManager.getConnection(jdbcURL, username, password);
                Statement stmt2 = newConn2.createStatement();
                String sql2 = "INSERT INTO razredi_predmeti (id_predmeti, id_razredi, id_ucitelji) VALUES ((SELECT id_predmeti FROM predmeti WHERE predmet = '" + rp.ImeP + "'), (SELECT r.id_razredi FROM razredi r INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta WHERE (r.razred = '" + rp.ImeR + "') AND (sl.solsko_leto = '" + rp.SLeto + "')), (SELECT id_ucitelji FROM ucitelji WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + rp.UciteljP + "'))));";
                stmt2.executeUpdate(sql2);
                stmt2.close();
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int IDRazrediPredmeti(RazredPredmet rp)
    {
        int id = 1;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT id_razredi_predmeti FROM razredi_predmeti WHERE (id_predmeti = (SELECT id_predmeti FROM predmeti WHERE predmet = '" + rp.ImeP + "')) AND (id_razredi = (SELECT r.id_razredi FROM razredi r INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta WHERE (r.razred = '\" + rp.ImeR + \"') AND (sl.solsko_leto = '" + rp.SLeto + "'))) AND (id_ucitelji = (SELECT id_ucitelji FROM ucitelji WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + rp.UciteljP + "'))));";
            ResultSet rs = stmt.executeQuery(sql);
            while(!rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
    public void InsertUreIzvedb(UreIzvedbe ure)
    {
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "INSERT INTO ure_izvedb(id_razredi_predmeti, id_vrste_ur, datum_cas) VALUES ('" + ure.Id_R_P_U + "', (SELECT id_vrste_ur FROM vrste_ur WHERE vrsta_ure = '" + ure.VrstaUre + "'), '" + ure.DatumCas + "');";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int IDUreIzvedb(UreIzvedbe ure){
        int id = 1;
        try(newConn){
            Statement stmt = newConn.createStatement();
            String sql = "SELECT id_ure_izvedb FROM ure_izvedb WHERE (id_razredi_predmeti = '" + ure.Id_R_P_U + "') AND (id_vrste_ur = (SELECT id_vrste_ur FROM vrste_ur WHERE vrsta_ure = '" + ure.VrstaUre + "')) AND (datum_cas LIKE '%" + ure.DatumCas + "%');";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
                id = rs.getInt(1);
            rs.close();
            stmt.close();
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return id;
    }
    public void InsertPrisotnosti(Prisotnost dPrisotnost)
    {
        try(newConn)
        {
            Statement stmt = newConn.createStatement();
            String sql = "INSERT INTO prisotnosti(id_ucenci, id_ure_izvedb, opomba) VALUES((SELECT id_ucenci FROM ucenci WHERE (id_osebe = (SELECT id_osebe FROM osebe WHERE ime || ' ' || priimek = '" + dPrisotnost.Ucenec + "'))), '" + dPrisotnost.IdUr + "', '" + dPrisotnost.Opomba + "')";
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
            String sql = "(SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN prisotnosti p on p.id_ucenci = u.id_ucenci INNER JOIN razredi r ON u.id_razredi = r.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN razredi_predmeti rp on r.id_razredi = rp.id_razredi INNER JOIN predmeti pr on pr.id_predmeti = rp.id_predmeti WHERE (r.razred = '" + pZaNazaj.ImeR + "') AND (pr.predmet = '" + pZaNazaj.ImeP + "') AND (sl.solsko_leto = '" + pZaNazaj.SLeto + "'))" +
                    "UNION (SELECT o.ime, o.priimek FROM osebe o INNER JOIN ucenci u ON u.id_osebe = o.id_osebe INNER JOIN prisotnosti p on p.id_ucenci = u.id_ucenci INNER JOIN ure_izvedb ui on ui.id_ure_izvedb = p.id_ure_izvedb INNER JOIN vrste_ur vu on vu.id_vrste_ur = ui.id_vrste_ur INNER JOIN razredi_predmeti rp on rp.id_razredi_predmeti = ui.id_razredi_predmeti INNER JOIN razredi r on r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON sl.id_solska_leta = r.id_solska_leta INNER JOIN predmeti pr on pr.id_predmeti = rp.id_predmeti " +
                    "WHERE(ui.datum_cas LIKE '%" + pZaNazaj.DatumCas + "%') AND(r.razred = '" + pZaNazaj.ImeR + "') AND(pr.predmet = '" + pZaNazaj.ImeP + "') AND(vu.vrsta_ure = '" + pZaNazaj.VrstaUre + "') AND (sl.solsko_leto = '" + pZaNazaj.SLeto + "'))";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                while(rs.next()){
                    String ime = rs.getString(1);
                    String priimek = rs.getString(2);
                    Ucenec u = new Ucenec(ime, priimek);
                    ucenci.add(u);
                }
            }
            else{
                Ucenec u = new Ucenec("", "");
                ucenci.add(u);
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN razredi r ON r.id_razredi = u.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta WHERE(sl.solsko_leto = '" + r.SLeto + "') AND (r.razred = '" + r.ImeR + "')";
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 1) AND (sl.solsko_leto = '" + r1.SLeto + "') AND (r.razred = '" + r1.ImeR + "') AND (p.predmet = '" + r1.ImeP + "')";
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 2) AND (sl.solsko_leto = '" + r2.SLeto + "') AND (r.razred = '" + r2.ImeR + "') AND (p.predmet = '" + r2.ImeP + "')";
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 3) AND (sl.solsko_leto = '" + r3.SLeto + "') AND (r.razred = '" + r3.ImeR + "') AND (p.predmet = '" + r3.ImeP + "')";
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 4) AND (sl.solsko_leto = '" + r4.SLeto + "') AND (r.razred = '" + r4.ImeR + "') AND (p.predmet = '" + r4.ImeP + "')";
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
            String sql = "SELECT COUNT(u.*) FROM ucenci u INNER JOIN ocene_ucenci ou ON ou.id_ucenci = u.id_ucenci INNER JOIN ocene o ON o.id_ocene = ou.id_ocene INNER JOIN razredi_predmeti rp ON rp.id_razredi_predmeti = ou.id_razredi_predmeti INNER JOIN razredi r ON r.id_razredi = rp.id_razredi INNER JOIN solska_leta sl ON r.id_solska_leta = sl.id_solska_leta INNER JOIN predmeti p ON rp.id_predmeti = p.id_predmeti WHERE(o.ocena_st = 5) AND (sl.solsko_leto = '" + r5.SLeto + "') AND (r.razred = '" + r5.ImeR + "') AND (p.predmet = '" + r5.ImeP + "')";
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
