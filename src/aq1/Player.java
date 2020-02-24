package aq1;

import aq1.Sounds.Sound;
import javafx.scene.control.TextField;

import java.io.File;

public class Player {

    private static int counter;
    private String name;
    private int points;
    private File buzzerSound;
    private boolean isSelected;
    private boolean isDisabled;
    private int id;
    private TextField pointsCounter;

    public Player() {
        counter++;
        this.id = counter;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public Player(String name) {
        this.name = name;
        counter++;
        this.id = counter;
    }

    public String getName() {
        return name;
    }

    public TextField getPointsCounter() {
        return pointsCounter;
    }

    public void setPointsCounter(TextField pointsCounter) {
        this.pointsCounter = pointsCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public File getBuzzerSound() {
        return buzzerSound;
    }

    public void setBuzzerSound(File buzzerSound) {
        this.buzzerSound = buzzerSound;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void playBuzzerSound() {
        Sound sound = new Sound();
        sound.Play(buzzerSound);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", points=" + points + ", buzzerSound=" + buzzerSound + ", isSelected=" + isSelected + ", id=" + id + '}';
    }

}
