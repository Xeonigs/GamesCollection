package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.Heuristic;
import src.GameInterfaces.GameLogic.Player;
import src.GameInterfaces.GameLogic.State;
import src.Games.ConnectFour.GameLogic.*;
import src.Games.ConnectFour.ConnectFour;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

// Area of improvement:
// - choose the shortest path to win

// - only win check last placed disc
// - instead of pointer use the bits of a long as board
public class MiniMaxPlayer implements ComputerPlayer {
    private final char symbol;
    private final Color color;
    private final Color highlightColor;

    private final int depth;
    private final Board board;
    private Board boardCopy;
    private float[/*column*/] evaluation;
    private BoardHandler boardHandler;
    private State state;
    private Player opponent;
    private Map<String, Float> rememberedBoards;

    public MiniMaxPlayer(char symbol, Color color, Color highlightColor, Board board, int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("Depth must be at least 1");
        }
        this.symbol = symbol;
        this.color = color;
        this.highlightColor = highlightColor;
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
        this.boardHandler = new GameBoardHandler(boardCopy);
        this.state = new GameState(boardCopy);
        this.rememberedBoards = new ConcurrentHashMap<>();
    }

    private void clearSetup() {
        this.boardCopy = null;
        this.evaluation = null;
        this.boardHandler = null;
        this.rememberedBoards = null;
    }

    private int minimax(Board board) {
        long startTime = System.nanoTime();
        List<Callable<MiniMaxResult>> tasks = new ArrayList<>();
        for (int i = 0; i < evaluation.length; i++) {
            if (boardHandler.isMoveValid(i)) {
                boardHandler.makeMove(this, i);
                tasks.add(new MiniMaxSearchThread(this.depth, i, board, rememberedBoards, this, opponent));
                boardHandler.undoMove();
            } else {
                evaluation[i] = -1;
            }
        }
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            List<Future<MiniMaxResult>> futures = executor.invokeAll(tasks);
            for (Future<MiniMaxResult> future : futures) {
                MiniMaxResult result = future.get();
                evaluation[result.column()] = result.value();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
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
        System.out.println("Computer move (" + getColor() + "): " + (indexOfHighestValue + 1) + " (" + evaluation[indexOfHighestValue] + ")");
        return indexOfHighestValue;
    }

    @Override
    public Character getSymbol() {
        return symbol;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Color getHighlightColor() {
        return highlightColor;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
