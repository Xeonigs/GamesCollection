package src.UserInterfaces.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Menu implements GraphicalUserInterface {
    private List<GUIObject> guiObjects = new ArrayList<>();

    public Menu() {
        guiObjects.add(new Button(300, 100, 200, 50, 1, "Start"));
        guiObjects.add(new Button(300, 200, 200, 50, 1, "Options"));
        guiObjects.add(new Button(300, 300, 200, 50, 1, "Exit"));
    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        for (var ob : guiObjects) {
            ob.render(graphics, mousePos);
        }
    }

    @Override
    public void keyTyped(char key) {

    }

    @Override
    public void keyPressed(char key) {

    }

    @Override
    public void keyReleased(char key) {

    }

    @Override
    public void mouseClicked(Point mousePos) {
        for (var ob : guiObjects) {
            if (ob.intersects(mousePos)) {
                ob.onClick();
            }
        }
    }

    @Override
    public void mousePressed(Point mousePos) {
    }

    @Override
    public void mouseReleased(Point mousePos) {

    }

    @Override
    public void mouseEntered(Point mousePos) {

    }

    @Override
    public void mouseExited(Point mousePos) {

    }
}
