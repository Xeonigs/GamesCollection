package src.ConnectFour.BusinessLogic;

import src.ConnectFour.BusinessLogic.Players.Player;
import src.ConnectFour.ConnectFour;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameBoardHandler implements BoardHandler {
    private Board board;
    private Deque<Integer> moves;

    public GameBoardHandler(Board board) {
        this.board = board;
        this.moves = new ArrayDeque<>(ConnectFour.COLUMN * ConnectFour.ROW);
    }

    @Override
    public void makeMove(Player player, int column) {
        board.value()[column][board.heights()[column]] = player;
        board.heights()[column]++;
        moves.push(column);
    }

    @Override
    public void undoMove() {
        final var undoMove = moves.pop();
        board.heights()[undoMove]--;
        board.value()[undoMove][board.heights()[undoMove]] = null;
    }

    @Override
    public boolean isMoveValid(int column) {
        return board.heights()[column] < board.value()[column].length;
    }
}
