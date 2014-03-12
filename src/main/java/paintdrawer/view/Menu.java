package paintdrawer.view;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by joel on 2014-03-12.
 */
public class Menu extends JMenuBar
{
    Map<MenuEntry, JMenuItem> fileTab = new HashMap<MenuEntry, JMenuItem>();
    Map<MenuEntry, JMenuItem> editTab = new HashMap<MenuEntry, JMenuItem>();

    public Menu()
    {
        fileTab.put(MenuEntry.OPEN, new JMenuItem("Open canvas"));
        fileTab.put(MenuEntry.SAVE, new JMenuItem("Save canvas"));
        fileTab.put(MenuEntry.EXIT, new JMenuItem("Exit"));

        addTab("File", fileTab);

        editTab.put(MenuEntry.REDO, new JMenuItem("Redo last change"));
        editTab.put(MenuEntry.UNDO, new JMenuItem("Undo last change"));

        addTab("Edit", editTab);
    }

    private void addTab(String name, Map<MenuEntry, JMenuItem> tab)
    {
        Iterator it   = tab.entrySet().iterator();
        JMenu tabMenu = new JMenu(name);

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            tabMenu.add((JMenuItem)pairs.getValue());
        }

        add(tabMenu);
    }

    public Map<MenuEntry, JMenuItem> getMenuTab(MenuTab tabName)
    {
         switch (tabName) {
             case FILE : return fileTab;
             case EDIT : return editTab;
             default   : return null;
         }
    }
}
