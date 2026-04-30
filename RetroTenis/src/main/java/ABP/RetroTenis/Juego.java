package ABP.RetroTenis;

import java.awt.*;


import javax.swing.*;

import ABP.RetroTenis.Estado.EstadoJuego;
import ABP.RetroTenis.Objetos.Pelota;
import ABP.RetroTenis.Objetos.Raqueta;
import ABP.RetroTenis.Objetos.Obstaculo;
import ABP.RetroTenis.logica.ControlTeclado;
import ABP.RetroTenis.logica.ControlTiempo;
import ABP.RetroTenis.logica.LogicaJuego;
import ABP.RetroTenis.interfaz.GestorSonido;
import ABP.RetroTenis.interfaz.Sound;
import ABP.RetroTenis.BasedDades;

@SuppressWarnings("serial")
public class Juego extends JPanel implements ABP.RetroTenis.interfaz.gameOver {

    private EstadoJuego estado = EstadoJuego.IDIOMA;
 
    private Pelota pelota;
    private Raqueta raqueta;

    private Obstaculo obstaculo1;
    private Obstaculo obstaculo2;

    private ControlTiempo tiempo;
    private LogicaJuego logica;

    private GestorSonido sonido;
    private BasedDades baseDades;

    private int idioma = 0;
    private int idiomaSeleccionado = 0;

    private String nombreJugador = "";

    private String[] opciones;
    private int opcionSeleccionada = 0;

    private String textoTitulo;
    private String textoNombre;
    private String textoJugador;
    private String textoNivel;
    private String textoTiempo;
    private String textoPausa;

    private int velocidadPelota = 5;
    private int tiempoMaximo = 20;

    private int segundosJugados = 0;

	private BasedDades basedades;

    public Juego() {

        setFocusable(true);

        pelota = new Pelota(this);
        raqueta = new Raqueta(this);

        // Obstaculos
        obstaculo1 = new Obstaculo(100, 120, 150, 20);
        obstaculo2 = new Obstaculo(350, 170, 150, 20);

        tiempo = new ControlTiempo(tiempoMaximo);
        
        baseDades = new BasedDades();

        logica = new LogicaJuego(pelota, raqueta, tiempo, obstaculo1, obstaculo2, basedades);

        sonido = new GestorSonido(Sound.BACK, Sound.GAMEOVER);
        
        

        setTextosIdioma();

        addKeyListener(new ControlTeclado(this));

        new Timer(1000, e -> {

            if (estado == EstadoJuego.JUGANDO) {

                logica.actualizarTiempo(estado);

                segundosJugados++;

                if (segundosJugados % 20 == 0) {
                    velocidadPelota++;
                    logica.aplicarVelocidad(velocidadPelota);
                }
            }

        }).start();

        sonido.iniciarMusica();
    }

    private void move() {
        logica.actualizar(estado);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (estado == EstadoJuego.IDIOMA) {

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.drawString("Idioma / Llengua", 180, 200);

            String[] langs = {"Español", "Català"};

            for (int i = 0; i < langs.length; i++) {

                int y = 300 + i * 60;

                if (i == idiomaSeleccionado) {
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

        if (estado == EstadoJuego.NOMBRE) {

            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));

            g2d.drawString(textoNombre, 160, 200);
            g2d.drawString(nombreJugador + "_", 220, 300);
            return;
        }

        if (estado == EstadoJuego.MENU) {

            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString(textoTitulo, 140, 120);

            g2d.setFont(new Font("Arial", Font.PLAIN, 30));

            for (int i = 0; i < opciones.length; i++) {

                int y = 250 + i * 60;

                if (i == opcionSeleccionada) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(200, y - 30, 200, 40);
                    g2d.setColor(Color.WHITE);
                } else {
                    g2d.setColor(Color.BLACK);
                }

                g2d.drawString(opciones[i], 220, y);
            }
            return;
        }

        
        // JUEGO
        Image fondo = null;
        
        
        if (velocidadPelota >= 0 && velocidadPelota <= 5) {	
        	fondo = new ImageIcon(getClass().getResource("/img/fondo1.jpg")).getImage();
        }
        
        if (velocidadPelota >= 6 && velocidadPelota <= 10) {
        	fondo = new ImageIcon(getClass().getResource("/img/fondo2.png")).getImage();
        }
        
        if (velocidadPelota >= 11 && velocidadPelota <= 15) {	
        	fondo = new ImageIcon(getClass().getResource("/img/fondo3.png")).getImage();
        }
        
        if (velocidadPelota >= 16) {
        	fondo = new ImageIcon(getClass().getResource("/img/fondo4.png")).getImage();
        }
        
        
       
        
        g2d.drawImage(fondo, 0, 0, 600, 800, null);

        g2d.setFont(new Font("Verdana", Font.BOLD, 20));

        g2d.drawString(textoJugador + nombreJugador, 10, 20);
        g2d.drawString(textoNivel + velocidadPelota, 10, 45);
        g2d.drawString(textoTiempo + tiempo.getTiempoRestante(), 10, 70);

        pelota.paint(g2d);
        raqueta.paint(g2d);

        // OBSTÁCULOS
        obstaculo1.paint(g2d);
        obstaculo2.paint(g2d);

        if (estado == EstadoJuego.PAUSA) {
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            g2d.drawString(textoPausa, 220, 400);
        }
    }
   

