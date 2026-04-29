package ABP.RetroTenis.Objetos;

import java.awt.*;
import ABP.RetroTenis.Juego;
import ABP.RetroTenis.interfaz.Sound;
import javax.swing.ImageIcon;

public class Pelota {

    private int x = 50;
    private int y = 100;

    private int xSpeed = 2;
    private int ySpeed = 2;

    private Juego juego;
    private Image ball;

    public Pelota(Juego juego) {
        this.juego = juego;
        

        ball = new ImageIcon(getClass().getResource("/img/Pokeball.png")).getImage();
    }

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {

        x += xSpeed- 3;
        y += ySpeed;

        // Parets
        if (x < 0) {
            x = 0;
            xSpeed = -xSpeed;
            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        if (x > juego.getWidth() - 30) {
            x = juego.getWidth() - 30;
            xSpeed = -xSpeed;
            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        // Sostre
        if (y < 0) {
            y = 0;
            ySpeed = -ySpeed;
            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        // Raqueta
        if (colisionConRaqueta()) {

            y = juego.getRaqueta().getBounds().y - 30;
            ySpeed = -ySpeed;

            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        // Terra
        if (y > juego.getHeight()) {
            juego.gameOver();
        }

        // Obstacles
        if (juego.getObstaculo1() != null) {
            colisionConObstaculo(juego.getObstaculo1());
        }

        if (juego.getObstaculo2() != null) {
            colisionConObstaculo(juego.getObstaculo2());
        }
    }

    private boolean colisionConRaqueta() {
        return juego.getRaqueta().getBounds().intersects(getBounds());
    }

    // Col·lisio d'objectes
    public void colisionConObstaculo(ABP.RetroTenis.Objetos.Obstaculo obs) {

        if (obs == null) return;

        if (getBounds().intersects(obs.getBounds())) {

            Rectangle ballRect = getBounds();
            Rectangle o = obs.getBounds();

            if (ballRect.y + ballRect.height - 2 <= o.y || ballRect.y + 2 >= o.y + o.height) {
                invertirY();
            } else {
                invertirX();
            }

            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public void paint(Graphics2D g) {
        g.drawImage(ball, x, y, 30, 30, juego);
    }

    public void setSpeed(int velocidad) {
        this.xSpeed = velocidad;
        this.ySpeed = velocidad;
    }

    public void invertirX() {
        xSpeed = -xSpeed;
    }

    public void invertirY() {
        ySpeed = -ySpeed;
    }
}


