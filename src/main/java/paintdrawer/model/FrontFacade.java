package paintdrawer.model;

import paintdrawer.controller.FrontController;
import paintdrawer.model.commands.AddAction;
import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.properties.ShapeSize;
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
    private Stack<ICommand> undoStack = new Stack<ICommand>();
    private Stack<ICommand> redoStack = new Stack<ICommand>();
    private Shape activeShape;
    private FrontController front;

    public FrontFacade(FrontController front)
    {
        this.front = front;
        prototypes.addAll(Arrays.asList(new Rectangle(), new Oval(), new Triangle(), new Hexagon()));
    }

    public void setActiveShape(Shape shape) { activeShape = shape; }
    public Shape getActiveShape() { return activeShape; }
    public List<Shape> getShapes()
    {
        return shapes;
    }
    public List<Shape> getPrototypes()
    {
        return prototypes;
    }

    public void addShape(String shapeName, int size, int x, int y, int lineWidth, boolean filled, Color color)
    {
        for (Shape s : prototypes) {
            if (s.toString().equalsIgnoreCase(shapeName)) {
                Shape shape = s.cloneShape();
                shape.init(color, lineWidth, size, filled, x, y);
                executeCommand(new AddAction(shape, this));
            }
        }
    }

    public List<ShapeSize> getShapeSizes()
    {
        ArrayList<ShapeSize> sizeList = new ArrayList<ShapeSize>();
        sizeList.add(new ShapeSize(30));
        sizeList.add(new ShapeSize(50));
        sizeList.add(new ShapeSize(70));

        return sizeList;
    }

    public List<LineSize> getLineWidths()
    {
        ArrayList<LineSize> sizeList = new ArrayList<LineSize>();
        sizeList.add(new LineSize(1));
        sizeList.add(new LineSize(10));
        sizeList.add(new LineSize(20));

        return sizeList;
    }

    public List<String> getColors()
    {
        return new ColorMap(Color.class).getColorLabels();
    }

    public void undo()
    {
        if (!undoStack.empty()) {
            ICommand command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
            front.update();
        }
    }

    public void redo()
    {
        if (!redoStack.empty()) {
            ICommand command = redoStack.pop();
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

    public void executeCommand(ICommand command)
    {
        System.out.println("Executing command" + command);

        redoStack.clear();
        undoStack.push(command);
        command.execute();
        front.update();
    }
}
