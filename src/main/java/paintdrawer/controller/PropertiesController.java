package paintdrawer.controller;

import paintdrawer.model.shapes.Shape;
import paintdrawer.view.PropertiesTile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by joel on 2014-03-12.
 */
public class PropertiesController implements ActionListener
{
    private FrontController front;
    private PropertiesTile propertiesTile;

    public PropertiesController(FrontController front, PropertiesTile propertiesTile)
    {
        this.front = front;
        this.propertiesTile = propertiesTile;

        initListeners(propertiesTile);
    }

    private void initListeners(PropertiesTile propertiesTile)
    {
        JComboBox lineBox = (JComboBox) propertiesTile.getComponent(0);
        lineBox.setActionCommand(PropertiesTile.Components.LINEWIDTH.name());
        lineBox.addActionListener(this);

        JComboBox colorBox = (JComboBox) propertiesTile.getComponent(1);
        colorBox.setActionCommand(PropertiesTile.Components.COLOR.name());
        colorBox.addActionListener(this);

        JButton closeButton = (JButton) propertiesTile.getComponent(2);
        closeButton.setActionCommand(PropertiesTile.Components.CLOSE.name());
        closeButton.addActionListener(this);

        JButton saveButton = (JButton) propertiesTile.getComponent(3);
        saveButton.setActionCommand(PropertiesTile.Components.SAVE.name());
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(PropertiesTile.Components.CLOSE.name())) {
            propertiesTile.setVisible(false);
        }

        if (e.getActionCommand().equals(PropertiesTile.Components.SAVE.name())) {
            Shape shape = front.getModel().getActiveShape();

            if (shape != null) {
                shape.setColor(propertiesTile.getColor());
                shape.setLineWidth(propertiesTile.getLineSize());
                shape.setMarked(false);
                front.update();
            }
        }
    }

    public Shape getIntersectingShape(MouseEvent e)
    {
        int x = e.getX(), y = e.getY();
        List<Shape> shapes = front.getModel().getShapes();
        Shape s;

        // Start from latest added shape, if they are stacked as layers
        for (int i = shapes.size() - 1; i >= 0; i--) {
            s = shapes.get(i);

            if (s.intersects(x, y)) {
                return s;
            }
        }

        return null;
    }

    public void togglePropertyBoard(MouseEvent e)
    {
        Shape s = getIntersectingShape(e);

        if (s != null) {
            if (s.isMarked()) {
                s.setMarked(false);
            } else {
                s.setMarked(true);
                front.getModel().setActiveShape(s);
                System.out.println("Set active shape");
            }
        }

        front.update();
    }
}
