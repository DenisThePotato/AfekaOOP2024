package DenisMogilevsky;

public class Product {
    protected eCategory category;
    protected String name;
    protected double price;
    protected final int serialNumber;
    protected static int currentSerialNumber = 0;

    public Product(){
        this.serialNumber = currentSerialNumber++;
    }

    public Product(Product product) {
        this.category = product.category;
        this.name = product.name;
        this.price = product.price;
        this.serialNumber = product.serialNumber;
    }

    public Product deepClone(){
        return new Product(this);
    }

    public void setCategory(eCategory category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return(name + ". Price: " + price + "$. Category: " + category.toString() + ". SN: " + serialNumber + ".");
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public eCategory getCategory() {
        return category;
    }
}
