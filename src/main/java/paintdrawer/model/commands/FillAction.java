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
    private FrontController front;

    public FillAction(Shape shape, FrontController front) {
        this.shape = shape;
        this.front =  front;
    }

    @Override
    public void execute() {
        if (shape != null) {
            shape.setFilled(true);
            front.update();
        }
    }

    @Override
    public void unexecute() {
        if (shape != null) {
            shape.setFilled(false);
            front.update();
        }
    }
}
