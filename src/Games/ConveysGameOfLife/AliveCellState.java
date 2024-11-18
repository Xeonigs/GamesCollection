package src.Games.ConveysGameOfLife;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class AliveCellState implements StateHandler {
    private Collection<Coordinates> aliveCells;
    private Queue<Coordinates> queuedChanges = new LinkedBlockingQueue<>();
    private Collection<Coordinates> cellsToCheck;

    public AliveCellState(Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public synchronized void changeQueuedChanges() {
        while (!queuedChanges.isEmpty()) {
            var cell = queuedChanges.poll();
            if (changeCell(cell)) {
                cellsToCheck.add(cell);
                for (var direction : CellChangesCalculator.DIRECTIONS) {
                    cellsToCheck.add(cell.add(direction));
                }
            }
        }
    }

    private boolean changeCell(Coordinates cell) {
        if (aliveCells.contains(cell)) {
            return aliveCells.remove(cell);
        } else {
            return aliveCells.add(cell);
        }
    }

    @Override
    public void queueChange(Coordinates cell) {
        queuedChanges.add(cell);
    }

    @Override
    public void queueChanges(Collection<Coordinates> cells) {
        queuedChanges.addAll(cells);
    }
}
