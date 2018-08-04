package florist;

public class Florist {

    private PriceList pl = PriceList.getInstance();

    public Florist() {
        pl.set("róża", 10);
        pl.set("bez", 12);
        pl.set("piwonia", 8);
    }

    @Override
    public String toString() {
        return pl.toString();
    }
}
