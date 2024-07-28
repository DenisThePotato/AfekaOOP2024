package DenisMogilevsky;

public class Seller extends User implements ResizableArrayUtility<Product>{
    private Product[] products = new Product[2];
    int currentProductIndex = 0;

    public Seller(){}

    public Seller(String name, String password){
        this.name = name;
        this.password = password;
    }

    public Seller(Seller seller){
        this.name = seller.name;
        this.password = seller.password;
        this.currentProductIndex = seller.currentProductIndex;
        Product[] newProducts = new Product[seller.products.length];
        for(int i = 0; i < newProducts.length; i++){
            if(seller.products[i] == null){
                break;
            }
            newProducts[i] = new Product(seller.products[i]);
        }
        this.products = newProducts;
    }

    public void addProduct(Product product) {
        if(bIsArrayFull(products)){
            products = doubleArraySize(products);
        }
        products[currentProductIndex++] = product;
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

    public int getCurrentProductIndex() {
        return currentProductIndex;
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

    public Product[] getProductsOfCategory(eCategory category){
        if(currentProductIndex == 0){
            return null;
        }
        Product[] eProducts = new Product[currentProductIndex];
        int currentIndex = 0;
        for(int i = 0; i < currentProductIndex; i++){
            if(products[i] == null){
                break;
            }
            if(products[i].getCategory() == category){
                eProducts[currentIndex++] = products[i];
            }
        }
        if(currentIndex == 0){
            return null;
        }
        Product[] exactSizeProducts = new Product[currentIndex];
        System.arraycopy(eProducts, 0, exactSizeProducts, 0, currentIndex);
        return exactSizeProducts;
    }
}
