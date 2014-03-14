package paintdrawer.model.commands;

import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An ResizeAction class implementing the command interface
 *
 */
public class ResizeAction implements ICommand {

    private Shape shape;
    private int size;
    private int oldSize;

    public ResizeAction(Shape shape, int size) {
        this.shape =  shape;
        this.size = size;
        this.oldSize = shape.getSize();
    }

    @Override
    public void execute() {
        if (shape != null) {
            shape.setSize(size);
        }
    }

    @Override
    public void unexecute() {
        if (shape != null) {
            shape.setSize(oldSize);
        }
    }
}
