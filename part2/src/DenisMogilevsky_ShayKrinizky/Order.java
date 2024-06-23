package DenisMogilevsky_ShayKrinizky;

import java.time.LocalDate;

public class Order implements ResizableArrayUtility<Product> {
    private Product[] products = new Product[2];
    private int currentProductIndex = 0;
    private double price = 0;
    private LocalDate purchaseDate;

    public double getPrice() {
        return price;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate() {
        this.purchaseDate = LocalDate.now();
    }

    public void addProduct(Product product) {
        price += product.getPrice();
        if(bIsArrayFull(products)){
            products = doubleArraySize(products);
        }
        products[currentProductIndex] = product;
        currentProductIndex++;
    }

    public void printOrder(){
        int index = 1;
        for(Product product: products){
            if(product == null){
                return;
            }
            System.out.println("   Product " + index + ": " + product);
            index++;
        }
    }

    @Override
    public boolean bIsArrayFull(Product[] arr) {
        return (currentProductIndex >= products.length);
    }

    @Override
    public Product[] doubleArraySize(Product[] arr) {
        Product[] newArr = new Product[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }
}
