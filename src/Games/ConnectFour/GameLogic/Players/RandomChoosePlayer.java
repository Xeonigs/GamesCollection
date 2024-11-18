package src.Games.ConnectFour.GameLogic.Players;

import java.awt.*;

public class RandomChoosePlayer implements ComputerPlayer {
    private final char symbol;
    private final Color color;
    private final Color highlightColor;
    public RandomChoosePlayer(char symbol, Color color, Color highlightColor) {
        this.symbol = symbol;
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public int getMove() {
        return (int) (Math.random() * 7);
    }

    @Override
    public Character getSymbol() {
        return symbol;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Color getHighlightColor() {
        return highlightColor;
    }
}
