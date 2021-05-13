package de.sharknoon.siedlervoncatan.sound;

import de.sharknoon.siedlervoncatan.Spielstart;
import de.sharknoon.siedlervoncatan.utility.Pfade;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    public static final MediaPlayer MUSIK_MENUE = new MediaPlayer(new Media(Pfade.SOUND_MENUE));
    public static final MediaPlayer MUSIK_MEER = new MediaPlayer(new Media(Pfade.SOUND_MEER));
    public static final MediaPlayer MUSIK_HANDEL = new MediaPlayer(new Media(Pfade.SOUND_HANDEL));
    public static final AudioClip WUERFEL_CLIP = new AudioClip(Pfade.SOUND_WUERFEL);
    public static final AudioClip WUERFEL_SHAKE_CLIP = new AudioClip(Pfade.SOUND_WUERFEL_WURF);
    public static final AudioClip BAU_CLIP = new AudioClip(Pfade.SOUND_BAU);
    public static final AudioClip BUTTON_CLIP = new AudioClip(Pfade.SOUND_KLICK);
    public static final AudioClip ERROR_CLIP = new AudioClip(Pfade.SOUND_ERROR);
    public static final AudioClip SIEGER_CLIP = new AudioClip(Pfade.SOUND_SIEGER);
    public static final AudioClip EVIL_LAUGH_CLIP = new AudioClip(Pfade.SOUND_EVIL_LAUGH);
    public static final AudioClip PAPER = new AudioClip(Pfade.SOUND_PAPER);
    private static Sound SOUND;
    private boolean musikAn = true;
    private boolean soundeffekteAn = true;
    private double musikVolume = 0.5;
    private double soundeffekteVolume = 1.0;
    private MediaPlayer musik;
    private AudioClip soundeffekt;

    static {
        MUSIK_HANDEL.setCycleCount(-1);
        MUSIK_MEER.setCycleCount(-1);
        MUSIK_MENUE.setCycleCount(-1);
    }

    public static Sound getInstanz() {
        if (SOUND == null) {
            SOUND = new Sound();
        }
        return SOUND;
    }

    private Sound() {
    }

    public void changeMusikVolume(double newVolume) {
        if (this.musik != null) {
            this.musik.setVolume(newVolume);
            this.musikVolume = newVolume;
        }
    }

    public double getMusikVolume() {
        return this.musikVolume;
    }

    public double getSoundeffekteVolume() {
        return this.soundeffekteVolume;
    }

    public void changeSoundeffekteVolume(double newVolume) {
        if (this.soundeffekt != null) {
            this.soundeffekt.setVolume(newVolume);
            this.soundeffekteVolume = newVolume;
        }
    }

    public void playMusik(MediaPlayer musik) {
        if (this.musik != null && !musik.equals(this.musik)) {
            this.musik.stop();
        }
        this.musik = musik;
        if (this.musikAn) {
            musik.setVolume(this.musikVolume);
            musik.play();
        }
    }

    public void playSoundeffekt(AudioClip clip) {
        this.soundeffekt = clip;
        if (this.soundeffekteAn) {
            clip.play(this.soundeffekteVolume);
        }
    }

    public void setMusikAn(boolean an) {
        this.musikAn = an;
        if (this.musik != null) {
            if (an) {
                this.playMusik(this.musik);
            } else {
                this.musik.stop();
            }
        }
    }

    public boolean getMusikAn() {
        return this.musikAn;
    }

    public void setSoundeffekteAn(boolean an) {
        this.soundeffekteAn = an;
        if (!an && this.soundeffekt != null) {
            this.soundeffekt.stop();
        }
    }

    public boolean getSoundeffekteAn() {
        return this.soundeffekteAn;
    }
}

