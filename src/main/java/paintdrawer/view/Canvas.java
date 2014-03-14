package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.commands.MoveAction;
import paintdrawer.model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Mats Maatson, Joel Denke
 *
 * View canvas component to draw shapes and implements observer pattern
 *
 */
public class Canvas extends JPanel implements Observer, MouseListener, MouseMotionListener
{
    private FrontController front;
    private MoveAction action;

    public Canvas(FrontController front)
    {
         addMouseListener(this);
         addMouseMotionListener(this);
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
                    properties.getShapeSize(),
                    e.getX(), e.getY(),
                    properties.getLineSize(), properties.getFilled(), properties.getColor()
                );
                front.update();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("Mouse is pressed");

        Dashboard dashboard = ((Dashboard)front.getView().getComponent(LayoutContainer.LayoutComponent.DASHBOARD.ordinal()));
        JToggleButton button = (JToggleButton)dashboard.getComponent(1);
        Shape s = front.getProperties().getIntersectingShape(e);

        if (s != null && action == null && !SwingUtilities.isRightMouseButton(e) && !button.isSelected()) {
            action = new MoveAction(s);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (action != null) {
            action.setPosition(e.getX(), e.getY());
            front.getModel().executeCommand(action);
            action = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e){
        if (action != null) {
            System.out.println("Moving");

            action.getShape().setPosition(e.getX(), e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }
}
