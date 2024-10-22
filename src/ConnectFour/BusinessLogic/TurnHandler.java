package src.ConnectFour.BusinessLogic;

public interface TurnHandler {
    Player getActivePlayer();
    Player getOpponent();
    void changePlayer();
}
