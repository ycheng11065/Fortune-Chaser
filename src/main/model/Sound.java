package model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Sound {

    private Clip clip;
    ArrayList<URL> soundurl = new ArrayList<URL>();

    public Sound() {

        soundurl.add(getClass().getResource("/sound/theme.wav"));
        soundurl.add(getClass().getResource("/sound/fortuneSong.wav"));
        soundurl.add(getClass().getResource("/sound/PowerUpSound.wav"));
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundurl.get(i));
            clip = AudioSystem.getClip();
            clip.open(ais);
            if (i == 0) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();

    }

    public Clip getClip() {
        return clip;
    }
}
