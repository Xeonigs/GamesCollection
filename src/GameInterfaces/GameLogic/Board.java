package src.GameInterfaces.GameLogic;

public interface Board {
    void makeMove(Player player, int column);
    void undoMove();
    boolean isMoveValid(int column);
}
