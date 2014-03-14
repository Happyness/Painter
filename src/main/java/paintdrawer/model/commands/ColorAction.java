package paintdrawer.model.commands;

import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An ColorAction class implementing the command interface
 *
 */
public class ColorAction implements ICommand {

    private Shape shape;
    private Color color;
    private Color oldColor;

    public ColorAction(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.oldColor = shape.getColor();
    }

    @Override
    public void execute() {
        if (shape != null) {
            shape.setColor(color);
        }
    }

    @Override
    public void unexecute() {
        if (shape != null) {
            shape.setColor(oldColor);
        }
    }
}
