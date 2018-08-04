package plants;

import com.google.common.base.Preconditions;
import florist.PriceList;

public abstract class Plant {

    private String name;
    private int amount;

    Plant(String name, int amount) {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(amount > 0);

        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int newAmount) {
        Preconditions.checkArgument(newAmount > 0);
        amount = newAmount;
    }

    public double getPrice() {
        double price = PriceList.getInstance().get(name);
        return this.amount * (price == PriceList.DEFAULT_PRICE_FOR_INVALID_POSITIONS ? 0 : price);
    }
}
