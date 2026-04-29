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

        switch (juego.getEstado()) {

            case IDIOMA:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    juego.cambiarIdiomaSeleccionado();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    juego.confirmarIdioma();
                }
                break;

            case NOMBRE:
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    juego.borrarNombre();
                } 
                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    juego.irMenu();
                } 
                else {
                    juego.escribirNombre(e.getKeyChar());
                }
                break;

            case MENU:
                if (e.getKeyCode() == KeyEvent.VK_UP) juego.menuArriba();
                if (e.getKeyCode() == KeyEvent.VK_DOWN) juego.menuAbajo();

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) juego.aumentarNivel();
                if (e.getKeyCode() == KeyEvent.VK_LEFT) juego.disminuirNivel();

                if (e.getKeyCode() == KeyEvent.VK_ENTER) juego.seleccionarOpcionMenu();
                break;

            case JUGANDO:
            case PAUSA:

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    juego.alternarPausa();
                }

                if (juego.getEstado() == EstadoJuego.JUGANDO) {
                    juego.getRaqueta().keyPressed(e);
                }
                break;
        }
    }
}