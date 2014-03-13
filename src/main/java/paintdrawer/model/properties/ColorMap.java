package paintdrawer.model.properties;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.List;

/**
 * Automaticly generates all available colors from Javas Color class
 * Borrowed from
 * http://www.dreamincode.net/forums/topic/185212-colorgetcolorblue-gives-null-pointer-exception/page__view__findpost__p__1086449?s=3e90809e3dea4094f24c439c860a0ebe
 */
public class ColorMap
{
    private final Map<String, Color> colorMap;
    private Color color;

    public ColorMap(Class<?> classWithColorFields)
    {
        colorMap = Collections.unmodifiableMap(populateColourMap(classWithColorFields));
    }

    public List<String> getColorLabels()
    {
        List<String> labels = new ArrayList<String>();
        Iterator it = colorMap.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            labels.add((String)pairs.getKey());
        }

        return labels;
    }

    public Map<String, Color> getAllColours() { return colorMap; }
    public Color getColor(String key) { return colorMap.get(key); }

    private static String camelize(String s)
    {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private Map<String,Color> populateColourMap(Class<?> classWithColorFields) {
        Map<String,Color> colourMap = new HashMap<String,Color>();
        for(Field f : classWithColorFields.getFields()) {
            if (isPublicStaticFinalColor(f)) {
                try {
                    String name = f.getName();
                    if (name.compareTo(name.toUpperCase()) == 0) {
                        colourMap.put(camelize(name), (Color)f.get(null));
                    }
                }
                catch (IllegalArgumentException e) {assert false : f;}
                catch (IllegalAccessException e)   {assert false : f;}
            }
        }
        return colourMap;
    }

    private static boolean isPublicStaticFinalColor(Field f)
    {
        int modifiers = f.getModifiers();
        return
                Modifier.isPublic(modifiers) &&
                        Modifier.isStatic(modifiers) &&
                        Modifier.isFinal(modifiers) &&
                        isColor(f.getType());
    }

    private static boolean isColor(Class<?> c)
    {
        if (c == null) {return false;}
        if (c.equals(Color.class)) {return true;}
        return isColor(c.getSuperclass());
    }
}
