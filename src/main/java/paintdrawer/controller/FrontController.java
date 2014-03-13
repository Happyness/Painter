package paintdrawer.controller;

import paintdrawer.model.FrontFacade;
import paintdrawer.view.*;
import paintdrawer.view.Canvas;
import paintdrawer.view.Menu;

import java.awt.*;
import java.util.Observable;

/**
 * @author Mats Maatson, Joel Denke
 *
 * Front Controller to cover all controllers, hide complexity between controllers
 *
 */
public class FrontController extends Observable
{
    private FrontFacade model;
    private LayoutContainer view;
    private PropertiesController properties;
    private DashboardController  dashboard;
    private MenuController menu;

    public FrontController(Menu menu)
    {
        this.model          = new FrontFacade(this);

        Canvas canvas       = new Canvas(this);
        PropertiesTile propertiesTile = new PropertiesTile(this);
        Dashboard dashboard = new Dashboard(this);

        view                 = new LayoutContainer();
        view.addComponent(LayoutContainer.LayoutComponent.DASHBOARD, BorderLayout.NORTH, dashboard);
        view.addComponent(LayoutContainer.LayoutComponent.PROPERTIES, BorderLayout.WEST, propertiesTile);
        view.addComponent(LayoutContainer.LayoutComponent.CANVAS, BorderLayout.CENTER, canvas);

        this.properties = new PropertiesController(this, propertiesTile);
        this.dashboard  = new DashboardController(this, dashboard);
        this.menu       = new MenuController(this, menu);

        addObserver(canvas);
        addObserver(propertiesTile);
    }

    public DashboardController getDashboard() { return dashboard; }
    public PropertiesController getProperties() { return properties; }
    public FrontFacade getModel()
    {
        return model;
    }
    public LayoutContainer getView() { return view; }

    public void update()
    {
        setChanged();
        notifyObservers();
    }
}
