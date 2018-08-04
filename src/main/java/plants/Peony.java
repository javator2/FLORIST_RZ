package plants;

public class Peony extends Plant {

    public Peony(int amount) {
        super ("piwonia", amount);
        Colors.check("czerwony", this.getClass());
    }
}
