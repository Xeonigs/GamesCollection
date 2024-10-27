package src.ConnectFour.BusinessLogic;

import src.ConnectFour.ConnectFour;

public class ComputerPlayer implements Player {
    private char symbol;
    private int depth;
    private Board board;
    private Board boardCopy;
    private double[/*depth*/][/*column*/] evaluationTable;
    private BoardHandler boardHandler;
    private State state;
    private BoardHeuristic boardHeuristic;
    private Player opponent;

    public ComputerPlayer(char symbol, Board board, int depth) {
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
        final var move = minimax(boardCopy, 0);
        clearSetup();
        return move;
    }

    private void newSetup() {
        this.boardCopy = board.clone();
        this.evaluationTable = new double[depth][ConnectFour.COLUMN];
        this.boardHandler = new GameBoardHandler(boardCopy);
        this.state = new GameState(boardCopy, boardHandler);
        this.boardHeuristic = new GameBoardHeuristic(boardCopy, state, this, opponent);
    }

    private void clearSetup() {
        this.boardCopy = null;
        this.evaluationTable = null;
        this.boardHandler = null;
        this.boardHeuristic = null;
    }

    private int minimax(Board board, int depth) {
        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                evaluationTable[depth][i] = min(board, depth + 1);
                boardHandler.undoMove();
            } else {
                evaluationTable[depth][i] = -1;
            }
        }

        // Area of improvement: choose the shortest path to win
        int indexOfHighestValue = 0;
        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (evaluationTable[depth][i] > evaluationTable[depth][indexOfHighestValue]) {
                indexOfHighestValue = i;
            }
        }
        System.out.println("Computer move (" + getSymbol() + "): " + (indexOfHighestValue + 1) + " (" + evaluationTable[depth][indexOfHighestValue] + ")");
        return indexOfHighestValue;
    }

    private double min(Board board, int depth) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluateBoard();
        }

        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(opponent, i);
                evaluationTable[depth][i] = max(board, depth + 1);
                boardHandler.undoMove();
                if (evaluationTable[depth][i] == 0) {
                    return evaluationTable[depth][i];
                }
            } else {
                evaluationTable[depth][i] = 2;
            }
        }

        double lowestValue = evaluationTable[depth][0];
        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (evaluationTable[depth][i] < lowestValue) {
                lowestValue = evaluationTable[depth][i];
            }
        }
        return lowestValue;
    }

    private double max(Board board, int depth) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluateBoard();
        }

        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (boardHandler.isMoveValid(i)) {
                evaluationTable[depth][i] = 0;
                boardHandler.makeMove(this, i);
                evaluationTable[depth][i] = min(board, depth + 1);
                boardHandler.undoMove();
                if (evaluationTable[depth][i] == 1) {
                    return evaluationTable[depth][i];
                }
            } else {
                evaluationTable[depth][i] = -1;
            }
        }

        double highestValue = evaluationTable[depth][0];
        for (int i = 0; i < evaluationTable[depth].length; i++) {
            if (evaluationTable[depth][i] > highestValue) {
                highestValue = evaluationTable[depth][i];
            }
        }
        return highestValue;
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
