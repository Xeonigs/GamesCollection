package src.ConnectFour.BusinessLogic;

public interface BoardHeuristic {
    /**
     * Evaluates the given board for the given player
     * @return Returns a double between 1 and 0 representing the winning chances of the given player
     */
    double evaluateBoard();
}