package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;

public interface State {
    boolean isRunning();
    Player getWinner();
}
