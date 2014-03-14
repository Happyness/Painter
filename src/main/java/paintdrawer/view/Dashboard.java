package paintdrawer.view;

import paintdrawer.controller.FrontController;
import paintdrawer.model.FrontFacade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by joel on 2014-03-12.
 */
public class Dashboard extends Toolbar implements Observer
{
    public enum Components {
        SHAPES, PAINT
    }

    private FrontController front;

    public Dashboard(FrontController front)
    {
        this.front = front;
        this.initComponents();
    }

    private void initComponents()
    {
        FrontFacade model = front.getModel();

        addComboBox(model.getPrototypes(), 0);
        add(new JToggleButton("Paint"), null, 1);
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}