package src.Games.ConnectFour;

import src.GameInterfaces.GameLogic.*;
import src.GameInterfaces.UI.InputHandler;
import src.GameInterfaces.UI.OutputHandler;
import src.Games.ConnectFour.GameLogic.*;
import src.Games.ConnectFour.GameLogic.Players.*;
import src.Games.ConnectFour.UI.*;
import src.UserInterfaces.ConsoleUserInterface;
import src.UserInterfaces.UserInterface;

public class ConnectFour {
    public static final int COLUMN = 7;
    public static final int ROW = 6;
    public static final int WINNING_LENGTH = 4;
    public void start() {
        UserInterface userInterface = new ConsoleUserInterface();
        InputHandler inputHandler = new ConsoleInputHandler(userInterface);

        Board board = new Board(new Player[COLUMN][ROW], new int[COLUMN]);
        BoardHandler boardHandler = new GameBoardHandler(board);
        State state = new GameState(board);

        Player player1 = new HumanPlayer('X', inputHandler, boardHandler);
        Player player2 = new MiniMaxPlayerV8('O', board, 11);
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        TurnHandler turnHandler = new GameTurnHandler(player1, player2);

        OutputHandler outputHandler = new ConsoleOutputHandler(board, state, userInterface);

        Loop gameLoop = new GameLoop(state, turnHandler, boardHandler, outputHandler);
        gameLoop.startLoop();
    }
}
