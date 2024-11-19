package src.Games.ConveysGameOfLife;

import javax.swing.text.html.Option;
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
        Set<Coordinates> changes = new ConcurrentSkipListSet<>();

        class Task implements Callable<Optional<Coordinates>> {
            private final Coordinates cell;
            private final Collection<Coordinates> aliveCells;

            public Task(Coordinates cell, Collection<Coordinates> aliveCells) {
                this.cell = cell;
                this.aliveCells = aliveCells;
            }

            @Override
            public Optional<Coordinates> call() {
                int neighbours = getNeighbours(cell, aliveCells, 4);
                if (aliveCells.contains(cell)) {
                    if (neighbours < 2 || neighbours > 3) {
                        return Optional.of(cell);
                    }
                } else {
                    if (neighbours == 3) {
                        return Optional.of(cell);
                    }
                }
                return Optional.empty();
            }
        }

        var threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Callable<Optional<Coordinates>>> tasks = new ArrayList<>();

        for (var cell : cellsToCheck) {
            tasks.add(new Task(cell, aliveCells));
        }
        var futures = executor.invokeAll(tasks);
        for (var future : futures) {
            try {
                future.get().ifPresent(changes::add);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

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


