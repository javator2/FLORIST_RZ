/*
*   Info do zadania dot. dodawania nowych pozycji:
*   - dodanie nowej pozycji polega na dodaniu nowej klasy do pakietu 'plants' o nazwie rośliny z wymaganym konstruktorem (sprawdź
*       którąś z istniejących pozycji). Klasa musi rozszerzać abstrakcyjną klasę Plant.
*   - aby dodać pozycję do cennika, należy dodać odpowiednią pozycję do klasy 'Florist' w pakiecie 'florist'
* */


import com.google.common.base.Preconditions;
import customer.Box;
import customer.Customer;
import customer.ShoppingCart;
import florist.Florist;
import florist.PriceList;
import plants.*;

import java.util.List;
import java.util.stream.Collectors;

public class FloristsTest {

    public static void main(String[] args) {
        Florist kwiaciarnia = new Florist();

        Customer janek = new Customer("Janek", 200);

        janek.get(new Rose(5));
        janek.get(new Peony(5));
        janek.get(new Freesia(3));
        janek.get(new Lilac(3));

        ShoppingCart wozekJanka = janek.getShoppingCart();
        System.out.println("Przed płaceniem\n" + wozekJanka);

        janek.pay();
        System.out.println("Po zapłaceniu:\n" + wozekJanka);

        System.out.println("Jankowi zostało: " + janek.getCash() + " zł");

        Box pudelkoJanka = new Box(janek);
        janek.pack(pudelkoJanka);

        System.out.println("Po zapakowaniu do pudełka\n" + janek.getShoppingCart());
        System.out.println(pudelkoJanka);

        System.out.println("Czerwone kwiaty w pudełku Janka kosztowały: " + valueOf(pudelkoJanka, "czerwony"));

        Customer stefan = new Customer("Stefan", 60);

        stefan.get(new Lilac(3));
        stefan.get(new Rose(5));

        System.out.println(stefan.getShoppingCart());

        stefan.pay();
        Box pudelkoStefana = new Box(stefan);
        stefan.pack(pudelkoStefana);
        System.out.println(pudelkoStefana);
        System.out.println("Stefanowi zostało " + stefan.getCash() + " zł");

    }

    private static double valueOf(Box box, String color) {
        Preconditions.checkNotNull(box);
        Preconditions.checkNotNull(color);
        List<Plant> plantList = box.getItems().stream().filter(item -> Colors.getColorByClass(item.getClass()).equals(color)).collect(Collectors.toList());
        return plantList.stream().mapToDouble(item -> item.getAmount() * PriceList.getInstance().get(item.getName())).sum();
    }

}
