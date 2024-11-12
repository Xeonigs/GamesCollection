package src.Games.ConveysGameOfLife;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.*;

public class ConveysGameOfLife {
    public void start() {
        GameState gameState = new CurrentGameState(false, 10, 10);

        SortedSet<Coordinates> sortedSet = new TreeSet<>();
        Set<Coordinates> aliveCells = Collections.synchronizedSet(sortedSet);

        /*
        aliveCells.add(new Coordinates(1, 0));
        aliveCells.add(new Coordinates(2, 1));
        aliveCells.add(new Coordinates(0, 2));
        aliveCells.add(new Coordinates(1, 2));
        aliveCells.add(new Coordinates(2, 2));
         */

        /*
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 300; j++) {
                aliveCells.add(new Coordinates(j * 4, i *4));
                aliveCells.add(new Coordinates(j * 4 + 1, i *4));
                aliveCells.add(new Coordinates(j * 4 + 2, i *4));
            }
        }
         */

        aliveCells.add(new Coordinates(7, 0));
        aliveCells.add(new Coordinates(5, 0));
        aliveCells.add(new Coordinates(5, 1));
        aliveCells.add(new Coordinates(3, 2));
        aliveCells.add(new Coordinates(3, 3));
        aliveCells.add(new Coordinates(3, 4));
        aliveCells.add(new Coordinates(1, 3));
        aliveCells.add(new Coordinates(1, 4));
        aliveCells.add(new Coordinates(1, 5));
        aliveCells.add(new Coordinates(0, 4));


        StateHandler cellState = new AliveCellState(aliveCells);
        StateChangeCalculator cellChanges = new CellChangesCalculator(aliveCells);
        MouseListener mouseListener = new FrameMouseListener(cellState, gameState);
        KeyListener keyboardListener = new FrameKeyboardListener(cellState, gameState);
        FramePrinter printer = new FramePrinter(aliveCells, new Coordinates(250, 250), gameState, mouseListener, keyboardListener);

        while (true) {
            if (gameState.isRunning()) {
                var changes = cellChanges.getChanges();
                cellState.queueChanges(changes);
                try {
                    Thread.sleep(gameState.getSpeed());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            cellState.changeQueuedChanges();
            printer.repaint();
        }
    }
}
