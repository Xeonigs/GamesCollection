package src.Games.ConveysGameOfLife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameKeyboardListener implements KeyListener {
    private StateHandler cellState;
    private GameState gameState;

    public FrameKeyboardListener(StateHandler cellState, GameState gameState) {
        this.cellState = cellState;
        this.gameState = gameState;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameState.toggleRunning();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            gameState.changeSpeed(10);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            gameState.changeSpeed(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameState.changePixelSize(1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gameState.changePixelSize(-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
