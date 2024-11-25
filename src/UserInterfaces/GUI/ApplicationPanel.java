package src.UserInterfaces.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        currentGUI.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentGUI.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentGUI.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentGUI.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentGUI.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentGUI.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        currentGUI.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        currentGUI.mouseExited(e);
    }

    @Override
    public void switchGUI(GUIObject gui) {
        this.currentGUI = gui;
    }
}
