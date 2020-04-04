package aq1.Handlers;

import aq1.AQAlert;
import aq1.Controller;
import aq1.Player;
import aq1.Question;
import aq1.Sounds.Sound;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.ArrayList;

public class QuestionHandler {



    ListView<String> questionsList;
    static ArrayList<Question> questionsArray = new ArrayList<>(10);

    public ListView<String> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ListView<String> questionsList) {
        this.questionsList = questionsList;
    }

    public static ArrayList<Question> getQuestionsArray() {
        return questionsArray;
    }

    public static void setQuestionsArray(ArrayList<Question> questionsArray) {
        QuestionHandler.questionsArray = questionsArray;
    }

    private void removeQuestion(ActionEvent event) {
        final int selectedIdx = questionsList.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String itemToRemove = questionsList.getSelectionModel().getSelectedItem();
            final int newSelectedIdx = (selectedIdx == questionsList.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;
            questionsArray.remove(selectedIdx);
            questionsList.getItems().remove(selectedIdx);
            questionsList.getSelectionModel().select(newSelectedIdx);
        }
    }

    public void guess(Player player){

        File correctSound = Controller.correctSound;
        File wrongSound = Controller.wrongSound;
//
//        int points;
//        points = player.getPoints();
//        System.out.println("Starting points are " + points);
//
//        if (!player.isDisabled()) {
//            int result = AQAlert.QuestionGuess(player.getName(), getSelectedQuestion());
//            if (result == 1) {
//
//                new Sound().Play(correctSound);
//                points = points + getSelectedQuestion().getPointValue();
//                int selectedId = questionsList.getSelectionModel().getSelectedIndex();
//                questionsList.getItems().set(selectedId, questionsList.getSelectionModel().getSelectedItem() + " (Besvarad)");
//                setSelectedQuestion(selectedId + 1);
//                System.out.println("Correct answer, points are now " + points);
//                p1.setDisabled(false);
//                p2.setDisabled(false);
//                p3.setDisabled(false);
//                p4.setDisabled(false);
//                styler.setBackgroundColor(p1PointsCounter, "white");
//                styler.setBackgroundColor(p2PointsCounter, "white");
//                styler.setBackgroundColor(p3PointsCounter, "white");
//                styler.setBackgroundColor(p4PointsCounter, "white");
//                player.setPoints(points);
//                return points;
//            } else if (result == 2) {
//                if (getSelectedQuestion().isPenalty()) {
//                    points = points - getSelectedQuestion().getPointValue();
//                    System.out.println("Wrong answer, points are now " + points);
//                }
//
//                switch (player.getId()) {
//                    case 1:
//                        player.setDisabled(true);
//                        styler.setBackgroundColor(p1PointsCounter, "yellow");
//                        break;
//                    case 2:
//                        player.setDisabled(true);
//                        styler.setBackgroundColor(p2PointsCounter, "yellow");
//                        break;
//                    case 3:
//                        player.setDisabled(true);
//                        styler.setBackgroundColor(p3PointsCounter, "yellow");
//                        break;
//                    case 4:
//                        player.setDisabled(true);
//                        styler.setBackgroundColor(p4PointsCounter, "yellow");
//                        break;
//                }
//                new Sound().Play(wrongSound);
//                System.out.println("returning " + points);
//                player.setPoints(points);
//                return points;
//            }
//        }
//        switch (player.getId()) {
//            case 1:
//                styler.setBackgroundColor(p1PointsCounter, "white");
//                break;
//            case 2:
//                styler.setBackgroundColor(p2PointsCounter, "white");
//                break;
//            case 3:
//                styler.setBackgroundColor(p3PointsCounter, "white");
//                break;
//            case 4:
//                styler.setBackgroundColor(p4PointsCounter, "white");
//                break;
//        }
//        return player.getPoints();
    }

    private Question getSelectedQuestion() {
        questionsList.getSelectionModel().selectedItemProperty();
        final int selectedId = questionsList.getSelectionModel().getSelectedIndex();
        return questionsArray.get(selectedId);
    }

}
