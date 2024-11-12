package src.Games.ConveysGameOfLife;

import java.util.*;

public class CellChangesCalculator implements StateChangeCalculator {
    private Collection<Coordinates> aliveCells;

    public static final Coordinates UP = new Coordinates(0, 1);
    public static final Coordinates DOWN = new Coordinates(0, -1);
    public static final Coordinates LEFT = new Coordinates(-1, 0);
    public static final Coordinates RIGHT = new Coordinates(1, 0);
    public static final Coordinates UP_LEFT = new Coordinates(-1, 1);
    public static final Coordinates UP_RIGHT = new Coordinates(1, 1);
    public static final Coordinates DOWN_LEFT = new Coordinates(-1, -1);
    public static final Coordinates DOWN_RIGHT = new Coordinates(1, -1);
    public static final Coordinates[] DIRECTIONS = {UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT};

    public CellChangesCalculator(Collection<Coordinates> aliveCells) {
        this.aliveCells = aliveCells;
    }

    @Override
    public Collection<Coordinates> getChanges() {
        Set<Coordinates> changes = new TreeSet<>();
        Collection<Coordinates> cellsToCheck = getCellsToCheck();
        for (var cell : cellsToCheck) {
            int neighbours = getNeighbours(cell);
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

    private Collection<Coordinates> getCellsToCheck() {
        Set<Coordinates> cellsToCheck = new TreeSet<>();
        for (var cell : aliveCells) {
            cellsToCheck.add(cell);
            for (var direction : DIRECTIONS) {
                cellsToCheck.add(cell.add(direction));
            }
        }
        return cellsToCheck;
    }

    private int getNeighbours(Coordinates cell) {
        int neighbours = 0;
        for (var direction : DIRECTIONS) {
            if (aliveCells.contains(cell.add(direction))) {
                neighbours++;
            }
        }
        return neighbours;
    }
}
