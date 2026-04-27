package ABP.RetroTenis.interfaz;

import java.awt.*;

public class DibujadorMenu {

    public void dibujarIdioma(Graphics2D g2d, int idiomaSeleccionado) {

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 600, 800);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("Idioma / Llengua", 180, 200);

        String[] idiomas = {"Español", "Català"};

        for (int i = 0; i < idiomas.length; i++) {

            int y = 300 + i * 60;

            if (i == idiomaSeleccionado) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(200, y - 30, 200, 40);
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.BLACK);
            }

            g2d.drawString(idiomas[i], 220, y);
        }
    }

    public void dibujarNombre(Graphics2D g2d, GestorIdioma idioma, String nombreJugador) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 600, 800);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));

        g2d.drawString(idioma.getTextoNombre(), 160, 200);
        g2d.drawString(nombreJugador + "_", 220, 300);
    }

    public void dibujarMenuPrincipal(Graphics2D g2d, GestorIdioma idioma, int opcionSeleccionada) {

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 600, 800);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.drawString(idioma.getTextoTitulo(), 140, 120);

        g2d.setFont(new Font("Arial", Font.PLAIN, 30));

        for (int i = 0; i < idioma.getOpciones().length; i++) {

            int y = 250 + i * 60;

            if (i == opcionSeleccionada) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect(200, y - 30, 200, 40);
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(Color.BLACK);
            }

            g2d.drawString(idioma.getOpciones()[i], 220, y);
        }
    }
}