package question3;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("serial")
public final class GraphicsTree extends Canvas {
    private Node root;

    public GraphicsTree() {
        widthProperty().addListener(evt -> GraphicsTree.this.drawEdges());
        heightProperty().addListener(evt -> GraphicsTree.this.drawEdges());
        createTree();
    }

    public void createTree() {
        root = createNode(Main.CEO);
        GraphicsTree.this.drawEdges();
    }
    
    public void drawEdges() {
        clearCanvas();
        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        if (root != null) {
            int treeHeight = getHeight(root);
            drawEdges(gc, root, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
            drawNodes(gc, root, 0, this.getWidth(), 0, this.getHeight() / treeHeight);
        }
    }
    
    protected void drawEdges(GraphicsContext gc, Node treeNode,
            double xMin, double xMax, double yMin, double yMax) {
        Point2D point1 = new Point2D(((xMin + xMax) / 2), yMin + yMax / 2);
        Point2D point2;
        ArrayList<Node> children = treeNode.getChildren();
        Node node;
        for (int i = 0; i < children.size(); i++) {
            node = children.get(i);
            point2 = new Point2D(xMin + ((2 * i + 1) * (xMax - xMin)) / (2 * children.size()), yMin + yMax + yMax / 2);
            new Edge(point1, point2).draw(gc);
            drawEdges(gc, node, xMin + (i * (xMax - xMin)) / children.size(),
                    xMin + ((i + 1) * (xMax - xMin)) / children.size(), yMin + yMax, yMax);
        }
    }
    
    public void drawNodes(GraphicsContext gc, Node treeNode,
            double xMin, double xMax, double yMin, double yMax) {
        Point2D point = new Point2D((xMin + xMax) / 2, yMin + yMax / 2);
        treeNode.setPoint(point);
        treeNode.draw(gc);
        ArrayList<Node> children = treeNode.getChildren();
        Node node;
        for (int i = 0; i < children.size(); i++) {
            node = children.get(i);
            drawNodes(gc, node, xMin + (i * (xMax - xMin)) / children.size(),
                    xMin + ((i + 1) * (xMax - xMin)) / children.size(), yMin + yMax, yMax);
        }
    }
    
    public static Node createNode(Employee employee) {
        Node tNode = new Node(employee.toString());
        Node childNode;
        for (Employee child : employee.getSubordinates()) {
            childNode = createNode(child);
            tNode.addChild(childNode);
        }
        return tNode;
    }
    
    private static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        ArrayList<Integer> heights = new ArrayList<>();
        Integer height;
        for (Node tNode : root.getChildren()) {
            height = getHeight(tNode);
            heights.add(height);
        }
        heights.sort(null);
        if (heights.isEmpty()) {
            return 1;
        }
        return heights.get(heights.size() - 1) + 1;        
    }
    
    public void clearCanvas() {
        getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}
