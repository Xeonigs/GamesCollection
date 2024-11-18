package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.Player;

import java.awt.*;

public class HumanPlayer implements Player {
    private final char symbol;
    private final Color color;
    private final Color highlightColor;

    public HumanPlayer(char symbol, Color color, Color highlightColor) {
        this.symbol = symbol;
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public Character getSymbol() {
        return symbol;
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
