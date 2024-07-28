package DenisMogilevsky;

import java.util.NoSuchElementException;
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
        System.out.println("[8]: Show all products of given category.");
        System.out.println("[9]: Create cart from order history (Customer).");
        System.out.println("-----------------------------------");
    }

    /**
     * Adds a seller to the database.
     * @param database the database.
     * @param scanner for getting inputs.
     */
    public static void option1(Database database, Scanner scanner){
        while(true) {
            try {
                String name;
                String password;
                Seller seller;
                while (true) {
                    System.out.print("Seller name: ");
                    name = scanner.nextLine();
                    if (database.bIsUsernameTaken(name)) {
                        System.out.println("The username is already registered.");
                    } else {
                        System.out.print("Password: ");
                        password = scanner.next();
                        scanner.nextLine();
                        if (password == null) {
                            System.out.println("The password can't be empty.");
                            continue;
                        }
                        seller = new Seller(name, password);
                        database.addUser(seller);
                        System.out.println("The seller " + name + " has been registered.");
                        break;
                    }
                }
                break;
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Adds a customer.
     * @param scanner for the user input.
     */
    public static void option2(Database database, Scanner scanner){
        while(true) {
            try {
                String name;
                String password;
                Customer customer;
                String country;
                String city;
                String street;
                int building;
                while (true) {
                    System.out.print("Customer name: ");
                    name = scanner.nextLine();
                    if (database.bIsUsernameTaken(name)) {
                        System.out.println("The username is already registered.");
                    } else {
                        System.out.print("Password: ");
                        password = scanner.next(); //changed
                        scanner.nextLine();
                        if (password == null) {
                            System.out.println("The password can't be empty.");
                            continue;
                        }
                        customer = new Customer(name, password);
                        while (true) {
                            System.out.print("Country of residence: ");
                            country = scanner.nextLine();
                            if (customer.getAddress().setCountryName(country)) {
                                break;
                            }
                            System.out.println("Invalid country name.");
                        }
                        while (true) {
                            System.out.print("City of residence: ");
                            city = scanner.nextLine();
                            if (customer.getAddress().setCityName(city)) {
                                break;
                            }
                            System.out.println("Invalid city name.");
                        }
                        while (true) {
                            System.out.print("Street name: ");
                            street = scanner.nextLine();
                            if (customer.getAddress().setStreetName(street)) {
                                break;
                            }
                            System.out.println("Invalid street name.");
                        }
                        while (true) {
                            System.out.print("Building number: ");
                            building = scanner.nextInt();
                            scanner.nextLine();
                            if (customer.getAddress().setBuildingNumber(building)) {
                                break;
                            }
                            System.out.println("Invalid building number.");
                        }
                        database.addUser(customer);
                        System.out.println("The customer " + name + " has been registered.");
                        break;
                    }
                }
                break;
            } catch (NoSuchElementException | IllegalStateException e) {
                scanner.nextLine();
                System.out.println("Illegal input.");
            }
        }
    }

    /**
     * Adds an item to a seller's item collection.
     * @param scanner for the user input.
     */
    public static void option3(Database database, Scanner scanner){
        if(!database.checkSeller()){
            System.out.println("A seller must exist for this function to be used.");
            return;
        }
        while(true) {
            try {
                database.printSellerNames();
                System.out.print("Seller name? ");
                String seller = scanner.nextLine(); //changed
                if (database.sellerIndexByName(seller) != -1) {
                    Product product = new Product();
                    System.out.print("Item name: ");
                    String itemName = scanner.nextLine();
                    product.setName(itemName);
                    System.out.print("Item price: ");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine();
                    product.setPrice(itemPrice);
                    System.out.print("Category: ");
                    String categoryName = scanner.next();
                    scanner.nextLine();
                    eCategory category = chooseCategory(categoryName);
                    if (category != null) {
                        product.setCategory(category);
                        System.out.println("Special packaging? (yes/no)");
                        String specialPackaging = scanner.next();
                        scanner.nextLine();
                        if (specialPackaging.equalsIgnoreCase("yes")) {
                            WrappableProduct wrappableProduct = new WrappableProduct(product);
                            System.out.println("Special packaging cost?");
                            double wrapCost = scanner.nextDouble();
                            scanner.nextLine();
                            wrappableProduct.setSpecialPackageCost(wrapCost);
                            database.getSellerByName(seller).addProduct(wrappableProduct);
                            System.out.println("The item has been added successfully.");
                        } else if (specialPackaging.equalsIgnoreCase("no")) {
                            database.getSellerByName(seller).addProduct(product);
                            System.out.println("The item has been added successfully.");
                        } else {
                            System.out.println("This is not a valid option.");
                        }
                    } else {
                        System.out.println("The category doesn't appear in the system.");
                    }
                } else {
                    System.out.println("The seller isn't registered in the system.");
                }
                break;
            } catch (NoSuchElementException | IllegalStateException scannerException) {
                System.out.println("Invalid input.");
            } catch (IllegalArgumentException wrapException){
                System.out.println(wrapException);
            }
        }
    }

    /**
     * Adds an item to a customer's cart.
     * @param scanner for the user input.
     */
    public static void option4(Database database, Scanner scanner){
        if(!database.checkCustomer() || !database.checkSellerWithProduct()){
            System.out.println("A customer and a seller with items must exist for this function to be used.");
            return;
        }
        while(true) {
            try {
                Seller seller = null;
                Customer customer = null;
                Product product = null;
                database.printCustomerNames();
                System.out.print("Customer name: ");
                String customerName = scanner.nextLine();
                customer = database.getCustomerByName(customerName);
                if (customer != null) {
                    database.printSellerNames();
                    System.out.print("What seller is the purchase from? ");
                    String sellerName = scanner.nextLine();
                    seller = database.getSellerByName(sellerName);
                    if (seller != null) {
                        seller = database.getSellerByName(sellerName);
                        seller.printProducts();
                        System.out.print("Product for purchase (name): "); //add validation
                        String productName = scanner.nextLine();
                        product = seller.getProductByName(productName);
                        if (product != null) {
                            if (product instanceof WrappableProduct) {
                                System.out.println("Do you want the product wrapped? (yes/no)");
                                String doWrap = scanner.next();
                                scanner.nextLine();
                                if (doWrap.equalsIgnoreCase("yes")) {
                                    ((WrappableProduct) product).wrapProduct();
                                    customer.addProductToCurrentOrder(product);
                                    System.out.println("The product has been added to the cart.");
                                } else if (doWrap.equalsIgnoreCase("no")) {
                                    //no special wrap added, treated like regular product.
                                    customer.addProductToCurrentOrder(product);
                                    System.out.println("The product has been added to the cart.");
                                } else {
                                    System.out.println("Not a valid option.");
                                }
                            } else {    // the product is not wrappable.
                                customer.addProductToCurrentOrder(product);
                                System.out.println("The product has been added to the cart.");
                            }
                        } else {
                            System.out.println("The product isn't sold by the seller.");
                        }
                    } else {
                        System.out.println("The seller isn't registered in the system.");
                    }
                } else {
                    System.out.println("The customer isn't registered in the system.");
                }
                break;
            } catch (NoSuchElementException | IllegalStateException e) {
                scanner.nextLine();
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Finalizes a purchase for a customer.
     * @param scanner for the user input.
     */
    public static void option5(Database database, Scanner scanner){
        if(!database.checkCustomer()){
            System.out.println("A customer must exist for this function to be used.");
            return;
        }
        while(true) {
            try {
                database.printCustomerNames();
                Customer customer = null;
                System.out.print("Customer name? ");
                String customerName = scanner.nextLine();
                customer = database.getCustomerByName(customerName);
                if (customer != null) {
                    System.out.println("Order total: " + customer.getCurrentOrder().getPrice() + ".");
                    customer.currentOrderToHistory();
                    System.out.println("Thank you for the purchase, " + customerName + ".");
                    break;
                } else {
                    System.out.println("The customer isn't registered in the system.");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                scanner.nextLine();
                System.out.println("Invalid input.");
            } catch(IllegalArgumentException emptyCartException){
                System.out.println(emptyCartException);
            }
        }
    }

    /**
     * Prints all customer details (User details, current order, and order history).
     * @param database the database.
     */
    public static void option6(Database database){
        if(!database.checkCustomer()){
            System.out.println("A customer must exist for this function to be used.");
            return;
        }
        for (Customer customer : database.getCustomersByLexicographicalOrder()) {
            if (customer == null) {
                return;
            }
            System.out.println("Customer name: " + customer.getName() + ".");
            System.out.println(customer.getAddress().toString());
            System.out.println("Current order:");
            customer.getCurrentOrder().printOrder();
            System.out.println("Order history:");
            customer.getOrderHistory().printOrderHistory();
        }
    }

    /**
     * Prints all seller details (User details, the products being sold and their details).
     * @param database the database.
     */
    public static void option7(Database database){
        if(!database.checkSeller()){
            System.out.println("A seller must exist for this function to be used.");
            return;
        }
        for(Seller seller: database.getSellersByProductAmount()){
            if(seller == null){
                return;
            }
            System.out.println("Seller name: " + seller.getName() + ".");
            seller.printProducts();
        }
    }

    /**
     * Prints all products of a certain type.
     * @param database the database.
     */
    public static void option8(Database database, Scanner scanner){
        while(true) {
            try {
                System.out.print("Category name? ");
                String category = scanner.next();
                scanner.nextLine();
                category = category.toUpperCase();
                Product[] productsOfCategory = null;
                boolean bCategoryFound = true;
                switch (category) {
                    case "KIDS":
                        System.out.println("All products from the kids category:");
                        productsOfCategory = database.getProductsOfCategory(eCategory.Kids);
                        break;
                    case "CLOTHING":
                        System.out.println("All products from the clothing category:");
                        productsOfCategory = database.getProductsOfCategory(eCategory.Clothing);
                        break;
                    case "ELECTRICITY":
                        System.out.println("All products from the electricity category:");
                        productsOfCategory = database.getProductsOfCategory(eCategory.Electricity);
                        break;
                    case "OFFICE":
                        System.out.println("All products from the office category:");
                        productsOfCategory = database.getProductsOfCategory(eCategory.Office);
                        break;
                    default:
                        System.out.println("The category doesn't appear in the system.");
                        bCategoryFound = false;
                }
                if (bCategoryFound) {
                    for (Product product : productsOfCategory) {
                        if (product != null) {
                            System.out.println(product);
                        }
                    }
                    break;
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                scanner.nextLine();
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * Creates an order from one of the orders from the order history.
     * if there are products in the current order, the user is asked whether they want to switch it.
     * @param database the database.
     */
    public static void option9(Database database, Scanner scanner){
        if(!database.checkCustomer()){
            System.out.println("A customer must exist for this function to be used.");
            return;
        }
        while(true) {
            try {
                database.printCustomerNames();
                System.out.print("Which customer would you like to choose cart from order history for? ");
                String customerName = scanner.nextLine();
                Customer customer = database.getCustomerByName(customerName);
                if (customer != null) {
                    if(customer.getOrderHistory().getCurrentOrderHistoryIndex() == 0){
                        System.out.println("The customer hasn't ordered yet.");
                        continue;
                    }
                    if (customer.getCurrentOrder().getCurrentProductIndex() != 0) {
                        System.out.println("There are currently items in the cart.");
                        System.out.print("Are you sure you want to replace the current cart? (yes/no) ");
                        String doReplace = scanner.next();
                        scanner.nextLine();
                        if (doReplace.equalsIgnoreCase("no")) {
                            break;
                        } else if (!doReplace.equalsIgnoreCase("yes")) {
                            System.out.println("Invalid input.");
                            continue;
                        }
                    }
                    customer.getOrderHistory().printOrderHistory();
                    System.out.print("Which order would you like to repurchase? ");
                    int orderNumber = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        customer.orderHistoryToCurrentOrder(orderNumber - 1);
                        System.out.println("The order has been added to cart.");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }
                } else {
                    System.out.println("The customer isn't registered in the system.");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                scanner.nextLine();
                System.out.println("Invalid input.");
            }
        }
    }

    public static eCategory chooseCategory(String string){
        string = string.toUpperCase();
        switch(string){
            case "KIDS":
                return eCategory.Kids;
            case "CLOTHING":
                return eCategory.Clothing;
            case "ELECTRICITY":
                return eCategory.Electricity;
            case "OFFICE":
                return eCategory.Office;
            default:
                System.out.println("The category doesn't appear in the system.");
                return null;
        }
    }

    /**
     * acts as a menu for a user. Each choice is realised in a relevant method.
     * @param args irrelevant to the program. Will be empty.
     */
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                printMenu();
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
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
                    case 8:
                        option8(database, scanner);
                        break;
                    case 9:
                        option9(database, scanner);
                        break;
                    default:
                        System.out.println("Invalid option chosen.");
                }
            } catch (NoSuchElementException | IllegalStateException e) {
                System.out.println("Invalid input.");
            }
        }
    }
}
