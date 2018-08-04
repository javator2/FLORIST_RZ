package plants;

public class Rose extends Plant {

    public Rose(int amount) {
        super ("róża", amount);
        Colors.check("czerwony", this.getClass());
    }
}
