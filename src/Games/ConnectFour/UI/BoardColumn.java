package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.TurnManager;
import src.Games.ConnectFour.GameLogic.Board;
import src.Games.ConnectFour.GameLogic.Players.ComputerPlayer;
import src.Games.ConnectFour.GameLogic.Players.HumanPlayer;
import src.UserInterfaces.GUI.ApplicationPanel;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class BoardColumn implements GUIObject {
    private final int column;
    private final BoardHandler boardHandler;
    private final Board board;
    private final TurnManager turnManager;
    private final Rectangle2D rectangle;
    private final int width;

    BoardColumn(int column, BoardHandler boardHandler, Board board, TurnManager turnManager, Point position, Dimension size) {
        this.column = column;
        this.boardHandler = boardHandler;
        this.board = board;
        this.turnManager = turnManager;
        this.rectangle = new Rectangle2D.Float(position.x, position.y, size.width, size.height);
        this.width = Math.min(size.width, size.height / board.value()[column].length);
    }

    @Override
    public void keyTyped(char key) {

    }

    @Override
    public void keyPressed(char key) {

    }

    @Override
    public void keyReleased(char key) {

    }

    @Override
    public void mouseClicked(Point mousePos) {
        var isMouseInRectangle = Objects.nonNull(mousePos) && rectangle.contains(mousePos);
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
    public void mousePressed(Point mousePos) {

    }

    @Override
    public void mouseReleased(Point mousePos) {

    }

    @Override
    public void mouseEntered(Point mousePos) {

    }

    @Override
    public void mouseExited(Point mousePos) {

    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        var colToRender = board.value()[column];
        for (int i = 0; i < colToRender.length; i++) {
            var player = colToRender[i];
            var color = player == null ? Color.GRAY : player.getColor();
            if (Objects.nonNull(mousePos) && rectangle.contains(mousePos) && board.heights()[column] == i) {
                color = turnManager.getActivePlayer().getHighlightColor();
            }
            graphics.setColor(color);

            var x = (int) rectangle.getX();
            var y = (int) rectangle.getMaxY() - (i * width + width);
            var size = (int) (width * 0.9);
            graphics.fillOval(x, y, size, size);
        }
    }
}
