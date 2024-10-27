package src.ConnectFour.UI;

import src.ConnectFour.BusinessLogic.Board;
import src.ConnectFour.BusinessLogic.State;

public class ConsoleOutputHandler implements OutputHandler {
    private final Board board;
    private final State state;
    private final UserInterface userInterface;

    public ConsoleOutputHandler(final Board board, final State state, final UserInterface userInterface) {
        this.board = board;
        this.state = state;
        this.userInterface = userInterface;
    }

    @Override
    public void printBoard() {
        clearScreen();
        writeBoard();
    }

    private void writeBoard() {
        final var value = board.value();
        final var noPlayerSymbol = '_';

        for (int row = value[0].length - 1; row >= 0; row--) {
        for (int col = 0; col < value.length; col++) {
                final var player = value[col][row];
                if (player == null) {
                    System.out.print(noPlayerSymbol);
                } else {
                    System.out.print(player.getSymbol());
                }
            }
            System.out.println();
        }
        for (int col = 0; col < value.length; col++) {
            System.out.print(col + 1);
        }
        System.out.println();
    }

    @Override
    public void printWinner() {
        clearScreen();
        writeBoard();
        final var winner = state.getWinner();
        if (winner == null) {
            userInterface.display("It's a draw!");
        } else {
            userInterface.display(winner.getSymbol() + " wins!");
        }
    }

    @Override
    public void clearScreen() {
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
