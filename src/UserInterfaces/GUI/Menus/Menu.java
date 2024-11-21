package src.UserInterfaces.GUI.Menus;

import src.UserInterfaces.GUI.ApplicationPanel;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.util.Collection;

public class Menu implements GUIObject {
    private final Color backgroundColor = new Color(0, 0, 0, 150);
    private final Collection<GUIObject> guiObjects;
    private final ApplicationPanel applicationPanel;

    public Menu(Collection<GUIObject> guiObjects, ApplicationPanel applicationPanel) {
        this.guiObjects = guiObjects;
        this.applicationPanel = applicationPanel;
    }

    public Runnable switchToMe() {
        return () -> applicationPanel.switchGUI(this);
    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, ApplicationPanel.SCREEN_SIZE.width, ApplicationPanel.SCREEN_SIZE.height);
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
