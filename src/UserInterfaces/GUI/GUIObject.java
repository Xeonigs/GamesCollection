package src.UserInterfaces.GUI;

import java.awt.*;

public interface GUIObject {
    void render(Graphics graphics, Point mousePos);
    void onClick();
    boolean intersects(Point point);
}
