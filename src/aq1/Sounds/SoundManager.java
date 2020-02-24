package aq1.Sounds;

import aq1.AQAlert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundManager {
    AQAlert testAlert = new AQAlert();

    //To store current position
    Long currentFrame;
    Clip clip;
    //Current status of clip
    String status;
    AudioInputStream audioInputStream;

    String filePath;

    //Create input stream with constructor
    public SoundManager(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            //Create stream object
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            //Create clip reference
            clip = AudioSystem.getClip();

            //Open audioInputStream to the clip
            clip.open(audioInputStream);
            this.filePath = filePath;

            play();

        } catch (Exception ex) {
            ex.printStackTrace();
            AQAlert.ErrorAlert("Fel", ex.toString());
        }


    }

    public void play() {
        clip.start();
        status = "play";
    }

    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream(filePath);
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
        resetAudioStream(filePath);
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream(filePath);
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    public void resetAudioStream(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public File browseSoundFile() {
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Välj ljud...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Ljudfiler", "*.wav", "*.mp3", "*.aac")
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
