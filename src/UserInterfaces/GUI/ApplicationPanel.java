package src.UserInterfaces.GUI;

import javax.swing.*;
import java.awt.*;

public class ApplicationPanel extends JPanel {
    public static final Dimension SCREEN_SIZE = new Dimension(800, 600);
    private Image screen;
    private GraphicUserInterface currentUI = new Menu();

    public ApplicationPanel() {
        setPreferredSize(SCREEN_SIZE);

        JFrame frame = new JFrame();
        frame.add(this);
        frame.pack();
        frame.setTitle("");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start() {
        while (true) {
            render();
        }
    }

    private void render() {
        screen = createVolatileImage(getWidth(), getHeight());
        Graphics g = screen.getGraphics();

        if (currentUI != null) {
            currentUI.render(g);
        }

        getGraphics().drawImage(screen, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
    }
}
