package src.UserInterfaces.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ApplicationPanel extends JPanel implements GUISwitcher {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    private GUIObject currentGUI;

    public ApplicationPanel() {
        setPreferredSize(SCREEN_SIZE);
        setBackground(BACKGROUND_COLOR);
        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setTitle("Games Collection");
        frame.setResizable(false);
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
        if (currentGUI == null) {
            return;
        }
        currentGUI.render(graphics, getMousePosition());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        var key = e.getKeyChar();
        currentGUI.keyTyped(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        var key = e.getKeyChar();
        currentGUI.keyPressed(key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var key = e.getKeyChar();
        currentGUI.keyReleased(key);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var mousePos = getMousePosition();
        currentGUI.mouseClicked(mousePos);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        var mousePos = getMousePosition();
        currentGUI.mousePressed(mousePos);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        var mousePos = getMousePosition();
        currentGUI.mouseReleased(mousePos);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        var mousePos = getMousePosition();
        currentGUI.mouseEntered(mousePos);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        var mousePos = getMousePosition();
        currentGUI.mouseExited(mousePos);
    }

    @Override
    public void switchGUI(GUIObject gui) {
        this.currentGUI = gui;
    }
}
