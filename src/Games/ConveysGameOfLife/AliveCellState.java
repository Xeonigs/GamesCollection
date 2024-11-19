package src.Games.ConveysGameOfLife;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

public class AliveCellState implements StateHandler {
    private Collection<Coordinates> aliveCells;
    private Queue<Coordinates> queuedChanges = new LinkedBlockingQueue<>();
    private Collection<Coordinates> cellsToCheck;

    public AliveCellState(Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public synchronized void changeQueuedChanges() throws InterruptedException, ExecutionException {

        class Task implements Callable<Collection<Coordinates>> {
            private final Collection<Coordinates> queuedChangesPartList;

            Task(Collection<Coordinates> queuedChangesPartList) {
                this.queuedChangesPartList = queuedChangesPartList;
            }

            @Override
            public Collection<Coordinates> call() throws Exception {
                Collection<Coordinates> cellsToCheck = new LinkedList<>();
                for (var cell : queuedChangesPartList) {
                    changeCell(cell);
                    cellsToCheck.add(cell);
                    for (var direction : CellChangesCalculator.DIRECTIONS) {
                        cellsToCheck.add(cell.add(direction));
                    }
                }
                return cellsToCheck;
            }
        }

        var threads = Runtime.getRuntime().availableProcessors();
        //var threads = 6;
        Collection<Callable<Collection<Coordinates>>> tasks = new LinkedList<>();
        var sizeOfPartList = queuedChanges.size() / threads + 1;
        var iterator = queuedChanges.iterator();
        for (int i = 0; i < threads; i++) {
            Collection<Coordinates> partList = new LinkedList<>();
            for (int j = 0; j < sizeOfPartList; j++) {
                if (!iterator.hasNext()) {
                    break;
                }
                partList.add(iterator.next());
            }
            tasks.add(new Task(partList));
        }


        ExecutorService executor = Executors.newFixedThreadPool(threads);
        var futures = executor.invokeAll(tasks);
        executor.shutdown();
        for (var future : futures) {
            cellsToCheck.addAll(future.get());
        }
        queuedChanges.clear();
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
