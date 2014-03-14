package paintdrawer.model.commands;

import paintdrawer.controller.FrontController;
import paintdrawer.model.interfaces.ICommand;
import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An AddAction class implementing the command interface
 *
 */
public class MoveAction implements ICommand
{
    private int x, y, prevX, prevY;
    private Shape shape;

    public MoveAction(Shape shape)
    {
        this.shape = shape;
        this.prevX = shape.getX();
        this.prevY = shape.getY();
    }

    public Shape getShape()
    {
        return shape;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute()
    {
        shape.setPosition(x, y);
    }

    @Override
    public void unexecute()
    {
        shape.setPosition(prevX, prevY);
    }
}
