package paintdrawer;

import paintdrawer.controller.FrontController;
import paintdrawer.view.Canvas;
import paintdrawer.view.Dashboard;
import paintdrawer.view.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by joel on 2014-03-12.
 */
public class drawApp extends JFrame
{
    JPanel layoutContainer = new JPanel();

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

        Menu menu                   = new Menu();
        FrontController front       = new FrontController(new Dashboard(), menu);
        Dashboard       dashboard   = new Dashboard();
        Canvas paintCanvas          = new Canvas();

        layoutContainer.setLayout(new BorderLayout(0, 0));
        layoutContainer.add(dashboard, BorderLayout.NORTH);
        layoutContainer.add(paintCanvas, BorderLayout.CENTER);

        setJMenuBar(menu);
        add(layoutContainer);
        //pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                drawApp draw = new drawApp();
            }
        };

        SwingUtilities.invokeLater(r);
    }
}
