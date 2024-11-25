package src.UserInterfaces.GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

public class Button implements GUIObject {
    private static final Color BUTTON_COLOR = new Color(25, 25, 25, 150);
    private static final Color BUTTON_HOVER_COLOR = new Color(75, 75, 75, 200);

    private final RoundRectangle2D roundedRectangle;
    private final String text;
    private final Runnable function;
    public Button(Point position, Dimension size, float round, String text, Runnable function) {
        var roundValue = (int) (Math.min(size.width, size.height) * round);
        this.roundedRectangle = new RoundRectangle2D.Float(position.x, position.y, size.width, size.height, roundValue, roundValue);
        this.text = text;
        this.function = function;
    }

    @Override
    public void render(Graphics graphics, Point mousePos) {
        graphics.setColor(BUTTON_COLOR);
        graphics.fillRoundRect(roundedRectangle.getBounds().x, roundedRectangle.getBounds().y, roundedRectangle.getBounds().width, roundedRectangle.getBounds().height, (int) roundedRectangle.getArcWidth(), (int) roundedRectangle.getArcHeight());
        if (Objects.nonNull(mousePos) && intersects(mousePos)) {
            graphics.setColor(BUTTON_HOVER_COLOR);
            graphics.fillRoundRect(roundedRectangle.getBounds().x, roundedRectangle.getBounds().y, roundedRectangle.getBounds().width, roundedRectangle.getBounds().height, (int) roundedRectangle.getArcWidth(), (int) roundedRectangle.getArcHeight());
        }
        drawText(graphics, text);
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
        if (intersects(mouseEvent.getPoint())) {
            function.run();
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
    
    private boolean intersects(Point point) {
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
