package com.example.calendarapp.DAO;
import com.example.calendarapp.calendar.Event;
import com.example.calendarapp.database.DatabaseConnector;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class eventsDAO {

    public static List<Event> getEventsForUser(String creatorUsername) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Event> events = new ArrayList<>();
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM event WHERE creatorUsername = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, creatorUsername);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int eventId = resultSet.getInt("eventId");
                String eventName = resultSet.getString("eventName");
                Timestamp eventDate = resultSet.getTimestamp("eventDate");
                int eventDuration = resultSet.getInt("eventDuration");
                String eventDescription = resultSet.getString("eventDescription");
                boolean isPublic = resultSet.getBoolean("isPublic");

                Event event = new Event(eventId, eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPublic);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return events;
    }


    public static boolean addEvent(String eventName, Timestamp eventDate, int eventDuration, String eventDescription, String username, boolean isPublic){
        Event event = new Event(eventName,eventDate,eventDuration,eventDescription,username,isPublic);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO event (eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPublic) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setTimestamp(2, new Timestamp(event.getEventDate().getTime()));
            preparedStatement.setInt(3, event.getEventDuration());
            preparedStatement.setString(4, event.getEventDescription());
            preparedStatement.setString(5, event.getCreatorUsername());
            preparedStatement.setBoolean(6, isPublic);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
