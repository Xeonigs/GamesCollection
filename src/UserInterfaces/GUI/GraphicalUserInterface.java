package src.UserInterfaces.GUI;

import java.awt.*;

public interface GraphicalUserInterface {
    void render(Graphics graphics, Point mousePos);
    void keyTyped(char key);
    void keyPressed(char key);
    void keyReleased(char key);
    void mouseClicked(Point mousePos);
    void mousePressed(Point mousePos);
    void mouseReleased(Point mousePos);
    void mouseEntered(Point mousePos);
    void mouseExited(Point mousePos);
}
