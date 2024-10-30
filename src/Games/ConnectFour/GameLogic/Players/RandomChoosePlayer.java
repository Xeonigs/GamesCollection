package src.Games.ConnectFour.GameLogic.Players;

import src.GameInterfaces.GameLogic.Player;

public class RandomChoosePlayer implements Player {
    private char symbol;
    private Player opponent;
    public RandomChoosePlayer(char symbol) {
        this.symbol = symbol;
    }
    @Override
    public int getMove() {
        return (int) (Math.random() * 7);
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
