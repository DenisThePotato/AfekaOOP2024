import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Order implements ResizableArrayUtility {
    private Product[] products = new Product[2];
    double price;
    LocalDate purchaseDate;

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate() {
        this.purchaseDate = LocalDate.now();
    }

    public void addProduct(Product product) {
        price += product.getPrice();
        if(products[products.length - 1] != null){
            ResizableArrayUtility.doubleArraySize(products);
        }
        for(Product currentProduct: products){
            if (currentProduct == null){
                currentProduct = product;
            }
            break;
        }
    }

    public void printOrder(){
        int index = 1;
        for(Product product: products){
            if(product == null){
                return;
            }
            System.out.println("   Product " + index + ": " + product + ".");
            index++;
        }
    }
}
