package com.example.calendarapp.DAO;
import com.example.calendarapp.calendar.Event;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

public class eventsDAO {

    public static void getEvents(String eventId){

    }

    public static boolean addEvent(String eventName, LocalDate eventDate, int eventDuration, String eventDescription, String username, boolean isPublic){
        Event event = new Event(eventName,eventDate,eventDuration,eventDescription,username,isPublic);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO event (eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPulbic) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventName());
            //preparedStatement.setDate(2, LocalDate.parse(event.getEventDate()));
            preparedStatement.setInt(3, event.getEventDuration());
            preparedStatement.setString(4, event.getCreatorUsername());
            preparedStatement.setBoolean(5, isPublic);

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
