import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database implements ResizableArrayUtility {
    private User[] users = new User[2];

    public User[] getUsers() {
        return users;
    }

    public void addUser(User user){
        if(ResizableArrayUtility.bIsArrayFull(users)){
            ResizableArrayUtility.doubleArraySize(users);
        }
        for(int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                break; // Exit the loop after adding the user
            }
        }
    }
}
