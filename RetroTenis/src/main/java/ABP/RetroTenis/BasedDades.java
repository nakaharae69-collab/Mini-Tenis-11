package ABP.RetroTenis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BasedDades {
	//version ya actualizada

    public static void guardarResultat(String nombreJugador, int velocidadPelota, int segundosJugados) {

        try {
            Connection conexio = DriverManager.getConnection("jdbc:mysql://localhost/MiniTenis", "root", "");

            Statement consultes = conexio.createStatement();

            consultes.executeUpdate("INSERT INTO JUGADORS (username, NivellMaxim) VALUES ('"+ nombreJugador + "', " + velocidadPelota + segundosJugados + ")");

            conexio.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//poner en el codigo de juego nuevo
//public void gameOver() {
//running = false;
//Sound.BACK.stop();
//Sound.GAMEOVER.start();

//logica.BasedDades.guardarResultat(nomJugador, nivell);

// inMenu = true;