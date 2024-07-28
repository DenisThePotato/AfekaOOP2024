package DenisMogilevsky;

import java.util.Arrays;

public class Database implements ResizableArrayUtility<User> {
    private User[] users = new User[2];
    int currentUserIndex = 0;

    public User[] getUsers() {
        return users;
    }

    /**
     * returns an array of Seller type users.
     * @return array of sellers if there are any, else null.
     */
    public Seller[] getSellersByProductAmount(){
        Seller[] sellersTemp = new Seller[users.length];
        int currentIndex = 0;
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i] instanceof Seller){
                sellersTemp[currentIndex++] = (Seller)users[i];
            }
        }
        if(currentIndex == 0){  //no sellers.
            return null;
        }
        Seller[] exactSizeSellers = new Seller[currentIndex];
        System.arraycopy(sellersTemp, 0, exactSizeSellers, 0, currentIndex);
        Arrays.sort(exactSizeSellers, new ProductAmountComparator());
        return exactSizeSellers;
    }

    /**
     * returns an array of all customer type users.
     * @return array of customers if there are any, else null.
     */
    public Customer[] getCustomersByLexicographicalOrder(){
        Customer[] customersTemp = new Customer[users.length];
        int currentIndex = 0;
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i] instanceof Customer){
                customersTemp[currentIndex++] = (Customer) users[i];
            }
        }
        if(currentIndex == 0){  //no customers.
            return null;
        }
        Customer[] exactSizeCustomers = new Customer[currentIndex];
        System.arraycopy(customersTemp, 0, exactSizeCustomers, 0, currentIndex);
        Arrays.sort(exactSizeCustomers, new UsernameComparator());
        return exactSizeCustomers;
    }

    public void addUser(User user){
        if(bIsArrayFull(users)){
            users = doubleArraySize(users);
        }
        users[currentUserIndex++] = user;
    }

    public Seller getSellerByName(String name){
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i].getName().equals(name) && users[i] instanceof Seller){
                return (Seller)users[i];
            }
        }
        return null;
    }

    public Customer getCustomerByName(String name){
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i].getName().equals(name) && users[i] instanceof Customer){
                return (Customer)users[i];
            }
        }
        return null;
    }

    public int sellerIndexByName(String name){
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i].getName().equals(name) && users[i] instanceof Seller){
                return i;
            }
        }
        return -1;
    }

    public int customerIndexByName(String name){
        for(int i = 0; i < users.length; i++){
            if(users[i] == null){
                break;
            }
            if(users[i].getName().equals(name) && users[i] instanceof Customer){
                return i;
            }
        }
        return -1;
    }

    public boolean bIsUsernameTaken(String name){
        for(User user: users){
            if(user == null){
                break;
            }
            if(user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void printCustomerNames(){
        System.out.println("Customer list:");
        for(User user: users){
            if(user == null){
                break;
            }
            if(user instanceof Customer){
                System.out.println(user.getName());
            }
        }
    }

    public void printSellerNames(){
        System.out.println("Seller list:");
        for(User user: users){
            if(user == null){
                break;
            }
            if(user instanceof Seller){
                System.out.println(user.getName());
            }
        }
    }

    @Override
    public boolean bIsArrayFull(User[] arr) {
        return (currentUserIndex >= users.length);
    }

    @Override
    public User[] doubleArraySize(User[] arr) {
        User[] newArr = new User[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    public Product[] getProductsOfCategory(eCategory category){
        Product[] tempAllProducts = new Product[0]; //temporary array for all products that far. Needed for the algorithm.
        Product[] allProducts = new Product[0]; //the final array that hold all products.
        for(User user: users){
            if(user instanceof Seller){
                Product[] currentProducts = ((Seller) user).getProductsOfCategory(category);
                if(currentProducts == null){
                    continue;
                }
                // makes tempAllProducts the size of tempAllProducts + currentProducts combined
                allProducts = Arrays.copyOf(tempAllProducts, tempAllProducts.length + currentProducts.length);
                // fills the empty part left for currentProducts in allProducts
                System.arraycopy(currentProducts, 0, allProducts, tempAllProducts.length, currentProducts.length);
                tempAllProducts = allProducts;
            }
        }
        return allProducts;
    }

    public boolean checkSeller(){
        for(User user: users){
            if(user == null){
                break;
            }
            if(user instanceof Seller){
                return true;
            }
        }
        return false;
    }

    public boolean checkCustomer(){
        for(User user: users){
            if(user == null){
                break;
            }
            if(user instanceof Customer){
                return true;
            }
        }
        return false;
    }

    public boolean checkSellerWithProduct(){
        for(User user: users){
            if(user == null){
                break;
            }
            if(user instanceof Seller){
                if(((Seller) user).getCurrentProductIndex() != 0){
                    return true;
                }
            }
        }
        return false;
    }
}
