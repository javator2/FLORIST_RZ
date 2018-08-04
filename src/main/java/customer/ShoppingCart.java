package customer;

import plants.Colors;
import plants.Plant;
import florist.PriceList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Plant> listOfPlants = new ArrayList<>();
    private Customer owner;

    ShoppingCart(Customer customer) {
        this.owner = customer;
    }

    public void add(Plant plant) {
        if(listOfPlants.contains(plant)) {
            plant.setAmount(listOfPlants.get(listOfPlants.indexOf(plant)).getAmount() + plant.getAmount());
        } else {
            listOfPlants.add(plant);
        }
    }

    List<Plant> getItems() {
        return this.listOfPlants;
    }

    void setItems(List<Plant> listOfPlants) {
        this.listOfPlants = listOfPlants;
    }

    void clear() {
        listOfPlants.clear();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Wózek właściciel %s", owner.getName()));
        if(listOfPlants.size() > 0) {
            listOfPlants.forEach(plant -> builder.append(String.format("%n%s, kolor: %s, ilość: %d, cena: %.1f", plant.getName(), Colors.getColorByClass(plant.getClass()), plant.getAmount(), PriceList.getInstance().get(plant.getName()))));
        } else builder.append(" -- pusto");
        return builder.toString();
    }
}
