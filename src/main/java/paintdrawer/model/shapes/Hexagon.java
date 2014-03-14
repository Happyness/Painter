package paintdrawer.model.shapes;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An class that represents a Hexagon
 *
 */
public class Hexagon extends Shape {

    @Override
    protected void drawShape(Graphics2D shape, boolean fill) {
        Polygon hexagon = getHexagon(new Polygon());
        if (fill) {
            shape.fillPolygon(hexagon);
        } else {
            shape.drawPolygon(hexagon);
        }
    }

    @Override
    public String toString() {
        return "Hexagon";
    }

    @Override
    public Shape cloneShape() {
        return new Hexagon();
    }

    private Polygon getHexagon(Polygon hexagon) {
        for(int i = 0; i < 6; i++) {
            hexagon.addPoint((int) (this.getX() +  (this.getSize()/2 * Math.cos((i * 2 * Math.PI)/6))), (int) (this.getY() + (this.getSize()/2 * Math.sin((i * 2 * Math.PI)/6))));
        }
        return hexagon;
    }

}
