package paintdrawer.model.properties;

/**
 * Created by joel on 2014-03-13.
 */
public class LineSize
{
     private String label;
     private int size;

     public LineSize(int size, String label)
     {
          this.size = size;
          this.label = label;
     }

     public int getSize()
     {
         return size;
     }

     public String toString()
     {
        return label;
     }
}
