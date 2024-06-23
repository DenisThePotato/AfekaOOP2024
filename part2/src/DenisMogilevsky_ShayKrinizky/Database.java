package DenisMogilevsky_ShayKrinizky;

public class Database implements ResizableArrayUtility<User> {
    private User[] users = new User[2];
    int currentUserIndex = 0;

    public User[] getUsers() {
        return users;
    }

    public void addUser(User user){
        if(bIsArrayFull(users)){
            users = doubleArraySize(users);
        }
        users[currentUserIndex] = user;
        currentUserIndex++;
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
}
