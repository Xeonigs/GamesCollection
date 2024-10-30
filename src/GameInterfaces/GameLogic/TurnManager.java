package src.GameInterfaces.GameLogic;

public interface TurnManager {
    Player getActivePlayer();
    Player getOpponent();
    void changePlayer();
}
