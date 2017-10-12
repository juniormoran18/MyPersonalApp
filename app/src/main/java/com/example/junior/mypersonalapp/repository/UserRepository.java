package com.example.junior.mypersonalapp.repository;

import com.example.junior.mypersonalapp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junior on 11/10/17.
 */

public class UserRepository {

    public static List<User> users = new ArrayList<>();

    static{
        users.add(new User(100, "jmoran", "moran", "Junior Moran"));
        users.add(new User(200, "sgustavo", "soda", "Gustavo Cerati"));
        users.add(new User(300, "bamstrong", "green", "Billie Joe Amnstrong"));
    }

    public static User login(String username, String password){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    public static User getUser(String username){
        for (User user : users){
            if(user.getUsername().equalsIgnoreCase(username)){
                return user;
            }
        }
        return null;
    }

}
