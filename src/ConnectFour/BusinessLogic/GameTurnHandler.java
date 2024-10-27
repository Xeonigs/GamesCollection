package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;

public class GameTurnHandler implements TurnHandler {
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public GameTurnHandler(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        isPlayer1Turn = true;
    }

    @Override
    public Player getActivePlayer() {
        if (isPlayer1Turn) {
            return player1;
        } else {
            return player2;
        }
    }

    @Override
    public Player getOpponent() {
        if (isPlayer1Turn) {
            return player2;
        } else {
            return player1;
        }
    }

    @Override
    public void changePlayer() {
        isPlayer1Turn = !isPlayer1Turn;
    }
}
