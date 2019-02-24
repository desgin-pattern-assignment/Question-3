package question3;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class Edge {
    private static final Color UI_DEFAULT_COLOR = Color.rgb(99, 99, 99);
    private Point2D point1, point2;

    public Edge(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public void draw(GraphicsContext gc) {
        gc.setLineWidth(4);
        gc.setStroke(UI_DEFAULT_COLOR);
        gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
    }
}
