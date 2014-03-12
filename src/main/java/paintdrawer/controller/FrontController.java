package paintdrawer.controller;

import paintdrawer.model.FrontFacade;
import paintdrawer.view.Dashboard;
import paintdrawer.view.Menu;

/**
 * @author Mats Maatson, Joel Denke
 *
 * Front Controller to cover all controllers, hide complexity between controllers
 *
 */
public class FrontController
{
    private FrontFacade model;
    private DashboardController dashboard;
    private MenuController menu;

    public FrontController(FrontFacade model, Dashboard dashboard, Menu menu)
    {
        this.model     = model;
        this.dashboard = new DashboardController(this, dashboard);
        this.menu      = new MenuController(this, menu);
    }

    public FrontFacade getModel()
    {
        return model;
    }
}
