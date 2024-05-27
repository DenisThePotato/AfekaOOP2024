//Denis Mogilevsky, 207270521 (Keren Kalif, Guy Isakov).
//Shay Krinizky, 212876114 ().
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static HashSet<String> sellers = new HashSet<>();
    static HashSet<String> customers = new HashSet<>();
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
    public static void option1(Scanner scanner){
        System.out.print("Seller's name? ");
        String newSeller = scanner.next();
        if(sellers.contains(newSeller)){
            System.out.println("The seller is already registered.");
        } else {
            sellers.add(newSeller);
        }
    }

    /**
     * Adds a customer to the customer HashSet.
     * @param scanner for the user input.
     */
    public static void option2(Scanner scanner){
        System.out.print("Customer's name? ");
        String newCustomer = scanner.next();
        if(customers.contains(newCustomer)){
            System.out.println("The customer is already registered.");
        } else {
            customers.add(newCustomer);
        }
    }

    /**
     * Adds an item to a seller's item collection.
     * @param scanner for the user input.
     */
    public static void option3(Scanner scanner){
        System.out.print("Seller's name? ");
        String newSeller = scanner.next();
        if(sellers.contains(newSeller)){
            System.out.print("Item name: ");
            String itemName = scanner.next();
            System.out.print("Item price: ");
            double itemPrice = scanner.nextDouble();
            System.out.print("Item category: ");
            String itemCategory = scanner.next();
        } else {
            System.out.println("The seller isn't registered in the system.");
        }
    }

    /**
     * Adds an item to a customer's cart.
     * @param scanner for the user input.
     */
    public static void option4(Scanner scanner){
        System.out.print("Customer's name? ");
        String customerName = scanner.next();
        if(customers.contains(customerName)){
            System.out.print("What seller is the purchase from?");
            String sellerName = scanner.next();
            if(sellers.contains(sellerName)){
                System.out.println("purchase by " + customerName + " from " + sellerName);
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
    public static void option5(Scanner scanner){
        System.out.print("Customer name? ");
        String customerName = scanner.next();
        if(customers.contains(customerName)){
            //purchase logic
            System.out.println("Thank you for the purchase.");
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }

    /**
     * Prints all seller names.
     */
    public static void option6(){
        for(String customer: customers){
            System.out.println(customer);
        }
    }

    /**
     * Prints all customer names.
     */
    public static void option7(){
        for(String seller: sellers){
            System.out.println(seller);
        }
    }

    /**
     * acts as a menu for a user. Each choice is realised in a relevant method.
     * @param args irrelevant to the program. Will be empty.
     */
    public static void main(String[] args) {
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
                    option1(scanner);
                    break;
                case 2:
                    option2(scanner);
                    break;
                case 3:
                    option3(scanner);
                    break;
                case 4:
                    option4(scanner);
                    break;
                case 5:
                    option5(scanner);
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                default:
                    System.out.println("Invalid option chosen.");
                    break;
            }
        }
    }
}