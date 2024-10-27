package src.ConnectFour.BusinessLogic;

import src.ConnectFour.UI.InputHandler;
import src.ConnectFour.UI.OutputHandler;

public class GameLoop implements Loop {
    private State state;
    private TurnHandler turnHandler;
    private BoardHandler boardHandler;
    private OutputHandler outputHandler;

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
        final var winner = state.getWinner();
        outputHandler.printWinner();
    }
}
