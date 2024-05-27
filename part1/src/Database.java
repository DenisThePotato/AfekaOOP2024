import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database{
    private HashMap<String, Customer> customers = new HashMap<>();
    private HashMap<String, Seller> sellers = new HashMap<>();

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public HashMap<String, Seller> getSellers() {
        return sellers;
    }
}
