package src.UserInterfaces.GUI;

import java.awt.*;

public interface GUIObject extends Renderable {
    void onClick();
    boolean intersects(Point point);
}
