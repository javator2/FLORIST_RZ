package plants;

public class Freesia extends Plant {

    public Freesia(int amount) {
        super ("frezja", amount);
        Colors.check("żółty", this.getClass());
    }
}
