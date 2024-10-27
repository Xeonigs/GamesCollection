package src.ConnectFour.BusinessLogic;

import src.ConnectFour.ConnectFour;

import java.util.function.Predicate;

import static java.lang.Math.*;

public class GameBoardHeuristic implements BoardHeuristic {
    private Board board;
    private State state;
    private Player player;
    private Player opponent;
    private int callCounter = 0;

    public GameBoardHeuristic(Board board, State state, Player player, Player opponent) {
        this.board = board;
        this.state = state;
        this.player = player;
        this.opponent = opponent;
    }

    @Override
    public double evaluateBoard() {
        callCounter++;
        if (!state.isRunning()) {
            final var winner = state.getWinner();
            if (winner == player) {
                return 1;
            } else if (winner == null) {
                return 0.5;
            } else {
                return 0;
            }
        }

        Predicate<Player> ownPlayerDemanded = player::equals;
        Predicate<Player> otherPlayerDemanded = opponent::equals;
        final var valueOwnPlayer = getEvaluationForDemandedPlayer(ownPlayerDemanded);
        final var valueOtherPlayer = getEvaluationForDemandedPlayer(otherPlayerDemanded);

        if (valueOwnPlayer == 0 || valueOtherPlayer == 0) {
            return 0.5;
        }

        System.out.println("Call counter: " + callCounter);
        return calculateValues(valueOwnPlayer, valueOtherPlayer);
    }

    private double calculateValues(int valueOwnPlayer, int valueOtherPlayer) {
        if (valueOwnPlayer > valueOtherPlayer) {
            // ownPlayer = 8
            // otherPlayer = 1
            // 1 / 8 = 0.125
            // 0.125 / 2 = 0.0625
            // 0.5 - 0.0625 = 0.4375
            // 0.5 + 0.4375 = 0.9375
            return 1 - (double) valueOtherPlayer / valueOwnPlayer / 2;

            // ownPlayer = 9
            // otherPlayer = 8
            // 8 / 9 = 0.8888888888888888
            // 0.8888888888888888 / 2 = 0.4444444444444444
            // 0.5 - 0.4444444444444444 = 0.05555555555555558
            // 0.5 + 0.05555555555555558 = 0.5555555555555556
        } else {
            // ownPlayer = 1
            // otherPlayer = 8
            // 1 / 8 = 0.125
            // 0.125 / 2 = 0.0625
            return (double) valueOwnPlayer / valueOtherPlayer / 2;

            // ownPlayer = 8
            // otherPlayer = 9
            // 8 / 9 = 0.8888888888888888
            // 0.8888888888888888 / 2 = 0.4444444444444444
        }
    }

    private int getEvaluationForDemandedPlayer(Predicate<Player> isPlayerDemanded) {
        int playerValue = 0;
        playerValue += getValueForDirection(1, 0, isPlayerDemanded);
        playerValue += getValueForDirection(0, 1, isPlayerDemanded);
        playerValue += getValueForDirection(1, 1, isPlayerDemanded);
        playerValue += getValueForDirection(1, -1, isPlayerDemanded);
        return playerValue;
    }

    private int getValueForDirection(int colDirection, int rowDirection, Predicate<Player> isPlayerDemanded) {
        int playerValue = 0;
        final var fromCol = ConnectFour.WINNING_LENGTH * max(0, -colDirection);
        final var fromRow = ConnectFour.WINNING_LENGTH * max(0, -rowDirection);
        final var toCol = ConnectFour.COLUMN - ConnectFour.WINNING_LENGTH * max(0, colDirection);
        final var toRow = ConnectFour.ROW - ConnectFour.WINNING_LENGTH * max(0, rowDirection);
        for (int col = fromCol; col < toCol; col++) {
            for (int row = fromRow; row < toRow; row++) {
                final var playerNullDemanded = isPlayerOnPositionNullOrDemanded(col, row, isPlayerDemanded);
                if (playerNullDemanded) {
                    var countOwnPlayer = 0;
                    for (int i = 0; i < ConnectFour.WINNING_LENGTH; i++) {
                        if (isPlayerOnPositionDemanded(col + i * colDirection, row + i * rowDirection, isPlayerDemanded)) {
                            countOwnPlayer++;
                        }
                    }
                    playerValue += valuationCalculation(countOwnPlayer);
                }
            }
        }
        return playerValue;
    }

    private boolean isPlayerOnPositionDemanded(int col, int row, Predicate<Player> isPlayerDemanded) {
        final var board = this.board.value();
        final var playerOnBoard = board[col][row];
        return isPlayerDemanded.test(playerOnBoard);
    }

    private boolean isPlayerOnBoardNull(int col, int row) {
        final var board = this.board.value();
        return board[col][row] == null;
    }

    private boolean isPlayerOnPositionNullOrDemanded(int col, int row, Predicate<Player> isPlayerDemanded) {
        final var isPlayerOnBoardNull = isPlayerOnBoardNull(col, row);
        final var isPlayerOnPositionDemanded = isPlayerOnPositionDemanded(col, row, isPlayerDemanded);
        return isPlayerOnBoardNull || isPlayerOnPositionDemanded;
    }

    private int valuationCalculation(int x) {
        return (int) pow(4, x - 1);
    }
}
