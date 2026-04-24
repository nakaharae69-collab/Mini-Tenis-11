package ABP.RetroTenis.Objetos;

import java.awt.*;
import java.awt.event.KeyEvent;
import ABP.RetroTenis.Juego;

public class Raqueta {

    private int x = 250;
    private int y = 700;

    private int dx = 0;

    private Juego juego;

    public Raqueta(Juego juego) {
        this.juego = juego;
    }

    public void move() {
        x += dx;

        if (x < 0) x = 0;
        if (x > juego.getWidth() - 100) {
            x = juego.getWidth() - 100;
        }
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
        dx = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 100, 20);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 20);
    }
}