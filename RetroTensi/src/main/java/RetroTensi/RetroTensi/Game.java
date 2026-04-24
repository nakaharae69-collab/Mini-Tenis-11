package RetroTensi.RetroTensi;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.sound.sampled.Clip;

@SuppressWarnings("serial")
public class Game extends JPanel {

    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);

    int ballSpeed = 1;

    boolean inMenu = true;
    boolean running = false;
    boolean enteringName = false;
    boolean selectingLanguage = true;
    boolean paused = false;

    int round = 0;
    int timeLeft = 20;
    int timeMax = 20;

    String playerName = "";

    // 🌍 IDIOMA
    int language = 0; // 0 español, 1 catalán
    int selectedLanguage = 0;

    // TEXTOS
    String[] options;
    String textTitle;
    String textName;
    String textPlayer;
    String textRound;
    String textTime;
    String textPause;

    private int selectedOption = 0;

    public Game() {

        setFocusable(true);
        requestFocusInWindow();

        // 🔥 IMPORTANTE: inicializar idioma desde el inicio
        language = 0;
        setLanguageTexts();

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

                // 🌍 SELECCIÓN IDIOMA
                if (selectingLanguage) {

                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                        selectedLanguage = 1 - selectedLanguage;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        language = selectedLanguage;
                        setLanguageTexts();

                        selectingLanguage = false;
                        enteringName = true;
                    }

                    return;
                }

                // 🧾 NOMBRE
                if (enteringName) {

                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && playerName.length() > 0) {
                        playerName = playerName.substring(0, playerName.length() - 1);
                    }

                    else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        enteringName = false;
                        inMenu = true;
                    }

                    else if (Character.isLetterOrDigit(e.getKeyChar()) && playerName.length() < 10) {
                        playerName += e.getKeyChar();
                    }

                    return;
                }

                // ⏸️ PAUSA
                if (!inMenu) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        paused = !paused;

                        if (paused) Sound.BACK.stop();
                        else Sound.BACK.loop(Clip.LOOP_CONTINUOUSLY);

                        return;
                    }
                }

                // 🎮 MENÚ
                if (inMenu) {

                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        selectedOption--;
                        if (selectedOption < 0) selectedOption = options.length - 1;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        selectedOption++;
                        if (selectedOption > options.length - 1) selectedOption = 0;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                        if (selectedOption == 3) System.exit(0);

                        if (selectedOption == 0) { ballSpeed = 1; timeMax = 30; }
                        if (selectedOption == 1) { ballSpeed = 2; timeMax = 20; }
                        if (selectedOption == 2) { ballSpeed = 3; timeMax = 10; }

                        ball = new Ball(Game.this);
                        racquet = new Racquet(Game.this);

                        inMenu = false;
                        running = true;
                        paused = false;

                        round = 0;
                        timeLeft = timeMax;

                        Sound.BACK.setFramePosition(0);
                        Sound.BACK.loop(Clip.LOOP_CONTINUOUSLY);
                    }

                } else {
                    racquet.keyPressed(e);
                }
            }
        });

        new Timer(1000, e -> {
            if (running && !paused) {
                timeLeft--;
                if (timeLeft <= 0) {
                    ballSpeed++;
                    round++;
                    timeLeft = timeMax;
                }
            }
        }).start();
    }

    private void setLanguageTexts() {

        if (language == 0) {
            options = new String[]{"Fácil", "Medio", "Difícil", "Salir"};
            textTitle = "MINI TENNIS";
            textName = "Introduce tu nombre:";
            textPlayer = "Jugador: ";
            textRound = "Ronda: ";
            textTime = "Tiempo: ";
            textPause = "PAUSA";
        } else {
            options = new String[]{"Fàcil", "Mitjà", "Difícil", "Sortir"};
            textTitle = "MINI TENNIS";
            textName = "Introdueix el teu nom:";
            textPlayer = "Jugador: ";
            textRound = "Ronda: ";
            textTime = "Temps: ";
            textPause = "PAUSA";
        }
    }

    private void move() {
        if (paused) return;
        ball.move();
        racquet.move();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (selectingLanguage) {

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("Idioma / Llengua", 180, 200);

            String[] langs = {"Español", "Català"};

            for (int i = 0; i < langs.length; i++) {

                int y = 300 + i * 60;

                if (i == selectedLanguage) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(200, y - 30, 200, 40);
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }

                g2d.drawString(langs[i], 220, y);
            }

            return;
        }

        if (enteringName) {

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));

            g2d.drawString(textName, 160, 200);
            g2d.drawString(playerName + "_", 220, 300);

            return;
        }

        if (inMenu) {

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString(textTitle, 140, 120);

            g2d.setFont(new Font("Arial", Font.PLAIN, 30));

            for (int i = 0; i < options.length; i++) {

                int y = 250 + i * 60;

                if (i == selectedOption) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(200, y - 30, 200, 40);
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }

                g2d.drawString(options[i], 220, y);
            }

            return;
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));

        g2d.drawString(textPlayer + playerName, 10, 20);
        g2d.drawString(textRound + round, 10, 45);
        g2d.drawString(textTime + timeLeft, 10, 70);

        ball.paint(g2d);
        racquet.paint(g2d);

        if (paused) {
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            g2d.drawString(textPause, 220, 400);
        }
    }

    public void gameOver() {
        running = false;
        Sound.BACK.stop();
        Sound.GAMEOVER.start();
        inMenu = true;
    }

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();

        frame.add(game);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.requestFocusInWindow();

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}