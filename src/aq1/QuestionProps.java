package aq1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QuestionProps {

    java.util.Properties prop = new java.util.Properties();

    public void addProperty(String key, String value) {
        prop.setProperty(key, value);
    }

    public void savePropertiesFile(String path) throws FileNotFoundException {
        try {
            OutputStream output = new FileOutputStream(path);
            prop.store(output, null);
        } catch(IOException e) {
            System.out.println("Error in saveProperties: " + e);
        }

    }

}
