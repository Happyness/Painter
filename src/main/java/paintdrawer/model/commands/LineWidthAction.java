package paintdrawer.model.commands;

import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An LineWidtAction class implementing the command interface
 *
 */
public class LineWidthAction implements ICommand {

    Shape shape;
    int lineWidth;
    int oldLineWidth;

    public LineWidthAction(Shape shape, int lineWidth) {
        this.shape = shape;
        this.lineWidth = lineWidth;
        this.oldLineWidth = shape.getLineWidth();
    }

    @Override
    public void execute() {
        if (shape != null) {
            shape.setLineWidth(lineWidth);
        }
    }

    @Override
    public void unexecute() {
        if (shape != null) {
            shape.setLineWidth(oldLineWidth);
        }
    }
}
