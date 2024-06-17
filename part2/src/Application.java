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
        String newSeller;
        String password;
        while(true) {
            System.out.print("Seller name: ");
            newSeller = scanner.next();
            if (indexInArray(database, newSeller) != -1) {
                System.out.println("The username is already registered.");
            } else {
                System.out.print("Password: ");
                password = scanner.next();
                if(password == null){
                    System.out.println("The password can't be empty.");
                    continue;
                }
                Seller seller = new Seller(newSeller, password);
                database.addUser(seller);
                System.out.println("The seller " + newSeller + " has been registered.");
                return;
            }
        }
    }

    /**
     * Adds a customer to the customer HashSet.
     * @param scanner for the user input.
     */
    public static void option2(Database database, Scanner scanner){
        String newCustomer;
        String password;
        while(true) {
            System.out.print("Customer name: ");
            newCustomer = scanner.next();
            if (indexInArray(database, newCustomer) != -1) {
                System.out.println("The username is already registered.");
            } else {
                System.out.print("Password: ");
                password = scanner.next();
                if(password == null){
                    System.out.println("The password can't be empty.");
                    continue;
                }
                Customer customer = new Customer(newCustomer, password);
                database.addUser(customer);
                System.out.println("The customer " + newCustomer + " has been registered.");
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
        if(indexInArray(database, seller) != -1){
            Product product = new Product();
            System.out.print("Item name: ");
            String itemName = scanner.next();
            product.setName(itemName);
            System.out.print("Item price: ");
            double itemPrice = scanner.nextDouble();
            product.setPrice(itemPrice);
            ((Seller)database.getUsers()[indexInArray(database, seller)]).addProduct(product);
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
        Seller seller = new Seller("1", "1");
        printCustomerNames(database);
        System.out.print("Customer name: ");
        String customerName = scanner.next();
        if(indexInArray(database, customerName) != -1){
            printSellerNames(database);
            System.out.print("What seller is the purchase from? ");
            String sellerName = scanner.next();
            if(indexInArray(database, sellerName) != -1){
                for(User user: database.getUsers()){
                    if(user.getName().equals(sellerName) && (user instanceof Seller)){
                        seller = (Seller)user;
                        seller.printProducts();
                        break;
                    }
                }
                System.out.print("Product for purchase (name): "); //add validation
                String productName = scanner.next();
                for(Product product: seller.getProducts()){
                    if(product.getName().equals(productName)){
                        ((Customer)database.getUsers()[indexInArray(database, customerName)]).addProductToCurrentOrder(product);
                        break;
                    }
                }
                System.out.println(sellerName + "'s product has been added to your cart, " + customerName + ".");
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
        System.out.print("Customer name? ");
        String customerName = scanner.next();
        if(indexInArray(database, customerName) != -1){
            ((Customer)database.getUsers()[indexInArray(database, customerName)]).
                    getOrderHistory().addToOrderHistory(((Customer)database.getUsers()[indexInArray(database, customerName)]).getCurrentOrder());
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

    public static int indexInArray(Database database, String name){
        int index = 0;
        for(User user: database.getUsers()){
            if(user == null){
                return (-1);
            }
            if(user.getName().equals(name)){
                return index;
            }
            index++;
        }
        return (-1);
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
