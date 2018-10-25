package net.eagle.tas.tradersb.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {
    public static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User("Adam", new Date(), 1));
        users.add(new User("Eve", new Date(), 2));
        users.add(new User("Jack", new Date(), 3));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.id == null)
            user.id = ++usersCount;

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users)
            if (user.id == id)
                return user;
        return null;
    }

    public User deleteById(int id) {

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
