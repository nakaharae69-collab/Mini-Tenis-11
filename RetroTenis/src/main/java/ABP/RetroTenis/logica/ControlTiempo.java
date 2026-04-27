package ABP.RetroTenis.logica;

import ABP.RetroTenis.Estado.EstadoJuego;

public class ControlTiempo {

    private int tiempoRestante;
    private int tiempoMaximo;
    private int ronda;
    private int velocidadPelota;

    public ControlTiempo(int tiempoMaximoInicial) {
        this.tiempoMaximo = tiempoMaximoInicial;
        this.tiempoRestante = tiempoMaximoInicial;
        this.ronda = 0;
        this.velocidadPelota = 1;
    }

    public void actualizar(EstadoJuego estado) {

        if (estado != EstadoJuego.JUGANDO) return;

        tiempoRestante--;

        if (tiempoRestante <= 0) {
            velocidadPelota++;
            ronda++;
            tiempoRestante = tiempoMaximo;
        }
    }

    // GETTERS

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public int getRonda() {
        return ronda;
    }

    public int getVelocidadPelota() {
        return velocidadPelota;
    }

    // SETTERS IMPORTANTES

    public void reiniciar(int tiempoMaximoNuevo) {
        this.tiempoMaximo = tiempoMaximoNuevo;
        this.tiempoRestante = tiempoMaximoNuevo;
        this.ronda = 0;
        this.velocidadPelota = 1;
    }
}