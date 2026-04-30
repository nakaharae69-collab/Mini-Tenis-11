package ABP.RetroTenis;

import java.awt.Container;

import javax.swing.JFrame;

import javax.swing.JLabel;



public class DataBaseForm extends Juego {
    public JFrame ourFrame = new JFrame("Game over");
    
    
    public DataBaseForm(String nombreJugador, int velocidadPelota) {
        
        ourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ourFrame.setBounds(200, 100, 400, 200);
        
        Container contains = ourFrame.getContentPane();
        contains.setLayout(null);
        
        JLabel logo = new JLabel("  Has perdut :(  ");
        logo.setBounds(60, 5, 250, 30);
        
        // Aquí està la clau: fem servir exactament els noms de les variables 
        // que hi ha entre els parèntesis del constructor (a dalt)
        JLabel nom_label = new JLabel("Nom del Jugador:    " + nombreJugador);
        JLabel punts_label = new JLabel("Punts totals del Jugador:    " + velocidadPelota);
        
        nom_label.setBounds(20, 30, 250, 30);
        punts_label.setBounds(20, 60, 250, 30); // He ajustat una mica la posició perquè no s'encavalquin
        
        contains.add(logo);
        contains.add(nom_label);
        contains.add(punts_label);
        
        ourFrame.setVisible(true);
    }
}