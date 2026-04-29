package ABP.RetroTenis.interfaz;

import javax.sound.sampled.Clip;

public class GestorSonido {

    private Clip musicaFondo;
    private Clip sonidoGameOver;

    public GestorSonido(Clip musicaFondo, Clip sonidoGameOver) {
        this.musicaFondo = musicaFondo;
        this.sonidoGameOver = sonidoGameOver;
    }

    public void iniciarMusica() {
        if (musicaFondo != null) {
            musicaFondo.setFramePosition(0);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void pararMusica() {
        if (musicaFondo != null) {
            musicaFondo.stop();
        }
    }

    public void pausarMusica() {
        pararMusica();
    }

    public void reanudarMusica() {
        if (musicaFondo != null) {
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void reproducirGameOver() {
        if (sonidoGameOver != null) {
            sonidoGameOver.setFramePosition(0);
            sonidoGameOver.start();
        }
    }
}