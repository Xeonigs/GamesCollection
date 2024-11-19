package src.Games.ConveysGameOfLife;

import javax.swing.text.html.Option;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CellChangesCalculator implements StateChangeCalculator {
    private final Collection<Coordinates> aliveCells;
    private final Collection<Coordinates> cellsToCheck;

    public static final Coordinates UP = new Coordinates(0, 1);
    public static final Coordinates DOWN = new Coordinates(0, -1);
    public static final Coordinates LEFT = new Coordinates(-1, 0);
    public static final Coordinates RIGHT = new Coordinates(1, 0);
    public static final Coordinates UP_LEFT = new Coordinates(-1, 1);
    public static final Coordinates UP_RIGHT = new Coordinates(1, 1);
    public static final Coordinates DOWN_LEFT = new Coordinates(-1, -1);
    public static final Coordinates DOWN_RIGHT = new Coordinates(1, -1);
    public static final Coordinates[] DIRECTIONS = {UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};

    public CellChangesCalculator(Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public Collection<Coordinates> getChanges() throws InterruptedException {
        Set<Coordinates> changes = new HashSet<>();

        class Task implements Callable<Collection<Coordinates>> {
            private final Collection<Coordinates> cells;
            private final Collection<Coordinates> aliveCells;

            public Task(Collection<Coordinates> cells, Collection<Coordinates> aliveCells) {
                this.cells = cells;
                this.aliveCells = aliveCells;
            }

            @Override
            public Collection<Coordinates> call() {
                Collection<Coordinates> changes = new ArrayList<>();
                for (var cell : cells) {
                    int neighbours = getNeighbours(cell, aliveCells, 4);
                    if (aliveCells.contains(cell)) {
                        if (neighbours < 2 || neighbours > 3) {
                            changes.add(cell);
                        }
                    } else {
                        if (neighbours == 3) {
                            changes.add(cell);
                        }
                    }
                }
                return changes;
            }
        }

        //var threads = Runtime.getRuntime().availableProcessors();
        var threads = 6;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Collection<Coordinates>>> tasks = new ArrayList<>();
        var sizeOfPartList = cellsToCheck.size() / threads + 1;
        var iterator = cellsToCheck.iterator();
        for (int i = 0; i < threads; i++) {
            Collection<Coordinates> partList = new LinkedList<>();
            for (int j = 0; j < sizeOfPartList; j++) {
                if (!iterator.hasNext()) {
                    break;
                }
                partList.add(iterator.next());
            }
            tasks.add(new Task(partList, aliveCells));
        }

        var futures = executor.invokeAll(tasks);
        executor.shutdown();
        for (var future : futures) {
            try {
                if (future.get() != null) {
                    var changesFromFuture = future.get();
                    for (var change : changesFromFuture) {
                        changes.add(change);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cellsToCheck.clear();
        return changes;
    }

    private static int getNeighbours(Coordinates cell, Collection<Coordinates> aliveCells, int maxCount) {
        int neighbours = 0;
        for (var direction : DIRECTIONS) {
            if (aliveCells.contains(cell.add(direction))) {
                neighbours++;
                if (neighbours == maxCount) {
                    break;
                }
            }
        }
        return neighbours;
    }
}


