package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;

public interface BoardHandler {
    void makeMove(Player player, int column);
    void undoMove();
    boolean isMoveValid(int column);
}
