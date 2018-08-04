package plants;

public class Lilac extends Plant {

    public Lilac(int amount) {
        super ("bez", amount);
        Colors.check("biały", this.getClass());
    }
}
