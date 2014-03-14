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

    private Shape shape, deletedShape;
    private FrontFacade model;

    public RemoveAction(Shape shape, FrontFacade front)
    {
        this.shape = shape;
        this.model = front;
        deletedShape = shape;
    }

    @Override
    public void execute()
    {
        model.removeShape(shape);
    }

    @Override
    public void unexecute()
    {
        System.out.println("Undo delete action");
        model.addShape(deletedShape);
    }
}
