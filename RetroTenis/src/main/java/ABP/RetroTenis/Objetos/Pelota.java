package ABP.RetroTenis.Objetos;

import java.awt.*;
import ABP.RetroTenis.Juego;
import ABP.RetroTenis.interfaz.Sound;

public class Pelota {

    private int x = 300;
    private int y = 200;

    private int xSpeed = 2;
    private int ySpeed = 2;

    private Juego juego;

    public Pelota(Juego juego) {
        this.juego = juego;
    }

    public void move() {

        x += xSpeed;
        y += ySpeed;

        // 🧱 paredes
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

        // 🧱 techo
        if (y < 0) {
            y = 0;
            ySpeed = -ySpeed;
            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        // 🏓 raqueta
        if (colisionConRaqueta()) {

            y = juego.getRaqueta().getBounds().y - 30; // 👈 evita atravesar
            ySpeed = -ySpeed;

            Sound.BALL.setFramePosition(0);
            Sound.BALL.start();
        }

        // 💀 suelo (game over)
        if (y > juego.getHeight()) {
            juego.gameOver();
        }
    }

    private boolean colisionConRaqueta() {
        return juego.getRaqueta().getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
    }

    // 🎯 NUEVO: necesario para LogicaJuego
    public void setSpeed(int velocidad) {
        this.xSpeed = velocidad;
        this.ySpeed = velocidad;
    }
}