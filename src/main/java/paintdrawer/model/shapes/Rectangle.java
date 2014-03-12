package paintdrawer.model.shapes;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An class that represents a Rectangle
 *
 */
public class Rectangle extends paintdrawer.model.abstracts.Shape {

    @Override
    protected void drawShape(Graphics2D shape) {
        shape.drawRect(this.getPosition().getX(), this.getPosition().getY(), this.getSize(), this.getSize());
    }

    @Override
    protected void drawFilledShape(Graphics2D filledShape) {
        filledShape.fillRect(this.getPosition().getX(), this.getPosition().getY(), this.getSize(), this.getSize());
    }

    @Override
    public paintdrawer.model.abstracts.Shape clone() {
        return new Rectangle();
    }
}
