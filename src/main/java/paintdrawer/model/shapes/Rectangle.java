package paintdrawer.model.shapes;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An class that represents a Rectangle
 *
 */
public class Rectangle extends Shape
{
    @Override
    protected void drawShape(Graphics2D shape, boolean fill) {
        if (fill) {
            shape.fillRect(this.getX(), this.getY(), this.getSize(), this.getSize());
        } else {
            shape.drawRect(this.getX(), this.getY(), this.getSize(), this.getSize());
        }
    }

    @Override
    public Shape cloneShape() {
        return new Rectangle();
    }

    public String toString()
    {
        return "Rectangle";
    }
}
