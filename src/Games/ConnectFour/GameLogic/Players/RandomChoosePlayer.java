package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.Player;

import java.awt.*;

public class RandomChoosePlayer implements ComputerPlayer {
    private Color color;
    private Color highlightColor;
    public RandomChoosePlayer(Color color, Color highlightColor) {
        this.color = color;
        this.highlightColor = highlightColor;
    }

    @Override
    public int getMove() {
        return (int) (Math.random() * 7);
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
