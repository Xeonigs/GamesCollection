package src.ConnectFour.BusinessLogic;

public interface Player {
    int getMove();
    char getSymbol();
    public void setOpponent(Player opponent);
}
