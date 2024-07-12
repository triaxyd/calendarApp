package com.example.calendarapp.DAO;
import com.example.calendarapp.calendar.Event;
import com.example.calendarapp.database.DatabaseConnector;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class eventsDAO {


    public static Event getEvent(int eventId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Event event = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM event WHERE eventId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String eventName = resultSet.getString("eventName");
                Timestamp eventDate = resultSet.getTimestamp("eventDate");
                int eventDuration = resultSet.getInt("eventDuration");
                String creatorUsername = resultSet.getString("creatorUsername");
                String eventDescription = resultSet.getString("eventDescription");
                boolean isPublic = resultSet.getBoolean("isPublic");

                event = new Event(eventId, eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPublic);
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
        return event;
    }


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



    public static List<Event> getPublicEventsForUser(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Event> events = new ArrayList<>();
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT e.eventId FROM event AS e JOIN participant AS p ON e.eventId = p.eventId WHERE p.username = ? AND e.creatorUsername != ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //get the eventId returned
                int eventId = resultSet.getInt("eventId");
                //find the event using the getEvent() method and add it to the events list
                events.add(getEvent(eventId));
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




    public static int addEvent(String eventName, Timestamp eventDate, int eventDuration, String eventDescription, String username, boolean isPublic) {
        Event event = new Event(eventName,eventDate,eventDuration,eventDescription,username,isPublic);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO event (eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPublic) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setTimestamp(2, new Timestamp(event.getEventDate().getTime()));
            preparedStatement.setInt(3, event.getEventDuration());
            preparedStatement.setString(4, event.getEventDescription());
            preparedStatement.setString(5, event.getCreatorUsername());
            preparedStatement.setBoolean(6, isPublic);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                //retrieve the generated key eventId
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    //retrieve the eventId from generated keys
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve auto-generated keys for eventId.");
                }
            } else {
                // handle case where no rows were affected
                throw new SQLException("Event creation failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Return -1 to indicate failure
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean editEvent(int eventId, String eventName, Timestamp eventDate, int eventDuration, String eventDescription, String username, boolean isPublic) {
        Event event = new Event(eventName,eventDate,eventDuration,eventDescription,username,isPublic);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "UPDATE event SET eventName = ?, eventDate = ?, eventDuration = ?, eventDescription = ?, creatorUsername = ?, isPublic = ? WHERE eventId = ?";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setTimestamp(2, new Timestamp(event.getEventDate().getTime()));
            preparedStatement.setInt(3, event.getEventDuration());
            preparedStatement.setString(4, event.getEventDescription());
            preparedStatement.setString(5, event.getCreatorUsername());
            preparedStatement.setBoolean(6, isPublic);
            preparedStatement.setInt(7, eventId);
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


    public static boolean deleteEvent(int eventId){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "DELETE FROM event WHERE eventId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);

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
