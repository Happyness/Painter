package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;
import paintdrawer.model.properties.ShapeSize;
import paintdrawer.model.shapes.Shape;
import paintdrawer.model.properties.ColorMap;
import paintdrawer.model.properties.LineSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by joel on 2014-03-12.
 */
public class PropertiesTile extends Toolbar implements Observer
{
    public enum Components {
        SIZE, LINEWIDTH, COLOR, DELETE, CLOSE
    }

    private FrontController front;

    public PropertiesTile(FrontController front)
    {
        this.front = front;
        this.initComponents();
        setOrientation(VERTICAL);
    }

    private void initComponents()
    {
        FrontFacade model = front.getModel();

        addComboBox(model.getShapeSizes(), 0);
        addComboBox(model.getLineWidths(), 1);
        addComboBox(model.getColors(), 2);

        add(new JButton("Delete"), null, 3);
        add(new JButton("Close"), null, 4);
    }

    public int getShapeSize()
    {
        ShapeSize selected = (ShapeSize)getBox(0).getSelectedItem();
        return selected.getSize();
    }

    public int getLineSize()
    {
        LineSize selected = (LineSize)getBox(1).getSelectedItem();
        return selected.getSize();
    }

    public Color getColor()
    {
        String selected = (String)getBox(2).getSelectedItem();
        return new ColorMap(Color.class).getColor(selected);
    }

    public LineSize getLineSizeObject(int size)
    {
        for (LineSize ls: front.getModel().getLineWidths()) {
            if (ls.getSize() == size) {
                return ls;
            }
        }

        return null;
    }

    public ShapeSize getShapeSizeObject(int size)
    {
        for (ShapeSize ss: front.getModel().getShapeSizes()) {
            if (ss.getSize() == size) {
                return ss;
            }
        }

        return null;
    }

    public void setMatchingItemIndex(int box, String label)
    {
        JComboBox cbox = getBox(box);

        for (int i = 0; i < cbox.getItemCount(); i++) {
            if (cbox.getItemAt(i).toString().equals(label)) {
                cbox.setSelectedIndex(i);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Shape shape = front.getModel().getActiveShape();

        if (shape != null) {
            setMatchingItemIndex(0, getShapeSizeObject(shape.getSize()).toString());
            setMatchingItemIndex(1, getLineSizeObject(shape.getLineWidth()).toString());

            ColorMap map = new ColorMap(Color.class);
            setMatchingItemIndex(2, map.getLabel(shape.getColor()));
        }
    }
}