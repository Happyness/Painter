package paintdrawer.model.properties;

/**
 * Created by joel on 2014-03-13.
 */
public class LineSize
{
     private String label;
     private int size;

     public LineSize(int size)
     {
          this.size = size;
     }

     public int getSize()
     {
         return size;
     }

     public String toString()
     {
        switch (size) {
            case 1: return "small";
            case 10: return "medium";
            case 20: return "large";
            default: return "medium";
        }
     }
}
