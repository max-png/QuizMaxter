package aq1.Handlers;

import aq1.Question;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class QuestionHandler {

    ListView<String> questionsList;
    static ArrayList<Question> questionsArray = new ArrayList<>();

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

}
