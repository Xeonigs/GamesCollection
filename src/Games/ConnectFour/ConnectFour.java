package src.Games.ConnectFour;

import src.GameInterfaces.GameLogic.*;
import src.GameInterfaces.GameLogic.BoardHandler;
import src.Games.ConnectFour.GameLogic.*;
import src.Games.ConnectFour.GameLogic.Players.*;
import src.Games.ConnectFour.UI.*;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.util.Objects;

public class ConnectFour {
    public static final int COLUMN = 7;
    public static final int ROW = 6;
    public static final int WINNING_LENGTH = 4;
    public static final Color PLAYER_RED = new Color(255, 65, 65);
    public static final Color PLAYER_RED_HIGHLIGHT = new Color(220, 169, 169);
    public static final Color PLAYER_BLUE = new Color(0, 125, 255);
    public static final Color PLAYER_BLUE_HIGHLGHT = new Color(155, 184, 217);

    public GUIObject loadGame(Class<? extends Player> player1Type, Class<? extends Player> player2Type) {
        Objects.requireNonNull(player1Type);
        Objects.requireNonNull(player2Type);

        var board = new Board(new Player[COLUMN][ROW], new int[COLUMN]);
        var boardHandler = new GameBoardHandler(board);
        var state = new GameState(board);

        var player1 = createPlayer(player1Type, 'X', PLAYER_RED, PLAYER_RED_HIGHLIGHT, board);
        var player2 = createPlayer(player2Type, 'O', PLAYER_BLUE, PLAYER_BLUE_HIGHLGHT, board);
        if (player1 instanceof MiniMaxPlayer miniMaxPlayer) {
            miniMaxPlayer.setOpponent(player2);
        }
        if (player2 instanceof MiniMaxPlayer miniMaxPlayer) {
            miniMaxPlayer.setOpponent(player1);
        }
        var turnHandler = new GameTurnHandler(player1, player2);

        return new ConnectFourUserInterface(state, turnHandler, boardHandler, board);
    }

    private Player createPlayer(Class<? extends Player> playerClass, char symbol, Color color, Color highlightColor, Board board) {
        if (playerClass == RandomChoosePlayer.class) {
            return new RandomChoosePlayer(symbol, color, highlightColor);
        } else if (playerClass == MiniMaxPlayer.class) {
            return new MiniMaxPlayer(symbol, color, highlightColor, board, 9);
        } else if (playerClass == HumanPlayer.class) {
            return new HumanPlayer(symbol, color, highlightColor);
        } else {
            throw new IllegalArgumentException("Unknown player class: " + playerClass);
        }
    }
}
