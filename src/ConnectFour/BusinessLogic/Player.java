package src.ConnectFour.BusinessLogic;

public interface Player {
    int getMove();
    char getSymbol();
    void setOpponent(Player opponent);
}
