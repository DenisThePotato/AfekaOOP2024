import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Customer extends User{
    private Address address;
    private Order currentOrder = new Order();
    private PurchaseHistory orderHistory = new PurchaseHistory();

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public PurchaseHistory getOrderHistory() {
        return orderHistory;
    }

    public void addProductToCurrentOrder(String seller, Product product){
        currentOrder.getProducts().get(product.getName()).add(product);
    }
    public void currentOrderToHistory(){
        for(Map.Entry<Seller, ArrayList<Product>> set : currentOrder.getProducts().entrySet()) {
            for(currentOrder)
        }
    }
}