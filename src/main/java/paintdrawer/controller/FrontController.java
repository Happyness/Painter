package paintdrawer.controller;

import paintdrawer.view.Dashboard;
import paintdrawer.view.Menu;

/**
 * Created by joel on 2014-03-12.
 */
public class FrontController
{
    private DashboardController dashboard;
    private MenuController menu;

    public FrontController(Dashboard dashboard, Menu menu)
    {
        this.dashboard = new DashboardController(dashboard);
        this.menu = new MenuController(menu);
    }
}
