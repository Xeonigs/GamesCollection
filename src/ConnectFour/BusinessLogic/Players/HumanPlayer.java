package src.ConnectFour.BusinessLogic.Players;

import src.ConnectFour.BusinessLogic.BoardHandler;
import src.ConnectFour.BusinessLogic.Players.Player;
import src.ConnectFour.ConnectFour;
import src.ConnectFour.UI.InputHandler;

import java.util.Optional;

public class HumanPlayer implements Player {
    private final char symbol;
    private final InputHandler inputHandler;
    private final BoardHandler boardHandler;
    private Player opponent;

    public HumanPlayer(char symbol, InputHandler inputHandler, BoardHandler boardHandler) {
        this.symbol = symbol;
        this.inputHandler = inputHandler;
        this.boardHandler = boardHandler;
    }

    @Override
    public int getMove() {
        if (opponent == null) {
            throw new IllegalStateException("Opponent must be set before calling getMove()");
        }
        Optional<Integer> move;
        do {
             move = inputHandler.getMove(this);
        } while(move.isEmpty() ||
                move.get() > ConnectFour.COLUMN - 1 ||
                move.get() < 0 ||
                !boardHandler.isMoveValid(move.get()));
        return move.get();
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
