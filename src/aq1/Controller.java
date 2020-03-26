package aq1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import aq1.Documentation.Texts;
import aq1.Handlers.FileManager;
import aq1.Handlers.QuestionHandler;
import aq1.Sounds.Sound;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class Controller implements Initializable {

    @FXML
    private TextField pointValue;
    @FXML
    private ListView<String> questionsList;
    @FXML
    private TextField p1Name;
    @FXML
    private TextField p1PointsCounter;
    @FXML
    private SplitMenuButton p1BuzzerSoundSelector;
    @FXML
    private TextField p2Name;
    @FXML
    private TextField p2PointsCounter;
    @FXML
    private SplitMenuButton p2BuzzerSoundSelector;
    @FXML
    private TextField p3Name;
    @FXML
    private TextField p3PointsCounter;
    @FXML
    private TextField p4Name;
    @FXML
    private TextField p4PointsCounter;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private Button updateQuestion;

    public static boolean editMode = true;

    public static ArrayList<Question> questionsArray = QuestionHandler.getQuestionsArray();

    public Styler styler = new Styler();

    public static File correctSound;
    public static File wrongSound;

    //    static String playerSelector = null;
    static int playerSelector = 0;

    ObservableList<String> strings = FXCollections.observableArrayList();
    @FXML
    private Button saveList;
    @FXML
    private Button loadList;
    @FXML
    private SplitMenuButton p3BuzzerSoundSelector;
    @FXML
    private SplitMenuButton p4BuzzerSoundSelector;
    @FXML
    private MenuItem editModeSelector;
    @FXML
    private HBox questionsHBox;
    @FXML
    private RadioButton penaltyNo;
    @FXML
    private ToggleGroup penaltyGroup;
    @FXML
    private RadioButton penaltyYes;

    @FXML
    private void handleKeyReleased(KeyEvent event) {

        if (!editMode) {
            int id = 0;
            switch (event.getCode()) {
                case DIGIT1:
                    if (!p1.isDisabled()) {
                        styler.setBackgroundColor(p1PointsCounter, "green");
                        p1.playBuzzerSound();
                        p1PointsCounter.setText(intToString(questionGuess(p1)));
                    }
                    break;
                case DIGIT2:
                    if (!p2.isDisabled()) {
                        styler.setBackgroundColor(p2PointsCounter, "green");
                        p2.playBuzzerSound();
                        p2PointsCounter.setText(intToString(questionGuess(p2)));
                    }
                    break;
                case DIGIT3:
                    if (!p3.isDisabled()) {
                        styler.setBackgroundColor(p3PointsCounter, "green");
                        p3.playBuzzerSound();
                        p3PointsCounter.setText(intToString(questionGuess(p3)));
                    }
                    break;
                case DIGIT4:
                    if (!p4.isDisabled()) {
                        styler.setBackgroundColor(p4PointsCounter, "green");
                        p4.playBuzzerSound();
                        p4PointsCounter.setText(intToString(questionGuess(p4)));
                    }
                    break;
                case DIGIT5:
                    p1PointsCounter.styleProperty().setValue("-fx-background-color: white");
                    p2PointsCounter.styleProperty().setValue("-fx-background-color: white");
                    p3PointsCounter.styleProperty().setValue("-fx-background-color: white");
                    p4PointsCounter.styleProperty().setValue("-fx-background-color: white");
                    p1.setDisabled(false);
                    p2.setDisabled(false);
                    p3.setDisabled(false);
                    p4.setDisabled(false);
                    break;

                case S:
                    questionsList.getSelectionModel().select(questionsList.getSelectionModel().getSelectedIndex() + 1);
                    break;

                case W:
                    if (questionsList.getSelectionModel().getSelectedIndex() > 0) {
                        questionsList.getSelectionModel().select(questionsList.getSelectionModel().getSelectedIndex() - 1);
                    }
                    break;
            }
        }
    }

    static Player p1 = new Player("Spelare 1");
    static Player p2 = new Player("Spelare 2");
    static Player p3 = new Player("Spelare 3");
    static Player p4 = new Player("Spelare 4");

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        p1Name.setText(p1.getName());
        p2Name.setText(p2.getName());
        p3Name.setText(p3.getName());
        p4Name.setText(p4.getName());

        p1PointsCounter.setText(intToString(p1.getPoints()));
        p2PointsCounter.setText(intToString(p2.getPoints()));
        p3PointsCounter.setText(intToString(p3.getPoints()));
        p4PointsCounter.setText(intToString(p4.getPoints()));

////        //Initialize a listener to the listview.
        ObservableList<String> data = FXCollections.observableArrayList();

        questionsArray.forEach((nextQuestion) -> data.add(nextQuestion.toString()));

        questionsList.setItems(data);

        questionsList.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            if (questionsArray.size() != 0) {
                final int selectedId = questionsList.getSelectionModel().getSelectedIndex();
                final int selectedQuestionValue = questionsArray.get(selectedId).getPointValue();
                final String selectedStringValue = questionsArray.get(selectedId).getTextValue();
                final boolean isPenalty = questionsArray.get(selectedId).isPenalty();

                questionTextArea.setText(selectedStringValue);
                if (isPenalty) {
                    penaltyYes.selectedProperty().set(true);
                } else {
                    penaltyNo.selectedProperty().set(true);
                }
                pointValue.setText(intToString(selectedQuestionValue));

                if (questionsList.getSelectionModel().getSelectedItem().toString().contains("NY GREN")) {
                    System.out.println("Setting everyone disabled");
                    p1.setDisabled(true);
                    p2.setDisabled(true);
                    p3.setDisabled(true);
                    p4.setDisabled(true);
                } else {
                    System.out.println("Setting everyone back to normal");
                    p1.setDisabled(false);
                    p2.setDisabled(false);
                    p3.setDisabled(false);
                    p4.setDisabled(false);
                }
            }

        });

        //Listener to the pointValueField to make it only accept integers.
        pointValue.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int getPointValue = Integer.parseInt(newValue);
            } catch (RuntimeException e) {
                System.out.println("Error in pointValueField listener: " + e);
                AQAlert.ErrorAlert("Hoppsan", "Poäng får endast vara heltal!");
                pointValue.setText("1");
            }
        });

    }

    @FXML
    private void onEnter(ActionEvent ae) {
        Node source = (Node) ae.getSource();
        String id;
        id = source.getId();

        switch (id) {
            case "p1Name":
                p1.setName(p1Name.getText());
                AQAlert.Alert("Namnbyte", "Spelare 1 heter nu " + p1.getName());
                break;
            case "p2Name":
                p2.setName(p2Name.getText());
                AQAlert.Alert("Namnbyte", "Spelare 2 heter nu " + p2.getName());
                break;
            case "p3Name":
                p3.setName(p3Name.getText());
                AQAlert.Alert("Namnbyte", "Spelare 3 heter nu " + p3.getName());
                break;
            case "p4Name":
                p4.setName(p4Name.getText());
                AQAlert.Alert("Namnbyte", "Spelare 4 heter nu " + p4.getName());
                break;
        }

    }

    @FXML
    private void editModeSelectorFired(ActionEvent event) {
        //Spelläge
        if (editMode) {
            editMode = false;
            editModeSelector.setText("Växla till redigeringsläge");
            questionsHBox.visibleProperty().set(false);
//            questionTextArea.disableProperty().set(true);

            updateQuestion.setVisible(false);

            penaltyNo.setDisable(true);
            penaltyYes.setDisable(true);


            questionTextArea.editableProperty().set(false);
            pointValue.editableProperty().set(false);
            p1Name.editableProperty().set(false);
            p2Name.editableProperty().set(false);
            p3Name.editableProperty().set(false);
            p4Name.editableProperty().set(false);
            setSelectedQuestion(0);
            AQAlert.Alert("Se upp!", "Spelläge aktiverat.");

        } else {
            //Redigeringsläge
            editMode = true;
            editModeSelector.setText("Växla till spelläge");
            questionsHBox.visibleProperty().set(true);
            questionTextArea.editableProperty().set(true);
            pointValue.editableProperty().set(true);
            p1Name.editableProperty().set(true);
            p2Name.editableProperty().set(true);
            p3Name.editableProperty().set(true);
            p4Name.editableProperty().set(true);

            penaltyNo.setDisable(false);
            penaltyYes.setDisable(false);

            updateQuestion.setVisible(true);
            AQAlert.Alert("Se upp!", "Redigeringsläge aktiverat.");
//            p1Name.getId()
        }
    }

    @FXML
    private void resetGameFired() {
        if (AQAlert.ConfirmationAlert("Nytt spel?", "Starta en ny omgång?")) {
            //TODO Städa upp detta.
            questionsArray.clear();
            Question.setCounter(1);
            questionsList.getItems().clear();
            questionTextArea.clear();
            p1.setName("Spelare 1");
            p2.setName("Spelare 2");
            p3.setName("Spelare 3");
            p4.setName("Spelare 4");
            p1.setPoints(0);
            p2.setPoints(0);
            p3.setPoints(0);
            p4.setPoints(0);
            p1PointsCounter.setText(intToString(0));
            p2PointsCounter.setText(intToString(0));
            p3PointsCounter.setText(intToString(0));
            p4PointsCounter.setText(intToString(0));

            p1Name.setText(p1.getName());
            p2Name.setText(p2.getName());
            p3Name.setText(p3.getName());
            p4Name.setText(p4.getName());
        }
    }

    @FXML
    private void showGuide() {
        Stage helpStage = new Stage();
        helpStage.setTitle("Guide");
        helpStage.setMinHeight(680);
        helpStage.setMinWidth(1200);
        VBox comp = new VBox();
        TextArea helpText = new TextArea(Texts.guideText());
        helpText.setEditable(false);
        helpText.setMinSize(1200, 680);
        comp.getChildren().add(helpText);
        comp.fillWidthProperty().setValue(true);
        comp.setMinSize(1200, 680);

        Scene stageScene = new Scene(comp, 1200, 680);
        helpStage.setScene(stageScene);
        helpStage.show();
    }

    @FXML
    private void selectCorrectSoundFired() {
        Sound sound = new Sound();
        correctSound = sound.loadSound("rätt svar");
        sound.Play(correctSound);
    }

    @FXML
    private void selectWrongSoundFired() {
        Sound sound = new Sound();
        wrongSound = sound.loadSound("fel svar");
        sound.Play(wrongSound);
    }

    @FXML
    private void loadSounds(){
//        FileManager fm = new FileManager();
        ArrayList<String> loadedList = new FileManager().loadArray();
        System.out.println(loadedList.toString());
        try {
            correctSound = new File(loadedList.get(0));
            wrongSound = new File(loadedList.get(1));
            p1.setBuzzerSound(new File(loadedList.get(2)));
            p2.setBuzzerSound(new File(loadedList.get(3)));
            p3.setBuzzerSound(new File(loadedList.get(4)));
            p4.setBuzzerSound(new File(loadedList.get(5)));

            p1BuzzerSoundSelector.setText(p1.getBuzzerSound().getName());
            p2BuzzerSoundSelector.setText(p2.getBuzzerSound().getName());
            p3BuzzerSoundSelector.setText(p3.getBuzzerSound().getName());
            p4BuzzerSoundSelector.setText(p4.getBuzzerSound().getName());

        } catch(Exception e){
            e.printStackTrace();
            AQAlert.ErrorAlert("Filen hittades inte", "Sökvägen till en av ljudfilerna var fel!");
        }
    }

    @FXML
    private void saveSounds() throws IOException{
        FileManager fm = new FileManager();
        List<Object> listToSave = new ArrayList<>();
        listToSave.add(correctSound.getAbsolutePath());
        listToSave.add(wrongSound.getAbsolutePath());
        listToSave.add(p1.getBuzzerSound().getAbsolutePath());
        listToSave.add(p2.getBuzzerSound().getAbsolutePath());
        listToSave.add(p3.getBuzzerSound().getAbsolutePath());
        listToSave.add(p4.getBuzzerSound().getAbsolutePath());
        try {
            fm.saveList(listToSave);
        }
        catch(Exception e){
            e.printStackTrace();
            AQAlert.ErrorAlert("Ett ljud saknades","Se till så alla ljud är korrekt valda (ljud för korrekt/inkorrekt svar och för alla spelare och försök sen igen.");
        }
    }

    //TODO Clean up the code with method calls
    @FXML
    private void selectBuzzerSound(ActionEvent event) {
        switch (playerSelector) {
            case 1:
                //TODO Change playerSelector from String to player.
//                Sound.setBuzzerSound(p1.setBuzzerSound(p1.));

                p1.setBuzzerSound(new Sound().loadSound(p1.getName()));
                p1.playBuzzerSound();

//                p1BuzzerSoundSelector.isFocused();
                p1BuzzerSoundSelector.setText(p1.getBuzzerSound().getName());
                break;
            case 2:
                //setBuzzerSound(p2);
                p2.setBuzzerSound(new Sound().loadSound(p2.getName()));
                p2.playBuzzerSound();
                p2BuzzerSoundSelector.setText(p2.getBuzzerSound().getName());
                break;
            case 3:
                p3.setBuzzerSound(new Sound().loadSound(p3.getName()));
                p3.playBuzzerSound();
                p3BuzzerSoundSelector.setText(p3.getBuzzerSound().getName());
                break;
            case 4:
                p4.setBuzzerSound(new Sound().loadSound(p4.getName()));
                p4.playBuzzerSound();
                p4BuzzerSoundSelector.setText(p4.getBuzzerSound().getName());
                break;
        }
    }

    @FXML
    private void addButtonFired(ActionEvent event) {

        int selectedIndex = questionsList.getSelectionModel().getSelectedIndex();
        int size = questionsList.getItems().size();

        //TODO Fixa det här
        questionTextArea.commitValue();
        String getTextValue = questionTextArea.getText();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Thead error in addButtonFired: " + e);
        }

        try {
            int getPointValue = Integer.parseInt(pointValue.getText());
            boolean isPenalty = penaltyYes.isSelected();
            Question newQuestion = new Question(getTextValue, getPointValue, isPenalty);

            if (selectedIndex < (size - 1) && selectedIndex != (-1)) {
                questionsList.getItems().add(selectedIndex + 1, newQuestion.toString());
                questionsArray.add(selectedIndex + 1, newQuestion);
                questionsList.getSelectionModel().select(selectedIndex + 1);
            } else {
                questionsList.getItems().add(newQuestion.toString());
                questionsArray.add(newQuestion);
                questionsList.getSelectionModel().selectLast();
            }
            pointValue.setText("1");
            questionTextArea.clear();

        } catch (Exception e) {
            System.out.println("Error in addButtonFired: " + e);
            AQAlert.ErrorAlert("Hoppsan", e.toString());
        }
    }

    private void clearButtonFired(ActionEvent event) {
        questionTextArea.clear();
    }

    //TODO Flyttade denna och questionsList + questionsArray till QuestionHandler
    private void removeQuestion(ActionEvent event) {
        questionTextArea.clear();
        final int selectedIdx = questionsList.getSelectionModel().getSelectedIndex();

        final int newSelectedIdx;
//        newSelectedIdx = (selectedIdx == questionsList.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

        questionsArray.remove(selectedIdx);
        questionsList.getItems().remove(selectedIdx);
        questionsList.getSelectionModel().select(selectedIdx);
    }


    //    //TODO Det här är onödigt, fixa.
    @FXML
    private void player1Selected(MouseEvent event) {
        playerSelector = 1;
    }

    @FXML
    private void player2Selected(MouseEvent event) {
        playerSelector = 2;
    }

    @FXML
    private void player3Selected(MouseEvent event) {
        playerSelector = 3;
    }

    @FXML
    private void player4Selected(MouseEvent event) {
        playerSelector = 4;
    }

    @FXML
    private void addBranchFired(ActionEvent event) {
        int insert = questionsList.getSelectionModel().getSelectedIndex() + 1;
        String text;
        text = questionTextArea.getText();
        Question newBranch = new Question(text, 0, false);
        Question.setCounter(Question.getCounter() - 1);
        newBranch.setId(0);
        questionsArray.add(insert, newBranch);
        questionsList.getItems().add(insert, "NY GREN");
        questionsList.getSelectionModel().select(insert);
        pointValue.setText("1");
    }

    //Behövs denna?
    @FXML
    private void addQuestionFired(MouseEvent event) {
    }

    @FXML
    private void loadListFired(ActionEvent event) throws NullPointerException {
        ArrayList<Question> loadedList = new FileManager().loadList();

        if (loadedList == null) {
            return;
        } else {

            try {
                questionsList.getItems().clear();
                Question.setCounter(loadedList.size() + 1);
                loadedList.forEach((q) -> {

                    questionsArray.add(q);
                    if (q.getId() == 0) {
                        questionsList.getItems().add("NY GREN");
                    } else {
                        questionsList.getItems().add(q.toString());
                    }
                });

            } catch (Exception e) {
                AQAlert.Alert("Fel", e.toString());
            }
        }

    }

    @FXML
    private void saveListFired(ActionEvent event) throws IOException {
//        FileManager.saveList(questionsArray, FXMLLoader.load(getClass().getResource("AQ1.fxml")));
        FileManager fm = new FileManager();
        List<Object> listToSave = new ArrayList<>(questionsArray);
        fm.saveList(listToSave);
    }

    private String intToString(int i) {
        String parsed = null;
        if (i <= 0 || i >= 0) {
            parsed = String.valueOf(i);
        } else {
            return "";
        }
        return parsed;
    }

    @FXML
    private void removeQuestionFired(ActionEvent event) throws IndexOutOfBoundsException {
        try {
            removeQuestion(event);
        } catch (Exception ex) {
            System.out.println("Caught an exception.");
        }
    }

    //TODO Lägg den här i en QuestionHandler
    private Question getSelectedQuestion() {
        questionsList.getSelectionModel().selectedItemProperty();
        final int selectedId = questionsList.getSelectionModel().getSelectedIndex();
        return questionsArray.get(selectedId);
    }

    //TODO Den här också
    private void setSelectedQuestion(int i) {
        questionsList.getSelectionModel().select(i);
    }

    private int questionGuess(Player player) {

        int points;
        points = player.getPoints();
        System.out.println("Starting points are " + points);

        if (!player.isDisabled()) {

            int result = AQAlert.QuestionGuess(player.getName(), getSelectedQuestion());

            if (result == 1) {
                new Sound().Play(correctSound);
                points = points + getSelectedQuestion().getPointValue();
                int selectedId = questionsList.getSelectionModel().getSelectedIndex();
                questionsList.getItems().set(selectedId, questionsList.getSelectionModel().getSelectedItem() + " (Besvarad)");
                setSelectedQuestion(selectedId + 1);
                System.out.println("Correct answer, points are now " + points);
                p1.setDisabled(false);
                p2.setDisabled(false);
                p3.setDisabled(false);
                p4.setDisabled(false);
                styler.setBackgroundColor(p1PointsCounter, "white");
                styler.setBackgroundColor(p2PointsCounter, "white");
                styler.setBackgroundColor(p3PointsCounter, "white");
                styler.setBackgroundColor(p4PointsCounter, "white");
                player.setPoints(points);
                return points;
            } else if (result == 2) {
                if (getSelectedQuestion().isPenalty()) {
                    points = points - getSelectedQuestion().getPointValue();
                    System.out.println("Wrong answer, points are now " + points);
                }

                switch (player.getId()) {
                    case 1:
                        player.setDisabled(true);
                        styler.setBackgroundColor(p1PointsCounter, "yellow");
                        break;
                    case 2:
                        player.setDisabled(true);
                        styler.setBackgroundColor(p2PointsCounter, "yellow");
                        break;
                    case 3:
                        player.setDisabled(true);
                        styler.setBackgroundColor(p3PointsCounter, "yellow");
                        break;
                    case 4:
                        player.setDisabled(true);
                        styler.setBackgroundColor(p4PointsCounter, "yellow");
                        break;
                }
                new Sound().Play(wrongSound);
                System.out.println("returning " + points);
                player.setPoints(points);
                return points;
            }
        }
        switch (player.getId()) {
            case 1:
                styler.setBackgroundColor(p1PointsCounter, "white");
                break;
            case 2:
                styler.setBackgroundColor(p2PointsCounter, "white");
                break;
            case 3:
                styler.setBackgroundColor(p3PointsCounter, "white");
                break;
            case 4:
                styler.setBackgroundColor(p4PointsCounter, "white");
                break;
        }
        return player.getPoints();
    }


    //Öka poäng med pilar
    @FXML
    private void increasePointsFired(ActionEvent event) {
        switch (playerSelector) {
            case 1:
                int points = p1.getPoints() + 1;
                p1.setPoints(points);
                p1PointsCounter.setText(intToString(points));
                break;
            case 2:
                points = p2.getPoints() + 1;
                p2.setPoints(points);
                p2PointsCounter.setText(intToString(points));
                break;
            case 3:
                points = p3.getPoints() + 1;
                p3.setPoints(points);
                p3PointsCounter.setText(intToString(points));
                break;
            case 4:
                points = p4.getPoints() + 1;
                p4.setPoints(points);
                p4PointsCounter.setText(intToString(points));
                break;
        }
    }

    //Minska poäng med pilar
    @FXML
    private void decreasePointsFired(ActionEvent event) {

        switch (playerSelector) {
            case 1:

                int points = p1.getPoints() - 1;
                p1.setPoints(points);
                p1PointsCounter.setText(intToString(points));
                break;
            case 2:
                points = p2.getPoints() - 1;
                p2.setPoints(points);
                p2PointsCounter.setText(intToString(points));
                break;
            case 3:
                points = p3.getPoints() - 1;
                p3.setPoints(points);
                p3PointsCounter.setText(intToString(points));
                break;
            case 4:
                points = p4.getPoints() - 1;
                p4.setPoints(points);
                p4PointsCounter.setText(intToString(points));
                break;
        }

    }

    @FXML
    public void clickP1Test(Event actionEvent){
        p1.setDisabled(false);
        styler.setBackgroundColor(p1PointsCounter, "green");
        p1PointsCounter.setText(intToString(questionGuess(p1)));
    }

    @FXML
    public void clickP2Test(Event actionEvent){
        p2.setDisabled(false);
        styler.setBackgroundColor(p2PointsCounter, "green");
        p2PointsCounter.setText(intToString(questionGuess(p2)));
    }

    @FXML
    public void clickP3Test(Event actionEvent){
        p3.setDisabled(false);
        styler.setBackgroundColor(p3PointsCounter, "green");
        p3PointsCounter.setText(intToString(questionGuess(p3)));
    }

    @FXML
    public void clickP4Test(Event actionEvent){
        p4.setDisabled(false);
        styler.setBackgroundColor(p4PointsCounter, "green");
        p4PointsCounter.setText(intToString(questionGuess(p4)));
    }

    @FXML
    public void updateQuestionFired(ActionEvent actionEvent) {
        questionTextArea.commitValue();
        try {
            getSelectedQuestion().setPointValue(Integer.parseInt(pointValue.getText()));
            getSelectedQuestion().setPenalty(penaltyYes.isSelected());
            getSelectedQuestion().setTextValue(questionTextArea.getText());

            AQAlert.Alert("Uppdatering", "Uppdateringen av fråga " + getSelectedQuestion().getId() + " lyckades!");
        } catch (RuntimeException e) {
            System.out.println("Error in addButtonFired: " + e);
            AQAlert.ErrorAlert("Hoppsan", "Poäng får inte innehålla text! :)");
        }
    }
}
