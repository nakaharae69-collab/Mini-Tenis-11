package ABP.RetroTenis;

import java.awt.Color;
import java.awt.Container;
import java.sql.*; 
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DataBaseForm extends Juego {
    public JFrame ourFrame = new JFrame("Game over");
    
    public DataBaseForm(String nombreJugador, int velocidadPelota) {
        
        ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ourFrame.setBounds(200, 100, 400, 300); // El fem una mica més alt per al rècord
        
        Container contains = ourFrame.getContentPane();
        contains.setLayout(null);
        
        JLabel logo = new JLabel("  Has perdut :(  ");
        logo.setBounds(60, 5, 250, 30);
        
        JLabel nom_label = new JLabel("Nom del Jugador:    " + nombreJugador);
        JLabel punts_label = new JLabel("Punts d'aquesta partida:    " + velocidadPelota);
        
        nom_label.setBounds(20, 40, 250, 30);
        punts_label.setBounds(20, 70, 250, 30);
        
       
        JLabel record_label = new JLabel("Buscant rècord...");
        record_label.setBounds(20, 110, 300, 30);
        record_label.setForeground(Color.BLUE); 
        try {
         
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/MiniTenis", "root", "");
            Statement st = con.createStatement();

            
            String sqlInsert = "INSERT INTO JUGADORS (Username, NivellMaxim) VALUES ('" + nombreJugador + "', " + velocidadPelota + ")";
            st.executeUpdate(sqlInsert);

           
            String sqlSelect = "SELECT MAX(NivellMaxim) FROM JUGADORS WHERE Username = '" + nombreJugador + "'";
            ResultSet rs = st.executeQuery(sqlSelect);

            if (rs.next()) {
                int elMillor = rs.getInt(1);
                record_label.setText("El teu rècord:    " + elMillor);
            }
            
            con.close();
        } catch (Exception e) {
            record_label.setText("No s'ha pogut carregar el rècord.");
            e.printStackTrace();
        }
       

        contains.add(logo);
        contains.add(nom_label);
        contains.add(punts_label);
        contains.add(record_label); 
        
        ourFrame.setVisible(true);
    }
}