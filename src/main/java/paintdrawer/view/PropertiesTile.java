package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;
import paintdrawer.model.shapes.Shape;
import paintdrawer.model.properties.ColorMap;
import paintdrawer.model.properties.LineSize;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by joel on 2014-03-12.
 */
public class PropertiesTile extends Toolbar implements Observer
{
    public enum Components {
        LINEWIDTH, COLOR, SAVE, CLOSE
    }

    private FrontController front;

    public PropertiesTile(FrontController front)
    {
        this.front = front;
        this.initComponents();
        setOrientation(VERTICAL);

        //System.out.println(Components.LINEWIDTH.ordinal());
    }

    private void initComponents()
    {
        FrontFacade model = front.getModel();

        add(generateComboBox(asComboBoxModel(model.getLineWidths())), null, 0);
        add(generateComboBox(asComboBoxModel(model.getColors())), null, 1);

        add(new JButton("Close"), null, 2);
        add(new JButton("Save"), null, 3);
    }

    public int getLineSize()
    {
        LineSize selected = (LineSize)getBox(0).getSelectedItem();
        return selected.getSize();
    }

    public Color getColor()
    {
        String selected = (String)getBox(1).getSelectedItem();
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

    @Override
    public void update(Observable o, Object arg)
    {
        Shape shape = front.getModel().getActiveShape();

        if (shape != null) {
            System.out.println("We have a active shape, yay");
            System.out.println(getBox(0).getSelectedItem());

            getBox(0).setSelectedItem(getLineSizeObject(shape.getLineWidth()).toString());

            System.out.println(getBox(0).getSelectedItem());


            getBox(1).setSelectedItem(shape.getColor().toString());
        }
    }
}