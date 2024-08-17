import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static String[] sellers = new String[2];
    static int sellersCurrentIndex = 0;
    static String[] customers = new String[2];
    static int customersCurrentIndex = 0;

    /**
     * Checks if an array is full.
     * @param arr array to check the size of.
     * @param index current index of array being used.
     * @return true if the array is full, and false otherwise.
     */
    public static boolean bIsArrayFull(String[] arr, int index){
        if(index == (arr.length - 1)){
            return true;
        }
        return false;
    }

    /**
     * doubles an array's size.
     * @param arr array to increase the size of.
     * @return the array with increased size.
     */
    public static String[] doubleArraySize(String[] arr){
        String[] newArr = new String[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    /**
     * Checks if an element is in an array.
     * @param arr array in which the element is searched for.
     * @param check string which is searched for.
     * @return the index of the element in the array, -1 if not found.
     */
    public static int elementIndex(String[] arr, String check){
        for(int i = 0; i < arr.length; i++){
            if(Objects.equals(arr[i], check)){
                return i;
            }
        }
        return -1;
    }

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
        if(elementIndex(sellers, newSeller) != -1){
            System.out.println("The seller is already registered.");
        } else {
            sellers[sellersCurrentIndex] = newSeller;
            sellersCurrentIndex++;
            System.out.println("The seller " + newSeller + " has been registered.");
        }
    }

    /**
     * Adds a customer to the customer HashSet.
     * @param scanner for the user input.
     */
    public static void option2(Scanner scanner){
        System.out.print("Customer's name? ");
        String newCustomer = scanner.next();
        if(elementIndex(customers, newCustomer) != -1){
            System.out.println("The customer is already registered.");
        } else {
            customers[customersCurrentIndex] = newCustomer;
            customersCurrentIndex++;
            System.out.println("The customer " + newCustomer + " has been registered.");
        }
    }

    /**
     * Adds an item to a seller's item collection.
     * @param scanner for the user input.
     */
    public static void option3(Scanner scanner){
        System.out.print("Seller's name? ");
        String newSeller = scanner.next();
        if(elementIndex(sellers, newSeller) != -1){
            System.out.print("Item name: ");
            String itemName = scanner.next();
            System.out.print("Item price: ");
            double itemPrice = scanner.nextDouble();
            System.out.print("Item category: ");
            String itemCategory = scanner.next();
            System.out.println("The item " + itemName + " has been added to the " + itemCategory + " category.");
            System.out.println("It's price is " + itemPrice + "$.");
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
        if(elementIndex(customers, customerName) != -1){
            System.out.print("What seller is the purchase from? ");
            String sellerName = scanner.next();
            if(elementIndex(sellers, sellerName) != -1){
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
    public static void option5(Scanner scanner){
        System.out.print("Customer name? ");
        String customerName = scanner.next();
        if(elementIndex(customers, customerName) != -1){
            System.out.println("Thank you for the purchase, " + customerName + ".");
        } else {
            System.out.println("The customer isn't registered in the system.");
        }
    }

    /**
     * Prints all seller names.
     */
    public static void option6(){
        for(String customer: customers){
            if(customer == null){
                return;
            }
            System.out.println(customer);
        }
    }

    /**
     * Prints all customer names.
     */
    public static void option7(){
        for(String seller: sellers){
            if(seller == null){
                return;
            }
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
                    if(bIsArrayFull(sellers, sellersCurrentIndex)){
                        sellers = doubleArraySize(sellers);
                    }
                    option1(scanner);
                    break;
                case 2:
                    if(bIsArrayFull(customers, customersCurrentIndex)){
                        customers = doubleArraySize(customers);
                    }
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