    private void setTextosIdioma() {

        if (idioma == 0) {
            opciones = new String[]{"Nivel: " + velocidadPelota, "Salir"};

            textoTitulo = "MINI TENNIS";
            textoNombre = "Introduce tu nombre:";
            textoJugador = "Jugador: ";
            textoNivel = "Nivel: ";
            textoTiempo = "Tiempo: ";
            textoPausa = "PAUSA";

        } else {
            opciones = new String[]{"Nivell: " + velocidadPelota, "Sortir"};

            textoTitulo = "MINI TENNIS";
            textoNombre = "Introdueix el teu nom:";
            textoJugador = "Jugador: ";
            textoNivel = "Nivell: ";
            textoTiempo = "Temps: ";
            textoPausa = "PAUSA";
        }
    }

    public void actualizarTextoNivel() {

        if (idioma == 0) {
            opciones[0] = "Nivel: " + velocidadPelota;
        } else {
            opciones[0] = "Nivell: " + velocidadPelota;
        }
    }

    public void aumentarNivel() {
        if (opcionSeleccionada == 0) {
            velocidadPelota++;
            actualizarTextoNivel();
        }
    }

    public void disminuirNivel() {
        if (opcionSeleccionada == 0 && velocidadPelota > 1) {
            velocidadPelota--;
            actualizarTextoNivel();
        }
    }

    public EstadoJuego getEstado() { return estado; }
    public Raqueta getRaqueta() { return raqueta; }

    // GETTERS IMPORTANTES (NUEVO)
    public Obstaculo getObstaculo1() { return obstaculo1; }
    public Obstaculo getObstaculo2() { return obstaculo2; }

    public void cambiarIdiomaSeleccionado() {
        idiomaSeleccionado = 1 - idiomaSeleccionado;
    }

    public void confirmarIdioma() {
        idioma = idiomaSeleccionado;
        setTextosIdioma();
        estado = EstadoJuego.NOMBRE;
    }

    public void escribirNombre(char c) {
        if (estado != EstadoJuego.NOMBRE) return;

        if (Character.isLetterOrDigit(c) && nombreJugador.length() < 10) {
            nombreJugador += c;
        }
    }

    public void borrarNombre() {
        if (nombreJugador.length() > 0) {
            nombreJugador = nombreJugador.substring(0, nombreJugador.length() - 1);
        }
    }

    public void irMenu() {
        estado = EstadoJuego.MENU;
    }

    public void menuArriba() {
        opcionSeleccionada--;
        if (opcionSeleccionada < 0) opcionSeleccionada = opciones.length - 1;
    }

    public void menuAbajo() {
        opcionSeleccionada++;
        if (opcionSeleccionada > opciones.length - 1) opcionSeleccionada = 0;
    }

    public void seleccionarOpcionMenu() {

        if (opcionSeleccionada == 1) System.exit(0);

        segundosJugados = 0;

        logica.aplicarVelocidad(velocidadPelota);
        logica.reiniciar(tiempoMaximo);

        estado = EstadoJuego.JUGANDO;
    }

    public void alternarPausa() {

        if (estado == EstadoJuego.JUGANDO) {
            estado = EstadoJuego.PAUSA;
            sonido.pausarMusica();
        }
        else if (estado == EstadoJuego.PAUSA) {
            estado = EstadoJuego.JUGANDO;
            sonido.reanudarMusica();
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Mini Tennis");
        Juego juego = new Juego();

        frame.add(juego);
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        

        new Timer(10, e -> {
            juego.move();
            juego.repaint();
        }).start();
        
    }
    //boolean para parar el bucle interminable
    private boolean jocAcabat = false;

    @Override
    public void hasPerdut() {
        if (jocAcabat) {
            return; 
        }
        jocAcabat = true; 

        
        Sound.BACK.stop();
        Sound.GAMEOVER.start();

        System.out.println("-----------------");
        System.out.println("----GAME OVER----");
        System.out.println("-----------------");

        
        try {
            new DataBaseForm(nombreJugador, velocidadPelota);
        } catch (Exception e) {
            System.out.println("Error general " + e.getMessage());
        }

        
        try {
            BasedDades.guardarResultat(nombreJugador, velocidadPelota);
            System.out.println("Els teus resultats s'han guardat correctament :)");
        } catch (Exception e) {
            System.out.println("No s'ha pogut guardar a la base de dades, però la finestra s'hauria de veure.");
            e.printStackTrace();
        }
    }
}