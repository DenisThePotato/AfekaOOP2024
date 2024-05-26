import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database{
    private HashMap<String, Customer> customers;
    private HashMap<String, Seller> sellers;

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public HashMap<String, Seller> getSellers() {
        return sellers;
    }
}
