package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.Board;
import src.GameInterfaces.GameLogic.Heuristic;
import src.GameInterfaces.GameLogic.Player;
import src.GameInterfaces.GameLogic.State;
import src.Games.ConnectFour.GameLogic.*;
import src.Games.ConnectFour.ConnectFour;

public class MiniMaxPlayerV3 implements Player {
    private final char symbol;
    private final int depth;
    private final src.Games.ConnectFour.GameLogic.Board board;
    private src.Games.ConnectFour.GameLogic.Board boardCopy;
    private double[/*column*/] evaluation;
    private double[/*depth*/] depthsEvaluation;
    private Board boardHandler;
    private State state;
    private Heuristic boardHeuristic;
    private Player opponent;

    public MiniMaxPlayerV3(char symbol, src.Games.ConnectFour.GameLogic.Board board, int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("Depth must be at least 1");
        }
        this.symbol = symbol;
        this.board = board;
        this.depth = depth;
    }

    @Override
    public int getMove() {
        if (opponent == null) {
            throw new IllegalStateException("Opponent must be set before calling getMove()");
        }
        newSetup();
        final var move = minimax(boardCopy);
        clearSetup();
        return move;
    }

    private void newSetup() {
        this.boardCopy = board.clone();
        this.evaluation = new double[ConnectFour.COLUMN];
        this.depthsEvaluation = new double[depth];
        this.boardHandler = new GameBoardHandler(boardCopy);
        this.state = new GameState(boardCopy);
        this.boardHeuristic = new GameBoardHeuristic(boardCopy, state, this, opponent);
    }

    private void clearSetup() {
        this.boardCopy = null;
        this.evaluation = null;
        this.depthsEvaluation = null;
        this.boardHandler = null;
        this.boardHeuristic = null;
    }

    private int minimax(src.Games.ConnectFour.GameLogic.Board board) {
        int depth = 0;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                evaluation[i] = min(board, depth + 1, -1);
                boardHandler.undoMove();
            } else {
                evaluation[i] = -1;
            }
        }
        //System.out.println("Time: " + duration / 1000000 + "ms");

        // Area of improvement: choose the shortest path to win
        int indexOfHighestValue = 0;
        for (int i = 0; i < evaluation.length; i++) {
            if (evaluation[i] > evaluation[indexOfHighestValue]) {
                indexOfHighestValue = i;
            }
        }
        System.out.println("Computer move (" + getSymbol() + "): " + (indexOfHighestValue + 1) + " (" + evaluation[indexOfHighestValue] + ")");
        return indexOfHighestValue;
    }
// the board, the current depth and the best value for the last depth
    private double min(src.Games.ConnectFour.GameLogic.Board board, int depth, double bestValue) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluate();
        }

        depthsEvaluation[depth] = 2;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(opponent, i);
                final var value = max(board, depth + 1, depthsEvaluation[depth]);
                boardHandler.undoMove();

                if (value == 0) {
                    return value;
                }
                if (value < bestValue) {
                    return value;
                }
                if (value < depthsEvaluation[depth]) {
                    depthsEvaluation[depth] = value;
                }
            }
        }

        return depthsEvaluation[depth];
    }

    private double max(src.Games.ConnectFour.GameLogic.Board board, int depth, double worstValue) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluate();
        }

        depthsEvaluation[depth] = -1;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                final var value = min(board, depth + 1, depthsEvaluation[depth]);
                boardHandler.undoMove();

                if (value == 1) {
                    return value;
                }
                if (value > worstValue) {
                    return value;
                }
                if (value > depthsEvaluation[depth]) {
                    depthsEvaluation[depth] = value;
                }
            }
        }

        return depthsEvaluation[depth];
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
