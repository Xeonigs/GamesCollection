package src.UserInterfaces.GUI.Menus;

import src.UserInterfaces.GUI.ApplicationPanel;
import src.UserInterfaces.GUI.GUIObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        for (var ob : guiObjects) {
            ob.mouseClicked(mouseEvent);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
