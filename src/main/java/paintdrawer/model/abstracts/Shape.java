package paintdrawer.model.abstracts;

import paintdrawer.model.interfaces.Clone;
import paintdrawer.model.properties.Position;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An abstract class that represents a Shape
 *
 */
public abstract class Shape implements Serializable, Clone {
    private int SIZE = 30;
    private Color color;
    private int lineWidth;
    private boolean filled;
    private boolean marked;
    private Position position;

    public void init(Color color, int lineWidth, boolean filled, int x, int y) {
        this.color = color;
        this.lineWidth = lineWidth;
        this.filled = filled;
        this.filled = filled;
        this.position = new Position(x, y);
    }

    public void draw(Graphics g) {
        Graphics2D shape = (Graphics2D) g;

        if(marked) {
            // TODO: set marked color
        } else {
            // TODO: set color
        }
        shape.setStroke(new BasicStroke(lineWidth));

        if(filled) {
            drawFilledShape(shape);
        } else {
            drawShape(shape);
        }
    }

    public int getSize() { return SIZE; }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public boolean isFiled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked =  marked;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() { return this.getClass().getSimpleName(); }

    protected void drawShape(Graphics2D shape) {}

    protected void drawFilledShape(Graphics2D filledShape) {}

}
