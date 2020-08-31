package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// plays sounds
public class PlayButtonMusic {
    public static final String COINSFALL_FILE = "./data/coinsFall.wav";
    public static final String TRANSACTIONS_FILE = "./data/transactions.txt";
    public static final String BUTTON_FILE = "./data/buttonClick.wav";
    public static final String TINYBUTTON_FILE = "./data/tinyButton.wav";
    public static final String DISCORDJOIN_FILE = "./data/dicordJoin.wav";
    public static final String CSGOPLANT_FILE = "./data/bombPlanted.wav";
    public static final String ERRO_FILE = "./data/erro.wav";
    public static final String MARIO_FILE = "./data/mario.wav";


    // COMMENTS: get hints of building this method from
    //           http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public PlayButtonMusic(String soundName) {
        AudioInputStream stream;
        Clip clip;
        try {
            stream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
