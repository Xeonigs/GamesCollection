package src.Games.ConnectFour.GameLogic;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.Loop;
import src.GameInterfaces.GameLogic.State;
import src.GameInterfaces.GameLogic.TurnHandler;
import src.GameInterfaces.UI.OutputHandler;

public class GameLoop implements Loop {
    private final State state;
    private final TurnHandler turnHandler;
    private final BoardHandler boardHandler;
    private final OutputHandler outputHandler;

    public GameLoop(State state, TurnHandler turnHandler, BoardHandler boardHandler, OutputHandler outputHandler) {
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
