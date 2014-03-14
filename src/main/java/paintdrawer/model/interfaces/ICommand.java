package paintdrawer.model.interfaces;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An command interface for command pattern
 *
 */
public interface ICommand
{
    public void execute();
    public void unexecute();
}
