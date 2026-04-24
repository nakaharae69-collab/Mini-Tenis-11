package RetroTensi.RetroTensi;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

	private static final int DIAMETER = 30;

	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;

	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {

		if (x + xa < 0)
			xa = game.ballSpeed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.ballSpeed;
		else if (y + ya < 0)
			ya = game.ballSpeed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()) {
			ya = -game.ballSpeed;
			y = game.racquet.getTopY() - DIAMETER;

			if (game.running && !game.paused) {
				Sound.BALL.stop();
				Sound.BALL.setFramePosition(0);
				Sound.BALL.start();
			}
		}

		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}