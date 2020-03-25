package aq1;

import aq1.Question;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.swing.*;

public class AQAlert {

    public static void ErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void Alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int QuestionGuess(String playerName, Question question) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Gissning!");
        alert.setHeaderText(playerName + " var snabbast!");
//        alert.setContentText(question.getTextValue() + "\n\nPoängvärde: " + question.getPointValue() + "\n\nRätt svar?");
        alert.setContentText(question.getTextValue() + "\n\nRätt svar?");

        ButtonType yes = new ButtonType("Ja", ButtonData.YES);
        ButtonType no = new ButtonType("Nej", ButtonData.NO);
        ButtonType cancel = new ButtonType("Avbryt", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no, cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yes) {
            // Svarade rätt! Hur många poäng etc?
            System.out.println(playerName + " svarade rätt!");
            return 1;
        } else if (result.get() == no) {
            //Svarade fel!
            System.out.println(playerName + " svarade fel!");
            return 2;
        } else {
            //Avbröt!
            return 3;
        }
    }

    public static boolean ConfirmationAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText("Var vänlig bekräfta");
        alert.setContentText(message);

        ButtonType yes = new ButtonType("Ja", ButtonData.YES);
        ButtonType no = new ButtonType("Nej", ButtonData.NO);
        ButtonType cancel = new ButtonType("Avbryt", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes,no,cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== yes){
            return true;
        } else {
            return false;
        }

    }

}
