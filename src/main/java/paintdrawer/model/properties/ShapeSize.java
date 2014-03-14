package paintdrawer.model.properties;

/**
 * @author Mats Maatson, Joel Denke
 *
 * A helper class for the property ShapeSize
 *
 */
public class ShapeSize
{
     private int size;

     public ShapeSize(int size)
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
            case 30: return "small";
            case 50: return "medium";
            case 70: return "large";
            default: return "medium";
        }
     }
}
