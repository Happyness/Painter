package paintdrawer.model.properties;

/**
 * @author Mats Maatson, Joel Denke
 *
 * A helper class for the property LineSize
 *
 */
public class LineSize
{
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
