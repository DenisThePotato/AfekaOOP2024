package DenisMogilevsky_ShayKrinizky;
//Denis Mogilevsky, 207270521 (Lector: Keren, Practice: Guy).
//Shay Krinizky, 212876114 (Lector: Iris, Practice: Guy).
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

    /**
     * Adds a seller to the seller HashSet.
     * @param scanner for the user input.
     */
    public static void option1(Database database, Scanner scanner){
        String name;
        String password;
        Seller seller = null;
        while(true) {
            System.out.print("Seller name: ");
            name = scanner.next();
            if (database.bIsUsernameTaken(name)) {
                System.out.println("The username is already registered.");
            } else {
                System.out.print("Password: ");
                password = scanner.next();
                if(password == null){
                    System.out.println("The password can't be empty.");
                    continue;
                }
                seller = new Seller(name, password);
                database.addUser(seller);
                System.out.println("The seller " + name + " has been registered.");
                break;
            }
        }
    }

    /**
     * Adds a customer to the customer HashSet.
     * @param scanner for the user input.
     */
    public static void option2(Database database, Scanner scanner){
        String name;
        String password;
        Customer customer;
        String country;
        String city;
        String street;
        int building;
        while(true) {
            System.out.print("Customer name: ");
            name = scanner.next();
            if (database.bIsUsernameTaken(name)) {
                System.out.println("The username is already registered.");
            } else {
                System.out.print("Password: ");
                password = scanner.next();
                if(password == null){
                    System.out.println("The password can't be empty.");
                    continue;
                }
                customer = new Customer(name, password);
                while(true){
                    System.out.print("Country of residence: ");
                    country = scanner.next();
                    if(customer.getAddress().setCountryName(country)){
                        break;
                    }
                    System.out.println("Invalid country name.");
                }
                while(true){
                    System.out.print("City of residence: ");
                    city = scanner.next();
                    if(customer.getAddress().setCityName(city)){
                        break;
                    }
                    System.out.println("Invalid city name.");
                }
                while(true){
                    System.out.print("Street name: ");
                    street = scanner.next();
                    if(customer.getAddress().setStreetName(street)){
                        break;
                    }
                    System.out.println("Invalid street name.");
                }
                while(true){
                    System.out.print("Building number: ");
                    building = scanner.nextInt();
                    scanner.nextLine();
                    if(customer.getAddress().setBuildingNumber(building)){
                        break;
                    }
                    System.out.println("Invalid building number.");
                }
                database.addUser(customer);
                System.out.println("The customer " + name + " has been registered.");
                return;
            }
        }
    }

    /**
     * Adds an item to a seller's item collection.
     * @param scanner for the user input.
     */
    public static void option3(Database database, Scanner scanner){
        printSellerNames(database);
        System.out.print("Seller name? ");
        String seller = scanner.next();
        if(database.sellerIndexByName(seller) != -1){
            Product product = new Product();
            System.out.print("Item name: ");
            String itemName = scanner.next();
            product.setName(itemName);
            System.out.print("Item price: ");
            double itemPrice = scanner.nextDouble();
            product.setPrice(itemPrice);
            database.getSellerByName(seller).addProduct(product);
            System.out.print("The item " + itemName + " has been added successfully, ");
            System.out.println("with the price of " + itemPrice + "$.");
        } else {
            System.out.println("The seller isn't registered in the system.");
        }
    }

    /**
     * Adds an item to a customer's cart.
     * @param scanner for the user input.
     */
    public static void option4(Database database, Scanner scanner){
        Seller seller = null;
        Customer customer = null;
        Product product = null;
        printCustomerNames(database);
        System.out.print("Customer name: ");
        String customerName = scanner.next();
        customer = database.getCustomerByName(customerName);
        if(customer != null) {
            printSellerNames(database);
            System.out.print("What seller is the purchase from? ");
            String sellerName = scanner.next();
            seller = database.getSellerByName(sellerName);
            if(seller != null) {
                seller = database.getSellerByName(sellerName);
                seller.printProducts();
                System.out.print("Product for purchase (name): "); //add validation
                String productName = scanner.next();
                product = seller.getProductByName(productName);
                if(product != null){
                    customer.addProductToCurrentOrder(product);
                    System.out.println(sellerName + "'s product has been added to your cart, " + customerName + ".");
                } else {
                    System.out.println("The product isn't sold by the seller.");
                }
            } else {
                System.out.println("The seller isn't registered in the system.");
            }
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }

    /**
     * Finalizes a purchase for a customer.
     * @param scanner for the user input.
     */
    public static void option5(Database database, Scanner scanner){
        Customer customer = null;
        System.out.print("Customer name? ");
        String customerName = scanner.next();
        customer = database.getCustomerByName(customerName);
        if(customer != null){
            System.out.println("Order total: " + customer.getCurrentOrder().getPrice() + ".");
            customer.currentOrderToHistory();
            System.out.println("Thank you for the purchase, " + customerName + ".");
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }

    /**
     * Prints all customer details (User details, current order, and order history).
     * @param database the database.
     */
    public static void option6(Database database){
        for(User user: database.getUsers()){
            if(user == null){
                return;
            }
            if(user instanceof Customer){
                System.out.println("Customer name: " + user.getName() + ".");
                System.out.println(((Customer) user).getAddress().toString());
                System.out.println("Current order:");
                ((Customer) user).getCurrentOrder().printOrder();
                System.out.println("Order history:");
                ((Customer) user).getOrderHistory().printOrderHistory();
            }
        }
    }

    /**
     * Prints all seller details (User details, the products being sold and their details).
     * @param database the database.
     */
    public static void option7(Database database){
        for(User user: database.getUsers()){
            if(user == null){
                return;
            }
            if(user instanceof Seller){
                System.out.println("Seller name: " + user.getName() + ".");
                ((Seller) user).printProducts();
            }
        }
    }

    public static void printCustomerNames(Database database){
        System.out.println("Customer list:");
        for(User user: database.getUsers()){
            if(user == null){
                break;
            }
            if(user instanceof Customer){
                System.out.println(user.getName());
            }
        }
    }

    public static void printSellerNames(Database database){
        System.out.println("Seller list:");
        for(User user: database.getUsers()){
            if(user == null){
                break;
            }
            if(user instanceof Seller){
                System.out.println(user.getName());
            }
        }
    }

    /**
     * acts as a menu for a user. Each choice is realised in a relevant method.
     * @param args irrelevant to the program. Will be empty.
     */
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option){
                case 0:
                    System.out.println("Thank you for using our services,");
                    System.out.println("Have a pleasant day.");
                    return;
                case 1:
                    option1(database, scanner);
                    break;
                case 2:
                    option2(database, scanner);
                    break;
                case 3:
                    option3(database, scanner);
                    break;
                case 4:
                    option4(database, scanner);
                    break;
                case 5:
                    option5(database, scanner);
                    break;
                case 6:
                    option6(database);
                    break;
                case 7:
                    option7(database);
                    break;
                default:
                    System.out.println("Invalid option chosen.");
                    break;
            }
        }
    }
}
