package src.ConnectFour.BusinessLogic;

import java.util.Stack;

public class GameBoardHandler implements BoardHandler {
    private Board board;
    private Stack<Integer> moves;

    public GameBoardHandler(Board board) {
        this.board = board;
        this.moves = new Stack<>();
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
