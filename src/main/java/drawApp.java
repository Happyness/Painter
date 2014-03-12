import controller.FrontController;
import view.Dashboard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by joel on 2014-03-12.
 */
public class drawApp
{
    public drawApp()
    {
        // Create frame with properties
        JFrame window = new JFrame("Painter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(100, 100, 640, 480);
        window.setResizable(false);
        window.setLocationRelativeTo(window);

        JPanel layoutContainer      = new JPanel();
        FrontController front       = new FrontController();
        Dashboard       dashboard   = new Dashboard();
        Canvas          paintCanvas = new Canvas();

        layoutContainer.setLayout(new BorderLayout(0, 0));
        layoutContainer.add(dashboard, BorderLayout.NORTH);
        layoutContainer.add(paintCanvas, BorderLayout.CENTER);

        //window.setJMenuBar(menu);
        window.add(layoutContainer);
        window.setVisible(true);
    }

    public static void main(String[] args)
    {
        drawApp draw = new drawApp();
    }
}
