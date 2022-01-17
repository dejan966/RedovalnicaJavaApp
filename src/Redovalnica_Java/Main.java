package Redovalnica_Java;

import java.sql.SQLException;

public class Main {
       public static void main(String[] args) {
        try {
            RedovalnicaDatabase rd = new RedovalnicaDatabase();
            for (Solsko_Leto item : rd.ReturnVsaSolskaLeta()) {
                System.out.println(item.SLeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
