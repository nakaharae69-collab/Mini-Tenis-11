package ABP.RetroTenis.Objetos;

import java.awt.*;
import java.awt.event.KeyEvent;

import ABP.RetroTenis.Juego;

public class Raqueta {

    private int x = 250;
    private int y = 600;

    private boolean izquierda;
    private boolean derecha;

    private Juego juego;

    public Raqueta(Juego juego) {
        this.juego = juego;
    }

    // movimiento
    public void move() {

        if (izquierda) x -= 17;
        if (derecha) x += 17;

        limitarPantalla();
    }

    // límites
    private void limitarPantalla() {

        if (x < 0) x = 0;

        if (x > juego.getWidth() - 100) {
            x = juego.getWidth() - 100;
        }
    }

    // teclas
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = true;
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) izquierda = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) derecha = false;
    }

    // bounds
    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 20);
    }

    // dibujo
    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 20);
    }
}