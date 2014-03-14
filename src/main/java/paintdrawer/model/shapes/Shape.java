package paintdrawer.model.shapes;

import paintdrawer.model.interfaces.IClone;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An abstract class that represents a Shape
 *
 */
public abstract class Shape implements Serializable, IClone
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
        return x >= this.x && x <= this.x + size + lineWidth && y >= this.y && y <= this.y + size + lineWidth;
    }

    public void draw(Graphics g)
    {
        Graphics2D shape = (Graphics2D) g;

        shape.setColor(color);

        if (marked) {
            final float dash1[] = {10.0f};
            final BasicStroke dashed =
                    new BasicStroke(lineWidth,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
            shape.setStroke(dashed);
        } else {
            shape.setStroke(new BasicStroke(lineWidth));
        }

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
