package RetroTensi.RetroTensi;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    public static Clip loadClip(String path) {
        try {
            URL url = Sound.class.getResource(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final Clip BALL = loadClip("ball.wav");
    public static final Clip GAMEOVER = loadClip("gameOver.wav");
    public static final Clip BACK = loadClip("back.wav");
}