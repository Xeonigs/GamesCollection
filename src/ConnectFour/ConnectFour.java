package src.ConnectFour;

import src.ConnectFour.BusinessLogic.*;
import src.ConnectFour.UI.*;

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

        //Player player1 = new HumanPlayer('X', inputHandler, boardHandler);
        Player player2 = new HumanPlayer('O', inputHandler, boardHandler);
        Player player1 = new ComputerPlayer('X', board, 8);
        //Player player2 = new ComputerPlayer('O', board, 7);
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        TurnHandler turnHandler = new GameTurnHandler(player1, player2);

        OutputHandler outputHandler = new ConsoleOutputHandler(board, state, userInterface);

        Loop gameLoop = new GameLoop(state, turnHandler, boardHandler, outputHandler);
        gameLoop.startLoop();
    }
}
