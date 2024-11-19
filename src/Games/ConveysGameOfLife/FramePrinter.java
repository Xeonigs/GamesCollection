package src.Games.ConveysGameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collection;

public class FramePrinter extends JPanel {
    private final Collection<Coordinates> aliveCells;
    private final GameState gameState;
    private final Dimension windowSize;
    private final MouseListener mouseAdapter;
    private final KeyListener keyAdapter;

    FramePrinter(Collection<Coordinates> aliveCells, Dimension windowSize, GameState gameState, MouseListener mouseAdapter, KeyListener keyAdapter) {
        this.aliveCells = aliveCells;
        this.windowSize = windowSize;
        this.gameState = gameState;
        this.mouseAdapter = mouseAdapter;
        this.keyAdapter = keyAdapter;

        setupJPanel();
    }

    void setupJPanel() {
        JFrame jFrame = new JFrame();
        jFrame.add(this);
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setSize(windowSize);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        requestFocus();
        addMouseListener(mouseAdapter);
        addKeyListener(keyAdapter);
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        var pos = getMousePosition();
        Color veryLightGray = new Color(230, 230, 230);
        g.setColor(veryLightGray);
        if (pos != null) {
            g.fillRect(pos.x / gameState.getPixelSize() * gameState.getPixelSize(), 0, gameState.getPixelSize(), windowSize.height);
            g.fillRect(0, pos.y / gameState.getPixelSize() * gameState.getPixelSize(), windowSize.width, gameState.getPixelSize());
        }

        Color lightGray = new Color(200, 200, 200);
        g.setColor(lightGray);
        if (pos != null) {
            g.fillRect(pos.x / gameState.getPixelSize() * gameState.getPixelSize(), pos.y / gameState.getPixelSize() * gameState.getPixelSize(), gameState.getPixelSize(), gameState.getPixelSize());
        }



        g.setColor(Color.BLACK);
        for (var cell : aliveCells) {
            if (cell.x * gameState.getPixelSize() < windowSize.width && cell.y * gameState.getPixelSize() < windowSize.height) {
                g.fillRect(cell.x * gameState.getPixelSize(), cell.y * gameState.getPixelSize(), gameState.getPixelSize(), gameState.getPixelSize());
            }
        }
    }
}
