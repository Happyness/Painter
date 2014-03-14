package paintdrawer.controller;

import paintdrawer.view.Dashboard;
import paintdrawer.view.LayoutContainer;
import paintdrawer.view.PropertiesTile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mats Maatson, Joel Denke
 *
 * An controller class controlling the dashboard
 *
 */
public class DashboardController implements ActionListener
{
    private FrontController front;
    private Dashboard dashboard;

    public DashboardController(FrontController front, Dashboard dashboard)
    {
        this.front     = front;
        this.dashboard = dashboard;

        initListeners(dashboard);
    }

    private void initListeners(Dashboard dashboard)
    {
        JComboBox lineBox = (JComboBox) dashboard.getComponent(Dashboard.SHAPE);
        lineBox.setActionCommand(Dashboard.Components.SHAPES.name());
        lineBox.addActionListener(this);

        JToggleButton saveButton = (JToggleButton) dashboard.getComponent(Dashboard.PAINT);
        saveButton.setActionCommand(Dashboard.Components.PAINT.name());
        saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(Dashboard.Components.PAINT.name())) {
            PropertiesTile properties = (PropertiesTile)front.getView().getComponent(LayoutContainer.LayoutComponent.PROPERTIES.ordinal());
            properties.setVisible(true);
        }
    }
}
