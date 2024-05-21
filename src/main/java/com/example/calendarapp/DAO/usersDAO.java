package com.example.calendarapp.DAO;
import com.example.calendarapp.users.User;
import com.example.calendarapp.crypto.PBKDF2;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;


public class usersDAO {


    public static boolean searchUser(String username) {
        // access database and search for user
        // if found, return true
        return true;
    }

    public static boolean validateUser(String username, String password) {
        // authenticate user based on credentials
        return true;
    }

    public static User returnUser(String username){
        // find user based on username and return his object
        return null;
    }


    public static void createUser(String username, String password, int age) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        // create new User object
        // create new user id
        // store user information to database
        User user;
        byte[] userSalt = PBKDF2.getSalt();
        byte[] hashedPassword = PBKDF2.generateHash(password,userSalt);

        String saltString = Arrays.toString(userSalt);
        String passwordString = Arrays.toString(hashedPassword);

        user = new User(username,passwordString,age,saltString);


    }

}
