package ABP.RetroTenis.logica;

import ABP.RetroTenis.Estado.EstadoJuego;
import ABP.RetroTenis.*;
import ABP.RetroTenis.Objetos.Pelota;
import ABP.RetroTenis.Objetos.Raqueta;

public class LogicaJuego {

    private Pelota pelota;
    private Raqueta raqueta;
    private ControlTiempo tiempo;
    private BasedDades basedades;

    public LogicaJuego(Pelota pelota, Raqueta raqueta, ControlTiempo tiempo,
                       Object o1, Object o2, BasedDades bbdd) {

        this.pelota = pelota;
        this.raqueta = raqueta;
        this.tiempo = tiempo;
        this.basedades = bbdd;
    }

    public void actualizar(EstadoJuego estado) {

        if (estado != EstadoJuego.JUGANDO) return;

        pelota.move();
        raqueta.move();
        
        
    }

    public void aplicarVelocidad(int v) {
        pelota.setSpeed(v);
    }

    public void actualizarTiempo(EstadoJuego estado) {
        tiempo.actualizar(estado);
    }

    public void reiniciar(int tiempoMaximo) {
        tiempo.reiniciar(tiempoMaximo);
        pelota.setPosicion(300, 200);
    }

    public int getRonda() {
        return tiempo.getRonda();
    }

    public int getTiempoRestante() {
        return tiempo.getTiempoRestante();
    }
}