package paintdrawer.model;

import paintdrawer.model.properties.Position;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An abstract class that represents a Shape
 *
 */
public abstract class Shape implements Serializable {
    private Color color;
    private int lineWidth;
    private boolean isFilled;
    private boolean isMarked;
    private Position position;

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
        return isFilled;
    }

    public void setFilled(boolean filled) {
        this.isFilled = filled;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        this.isMarked =  marked;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
