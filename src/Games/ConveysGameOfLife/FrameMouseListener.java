package src.Games.ConveysGameOfLife;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FrameMouseListener implements MouseListener {
    private StateHandler cellState;
    private GameState gameState;

    public FrameMouseListener(StateHandler cellState, GameState gameState) {
        this.cellState = cellState;
        this.gameState = gameState;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Coordinates cell = new Coordinates(e.getX() / gameState.getPixelSize(), e.getY() / gameState.getPixelSize());
        cellState.queueChange(cell);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
