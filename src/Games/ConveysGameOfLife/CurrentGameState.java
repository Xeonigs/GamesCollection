package src.Games.ConveysGameOfLife;

import java.util.Collection;

public class CurrentGameState implements GameState {
    private boolean running;
    private int speed;
    private int pixelSize;
    private Collection<Coordinates> aliveCells;
    private Collection<Coordinates> cellsToCheck;

    static final int MIN_SPEED = 2;
    static final int MAX_SPEED = 1000;

    static final int MIN_PIXEL_SIZE = 1;
    static final int MAX_PIXEL_SIZE = 50;

    public CurrentGameState(boolean running, int speed, int pixelSize, Collection<Coordinates> aliveCells, Collection<Coordinates> cellsToCheck) {
        this.running = running;
        this.speed = speed;
        this.pixelSize = pixelSize;
        this.aliveCells = aliveCells;
        this.cellsToCheck = cellsToCheck;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void toggleRunning() {
        if (!running) {
            aliveCells.parallelStream().forEach(cell -> {
                cellsToCheck.add(cell);
                cellsToCheck.addAll(cell.getNeighbours());
            });
        } else {
            synchronized (cellsToCheck) {
                cellsToCheck.clear();
            }
        }
        running = !running;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void changeSpeed(int speed) {
        if (this.speed + speed < MIN_SPEED) {
            this.speed = MIN_SPEED;
        } else if (this.speed + speed > MAX_SPEED) {
            this.speed = MAX_SPEED;
        } else {
            this.speed += speed;
        }
    }

    @Override
    public int getPixelSize() {
        return pixelSize;
    }

    @Override
    public void changePixelSize(int pixelSize) {
        if (this.pixelSize + pixelSize < MIN_PIXEL_SIZE) {
            this.pixelSize = MIN_PIXEL_SIZE;
        } else if (this.pixelSize + pixelSize > MAX_PIXEL_SIZE) {
            this.pixelSize = MAX_PIXEL_SIZE;
        } else {
            this.pixelSize += pixelSize;
        }
    }
}
