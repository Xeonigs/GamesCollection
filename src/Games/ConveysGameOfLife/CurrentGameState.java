package src.Games.ConveysGameOfLife;

public class CurrentGameState implements GameState {
    boolean running;
    int speed;
    int pixelSize;

    static final int MIN_SPEED = 2;
    static final int MAX_SPEED = 1000;

    static final int MIN_PIXEL_SIZE = 1;
    static final int MAX_PIXEL_SIZE = 50;

    public CurrentGameState(boolean running, int speed, int pixelSize) {
        this.running = running;
        this.speed = speed;
        this.pixelSize = pixelSize;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void toggleRunning() {
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
