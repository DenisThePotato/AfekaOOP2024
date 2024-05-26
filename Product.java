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
        isSpecial = special;
    }

    public String getName() {
        return this.name;
    }
}
