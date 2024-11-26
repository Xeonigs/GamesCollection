package src.Games.ConveysGameOfLife;

import java.util.*;
import java.util.stream.Collectors;

public class CellChangesCalculator implements StateChangeCalculator {
    private final Collection<Coordinates> aliveCells;
    private final Collection<Coordinates> cellsToCheck;

    public CellChangesCalculator(Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public Collection<Coordinates> getChanges() {
        var changes = cellsToCheck.parallelStream().
                filter(this::isCellToChange).
                collect(Collectors.toList());

        cellsToCheck.clear();
        return changes;
    }

    private boolean isCellToChange(Coordinates cell) {
        int neighbours = countNeighbours(cell, 4);
        if (aliveCells.contains(cell)) {
            return neighbours < 2 || neighbours > 3;
        } else {
            return neighbours == 3;
        }
    }

    private int countNeighbours(Coordinates cell, int maxCount) {
        int neighbours = 0;
        for (var neighbour : cell.getNeighbours()) {
            if (aliveCells.contains(neighbour)) {
                neighbours++;
                if (neighbours == maxCount) {
                    return neighbours;
                }
            }
        }
        return neighbours;
    }
}


