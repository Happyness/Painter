package paintdrawer.model.interfaces;

import paintdrawer.model.shapes.Shape;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An clone interface for prototype pattern
 *
 */
public interface Clone {
    public Shape cloneShape();
}
