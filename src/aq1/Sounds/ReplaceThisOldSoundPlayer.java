package aq1.Sounds;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import aq1.AQAlert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class ReplaceThisOldSoundPlayer {

    AQAlert testAlert = new AQAlert();

    String path = "SFX\\";
    String correctSFX = "correct.wav";
    String incorrectSFX = "incorrect.wav";

    public static void PlaySound(String filePath) {
        InputStream sound;
        try {
            sound = new FileInputStream(new File(filePath));

            //AudioStream audioStream = new AudioStream(sound);
            //AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            AQAlert.ErrorAlert("Error", "Något gick fel med ljudet: " + e);
            System.out.println(e);
        }
    }

    public static File loadSound() {
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Bläddra efter ljud...");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Ljudfiler", "*.wav", "*.mp3", "*.aac")
        );

        File selectedFile = fileChooser.showOpenDialog(newStage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile);
            return selectedFile;
        } else {
            return null;
        }
    }

}
