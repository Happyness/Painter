package paintdrawer.view;

import javax.swing.*;
import java.util.List;

/**
 * Created by joel on 2014-03-13.
 */
public class Toolbar extends JToolBar
{
    protected DefaultComboBoxModel<Object> asComboBoxModel(List list)
    {
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<Object>();

        for (Object o: list) {
            model.addElement(o);
        }

        return model;
    }

    protected JComboBox<String> generateComboBox(DefaultComboBoxModel model)
    {
        JComboBox<String> selectBox = new JComboBox<String>();
        selectBox.setModel(model);
        selectBox.setMaximumRowCount(5);
        return selectBox;
    }

    public JComboBox getBox(int pos)
    {
        if (pos < getComponentCount()) {
            return (JComboBox) getComponent(pos);
        }

        return null;
    }

    public String getSelected(int pos)
    {
        return getBox(pos).getModel().getSelectedItem().toString();
    }
}
