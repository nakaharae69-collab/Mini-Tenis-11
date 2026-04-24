package RetroTensi.RetroTensi;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;

    int x = 0;

    private int Y;
    private Game game;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private final int speed = 10;

    public Racquet(Game game) {
        this.game = game;
    }

    public void move() {

        // siempre abajo
        Y = game.getHeight() - 60;

        if (leftPressed) {
            x -= speed;
        }

        if (rightPressed) {
            x += speed;
        }

        // límites pantalla
        if (x < 0) x = 0;
        if (x > game.getWidth() - WIDTH) x = game.getWidth() - WIDTH;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = true;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = true;
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            leftPressed = false;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            rightPressed = false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}