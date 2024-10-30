package src.GameInterfaces.GameLogic;

public interface State {
    boolean isRunning();
    Player getWinner();
}
