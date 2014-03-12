package paintdrawer.model.commands;

import paintdrawer.model.abstracts.Shape;
import paintdrawer.model.interfaces.Command;

import java.util.List;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An AddAction class implementing the command interface
 *
 */
public class AddAction implements Command {

    private Shape shape;
    private List<Shape> shapes;

    public AddAction(Shape shape, List<Shape> shapes) {
        this.shape = shape;
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        shapes.add(shape);
    }

    @Override
    public void unexecute() {
        shapes.remove(shape);
    }

    @Override
    public void reexecute() {
        // TODO: redo
    }
}
