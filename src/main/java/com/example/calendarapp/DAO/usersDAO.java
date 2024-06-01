package com.example.calendarapp.DAO;
import com.example.calendarapp.database.DatabaseConnector;
import com.example.calendarapp.users.User;
import com.example.calendarapp.crypto.PBKDF2;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;


public class usersDAO {


    public static boolean searchUser(String username) {
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1 FROM user WHERE username = ?")) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateUser(String username, String password) {
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT password, salt FROM user WHERE username = ?")) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    String storedSalt = resultSet.getString("salt");

                    byte[] saltBytes = Base64.getDecoder().decode(storedSalt);
                    byte[] hashedPassword = PBKDF2.generateHash(password, saltBytes);
                    String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);

                    return storedPassword.equals(hashedPasswordString);
                }
            }

        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }




    public static User returnUser(String username){
        // find user based on username and return his object
        return null;
    }





    public static boolean createUser(String username, String password, int age) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
        User user;
        byte[] userSalt = PBKDF2.getSalt();
        byte[] hashedPassword = PBKDF2.generateHash(password,userSalt);

        String saltString = Base64.getEncoder().encodeToString(userSalt);
        String passwordString = Base64.getEncoder().encodeToString(hashedPassword);

        user = new User(username,passwordString,age,saltString);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO user (username, password, age, salt) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getSalt());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
