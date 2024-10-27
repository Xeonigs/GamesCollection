package src.ConnectFour.BusinessLogic;

import src.ConnectFour.ConnectFour;

public class GameState implements State {
    private final Board board;

    public GameState(Board board) {
        this.board = board;
    }

    @Override
    public boolean isRunning() {
        return getWinner() == null && !isFull();
    }

    @Override
    public Player getWinner() {
        final var board = this.board.value();

        final var columns = board.length;
        final var rows = board[0].length;
        final var winningLength = ConnectFour.WINNING_LENGTH;

        for (int iCol = 0; iCol < columns; iCol++) {
            for (int iRow = 0; iRow < rows - (winningLength - 1); iRow++) {
                if (board[iCol][iRow] != null &&
                        board[iCol][iRow] == board[iCol][iRow + 1] &&
                        board[iCol][iRow] == board[iCol][iRow + 2] &&
                        board[iCol][iRow] == board[iCol][iRow + 3]) {
                    return board[iCol][iRow];
                }
            }
        }

        for (int iCol = 0; iCol < columns - (winningLength - 1); iCol++) {
            for (int iRow = 0; iRow < rows; iRow++) {
                if (board[iCol][iRow] != null &&
                        board[iCol][iRow] == board[iCol + 1][iRow] &&
                        board[iCol][iRow] == board[iCol + 2][iRow] &&
                        board[iCol][iRow] == board[iCol + 3][iRow]) {
                    return board[iCol][iRow];
                }
            }
        }

        for (int iCol = 0; iCol < columns - (winningLength - 1); iCol++) {
            for (int iRow = 0; iRow < rows - (winningLength - 1); iRow++) {
                if (board[iCol][iRow] != null &&
                        board[iCol][iRow] == board[iCol + 1][iRow + 1] &&
                        board[iCol][iRow] == board[iCol + 2][iRow + 2] &&
                        board[iCol][iRow] == board[iCol + 3][iRow + 3]) {
                    return board[iCol][iRow];
                }
            }
        }

        for (int iCol = 0; iCol < columns - (winningLength - 1); iCol++) {
            for (int iRow = winningLength - 1; iRow < rows; iRow++) {
                if (board[iCol][iRow] != null &&
                        board[iCol][iRow] == board[iCol + 1][iRow - 1] &&
                        board[iCol][iRow] == board[iCol + 2][iRow - 2] &&
                        board[iCol][iRow] == board[iCol + 3][iRow - 3]) {
                    return board[iCol][iRow];
                }
            }
        }

        return null;
    }

    private boolean isFull() {
        for (int i = 0; i < board.heights().length; i++) {
            if (board.heights()[i] < board.value()[i].length) {
                return false;
            }
        }
        return true;
    }
}
