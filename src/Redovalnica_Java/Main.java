package Redovalnica_Java;

import java.sql.SQLException;

public class Main {
       public static void main(String[] args) {
        try {
            RedovalnicaDatabase rd = new RedovalnicaDatabase();
            Ucitelj uc = new Ucitelj("zan.gozdni@scv.si");
            String sd = rd.ReturnImePriimekUcitelja(uc);
            System.out.println(sd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
