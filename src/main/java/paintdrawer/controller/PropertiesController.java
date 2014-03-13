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
    private Shape markedShape;

    private final int SIZE = 0;
    private final int LINEWIDTH = 1;
    private final int COLOR = 2;
    private final int CLOSE = 3;
    private final int SAVE = 4;

    public PropertiesController(FrontController front, PropertiesTile propertiesTile)
    {
        this.front = front;
        this.propertiesTile = propertiesTile;

        initListeners(propertiesTile);
    }

    private void initListeners(PropertiesTile propertiesTile)
    {
        JComboBox sizeBox = (JComboBox) propertiesTile.getComponent(SIZE);
        sizeBox.setActionCommand(PropertiesTile.Components.SIZE.name());
        sizeBox.addActionListener(this);

        JComboBox lineBox = (JComboBox) propertiesTile.getComponent(LINEWIDTH);
        lineBox.setActionCommand(PropertiesTile.Components.LINEWIDTH.name());
        lineBox.addActionListener(this);

        JComboBox colorBox = (JComboBox) propertiesTile.getComponent(COLOR);
        colorBox.setActionCommand(PropertiesTile.Components.COLOR.name());
        colorBox.addActionListener(this);

        JButton closeButton = (JButton) propertiesTile.getComponent(CLOSE);
        closeButton.setActionCommand(PropertiesTile.Components.CLOSE.name());
        closeButton.addActionListener(this);

        JButton saveButton = (JButton) propertiesTile.getComponent(SAVE);
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
                shape.setSize(propertiesTile.getShapeSize());
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

        if (markedShape != null) {
            markedShape.setMarked(false);
            markedShape = null;
        }

        if (s != null) {
            if (s.isMarked()) {
                s.setMarked(false);
            } else {
                markedShape = s;
                s.setMarked(true);
                front.getModel().setActiveShape(s);
                System.out.println("Set active shape");
            }
        }

        propertiesTile.setVisible(true);
        front.update();
    }
}
