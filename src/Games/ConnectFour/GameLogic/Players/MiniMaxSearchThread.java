package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.Heuristic;
import src.GameInterfaces.GameLogic.Player;
import src.GameInterfaces.GameLogic.State;
import src.Games.ConnectFour.GameLogic.Board;
import src.Games.ConnectFour.GameLogic.GameBoardHandler;
import src.Games.ConnectFour.GameLogic.GameBoardHeuristic;
import src.Games.ConnectFour.GameLogic.GameState;

import java.util.Map;
import java.util.concurrent.Callable;

// has return value
public class MiniMaxSearchThread implements Callable<MiniMaxResult> {
    private final int depth;
    private final int startColumn;
    private final Board board;
    private final BoardHandler boardHandler;
    private final State state;
    private final Heuristic boardHeuristic;
    private final Player activePlayer;
    private final Player opponent;
    private final Map<String, Float> rememberedBoards;
    private final float[/*depth*/] depthsEvaluation;

    public MiniMaxSearchThread(int depth, int startColumn, Board board, Map<String, Float> rememberedBoards, Player activePlayer, Player opponent) {
        this.depth = depth;
        this.startColumn = startColumn;
        this.board = board.clone();
        this.rememberedBoards = rememberedBoards;
        this.activePlayer = activePlayer;
        this.opponent = opponent;

        this.boardHandler = new GameBoardHandler(this.board);
        this.state = new GameState(this.board);
        this.boardHeuristic = new GameBoardHeuristic(this.board, this.state, activePlayer, opponent);
        this.depthsEvaluation = new float[depth];
    }

    @Override
    public MiniMaxResult call() {
        return new MiniMaxResult(startColumn, min(board, 0, -1));
    }


    private float min(Board board, int depth, float bestValue) {
        if (depth == this.depth || !state.isRunning()) {
            return boardHeuristic.evaluate();
        }

        depthsEvaluation[depth] = 2;
        for (int i = 0; i < board.heights().length; i++) {
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
            return boardHeuristic.evaluate();
        }

        depthsEvaluation[depth] = -1;
        for (int i = 0; i < board.heights().length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(activePlayer, i);
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
}
