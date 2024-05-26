import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //maybe add a regex later for the scanner (both for the int input and string)
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        String current;
        while(true){
            System.out.println("-----------------------------------");
            System.out.println("[1]: Add a seller.");
            System.out.println("[2]: Add a customer.");
            System.out.println("[3]: Add a product (seller).");
            System.out.println("[4]: Add a product (customer).");
            System.out.println("[5]: Finalize purchase (customer).");
            System.out.println("[6]: Show customer details.");
            System.out.println("[7]: Show seller details.");
            System.out.println("-----------------------------------");
            switch(scanner.nextInt()){
                case 0:
                    break;
                case 1:
                    System.out.print("Input the seller's name: ");
                    current = scanner.next();
                    if (database.getSellers().containsKey(current)){
                        System.out.println("Seller is already in the database.");
                        continue;
                    }
                    Seller newSeller = new Seller();
                    database.getSellers().put(current, newSeller);
                case 2:
                    System.out.print("Input the customer's name: ");
                    current = scanner.next();
                    if (database.getCustomers().containsKey(current)){
                        System.out.println("Customer is already in the database.");
                        continue;
                    }
                    Customer newCustomer = new Customer();
                    database.getCustomers().put(current, newCustomer);
                case 3:

            }
        }
    }
}
