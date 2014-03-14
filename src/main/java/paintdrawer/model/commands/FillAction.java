package paintdrawer.model.commands;

import paintdrawer.controller.FrontController;
import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An FillAction class implementing the command interface
 *
 */
public class FillAction implements ICommand {

    private Shape shape;
    private boolean filled;

    public FillAction(Shape shape, boolean filled) {
        this.shape = shape;
        this.filled = filled;
    }

    @Override
    public void execute()
    {
        if (shape != null) {
            shape.setFilled(filled);
        }
    }

    @Override
    public void unexecute()
    {
        if (shape != null) {
            shape.setFilled(!filled);
        }
    }
}
