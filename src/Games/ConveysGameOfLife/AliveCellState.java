package src.Games.ConveysGameOfLife;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class AliveCellState implements StateHandler {
    private Collection<Coordinates> aliveCells;
    private Queue<Coordinates> queuedChanges = new LinkedBlockingQueue<>() {
    };

    public AliveCellState(Collection<Coordinates> aliveCells) {
        this.aliveCells = aliveCells;
    }

    @Override
    public synchronized void changeQueuedChanges() {
        while (!queuedChanges.isEmpty()) {
            changeCell(queuedChanges.poll());
        }
    }

    private void changeCell(Coordinates cell) {
        if (aliveCells.contains(cell)) {
            aliveCells.remove(cell);
        } else {
            aliveCells.add(cell);
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
