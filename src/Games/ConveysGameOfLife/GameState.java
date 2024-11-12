package src.Games.ConveysGameOfLife;

public interface GameState {
    boolean isRunning();
    void toggleRunning();
    int getSpeed();
    void changeSpeed(int speed);
    int getPixelSize();
    void changePixelSize(int pixelSize);
}
