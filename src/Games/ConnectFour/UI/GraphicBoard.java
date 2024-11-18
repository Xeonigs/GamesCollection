package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.TurnManager;
import src.Games.ConnectFour.GameLogic.Board;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GraphicBoard implements GUIObject {
    private final Board board;
    private final BoardHandler boardHandler;
    private final TurnManager turnManager;
    private final List<GUIObject> columns = new ArrayList<>();
    private final Rectangle2D rectangle;

    public GraphicBoard(Board board, BoardHandler boardHandler, TurnManager turnManager, Point position, Dimension size) {
        this.board = board;
        this.boardHandler = boardHandler;
        this.turnManager = turnManager;
        this.rectangle = new Rectangle2D.Float(position.x, position.y, size.width, size.height);
        var sizeOfSingleSquare = Math.min(size.width / board.value().length, size.height / board.value()[0].length);
        int xOffset = ((int)rectangle.getWidth() - board.value().length * sizeOfSingleSquare) / 2;
        int yOffset = ((int)rectangle.getHeight() - board.value()[0].length * sizeOfSingleSquare) / 2;
        for (int i = 0; i < board.value().length; i++) {
            columns.add(new BoardColumn(i, this.boardHandler, this.board, this.turnManager, new Point((int) rectangle.getX() + i * sizeOfSingleSquare + xOffset, (int) rectangle.getY() + yOffset), new Dimension(sizeOfSingleSquare, sizeOfSingleSquare * board.value()[0].length)));
        }
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
        for (var column : columns) {
            column.mouseClicked(mousePos);
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
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        for (var column : columns) {
            column.render(graphics, mousePos);
        }
    }
}
