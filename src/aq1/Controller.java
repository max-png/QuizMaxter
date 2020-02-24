package aq1;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import aq1.Handlers.QuestionHandler;
import aq1.Sounds.Sound;
import aq1.Sounds.ReplaceThisOldSoundPlayer;
import aq1.Sounds.SoundManager;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
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

public class Controller implements Initializable {

    private Label label;
    @FXML
    private AnchorPane Upper;
    @FXML
    private TextField pointValue;
    @FXML
    private SplitMenuButton soundSampleSelector;
    @FXML
    private ListView<String> questionsList;
    @FXML
    private Button addQuestion;
    @FXML
    private Button removeQuestion;
    @FXML
    private Button addBranch;
    @FXML
    private AnchorPane Lower;
    @FXML
    private Group p1Field;
    @FXML
    private TextField p1Name;
    @FXML
    private Button upArrow1;
    @FXML
    private Button downArrow1;
    @FXML
    private TextField p1PointsCounter;
    @FXML
    private Group p2Field;
    @FXML
    private SplitMenuButton p1BuzzerSoundSelector;
    @FXML
    private TextField p2Name;
    @FXML
    private Button upArrow2;
    @FXML
    private Button downArrow2;
    @FXML
    private TextField p2PointsCounter;
    @FXML
    private SplitMenuButton p2BuzzerSoundSelector;
    @FXML
    private Group p3Field;
    @FXML
    private TextField p3Name;
    @FXML
    private Button upArrow3;
    @FXML
    private Button downArrow3;
    @FXML
    private TextField p3PointsCounter;
    @FXML
    private Group p4Field;
    @FXML
    private TextField p4Name;
    @FXML
    private Button upArrow4;
    @FXML
    private Button downArrow4;
    @FXML
    private TextField p4PointsCounter;
    @FXML
    private TextArea questionTextArea;

    public static boolean editMode = true;

    //TODO Flyttade denna till QUestionHandler
//    public static ArrayList<Question> questionsArray = new ArrayList<>();
    public static ArrayList<Question> questionsArray = QuestionHandler.getQuestionsArray();
//Verkar funka att ladda

//    public static SoundManager soundManager = new SoundManager();

    public Styler styler = new Styler();

//    private static int p1Points = 0;
//    private static int p2Points = 0;
//    private static int p3Points = 0;
//    private static int p4Points = 0;

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
                System.out.println("Spelare 1 heter nu " + p1.getName());
                break;
            case "p2Name":
                p2.setName(p2Name.getText());
                System.out.println("Spelare 2 heter nu " + p2.getName());
                break;
            case "p3Name":
                p3.setName(p3Name.getText());
                System.out.println("Spelare 3 heter nu " + p3.getName());
                break;
            case "p4Name":
                p4.setName(p4Name.getText());
                System.out.println("Spelare 4 heter nu " + p4.getName());
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

            AQAlert.Alert("Se upp!", "Redigeringsläge aktiverat.");
//            p1Name.getId()
        }
    }

    @FXML
    private File selectQuestionSound() {
        return new Sound().loadSound("Fråga " + questionsArray.size());
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

        //TODO Fixa det här
        questionTextArea.commitValue();
//        File getSoundValue = selectQuestionSound();
        String getTextValue = questionTextArea.getText();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Thead error in addButtonFired: " + e);
        }

        try {
            int getPointValue = Integer.parseInt(pointValue.getText());
            boolean isPenalty = penaltyYes.isSelected();
            Question newQuestion = new Question(getTextValue, getPointValue, isPenalty, null);

            questionTextArea.clear();
            questionsArray.add(newQuestion);
            questionsList.getItems().add(newQuestion.toString());
        } catch (RuntimeException e) {
            System.out.println("Error in addButtonFired: " + e);
            AQAlert.ErrorAlert("Hoppsan", "Poäng får inte innehålla text! :)");
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
        String itemToRemove = questionsList.getSelectionModel().getSelectedItem();
        newSelectedIdx = (selectedIdx == questionsList.getItems().size() - 1) ? selectedIdx - 1 : selectedIdx;

        questionsArray.remove(selectedIdx);
        questionsList.getItems().remove(selectedIdx);
        questionsList.getSelectionModel().select(newSelectedIdx);

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
        String text;
        text = questionTextArea.getText();
        Question newBranch = new Question(text, 0, false, null);
        Question.setCounter(Question.getCounter() - 1);
        newBranch.setId(0);
        questionsArray.add(newBranch);
        questionsList.getItems().add("NY GREN");
    }

    //Behövs denna?
    @FXML
    private void addQuestionFired(MouseEvent event) {
    }

    @FXML
    private void loadListFired(ActionEvent event) throws NullPointerException {
        ArrayList<Question> loadedList = new FileManager().loadList();
        try {
            questionsArray.clear();
            questionsList.getItems().clear();

            loadedList.forEach((q) -> {
                questionsArray.add(q);
                questionsList.getItems().add(q.toString());
            });

        } catch (Exception e) {
            AQAlert.Alert("Fel", e.toString());
        }
    }

    @FXML
    private void saveListFired(ActionEvent event) throws IOException {
//        FileManager.saveList(questionsArray, FXMLLoader.load(getClass().getResource("AQ1.fxml")));
        FileManager fm = new FileManager();
        fm.saveList(questionsArray);
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

            if (AQAlert.QuestionGuess(player.getName(), getSelectedQuestion())) {
                points = points + getSelectedQuestion().getPointValue();
                int selectedId = questionsList.getSelectionModel().getSelectedIndex();
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
            } else if (getSelectedQuestion().isPenalty()) {
                points = points - getSelectedQuestion().getPointValue();
                System.out.println("Wrong answer, points are now " + points);
            }
            //TODO Disable this player until someone guessed right, also put a points counter in each player
            //TODO Also make sure the guessing player gets yellow.

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

            System.out.println("returning " + points);
            player.setPoints(points);
            return points;
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

}
