package src.ConnectFour.BusinessLogic.Players;

public interface Player {
    int getMove();
    char getSymbol();
    void setOpponent(Player opponent);
}
