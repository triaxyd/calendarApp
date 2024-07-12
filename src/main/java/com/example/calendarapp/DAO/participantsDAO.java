package com.example.calendarapp.DAO;

import com.example.calendarapp.database.DatabaseConnector;
import com.example.calendarapp.users.Participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class participantsDAO {

    public static List<Participant> getEventParticipants(int eventId){
        List<Participant> participants = new ArrayList<Participant>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT username FROM participant where eventId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                Participant participant= new Participant(username, eventId);
                participants.add(participant);
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
        System.out.println("Participants fetched for eventId " + eventId + ": " + participants);

        return participants;
    }

    public static boolean addParticipantToEvent(int eventId, String username){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO participant (eventId, username) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, username);

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

    public static boolean deleteParticipantsFromEvent(int eventId){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "DELETE FROM participant WHERE eventId=? ";
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
