package com.slim.springbootrestfulservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount  = 3;


    static {
        users.add(new User(1, "Slim", new Date()));
        users.add(new User(2, "Maria", new Date()));
        users.add(new User(3, "Joe", new Date()));
    }

    public List<User> getAllUsers() {
        return users;

    };

    public User addUser (User user) {
        if(user.getId() == null) {
            user.setId((++usersCount));
        }
        users.add(user);
        return user;
    }

    public User findOne(Integer id) {
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        };
        return null;
    };

    public User deleteOne(Integer id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        };
        return null;
    };
}
