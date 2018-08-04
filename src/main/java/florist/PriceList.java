package florist;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

public class PriceList {

    private Map<String, Double> priceMap = new HashMap<>();
    public static final double DEFAULT_PRICE_FOR_INVALID_POSITIONS = -1.0;


    public void set(String name, double price) {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(price > 0);
        priceMap.put(name, price);
    }

    public double get(String name) {
        return priceMap.getOrDefault(name, DEFAULT_PRICE_FOR_INVALID_POSITIONS);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        priceMap.forEach((key, value) -> builder.append(String.format("%s: %.2f%n", key, value)));
        return builder.toString();
    }

    //-----------------------------------------
    // Singleton
    //-----------------------------------------
    private static final PriceList priceList = new PriceList();

    private PriceList() {}

    public static PriceList getInstance() {
        return PriceList.priceList;
    }
    //-----------------------------------------
}
