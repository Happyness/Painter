package paintdrawer.model.shapes;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An class that represents a Oval
 *
 */
public class Oval extends Shape
{
    @Override
    protected void drawShape(Graphics2D shape, boolean fill) {
        if (fill) {
            shape.fillOval(this.getX(), this.getY(), this.getSize(), this.getSize());
        } else {
            shape.drawOval(this.getX(), this.getY(), this.getSize(), this.getSize());
        }
    }

    @Override
    public Shape cloneShape() {
        return new Oval();
    }

    public String toString()
    {
        return "Oval";
    }
}
