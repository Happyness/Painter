package paintdrawer.controller;

import paintdrawer.view.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Mats Maatson, Joel Denke
 *
 * Menu action controller
 *
 */
public class MenuController implements ActionListener
{
    private FrontController front;
    private Menu menu;

    public MenuController(FrontController front, Menu menu)
    {
        this.front = front;
        this.menu  = menu;
        addActionListeners(menu.getMenuTab(Menu.MenuTab.FILE));
        addActionListeners(menu.getMenuTab(Menu.MenuTab.EDIT));
    }

    private void addActionListeners(Map<Menu.MenuEntry, JMenuItem> tab)
    {
        Iterator it = tab.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            JMenuItem item = (JMenuItem)pairs.getValue();
            item.setActionCommand(((Menu.MenuEntry) pairs.getKey()).name());
            item.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(Menu.MenuEntry.NEW.name())) {
            front.getModel().getShapes().clear();
            front.update();
        }

        if (e.getActionCommand().equals(Menu.MenuEntry.EXIT.name())) {
            System.exit(0);
        }

        if (e.getActionCommand().equals(Menu.MenuEntry.UNDO.name())) {
            front.getModel().undo();
        }

        if (e.getActionCommand().equals(Menu.MenuEntry.REDO.name())) {
            front.getModel().redo();
        }

        if (e.getActionCommand().equals(Menu.MenuEntry.SAVE.name())) {
            File file = menu.validFileDialog(false, new File("shapes.txt"));

            if (file != null) {
                if(!front.getModel().save(file)) {
                    JOptionPane.showMessageDialog(null, "Failed saving file to disk");
                }
            }
        }

        if (e.getActionCommand().equals(Menu.MenuEntry.OPEN.name()))
        {
            File file = menu.validFileDialog(true, new File("shapes.txt"));

            if (file != null) {
                if(!front.getModel().load(file)) {
                    JOptionPane.showMessageDialog(null, "Failed load selected file");
                }
            }
        }
    }
}
