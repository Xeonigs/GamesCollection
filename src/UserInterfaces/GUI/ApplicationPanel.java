package src.UserInterfaces.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/// <summary>
/// This class is the main panel for the application.
///
/// </summary>
public class ApplicationPanel extends JPanel implements Runnable, KeyListener, MouseListener {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    private final GraphicalUserInterface currentUI = new Menu();

    public ApplicationPanel() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(BACKGROUND_COLOR);
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setTitle("Games Collection");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.addMouseListener(this);
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        var mousePos = getMousePosition();
        var awd = MouseInfo.getPointerInfo();
        getMousePosition();
        currentUI.render(graphics, mousePos);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        var key = e.getKeyChar();
        currentUI.keyTyped(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        var key = e.getKeyChar();
        currentUI.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var key = e.getKeyChar();
        currentUI.keyReleased(key);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var mousePos = getMousePosition();
        currentUI.mouseClicked(mousePos);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        var mousePos = getMousePosition();
        currentUI.mousePressed(mousePos);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        var mousePos = getMousePosition();
        currentUI.mouseReleased(mousePos);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        var mousePos = getMousePosition();
        currentUI.mouseEntered(mousePos);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        var mousePos = getMousePosition();
        currentUI.mouseExited(mousePos);
    }
}
