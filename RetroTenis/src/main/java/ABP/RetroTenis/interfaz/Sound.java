package ABP.RetroTenis.interfaz;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    public static Clip BACK = cargar("/ABP/RetroTenis/interfaz/recursos/back.wav");
    public static Clip GAMEOVER = cargar("/ABP/RetroTenis/interfaz/recursos/gameover.wav");
    public static Clip BALL = cargar("/ABP/RetroTenis/interfaz/recursos/ball.wav");

    // método único simplificado
    private static Clip cargar(String ruta) {
        try {
            URL url = Sound.class.getResource(ruta);

            if (url == null) {
                System.out.println("No se encontró: " + ruta);
                return null;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);

            return clip;

        } catch (Exception e) {
            System.out.println("Error cargando: " + ruta);
            return null;
        }
    }
}