package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An view extending Toolbar showing the Dashboard and implementing the observer pattern
 *
 */
public class Dashboard extends Toolbar implements Observer
{
    public enum Components {
        SHAPES, PAINT
    }

    public final static int SHAPE = 0;
    public final static int PAINT = 1;

    private FrontController front;

    public Dashboard(FrontController front)
    {
        this.front = front;
        this.initComponents();
    }

    private void initComponents()
    {
        FrontFacade model = front.getModel();

        addComboBox(model.getPrototypes(), Dashboard.SHAPE);
        add(new JToggleButton("Paint"), null, Dashboard.PAINT);
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}