import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Line extends JComponent {

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Color color;
    private int dotSize;
    private float[] dashPattern = {5f};
    private int dashLength = 5;
    private int dashSpace = 5;
    private int strokeWidth = 1;

    public Line(int startX, int startY, int endX, int endY, Color color, int dotSize) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.dotSize = dotSize;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f));
        int y = startY;
        while (y < endY) {
            g2d.drawLine(startX, y, startX, y + dashLength);
            y += dashLength + dashSpace;
        }
    }
}
