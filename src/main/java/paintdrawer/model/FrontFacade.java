package paintdrawer.model;

import java.io.File;

/**
 * Created by joel on 2014-03-12.
 */
public class FrontFacade
{

    public boolean save(File file)
    {
        System.out.println(file.getAbsolutePath());
        return true;
    }

    public boolean load(File file)
    {
        System.out.println(file.getAbsolutePath());
        return true;
    }
}
