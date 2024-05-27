import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private HashMap<Seller, ArrayList<Product>> products = new HashMap<>();
    int price;

    public HashMap<Seller, ArrayList<Product>> getProducts() {
        return products;
    }
}
