package src.GameInterfaces.GameLogic;

public interface BoardHandler {
    void makeMove(Player player, int column);
    void undoMove();
    boolean isMoveValid(int column);
    int movesCount();
}
