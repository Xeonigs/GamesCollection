package src.ConnectFour.BusinessLogic;

public class GameLoop implements Loop {
    private State state;
    private Renderer renderer;
    private TurnHandler turnHandler;
    private BoardHandler boardHandler;

    public GameLoop(State state, Renderer renderer, TurnHandler turnHandler, BoardHandler board) {
        this.state = state;
        this.renderer = renderer;
        this.turnHandler = turnHandler;
        this.boardHandler = board;
    }

    @Override
    public void startLoop() {
        while (state.isRunning()) {
            renderer.render();
            final var player = turnHandler.getActivePlayer();
            final var column = player.getMove();
            boardHandler.makeMove(player, column);
            turnHandler.changePlayer();
        }
        final var winner = state.getWinner();
    }
}
