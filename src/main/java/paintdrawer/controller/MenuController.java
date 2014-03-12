package paintdrawer.controller;

import paintdrawer.view.Menu;
import paintdrawer.view.MenuEntry;
import paintdrawer.view.MenuTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joel on 2014-03-12.
 */
public class MenuController implements ActionListener
{
    public MenuController(Menu menu)
    {
        addActionListeners(menu.getMenuTab(MenuTab.FILE));
        addActionListeners(menu.getMenuTab(MenuTab.FILE));
    }

    private void addActionListeners(Map<MenuEntry, JMenuItem> tab)
    {
        Iterator it = tab.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            JMenuItem item = (JMenuItem)pairs.getValue();
            item.setActionCommand(((MenuEntry) pairs.getKey()).name());
            item.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(MenuEntry.EXIT.name())) {
            System.exit(0);
        }

        if (e.getActionCommand().equals(MenuEntry.UNDO.name())) {
            //model.undo();
        }
    }
}
