import controller.FrontController;
import view.Dashboard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by joel on 2014-03-12.
 */
public class drawApp extends JFrame
{
    public drawApp()
    {
        super("Paint drawer app");

        // Create frame with properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        setResizable(false);
        setLocationRelativeTo(this);

        ResourceManager resources = new ResourceManager();

        setIconImage(resources.getIconImage("icon", "png"));

        JPanel layoutContainer      = new JPanel();
        FrontController front       = new FrontController();
        Dashboard       dashboard   = new Dashboard();
        Canvas          paintCanvas = new Canvas();

        layoutContainer.setLayout(new BorderLayout(0, 0));
        layoutContainer.add(dashboard, BorderLayout.NORTH);
        layoutContainer.add(paintCanvas, BorderLayout.CENTER);

        //window.setJMenuBar(menu);
        add(layoutContainer);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        drawApp draw = new drawApp();
    }
}
