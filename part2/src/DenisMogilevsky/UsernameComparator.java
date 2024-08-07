package DenisMogilevsky;

import java.util.Comparator;

public class UsernameComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2){
        return user1.getName().compareTo(user2.getName());
    }
}
