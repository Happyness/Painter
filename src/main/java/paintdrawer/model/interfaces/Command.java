package paintdrawer.model.interfaces;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An command interface for command pattern
 *
 */
public interface Command {
    public void execute();
    public void unexecute();
}
