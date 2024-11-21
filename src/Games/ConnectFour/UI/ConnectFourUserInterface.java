package src.Games.ConnectFour.UI;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.State;
import src.GameInterfaces.GameLogic.TurnManager;

import src.Games.ConnectFour.GameLogic.Board;
import src.Games.ConnectFour.GameLogic.Players.HumanPlayer;
import src.UserInterfaces.GUI.Button;
import src.UserInterfaces.GUI.GUIObject;
import src.UserInterfaces.GUI.Menus.Menu;

import java.awt.*;
import java.util.LinkedList;

public class ConnectFourUserInterface implements GUIObject {
    private final State state;
    private final TurnManager turnManager;
    private final BoardHandler boardHandler;
    private final Board board;

    private final GraphicBoard graphicBoard;

    private final Menu menu;
    private boolean showMenu = false;

    public ConnectFourUserInterface(State state, TurnManager turnManager, BoardHandler boardHandler, Board board, Runnable returnToLobby) {
        this.state = state;
        this.turnManager = turnManager;
        this.boardHandler = boardHandler;
        this.board = board;

        this.graphicBoard = new GraphicBoard(board, boardHandler, turnManager, state, new Point(0, 100), new Dimension(800, 400));

        var gameMenuObjects = new LinkedList<GUIObject>();
        this.menu = new Menu(gameMenuObjects, null);

        gameMenuObjects.add(new Button(new Point(300, 100), new Dimension(200, 50), 1, "Continue", () -> showMenu = false));
        gameMenuObjects.add(new Button(new Point(300, 200), new Dimension(200, 50), 1, "Return to lobby", returnToLobby::run));
    }

    @Override
    public void keyTyped(char key) {
        if (key == 'm') {
            showMenu = !showMenu;
        }
    }

    @Override
    public void keyPressed(char key) {

    }

    @Override
    public void keyReleased(char key) {

    }

    @Override
    public void mouseClicked(Point mousePos) {
        if (showMenu) {
            menu.mouseClicked(mousePos);
        } else {
            if (state.isRunning() && turnManager.getActivePlayer() instanceof HumanPlayer) {
                graphicBoard.mouseClicked(mousePos);
            }
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
        if (showMenu) {
            graphicBoard.render(graphics, null);
            menu.render(graphics, mousePos);
        } else {
            graphicBoard.render(graphics, mousePos);
        }
    }
}
