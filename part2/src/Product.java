public class Product {
    enum Category{
        Kids,
        Electricity,
        Office,
        Clothing
    }
    private Category category;
    private String name;
    private double price;
    private boolean isSpecial; //extra cost, enable special packaging option?

    public void setCategory(int category) {
        switch(category){
            case 1:
                this.category = Category.Kids;
            case 2:
                this.category = Category.Electricity;
            case 3:
                this.category = Category.Office;
            case 4:
                this.category = Category.Clothing;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSpecial(boolean special) {
        this.isSpecial = special;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (this.isSpecial){
            return("Product details:\n" + this.name + ", " + this.category + ".\n" + "Price: " + this.price + ". No special packaging.");
        }
        return("Product details:\n" + this.name + ", " + this.category + ".\n" + "Price: " + this.price + ". Has special packaging.");
    }
}
