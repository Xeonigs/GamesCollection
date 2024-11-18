package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.State;
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
    private final State state;
    private final List<GUIObject> discFields = new ArrayList<>();
    private final Rectangle2D rectangle;

    public GraphicBoard(Board board, BoardHandler boardHandler, TurnManager turnManager, State state, Point position, Dimension size) {
        this.board = board;
        this.boardHandler = boardHandler;
        this.turnManager = turnManager;
        this.state = state;
        this.rectangle = new Rectangle2D.Float(position.x, position.y, size.width, size.height);
        setDiscFields();
    }

    private void setDiscFields() {
        var sizeOfSingleSquare = Math.min(rectangle.getWidth() / board.value().length, rectangle.getHeight() / board.value()[0].length);
        int xOffset = (int) (rectangle.getWidth() - board.value().length * sizeOfSingleSquare) / 2;
        int yOffset = (int) (rectangle.getHeight() - board.value()[0].length * sizeOfSingleSquare) / 2;
        for (int column = 0; column < board.value().length; column++) {
            var positionOfSingleColumn = new Point((int) (rectangle.getX() + xOffset + column * sizeOfSingleSquare), (int) rectangle.getY());
            var sizeOfSingleColumnDimension = new Dimension((int) sizeOfSingleSquare, (int) rectangle.getHeight());
            for (int row = board.value()[0].length - 1; row >= 0; row--) {
                var positionOfSingleSquare = new Point((int) (rectangle.getX() + xOffset + column * sizeOfSingleSquare), (int) (rectangle.getMaxY() + yOffset - (row * sizeOfSingleSquare + sizeOfSingleSquare)));
                var sizeOfSingleSquareDimension = new Dimension((int) sizeOfSingleSquare, (int) sizeOfSingleSquare);
                discFields.add(new GraphicDiscField(column, row, boardHandler, board, turnManager, state, positionOfSingleSquare, sizeOfSingleSquareDimension, positionOfSingleColumn, sizeOfSingleColumnDimension));
            }
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
        for (var discField : discFields) {
            discField.mouseClicked(mousePos);
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
        discFields.forEach(discField -> discField.render(graphics, mousePos));
    }
}
