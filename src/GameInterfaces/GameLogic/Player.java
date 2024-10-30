package src.GameInterfaces.GameLogic;

public interface Player {
    int getMove();
    char getSymbol();
    void setOpponent(Player opponent);
}
