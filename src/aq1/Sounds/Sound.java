package aq1.Sounds;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import aq1.AQAlert;
import aq1.Player;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class Sound {

    private String name;
    private File path;


    public File loadSound(String selector) {
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Välj ljud åt " + selector);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ljudfiler", "*.wav", "*.mp3", "*.aac")
        );

        File selectedFile = fileChooser.showOpenDialog(newStage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile);
            setPath(selectedFile);
        }
            return selectedFile;
    }

    public void Play(File path) {
        InputStream sound;
        if (path != null) {
            try {
                sound = new FileInputStream(path);
                SoundManager soundManager = new SoundManager(path.getAbsolutePath());
                soundManager.play();
            } catch (Exception e) {
                AQAlert.ErrorAlert("Error", "Något gick fel med ljudet: " + e);
                System.out.println(e);
            }
        } else {
//            AQAlert.ErrorAlert("Inget ljud!", "Inget ljud var laddat.");
            System.out.println("Inget ljud laddat.");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public static void setBuzzerSound(Player player) {
        player.setBuzzerSound(new Sound().loadSound(player.getName()));
        player.playBuzzerSound();
    }

}
