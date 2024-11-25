package src.UserInterfaces.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputListener {
    void keyTyped(KeyEvent keyEvent);
    void keyPressed(KeyEvent keyEvent);
    void keyReleased(KeyEvent keyEvent);
    void mouseClicked(MouseEvent mouseEvent);
    void mousePressed(MouseEvent mouseEvent);
    void mouseReleased(MouseEvent mouseEvent);
    void mouseEntered(MouseEvent mouseEvent);
    void mouseExited(MouseEvent mouseEvent);
}
