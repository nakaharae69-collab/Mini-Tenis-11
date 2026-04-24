package ABP.RetroTenis.logica;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ABP.RetroTenis.Juego;
import ABP.RetroTenis.Estado.EstadoJuego;

public class ControlTeclado implements KeyListener {

    private Juego juego;

    public ControlTeclado(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        juego.getRaqueta().keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        EstadoJuego estado = juego.getEstado();

        // 🌍 IDIOMA
        if (estado == EstadoJuego.IDIOMA) {

            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                juego.cambiarIdiomaSeleccionado();
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                juego.confirmarIdioma();
            }

            return;
        }

        // 🧾 NOMBRE
        if (estado == EstadoJuego.NOMBRE) {

            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                juego.borrarNombre();
            }

            else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                juego.irMenu();
            }

            else {
                juego.escribirNombre(e.getKeyChar());
            }

            return;
        }

        // 🎮 MENÚ
        if (estado == EstadoJuego.MENU) {

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                juego.menuArriba();
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                juego.menuAbajo();
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                juego.seleccionarOpcionMenu();
            }

            return;
        }

        // 🎮 JUGANDO / PAUSA
        if (estado == EstadoJuego.JUGANDO || estado == EstadoJuego.PAUSA) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                juego.alternarPausa();
            }

            if (estado == EstadoJuego.JUGANDO) {
                juego.getRaqueta().keyPressed(e);
            }
        }
    }
}