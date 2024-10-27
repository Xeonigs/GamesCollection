package src.ConnectFour.BusinessLogic.Players;

import src.ConnectFour.BusinessLogic.*;
import src.ConnectFour.ConnectFour;

import java.util.HashMap;
import java.util.Map;

// Area of improvement:
// - choose the shortest path to win

// - only win check last placed disc
// - instead of pointer use the bits of a long as board
public class MiniMaxPlayerV10 implements Player {
    private final char symbol;
    private final int depth;
    private final Board board;
    private Board boardCopy;
    private float[/*column*/] evaluation;
    private float[/*depth*/] depthsEvaluation;
    private BoardHandler boardHandler;
    private State state;
    private BoardHeuristic boardHeuristic;
    private Player opponent;
    private Map<String, Float> rememberedBoards;

    public MiniMaxPlayerV10(char symbol, Board board, int depth) {
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
        this.evaluation = new float[ConnectFour.COLUMN];
        this.depthsEvaluation = new float[depth];
        this.boardHandler = new GameBoardHandler(boardCopy);
        this.state = new GameState(boardCopy);
        this.boardHeuristic = new GameBoardHeuristic(boardCopy, state, this, opponent);
        //this.rememberedBoards = Map.of();
        this.rememberedBoards = new HashMap<>();
    }

    private void clearSetup() {
        this.boardCopy = null;
        this.evaluation = null;
        this.depthsEvaluation = null;
        this.boardHandler = null;
        this.boardHeuristic = null;
        this.rememberedBoards = null;
    }

    private int minimax(Board board) {
        int depth = 0;
        long startTime = System.nanoTime();
        depthsEvaluation[depth] = -1;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                final var boardString = board.toString();
                float value;
                if (rememberedBoards.containsKey(boardString)) {
                    value = rememberedBoards.get(boardString);
                    boardHandler.undoMove();
                } else {
                    value = max(board, depth + 1, -1);
                    rememberedBoards.put(boardString, value);
                    rememberedBoards.put(board.horizontallyMirroredToString(), value);
                    boardHandler.undoMove();
                }
                evaluation[i] = value;
            } else {
                evaluation[i] = -1;
            }
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Time: " + duration / 1000000 + "ms");

        int indexOfHighestValue = 0;
        for (int i = 0; i < evaluation.length; i++) {
            if (evaluation[i] > evaluation[indexOfHighestValue]) {
                indexOfHighestValue = i;
            }
        }
        System.out.println("Computer move (" + getSymbol() + "): " + (indexOfHighestValue + 1) + " (" + evaluation[indexOfHighestValue] + ")");
        return indexOfHighestValue;
    }

    private float min(Board board, int depth, float bestValue) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluateBoard();
        }

        depthsEvaluation[depth] = 2;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(opponent, i);
                final var boardString = board.toString();
                float value;
                if (rememberedBoards.containsKey(boardString)) {
                    value = rememberedBoards.get(boardString);
                    boardHandler.undoMove();
                } else {
                    value = max(board, depth + 1, depthsEvaluation[depth]);
                    rememberedBoards.put(boardString, value);
                    rememberedBoards.put(board.horizontallyMirroredToString(), value);
                    boardHandler.undoMove();
                }

                if (value < bestValue || value == 0) {
                    return value;
                }
                if (value < depthsEvaluation[depth]) {
                    depthsEvaluation[depth] = value;
                }
            }
        }

        return depthsEvaluation[depth];
    }

    private float max(Board board, int depth, float worstValue) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluateBoard();
        }

        depthsEvaluation[depth] = -1;
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                final var boardString = board.toString();
                float value;
                if (rememberedBoards.containsKey(boardString)) {
                    value = rememberedBoards.get(boardString);
                    boardHandler.undoMove();
                } else {
                    value = min(board, depth + 1, depthsEvaluation[depth]);
                    rememberedBoards.put(boardString, value);
                    rememberedBoards.put(board.horizontallyMirroredToString(), value);
                    boardHandler.undoMove();
                }

                if (value > worstValue || value == 1) {
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
