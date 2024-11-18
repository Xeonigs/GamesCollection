package src.UserInterfaces.GUI.Menus;

import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.util.Collection;

public class Menu implements GUIObject {
    private Collection<GUIObject> guiObjects;

    public Menu(Collection<GUIObject> guiObjects) {
        this.guiObjects = guiObjects;
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
            ob.mouseClicked(mousePos);
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
