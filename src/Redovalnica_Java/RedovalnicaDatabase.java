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
}
