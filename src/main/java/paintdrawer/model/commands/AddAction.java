package paintdrawer.model.commands;

import paintdrawer.model.FrontFacade;
import paintdrawer.model.shapes.Shape;
import paintdrawer.model.interfaces.Command;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An AddAction class implementing the command interface
 *
 */
public class AddAction implements Command {

    private Shape shape;
    private FrontFacade model;

    public AddAction(Shape shape, FrontFacade model)
    {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void execute() {
        model.addShape(shape);
    }

    @Override
    public void unexecute() {
        model.removeShape(shape);
    }

    @Override
    public void reexecute() {
        // TODO: redo
    }
}
