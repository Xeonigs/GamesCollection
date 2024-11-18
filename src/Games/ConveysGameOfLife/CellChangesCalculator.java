package src.Games.ConveysGameOfLife;

import java.util.*;

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
    public Collection<Coordinates> getChanges() {
        Set<Coordinates> changes = new TreeSet<>();
        for (var cell : cellsToCheck) {
            int neighbours = getNeighbours(cell, 4);
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
        cellsToCheck.clear();
        return changes;
    }

    private int getNeighbours(Coordinates cell, int maxCount) {
        int neighbours = 0;
        for (var direction : DIRECTIONS) {
            if (neighbours == maxCount) {
                return neighbours;
            }
            if (aliveCells.contains(cell.add(direction))) {
                neighbours++;
            }
        }
        return neighbours;
    }
}


