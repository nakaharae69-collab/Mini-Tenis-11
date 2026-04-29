package ABP.RetroTenis.Objetos;

import java.awt.*;
import javax.swing.ImageIcon;

import ABP.RetroTenis.Juego;
import ABP.RetroTenis.interfaz.Sound;

public class Pelota {

    private int x = 50;
    private int y = 100;

    private int xSpeed = 3;
    private int ySpeed = 3;

    private Juego juego;
    private Image ball;

    public Pelota(Juego juego) {
        this.juego = juego;
        ball = new ImageIcon(getClass().getResource("/img/Pokeball.png")).getImage();
    }

    public void move() {

        x += xSpeed;
        y += ySpeed;

        rebotarParedes();
        colisionRaqueta();
        colisionObstaculos();
        gameOver();
    }

    // PAREDES
    private void rebotarParedes() {

        if (x <= 0) {
            x = 0;
            xSpeed = Math.abs(xSpeed);
            sonido();
        }

        if (x >= juego.getWidth() - 30) {
            x = juego.getWidth() - 30;
            xSpeed = -Math.abs(xSpeed);
            sonido();
        }

        if (y <= 0) {
            y = 0;
            ySpeed = Math.abs(ySpeed);
            sonido();
        }
    }

    // RAQUETA
    private void colisionRaqueta() {

        if (juego.getRaqueta().getBounds().intersects(getBounds())) {

            y = juego.getRaqueta().getBounds().y - 30;
            ySpeed = -Math.abs(ySpeed);

            sonido();
        }
    }

    // OBSTÁCULOS
    private void colisionObstaculos() {

        if (juego.getObstaculo1() != null &&
            getBounds().intersects(juego.getObstaculo1().getBounds())) {
            ySpeed = -ySpeed;
            sonido();
        }

        if (juego.getObstaculo2() != null &&
            getBounds().intersects(juego.getObstaculo2().getBounds())) {
            ySpeed = -ySpeed;
            sonido();
        }
    }

    // GAME OVER
    private void gameOver() {
        if (y > juego.getHeight()) {
            juego.hasPerdut();
        }
    }

    // sonido
    private void sonido() {
        if (Sound.BALL != null) {
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

    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSpeed(int v) {
        xSpeed = v;
        ySpeed = v;
    }
}