package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;

public interface TurnHandler {
    Player getActivePlayer();
    Player getOpponent();
    void changePlayer();
}
