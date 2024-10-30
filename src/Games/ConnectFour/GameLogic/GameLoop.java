package src.Games.ConnectFour.GameLogic;

import src.GameInterfaces.GameLogic.Board;
import src.GameInterfaces.GameLogic.Loop;
import src.GameInterfaces.GameLogic.State;
import src.GameInterfaces.GameLogic.TurnManager;
import src.GameInterfaces.UI.OutputHandler;

public class GameLoop implements Loop {
    private final State state;
    private final TurnManager turnHandler;
    private final Board boardHandler;
    private final OutputHandler outputHandler;

    public GameLoop(State state, TurnManager turnHandler, Board boardHandler, OutputHandler outputHandler) {
        this.state = state;
        this.turnHandler = turnHandler;
        this.boardHandler = boardHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public void startLoop() {
        while (state.isRunning()) {
            outputHandler.printBoard();
            final var player = turnHandler.getActivePlayer();
            final var column = player.getMove();
            boardHandler.makeMove(player, column);
            turnHandler.changePlayer();
        }
        outputHandler.printWinner();
    }
}
