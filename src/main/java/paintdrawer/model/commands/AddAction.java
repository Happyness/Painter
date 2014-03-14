package paintdrawer.model.commands;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;
import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An AddAction class implementing the command interface
 *
 */
public class AddAction implements ICommand {

    private Shape shape;
    private FrontFacade model;
    private FrontController front;

    public AddAction(Shape shape, FrontFacade model, FrontController front)
    {
        this.shape = shape;
        this.model = model;
        this.front = front;
    }

    @Override
    public void execute() {
        model.addShape(shape);
        front.update();
    }

    @Override
    public void unexecute() {
        model.removeShape(shape);
        front.update();
    }
}
