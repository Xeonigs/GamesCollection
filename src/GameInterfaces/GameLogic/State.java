package src.GameInterfaces.GameLogic;

import src.Games.ConnectFour.GameLogic.WinningLine;

import java.util.Optional;

public interface State {
    boolean isRunning();
    Player getWinner();
    Optional<WinningLine> getWinningLine();
}
