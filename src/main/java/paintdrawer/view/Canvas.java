package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Mats Maatson, Joel Denke
 *
 * View canvas component to draw shapes and implements observer pattern
 *
 */
public class Canvas extends JPanel implements Observer, MouseListener
{
    private FrontController front;

    public Canvas(FrontController front)
    {
         addMouseListener(this);
         this.front = front;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        System.out.println("Calling draw in canvas");

        for (Shape s : front.getModel().getShapes()) {
            s.draw(g);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println(String.format("X: %d, Y: %d", e.getX(), e.getY()));

        if (SwingUtilities.isRightMouseButton(e)) {
            front.getProperties().togglePropertyBoard(e);
        } else {
            Dashboard dashboard = ((Dashboard)front.getView().getComponent(LayoutContainer.LayoutComponent.DASHBOARD.ordinal()));
            String shape = dashboard.getSelected(0);
            JToggleButton button = (JToggleButton)dashboard.getComponent(1);

            if (shape != null && button.isSelected()) {
                PropertiesTile properties = (PropertiesTile)front.getView().getComponent(1);

                front.getModel().addShape(
                    shape,
                    e.getX(), e.getY(),
                    properties.getLineSize(), true, properties.getColor()
                );
                front.update();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
