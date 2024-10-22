package src.ConnectFour.BusinessLogic;

public interface State {
    boolean isRunning();
    Player getWinner();
}
