package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.Board;
import src.GameInterfaces.GameLogic.Player;
import src.Games.ConnectFour.ConnectFour;
import src.GameInterfaces.UI.InputHandler;

import java.util.Optional;

public class HumanPlayer implements Player {
    private final char symbol;
    private final InputHandler inputHandler;
    private final Board boardHandler;
    private Player opponent;

    public HumanPlayer(char symbol, InputHandler inputHandler, Board boardHandler) {
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
