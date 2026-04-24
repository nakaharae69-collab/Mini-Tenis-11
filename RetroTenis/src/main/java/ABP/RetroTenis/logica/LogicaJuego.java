package ABP.RetroTenis.logica;

import ABP.RetroTenis.Estado.EstadoJuego;
import ABP.RetroTenis.Objetos.Pelota;
import ABP.RetroTenis.Objetos.Raqueta;

public class LogicaJuego {

    private Pelota pelota;
    private Raqueta raqueta;
    private ControlTiempo tiempo;

    public LogicaJuego(Pelota pelota, Raqueta raqueta, ControlTiempo tiempo) {
        this.pelota = pelota;
        this.raqueta = raqueta;
        this.tiempo = tiempo;
    }

    // 🎮 actualizar juego
    public void actualizar(EstadoJuego estado) {

        if (estado != EstadoJuego.JUGANDO) return;

        pelota.move();
        raqueta.move();
    }

    // ⚡ velocidad
    public void aplicarVelocidad(int velocidad) {
        pelota.setSpeed(velocidad);
    }

    // ⏱️ tiempo
    public void actualizarTiempo(EstadoJuego estado) {
        tiempo.actualizar(estado);
    }

    public void reiniciar(int tiempoMaximo) {
        tiempo.reiniciar(tiempoMaximo);
    }

    // 📊 getters
    public int getRonda() {
        return tiempo.getRonda();
    }

    public int getTiempoRestante() {
        return tiempo.getTiempoRestante();
    }
}