package paintdrawer.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * Layout container, containing all components with dashboard and canvas
 *
 */
public class LayoutContainer extends JPanel
{
    public enum LayoutComponent
    {
        DASHBOARD, PROPERTIES, CANVAS
    }

    public LayoutContainer()
    {
        setLayout(new BorderLayout(0, 0));
    }

    public void addComponent(LayoutComponent componentType, String pos, Component component)
    {
        add(component, pos, componentType.ordinal());
    }

    public Component getComponent(int pos)
    {
        return super.getComponent(pos);
    }
}
