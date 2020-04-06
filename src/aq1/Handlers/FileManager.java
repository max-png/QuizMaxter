package aq1.Handlers;

import aq1.AQAlert;
import aq1.Main;
import aq1.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {

    Gson gson = new Gson();

    public void saveList(List<Object> list) {

        FileChooser fileChooser = new FileChooser();

        Stage saveStage = new Stage();
        saveStage.setTitle("Spara lista...");
//        saveStage.setScene(new Scene(root, 450, 450));

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("json (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(saveStage);
        if (file == null) {
            AQAlert.ErrorAlert("null","File cannot be empty.");
        } else {

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
    }

    public void save(Object list) {

        FileChooser fileChooser = new FileChooser();

        Stage saveStage = new Stage();
        saveStage.setTitle("Spara lista...");

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("json (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(saveStage);
        if (file == null) {
            AQAlert.ErrorAlert("null","File cannot be empty.");
        } else {

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
    }

    public ArrayList<Question> loadList() {
        ArrayList<Question> loadedList;
        File loadedFile = getListFile();
        Type questionListType = new TypeToken<ArrayList<Question>>() {
        }.getType();

        //Read the File
        try {
            FileReader fileReader;
            fileReader = new FileReader(loadedFile);
            if (loadedFile == null) {
                return null;
            } else {
                loadedList = gson.fromJson(fileReader, questionListType);
                AQAlert.Alert("Lyckad laddning", "Hämtade " + loadedFile.getName() + " från " + loadedFile.getAbsolutePath());
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, e);
            return null;
        }

        return loadedList;
    }

    public HashMap<String,String> loadMap(){
        HashMap<String,String> loadedMap;
        File loadedFile = getListFile();
        Type listType = new TypeToken<HashMap<String,String>>(){}.getType();

        try {
            FileReader fileReader;
            fileReader = new FileReader(loadedFile);
            if(loadedFile == null){
                return null;
            } else {
                loadedMap = gson.fromJson(fileReader,listType);
                AQAlert.Alert("Lyckad laddning","Hämtade!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return loadedMap;
    }

    public ArrayList<String> loadArray() {
        ArrayList<String> loadedList;
        File loadedFile = getListFile();
        Type questionListType = new TypeToken<ArrayList<String>>() {
        }.getType();

        //Read the File
        try {
            FileReader fileReader;
            fileReader = new FileReader(loadedFile);
            if (loadedFile == null) {
                return null;
            } else {
                loadedList = gson.fromJson(fileReader, questionListType);
                AQAlert.Alert("Lyckad laddning", "Hämtade " + loadedFile.getName() + " från " + loadedFile.getAbsolutePath());
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, e);
            return null;
        }

        return loadedList;
    }

//    public String getAbsoluteFilePath(String extType, String fileExtension) {
//        newStage = new Stage();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Bläddra...");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter(extType, fileExtension)
//        );
//        File selectedFile = fileChooser.showOpenDialog(newStage);
//        if (selectedFile != null) {
//            System.out.println("Selected file: " + selectedFile);
//            String path = selectedFile.getAbsolutePath();
//            return path;
//        } else {
//            return null;
//        }
//    }

    private File getListFile() {
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Bläddra...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Lista", "*.json")
        );

        File selectedFile = fileChooser.showOpenDialog(newStage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile);
            String path = selectedFile.getAbsolutePath();
            return selectedFile;
        } else {
            return null;
        }
    }

    private void saveListFile(List<Question> listToSave) {

    }

}
