package src.Games.ConveysGameOfLife;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConveysGameOfLife {
    public void start() {
        Set<Coordinates> aliveCells = new ConcurrentSkipListSet<>();
        Set<Coordinates> cellsToCheck = new ConcurrentSkipListSet<>();

        GameState gameState = new CurrentGameState(false, 10, 10, aliveCells, cellsToCheck);

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

        /*
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
         */

        int size = 2000;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() > 0.5) {
                    aliveCells.add(new Coordinates(j, i));
                }
            }
        }


        StateHandler cellState = new AliveCellState(aliveCells, cellsToCheck);
        StateChangeCalculator cellChanges = new CellChangesCalculator(aliveCells, cellsToCheck);
        MouseListener mouseListener = new FrameMouseListener(cellState, gameState);
        KeyListener keyboardListener = new FrameKeyboardListener(cellState, gameState);
        FramePrinter printer = new FramePrinter(aliveCells, new Dimension(1200, 800), gameState, mouseListener, keyboardListener);

        while (true) {
            if (gameState.isRunning()) {
                var timeBefore = System.currentTimeMillis();
                try {
                    var changes = cellChanges.getChanges();
                    cellState.queueChanges(changes);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                var timeElapsed = System.currentTimeMillis() - timeBefore;
                try {
                    var sleepTime = Math.max(gameState.getSpeed() - (long)timeElapsed, 0);
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            cellState.changeQueuedChanges();
            printer.repaint();
        }
    }
}
