import java.util.HashMap;

public class Seller extends User {
    private HashMap<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        this.products.put(product.getName(), product);
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }
}
