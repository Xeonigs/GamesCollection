package src.UserInterfaces.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Menu implements GraphicUserInterface {
    private List<GUIObject> button = new ArrayList<>();

    @Override
    public void render(Graphics g) {
        Button b = new Button(100, 100, 100, 100, "Button");
        b.render(g);
    }

    public void addButton(GUIObject button) {
        this.button.add(button);
    }
}
