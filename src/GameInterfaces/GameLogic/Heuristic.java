package src.GameInterfaces.GameLogic;

public interface Heuristic {
    /**
     * Evaluates the current state of the game.
     *
     * @return Returns a double between 1 and 0 representing the current state of the game.
     */
    float evaluate();
}
