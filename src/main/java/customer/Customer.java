package customer;

import com.google.common.base.Preconditions;
import plants.Plant;
import florist.PriceList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {

    private String name;
    private double cash;
    private ShoppingCart shoppingCart;

    public Customer(String name, int cash) {
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(cash > 0);

        this.name = name;
        this.cash = cash;
        this.shoppingCart = new ShoppingCart(this);
    }

    public double getCash() {
        return this.cash;
    }

    public String getName() {
        return this.name;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void get(Plant plant) {
        shoppingCart.add(plant);
    }

    public void pay() {
        /*
        * Info: Sortowanie jest wymagane ponieważ w innym przypadku, gdy w pierwszej kolejności dodamy pozycję o mniejszej wartości, może
        * ona wykluczyć możliwość kupna bardziej wartościowej pozycji, która byłaby możliwa do kupna, gdyby nie kupić tańszego produktu.
        * Sprawdź: Koszyk Stefana
        * */
        List<Plant> listOfItems = shoppingCart.getItems();
        removeInvalidPositions(listOfItems);
        shoppingCart.setItems(listOfItems = sortItemsByValue(listOfItems));

        for(int i = 0; i < listOfItems.size(); i ++) {
            Plant item = listOfItems.get(i);
            double price = item.getPrice();
            if(price > cash) {
                listOfItems.remove(item);
                i --;
            } else {
                this.cash -= price;
            }
        }
    }

    public void pack(Box box) {
        box.addAll(shoppingCart);
    }

    private void removeInvalidPositions(List<Plant> listOfItems) {
        listOfItems.removeIf(item -> PriceList.getInstance().get(item.getName()) == PriceList.DEFAULT_PRICE_FOR_INVALID_POSITIONS);
    }

    private List<Plant> sortItemsByValue(List<Plant> listOfItems) {
        return listOfItems.stream().sorted(Comparator.comparing(Plant::getPrice).reversed()).collect(Collectors.toList());
    }
}
