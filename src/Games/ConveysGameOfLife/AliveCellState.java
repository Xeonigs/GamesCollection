package src.Games.ConveysGameOfLife;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AliveCellState implements StateHandler {
    private final Collection<Coordinates> queuedChanges = new ConcurrentLinkedDeque<>();
    private final Collection<Coordinates> aliveCells;
    private final Collection<Coordinates> cellsToCheck;

    public AliveCellState(Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public synchronized void changeQueuedChanges() {
        queuedChanges.parallelStream().forEach(this::changeCell);
        queuedChanges.clear();
    }

    private void changeCell(Coordinates cell) {
        if (!aliveCells.add(cell)) {
            aliveCells.remove(cell);
        }
        cellsToCheck.add(cell);
        cellsToCheck.addAll(cell.getNeighbours());
    }

    @Override
    public void queueChange(Coordinates cell) {
        queuedChanges.add(cell);
    }

    @Override
    public void queueChanges(Collection<Coordinates> cells) {
        cells.parallelStream().forEach(this::queueChange);
    }
}
