package ABP.RetroTenis.Objetos;

import java.awt.*;

public class Obstaculo {

    private int x, y, width, height;

    public Obstaculo(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // dibujo
    public void paint(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    // bounds exactos
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // getters útiles
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}