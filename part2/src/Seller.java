import java.util.HashMap;
import java.util.Objects;

public class Seller extends User implements ResizableArrayUtility {
    private Product[] products = new Product[2];

    public Seller(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void addProduct(Product product) {
        if(ResizableArrayUtility.bIsArrayFull(products)){
            ResizableArrayUtility.doubleArraySize(products);
        }
        for(Product currentProduct: products){
            if (currentProduct == null){
                currentProduct = product;
                break;
            }
        }
    }

    public Product[] getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void printProducts(){
        int index = 1;
        for(Product product: products){
            if(product == null){
                return;
            }
            System.out.println("Product " + index + ":");
            System.out.println(product.toString());
        }
    }
}
