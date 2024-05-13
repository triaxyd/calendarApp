package com.example.calendarapp.DAO;
import com.example.calendarapp.users.User;


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


    public static void createUser(String username, String password, int age) {
        // create new User object
        // create new user id
        // store user information to database
    }

}
