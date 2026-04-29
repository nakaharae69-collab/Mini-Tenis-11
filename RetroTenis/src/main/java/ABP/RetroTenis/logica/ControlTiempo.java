package ABP.RetroTenis.logica;

import ABP.RetroTenis.Estado.EstadoJuego;

public class ControlTiempo {

    private int tiempoRestante;
    private int tiempoMaximo;
    private int ronda;
    private int velocidadPelota;

    public ControlTiempo(int tiempoMaximo) {
        reiniciar(tiempoMaximo);
    }

    public void actualizar(EstadoJuego estado) {

        if (estado == EstadoJuego.JUGANDO) {
            tiempoRestante--;

            if (tiempoRestante <= 0) {
                ronda++;
                velocidadPelota++;
                tiempoRestante = tiempoMaximo;
            }
        }
    }

    // reiniciar todo
    public void reiniciar(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
        this.tiempoRestante = tiempoMaximo;
        this.ronda = 0;
        this.velocidadPelota = 1;
    }

    // GETTERS

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getRonda() {
        return ronda;
    }

    public int getVelocidadPelota() {
        return velocidadPelota;
    }
}