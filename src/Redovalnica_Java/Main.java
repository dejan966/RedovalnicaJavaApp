package Redovalnica_Java;

import java.sql.SQLException;

public class Main {
       public static void main(String[] args) {
        try {
            RedovalnicaDatabase rd = new RedovalnicaDatabase();
            for (Razred item : rd.ReturnVseRazrede()) {
                System.out.println(item.ImeR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
