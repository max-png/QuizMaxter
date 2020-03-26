package aq1;

public class Game {

    Game currentGame;

    Player playerOne;
    Player playerTwo;
    Player playerThree;
    Player playerFour;

    public Game() {

    }

    public void newGame(String p1Name, String p2Name, String p3Name, String p4Name){
        playerOne = new Player(p1Name);
        playerTwo = new Player(p2Name);
        playerThree = new Player(p3Name);
        playerFour = new Player(p4Name);
    }

}
