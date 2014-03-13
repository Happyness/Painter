package paintdrawer;

import paintdrawer.controller.FrontController;
import paintdrawer.view.Menu;

import javax.swing.*;

/**
 * @author Mats Maatson, Joel Denke
 *
 * Main draw application
 *
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

        Menu menu                   = new Menu();
        FrontController front       = new FrontController(menu);

        setJMenuBar(menu);
        add(front.getView());
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
