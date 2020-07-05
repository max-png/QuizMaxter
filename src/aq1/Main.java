package aq1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AQ1.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("QuizMaxter 3000 v.1.1");
        Image img = new Image("/icons/QuizMaxter.png");

        stage.getIcons().add(img);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
