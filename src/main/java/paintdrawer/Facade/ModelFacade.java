package paintdrawer.Facade;

import paintdrawer.model.abstracts.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * A class that implements the faced pattern hiding complexity of the model
 *
 */
public class ModelFacade extends Observable {
    List<Shape> drawnShapes;
    List<Shape> prototypeShapes;

    public ModelFacade() {
        drawnShapes = new ArrayList<Shape>();
        prototypeShapes = new ArrayList<Shape>();
    }

    public void drawShape(String shapeName, int x, int y, int lineWidth, boolean filled, Color color) {
        Shape shape = null;

        for (Shape s : prototypeShapes) {
            if (s.getName().equalsIgnoreCase(shapeName)) {
                shape = s.cloneShape();
                break;
            }
        }

        if (shape != null) {
            shape.init(color, lineWidth, filled, x, y);
            drawnShapes.add(shape);
            setChanged();
            notifyObservers();
        }
    }


}
