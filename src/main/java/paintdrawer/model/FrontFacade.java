package paintdrawer.model;

import java.awt.*;
import java.io.File;
import java.util.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * A class that implements the faced pattern hiding complexity of the model
 *
 */
public class FrontFacade extends Observable
{
    java.util.List<paintdrawer.model.abstracts.Shape> drawnShapes;
    java.util.List<paintdrawer.model.abstracts.Shape> prototypeShapes;

    public FrontFacade()
    {
        drawnShapes = new ArrayList<paintdrawer.model.abstracts.Shape>();
        prototypeShapes = new ArrayList<paintdrawer.model.abstracts.Shape>();
    }

    public void drawShape(String shapeName, int x, int y, int lineWidth, boolean filled, Color color)
    {
        paintdrawer.model.abstracts.Shape shape = null;

        for (paintdrawer.model.abstracts.Shape s : prototypeShapes) {
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

    public boolean save(File file)
    {
        System.out.println(file.getAbsolutePath());
        return true;
    }

    public boolean load(File file)
    {
        System.out.println(file.getAbsolutePath());
        return true;
    }
}
