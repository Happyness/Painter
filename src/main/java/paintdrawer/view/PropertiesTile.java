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
 * @author Mats Maatson, Joel Denke
 *
 * An view showing the Property Menu
 *
 */
public class PropertiesTile extends Toolbar implements Observer
{
    public enum Components {
        SIZE, LINEWIDTH, COLOR, DELETE, CLOSE, FILL
    }

    public static final int SIZE = 1;
    public static final int LINEWIDTH = 3;
    public static final int COLOR = 5;
    public static final int DELETE = 6;
    public static final int CLOSE = 7;
    public static final int FILL = 8;

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

        add(new JLabel("Shape size"), 0);
        addComboBox(model.getShapeSizes(), SIZE);
        add(new JLabel("Line width"), 2);
        addComboBox(model.getLineWidths(), LINEWIDTH);
        add(new JLabel("Color"), 4);
        addComboBox(model.getColors(), COLOR);

        add(new JButton("Delete"), null, DELETE);
        add(new JButton("Close"), null, CLOSE);
        add(new JToggleButton("Fill"), null, FILL);
    }

    public int getShapeSize()
    {
        ShapeSize selected = (ShapeSize)getBox(SIZE).getSelectedItem();
        return selected.getSize();
    }

    public int getLineSize()
    {
        LineSize selected = (LineSize)getBox(LINEWIDTH).getSelectedItem();
        return selected.getSize();
    }

    public boolean getFilled()
    {
        JToggleButton button = (JToggleButton)getComponent(FILL);
        return button.isSelected();
    }

    public Color getColor()
    {
        String selected = (String)getBox(COLOR).getSelectedItem();
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
            setMatchingItemIndex(SIZE, getShapeSizeObject(shape.getSize()).toString());
            setMatchingItemIndex(LINEWIDTH, getLineSizeObject(shape.getLineWidth()).toString());

            ColorMap map = new ColorMap(Color.class);
            setMatchingItemIndex(COLOR, map.getLabel(shape.getColor()));

            JToggleButton button = (JToggleButton)getComponent(FILL);
            button.setSelected(shape.isFilled());
        }
    }
}