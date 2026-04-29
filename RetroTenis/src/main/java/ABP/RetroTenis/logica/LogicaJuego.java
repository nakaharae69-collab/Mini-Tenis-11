package ABP.RetroTenis.logica;

import ABP.RetroTenis.Estado.EstadoJuego;
import ABP.RetroTenis.Objetos.Pelota;
import ABP.RetroTenis.Objetos.Raqueta;
import ABP.RetroTenis.Objetos.Obstaculo;

public class LogicaJuego {

    private Pelota pelota;
    private Raqueta raqueta;
    private ControlTiempo tiempo;

    private Obstaculo obstaculo1;
    private Obstaculo obstaculo2;

    public LogicaJuego(Pelota pelota, Raqueta raqueta, ControlTiempo tiempo,
                       Obstaculo o1, Obstaculo o2) {

        this.pelota = pelota;
        this.raqueta = raqueta;
        this.tiempo = tiempo;
        this.obstaculo1 = o1;
        this.obstaculo2 = o2;
    }

    // 🎮 actualizar juego
    public void actualizar(EstadoJuego estado) {

        if (estado != EstadoJuego.JUGANDO) return;

        pelota.move();
        raqueta.move();

        // 🧱 colisiones seguras
        comprobarColisionObstaculo(obstaculo1);
        comprobarColisionObstaculo(obstaculo2);
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
        pelota.setPosicion(300, 200);
    }

    // 🧱 COLISIÓN OBSTÁCULOS (MEJORADA)
    private void comprobarColisionObstaculo(Obstaculo obs) {

        if (obs == null) return;

        if (pelota.getBounds().intersects(obs.getBounds())) {

            int px = pelota.getBounds().x + pelota.getBounds().width / 2;

            int ox = obs.getBounds().x;
            int ow = obs.getBounds().width;

            // 🔄 rebote más estable (evita bugs de "pegado")
            boolean golpeVertical =
                    px >= ox && px <= ox + ow;

            if (golpeVertical) {
                pelota.invertirY();
            } else {
                pelota.invertirX();
            }
        }
    }

    // 📊 getters
    public int getRonda() {
        return tiempo.getRonda();
    }

    public int getTiempoRestante() {
        return tiempo.getTiempoRestante();
    }
}