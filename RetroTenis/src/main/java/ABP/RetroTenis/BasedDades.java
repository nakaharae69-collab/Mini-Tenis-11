package ABP.RetroTenis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BasedDades {

    public static void guardarResultat(String nombreJugador, int velocidadPelota) {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver"); 

            Connection conexio = DriverManager.getConnection("jdbc:mysql://localhost/MiniTenis", "root", "");
            Statement consultes = conexio.createStatement();

            // Executem l'INSERT. He posat els noms segons el teu SQL de Workbench.
         // Codi corregit:
            String sql = "INSERT INTO JUGADORS (Username, NivellMaxim) VALUES ('" + nombreJugador + "', " + velocidadPelota + ")";            consultes.executeUpdate(sql);

            System.out.println("SQL que s'ha executat: " + sql);

            // Tanquem tot
            consultes.close();
            conexio.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error, o s'ha trobat  l'SQL");
        } catch (SQLException e) {
            System.out.println("Error general " + e.getMessage());
            e.printStackTrace();
        }
    }
}
