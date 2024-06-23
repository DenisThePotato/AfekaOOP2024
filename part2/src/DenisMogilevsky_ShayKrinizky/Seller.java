package DenisMogilevsky_ShayKrinizky;

public class Seller extends User implements ResizableArrayUtility<Product>{
    private Product[] products = new Product[2];
    int currentProductIndex = 0;

    public Seller(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void addProduct(Product product) {
        if(bIsArrayFull(products)){
            products = doubleArraySize(products);
        }
        products[currentProductIndex] = product;
        currentProductIndex++;
    }

    public Product[] getProducts() {
        return products;
    }

    public Product getProductByName(String name){
        for(Product product: products){
            if(product == null){
                break;
            }
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return(name + ", seller. Products: " + currentProductIndex + ".");
    }

    public void printProducts(){
        int index = 1;
        for(Product product: products){
            if(product == null){
                return;
            }
            System.out.println("Product " + index + ": " + product);
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
