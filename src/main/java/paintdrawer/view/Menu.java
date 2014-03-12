package paintdrawer.view;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Mats Maatson, Joel Denke
 *
 * View menu, used by Java Swing menu
 *
 */
public class Menu extends JMenuBar
{
    private final JFileChooser fileDialog = new JFileChooser();
    private Map<MenuEntry, JMenuItem> fileTab = new HashMap<MenuEntry, JMenuItem>();
    private Map<MenuEntry, JMenuItem> editTab = new HashMap<MenuEntry, JMenuItem>();

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


    public File validFileDialog(boolean open, File file)
    {
        fileDialog.setSelectedFile(file);

        int response = open ? fileDialog.showOpenDialog(this) : fileDialog.showSaveDialog(this);

        if (response == JFileChooser.APPROVE_OPTION) {
            return fileDialog.getSelectedFile();
        } else {
            return null;
        }
    }
}
