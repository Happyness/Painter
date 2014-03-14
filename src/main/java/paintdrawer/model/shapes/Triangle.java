package paintdrawer.model.shapes;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An class that represents a Triangle
 *
 */
public class Triangle extends Shape {

    @Override
    protected void drawShape(Graphics2D shape, boolean fill) {
        Polygon triangle = new Polygon(getXPoints(), getYPoints(), 3);
        if (fill) {
            shape.fillPolygon(triangle);
        } else {
            shape.drawPolygon(triangle);
        }
    }

    @Override
    public String toString() {
        return "Triangle";
    }

    @Override
    public Shape cloneShape() {
        return new Triangle();
    }

    private int[] getXPoints() {
        return new int[]{this.getX(), this.getX(), this.getX() + this.getSize()};
    }

    private int[] getYPoints() {
        return new int[]{this.getY(), this.getY() + this.getSize(), this.getY() + this.getSize()};
    }
}
