package src.UserInterfaces.GUI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

public class Button implements GUIObject {
    private final RoundRectangle2D roundedRectangle;
    String text;
    public Button(int x, int y, int width, int height, int round, String text) {
        var roundValue = (int) (Math.min(width, height) * round);
        this.roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, roundValue, roundValue);
        this.text = text;
    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        if (Objects.nonNull(mousePos) && intersects(mousePos)) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRoundRect(roundedRectangle.getBounds().x, roundedRectangle.getBounds().y, roundedRectangle.getBounds().width, roundedRectangle.getBounds().height, (int) roundedRectangle.getArcWidth(), (int) roundedRectangle.getArcHeight());
        }
        drawText(graphics, text);
    }

    @Override
    public void onClick() {
        System.out.println(text + " clicked");
    }

    @Override
    public boolean intersects(Point point) {
        return roundedRectangle.intersects(point.x, point.y, 1, 1);
    }

    private void drawText(Graphics graphics, String text) {
        var fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();

        graphics.setColor(Color.WHITE);
        var x = (int) roundedRectangle.getX();
        var y = (int) roundedRectangle.getY();
        var width = (int) roundedRectangle.getWidth();
        var height = (int) roundedRectangle.getHeight();
        graphics.drawString(text, x + width / 2 - textWidth / 2, y + height / 2 + textHeight / 4);
    }
}
