package ABP.RetroTenis.interfaz;

import java.awt.*;

public class DibujadorMenu {

    private Font titulo = new Font("Arial", Font.BOLD, 30);
    private Font normal = new Font("Arial", Font.PLAIN, 25);

    // Seleccio d'idioma
    public void dibujarIdioma(Graphics2D g, int seleccionado) {
        String[] opciones = {"Español", "Català"};
        dibujarMenu(g, "Idioma / Llengua", opciones, seleccionado);
    }

    // Seleccio de nom
    public void dibujarNombre(Graphics2D g, String texto, String nombre) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 800);

        g.setColor(Color.WHITE);
        g.setFont(titulo);

        g.drawString(texto, 150, 200);
        g.drawString(nombre + "_", 220, 300);
    }

    // Menu principal
    public void dibujarMenuPrincipal(Graphics2D g, String titulo, String[] opciones, int seleccionado) {
        dibujarMenu(g, titulo, opciones, seleccionado);
    }

    // Metode general
    private void dibujarMenu(Graphics2D g, String tituloTexto, String[] opciones, int seleccionado) {

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 800);

        g.setColor(Color.BLACK);
        g.setFont(titulo);
        g.drawString(tituloTexto, 150, 150);

        g.setFont(normal);

        for (int i = 0; i < opciones.length; i++) {

            int y = 250 + i * 60;

            if (i == seleccionado) {
                g.setColor(Color.BLACK);
                g.fillRect(180, y - 25, 240, 40);
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString(opciones[i], 200, y);
        }
    }
}