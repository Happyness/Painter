package paintdrawer.model.commands;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;
import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An RemoveAction class implementing the command interface
 *
 */
public class RemoveAction implements ICommand {

    private Shape shape;
    private FrontFacade model;
    private FrontController front;


    public RemoveAction(Shape shape, FrontFacade model, FrontController front) {
        this.shape = shape;
        this.model = model;
        this.front = front;
    }

    @Override
    public void execute() {
        model.removeShape(shape);
        front.update();
    }

    @Override
    public void unexecute() {
        model.addShape(shape);
        front.update();
    }
}
