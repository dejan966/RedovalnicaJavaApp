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

                Razred uc = new Razred(razred);
                razredi.add(uc);
            }
            rs.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return razredi;
    }
}
