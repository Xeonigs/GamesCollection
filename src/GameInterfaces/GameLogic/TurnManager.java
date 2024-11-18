package src.GameInterfaces.GameLogic;

public interface TurnManager {
    Player getActivePlayer();
    Player getOpponent();
    boolean isActivePlayer(Player player);
    boolean isOpponent(Player player);
    void changePlayer();
}
