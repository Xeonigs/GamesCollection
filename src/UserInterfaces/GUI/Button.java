package src.UserInterfaces.GUI;

import java.awt.*;

public class Button implements GUIObject {
    int x, y, width, height;
    String text;
    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.drawString(text, x + width / 2, y + height / 2);
    }
}
