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
}
