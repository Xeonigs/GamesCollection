package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.State;
import src.GameInterfaces.GameLogic.TurnManager;
import src.Games.ConnectFour.GameLogic.Board;
import src.Games.ConnectFour.GameLogic.Players.ComputerPlayer;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class GraphicDiscField implements GUIObject {
    private final int column;
    private final int row;
    private final BoardHandler boardHandler;
    private final Board board;
    private final TurnManager turnManager;
    private final State state;
    private final Rectangle2D fieldRec;
    private final Rectangle2D columnRec;
    private final int width;

    GraphicDiscField(int column, int row, BoardHandler boardHandler, Board board, TurnManager turnManager, State state, Point position, Dimension size, Point colPosition, Dimension colSize) {
        this.column = column;
        this.row = row;
        this.boardHandler = boardHandler;
        this.board = board;
        this.turnManager = turnManager;
        this.state = state;
        this.fieldRec = new Rectangle2D.Float(position.x, position.y, size.width, size.height);
        this.columnRec = new Rectangle2D.Float(colPosition.x, colPosition.y, colSize.width, colSize.height);
        this.width = Math.min(size.width, size.height);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        var isMouseInRectangle = Objects.nonNull(mouseEvent) && fieldRec.contains(mouseEvent.getPoint());
        var isMoveValid = boardHandler.isMoveValid(column);

        if (isMouseInRectangle && isMoveValid) {
            var activePlayer = turnManager.getActivePlayer();
            boardHandler.makeMove(activePlayer, column);
            turnManager.changePlayer();
        }

        if (turnManager.getActivePlayer() instanceof ComputerPlayer computerPlayer) {
            var move = computerPlayer.getMove();
            boardHandler.makeMove(computerPlayer, move);
            turnManager.changePlayer();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        var player = board.value()[column][row];
        var color = player == null ? Color.GRAY : player.getColor();
        if (Objects.nonNull(mousePos) && columnRec.contains(mousePos) && board.heights()[column] == row && state.isRunning()) {
            color = turnManager.getActivePlayer().getHighlightColor();
        }
        graphics.setColor(color);

        var x = (int) fieldRec.getX();
        var y = (int) fieldRec.getY();
        var size = (int) (width * 0.9);
        graphics.fillOval(x, y, size, size);
    }
}
