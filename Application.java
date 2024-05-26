import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void printMenu(){
        System.out.println("-----------------------------------");
        System.out.println("[1]: Add a seller.");
        System.out.println("[2]: Add a customer.");
        System.out.println("[3]: Add a product (seller).");
        System.out.println("[4]: Add a product (customer).");
        System.out.println("[5]: Finalize purchase (customer).");
        System.out.println("[6]: Show customer details.");
        System.out.println("[7]: Show seller details.");
        System.out.println("-----------------------------------");
    }
    public static void option1(Database database, Scanner scanner){
        System.out.print("Input the seller's name: ");
        if (database.getSellers().containsKey(scanner.next())){
            System.out.println("Seller is already in the database.");
        }
        Seller newSeller = new Seller();
        database.getSellers().put(scanner.next(), newSeller);
    }
    public static void option2(Database database, Scanner scanner){
        System.out.print("Input the customer's name: ");
        if (database.getCustomers().containsKey(scanner.next())){
            System.out.println("Customer is already in the database.");
        }
        Customer newCustomer = new Customer();
        database.getCustomers().put(scanner.next(), newCustomer);
    }
    public static void option3(Database database, Scanner scanner){
        System.out.print("Seller name: ");
        if (database.getSellers().containsKey(scanner.next())){
            Product newProduct = new Product();
            System.out.print("Product name: ");
            newProduct.setName(scanner.next());
            System.out.println("Product price: ");
            newProduct.setPrice(scanner.nextInt());
            System.out.println("Product category: Kids(0), Electricity(1), Office(2), Clothing(3)");
            newProduct.setCategory(scanner.nextInt());
            System.out.println("Does the product require special wrapping(y / n)?");
            if (scanner.next().equals("y")){
                newProduct.setSpecial(true);
            } else {
                newProduct.setSpecial(false);
            }
        } else {
            System.out.println("The Seller isn't registered in the system.");
        }
    }
    public static void option4(Database database, Scanner scanner){
        String seller;
        System.out.print("Customer name: ");
        String customer = scanner.next();   // CUSTOMER NAME
        if (database.getCustomers().containsKey(customer)){
            System.out.println("Which seller would you like to make the purchase from? ");
            seller = scanner.next();    //SELLER NAME
            if (database.getSellers().containsKey(seller)){
                System.out.println("Which product would you like to purchase? ");
                String product = scanner.next();    //PRODUCT NAME
                if (database.getSellers().get(seller).getProducts().containsKey(product)){
                    database.getCustomers().get(customer).getCurrentOrder().getProducts().get(seller).add(product); //fix
                } else {
                    System.out.println("This product is not currently sold by the seller.");
                }
            } else {
                System.out.println("The seller isn't registered in the system.");
            }
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }
    public static void option5(Database database, Scanner scanner){
        System.out.print("Which customer is paying? ");
        String customer = scanner.next();
        if(database.getCustomers().containsKey(customer)){
            database.getCustomers().get(customer).currentOrderToHistory();
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }
    public static void option6(Database database, Scanner scanner){
        for (Map.Entry<String, Customer> entry : database.getCustomers().entrySet()) {
            System.out.println(entry.getKey());
        }
    }
    public static void option7(Database database, Scanner scanner){
        for (Map.Entry<String, Seller> entry : database.getSellers().entrySet()) {
            System.out.println(entry.getKey());
        }
    }
    public static void main(String[] args) {
        //maybe add a regex later for the scanner (both for the int input and string)
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            switch(scanner.nextInt()){
                case 0:
                    break;
                case 1:
                    option1(database, scanner);
                case 2:
                    option2(database, scanner);
                case 3:
                    option3(database, scanner);
                case 4:
                    option4(database, scanner);
                case 5:
                    option5(database, scanner);
                case 6:
                    option6(database, scanner);
                case 7:
                    option7(database, scanner);
            }
        }
    }
}
