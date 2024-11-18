package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.State;
import src.GameInterfaces.GameLogic.TurnManager;

import src.Games.ConnectFour.GameLogic.Board;
import src.Games.ConnectFour.GameLogic.Players.HumanPlayer;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.util.Objects;

public class ConnectFourUserInterface implements GUIObject {
    private final State state;
    private final TurnManager turnManager;
    private final BoardHandler boardHandler;
    private final Board board;

    private final GraphicBoard graphicBoard;

    public ConnectFourUserInterface(State state, TurnManager turnManager, BoardHandler boardHandler, Board board) {
        this.state = state;
        this.turnManager = turnManager;
        this.boardHandler = boardHandler;
        this.board = board;
        this.graphicBoard = new GraphicBoard(board, boardHandler, turnManager, state, new Point(0, 100), new Dimension(800, 400));
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
        if (state.isRunning() && turnManager.getActivePlayer() instanceof HumanPlayer) {
            graphicBoard.mouseClicked(mousePos);
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
        graphicBoard.render(graphics, mousePos);
        if (Objects.nonNull(state.getWinner())) {

        }
    }
}
