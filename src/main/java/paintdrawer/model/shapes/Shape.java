package paintdrawer.model.shapes;

import paintdrawer.model.interfaces.Clone;
import paintdrawer.model.properties.ColorMap;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An abstract class that represents a Shape
 *
 */
public abstract class Shape implements Serializable, Clone
{
    private int size;
    private Color color;
    private int lineWidth;
    private boolean filled;
    private boolean marked;
    private int x, y;

    public void init(Color color, int lineWidth, int size, boolean filled, int x, int y)
    {
        this.color = color;
        this.size  = size;
        this.lineWidth = lineWidth;
        this.filled = filled;
        this.x      = x;
        this.y      = y;
    }

    public boolean intersects(int x, int y)
    {
        return x >= this.x && x <= this.x + size && y >= this.y && y <= this.y + size;
    }

    public void draw(Graphics g)
    {
        Graphics2D shape = (Graphics2D) g;

        if(marked) {
            shape.setColor(Color.MAGENTA);
        } else {
            shape.setColor(color);
        }

        shape.setStroke(new BasicStroke(lineWidth));
        drawShape(shape, filled);
    }

    public void setSize(int size) { this.size = size; }
    public int getSize() { return size; }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public boolean isFilled() {
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

    public int getX() {
        return x;
    }
    public int getY() { return y; }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    protected void drawShape(Graphics2D shape, boolean fill) {}
    abstract public String toString();

}
