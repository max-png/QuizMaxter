package aq1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {

    static Gson gson = new Gson();

    public static void saveList(List<Question> list, Parent root) throws IOException {

        FileChooser fileChooser = new FileChooser();

        Stage saveStage = new Stage();
        saveStage.setTitle("Spara lista...");
        saveStage.setScene(new Scene(root, 450, 450));

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("json (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(saveStage);

        //Write the File
        try {
            System.out.println("Writing file.");
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(list));
            fileWriter.close();

            AQAlert.Alert("Lyckad sparning!", "Sparade " + file.getName() + " till " + file.getAbsolutePath());

        } catch (IOException e) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, e);

        }
    }

    public static ArrayList loadList(Parent root) {

        FileChooser fileChooser = new FileChooser();

        Stage loadStage = new Stage();
        loadStage.setTitle("Ladda lista...");
        loadStage.setScene(new Scene(root, 450, 450));

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("json (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(loadStage);

        gson = new Gson();
        ArrayList<Question> loadedList = null;

        Type questionListType = new TypeToken<ArrayList<Question>>() {
        }.getType();

        //Read the File
        try {
            FileReader fileReader;
            fileReader = new FileReader(file);
            loadedList = gson.fromJson(fileReader, questionListType);
            AQAlert.Alert("Lyckad laddning", "Hämtade " + file.getName() + " från " + file.getAbsolutePath());

        } catch (IOException e) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, e);
        }

        return loadedList;
    }

}
