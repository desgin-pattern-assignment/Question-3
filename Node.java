package question3;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public final class Node {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 65;
    private static final Color BACKGROUND_COLOR = Color.rgb(49, 116, 222);
    private static final Color BORDER_COLOR = Color.rgb(99, 99, 99);
    private static final Color FONT_COLOR = Color.web("#FCFCFC");
    private static final Font FONT = Font.font("Arial", FontWeight.BOLD, 10);
    
    public String text;
    private Point2D center;
    private ArrayList<Node> children = new ArrayList<>();
    
    public void addChild(Node child) {
        children.add(child);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }
    
    public Node(Point2D center, String text) {
        this.center = center;
        this.text = text;
    }
    
    public Node(String text) {
        this.text = text;
    }
    
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(3);
        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(center.getX() - WIDTH / 2, center.getY() - HEIGHT / 2, WIDTH, HEIGHT);
        gc.setStroke(BORDER_COLOR);
        gc.strokeRect(center.getX() - WIDTH / 2, center.getY() - HEIGHT / 2, WIDTH, HEIGHT);
        gc.setFont(FONT);
        gc.setFill(FONT_COLOR);
        gc.fillText(text, center.getX() - (WIDTH * 0.45), center.getY() - (HEIGHT * 0.3));
    }
    
    public Point2D getPoint() {
        return center;
    }
    
    public void setPoint(Point2D point) {
        this.center = point;
    }
}
