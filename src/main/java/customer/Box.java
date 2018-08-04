package customer;

import com.google.common.base.Preconditions;
import florist.PriceList;
import plants.Colors;
import plants.Plant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Box {
    private Customer owner;
    private List<Plant> listOfPlants = new ArrayList<>();

    public Box(Customer customer) {
        Preconditions.checkNotNull(customer);
        this.owner = customer;
    }

    void addAll(ShoppingCart shoppingCart) {
        /*
        * Sortowanie po nazwie, ze względu na wymogi zadania pod względem przepakowania produktów do pudełka.
        * Sprawdź: Janek - wózek przed płaceniem i Janek - pudełko po przepakowaniu.
        * */
        this.listOfPlants = new ArrayList<>(shoppingCart.getItems().stream().sorted(Comparator.comparing(Plant::getName)).collect(Collectors.toList()));
        shoppingCart.clear();
    }

    public List<Plant> getItems() {
        return this.listOfPlants;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Pudełko właściciel %s", owner.getName()));
        if(listOfPlants.size() > 0) {
            listOfPlants.forEach(plant -> builder.append(String.format("%n%s, kolor: %s, ilość: %d, cena: %.1f", plant.getName(), Colors.getColorByClass(plant.getClass()), plant.getAmount(), PriceList.getInstance().get(plant.getName()))));
        } else builder.append(" -- pusto");
        return builder.toString();
    }
}
