package ABP.RetroTenis.interfaz;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    public static Clip BACK;
    public static Clip GAMEOVER;
    public static Clip BALL;

    static {

        try {
        	BACK = cargarSonido("/ABP/RetroTenis/interfaz/recursos/back.wav");
        	GAMEOVER = cargarSonido("/ABP/RetroTenis/interfaz/recursos/gameover.wav");
            BALL = cargarSonido("/ABP/RetroTenis/interfaz/recursos/ball.wav");
        } catch (Exception e) {
            System.out.println("❌ Error cargando sonidos:");
            e.printStackTrace();
        }
    }

    // 🔊 método para cargar clips
    private static Clip cargarSonido(String ruta) throws Exception {

        URL url = Sound.class.getResource(ruta);

        if (url == null) {
            throw new RuntimeException("No se encontró el archivo: " + ruta);
        }

        AudioInputStream audio = AudioSystem.getAudioInputStream(url);

        Clip clip = AudioSystem.getClip();
        clip.open(audio);

        return clip;
    }
}