package paintdrawer.model;

import paintdrawer.controller.FrontController;
import paintdrawer.model.commands.AddAction;
import paintdrawer.model.interfaces.Command;
import paintdrawer.model.shapes.Shape;
import paintdrawer.model.properties.ColorMap;
import paintdrawer.model.properties.LineSize;
import paintdrawer.model.shapes.*;
import paintdrawer.model.shapes.Rectangle;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * @author Mats Maatson, Joel Denke
 *
 * A class that implements the faced pattern hiding complexity of the model
 *
 */
public class FrontFacade extends Observable
{
    private List<Shape> shapes = new ArrayList<Shape>();
    private List<Shape> prototypes = new ArrayList<Shape>();
    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();
    private Shape activeShape;
    private FrontController front;

    public FrontFacade(FrontController front)
    {
        this.front = front;
        prototypes.addAll(Arrays.asList(new Rectangle(), new Oval()));
    }

    public Shape getActiveShape() { return activeShape; }
    public List<Shape> getShapes()
    {
        return shapes;
    }
    public List<Shape> getPrototypes()
    {
        return prototypes;
    }

    public void addShape(String shapeName, int x, int y, int lineWidth, boolean filled, Color color)
    {
        for (Shape s : prototypes) {
            if (s.toString().equalsIgnoreCase(shapeName)) {
                Shape shape = s.cloneShape();
                shape.init(color, lineWidth, filled, x, y);

                Command addCommand = new AddAction(shape, this);
                setCommand(addCommand);

                front.update();
            }
        }
    }

    // @TODO, fix this
    public List<LineSize> getLineWidths()
    {
        ArrayList<LineSize> sizeList = new ArrayList<LineSize>();
        sizeList.add(new LineSize(10, "small"));
        sizeList.add(new LineSize(20, "medium"));
        sizeList.add(new LineSize(40, "large"));

        return sizeList;
    }

    public List<String> getColors()
    {
        return new ColorMap(Color.class).getColorLabels();
    }

    public void undo()
    {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
            front.update();
        }
    }

    public void redo()
    {
        if (!undoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
            front.update();
        }
    }

    public void addShape(Shape shape)
    {
        shapes.add(shape);
    }

    public void removeShape(Shape shape)
    {
        shapes.remove(shape);
    }

    public boolean save(File file)
    {
        System.out.println(file.getAbsolutePath());
        ObjectOutputStream stream = null;

        try {
            stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(shapes);
        } catch (Exception e) {
            return false;
        } finally {
            try { if (stream != null) stream.close(); } catch (Exception e2) {}
        }

        return true;
    }

    public boolean load(File file)
    {
        System.out.println(file.getAbsolutePath());
        ObjectInputStream stream = null;

        try {
            stream = new ObjectInputStream(new FileInputStream(file));
            shapes = (List<Shape>) stream.readObject();
            front.update();
        } catch (Exception e) {
            shapes = new ArrayList<Shape>();
            return false;
        } finally {
            try { if (stream != null) stream.close(); } catch (Exception e2) {}
        }

        return true;
    }

    private void setCommand(Command command) {
        redoStack.clear();
        undoStack.push(command);
        command.execute();

    }
}
