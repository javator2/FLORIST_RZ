package plants;

import com.google.common.base.Preconditions;

import java.util.*;

public class Colors {
    private static Map<String, List<Class>> colorsOfFlowers = new HashMap<>();

    static void check(String color, Class classIn) {
        Preconditions.checkNotNull(color);
        Preconditions.checkNotNull(classIn);

        if(colorsOfFlowers.containsKey(color)) {
            if(!colorsOfFlowers.get(color).contains(classIn)) {
                colorsOfFlowers.get(color).add(classIn);
            }
        } else {
            colorsOfFlowers.put(color, new ArrayList<>(Arrays.asList(classIn)));
        }
    }

    public static String getColorByClass(Class classIn) {
        final String[] color = new String[1];
        colorsOfFlowers.forEach((key, value) -> {
            if(value.contains(classIn)) {
                color[0] = key;
            }
        });
        return color[0];
    }

    public static List<Class> getListOfPlantsByColor(String color) {
        Preconditions.checkNotNull(color);
        return colorsOfFlowers.getOrDefault(color, null);
    }
}
