package src.GameInterfaces.GameLogic;

public interface TurnHandler {
    Player getActivePlayer();
    Player getOpponent();
    void changePlayer();
}
