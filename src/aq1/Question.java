package aq1;

import java.io.File;

public class Question {

    private static int counter = 1;

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int aCounter) {
        counter = aCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String textValue;
    private int pointValue;
    private boolean penalty;

    public Question(String textValue, int pointValue, boolean penalty) {
        this.textValue = textValue;
        this.pointValue = pointValue;
        this.penalty = penalty;
        this.id = counter;
        counter++;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String question) {
        this.textValue = question;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    @Override
    public String toString() {
        return "Fråga " + this.getId();
    }

    public String detailedToString() {
        return "Question{" + "id=" + id + ", textValue=" + textValue + ", pointValue=" + pointValue + ", penalty=" + penalty;
    }


    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

}
