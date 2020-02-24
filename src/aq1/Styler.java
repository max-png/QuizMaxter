package aq1;

import javafx.scene.control.TextField;

public class Styler {

    public void setBackgroundColor(TextField player, String color) {
        player.styleProperty().setValue("-fx-background-color: " + color);
    }

    public void setPlayerBG(Player player, String color){
        int id = player.getId();
    }

}


