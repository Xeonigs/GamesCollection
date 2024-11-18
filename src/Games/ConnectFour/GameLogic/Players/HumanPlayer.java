package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.BoardHandler;
import src.GameInterfaces.GameLogic.Player;

import java.awt.*;

public class HumanPlayer implements Player {
    private final Color color;
    private final Color highlightColor;

    public HumanPlayer(Color color, Color highlightColor) {
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Color getHighlightColor() {
        return highlightColor;
    }
}
