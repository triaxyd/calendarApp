package com.example.calendarapp.DAO;

import com.example.calendarapp.calendar.Comment;
import com.example.calendarapp.database.DatabaseConnector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class commentsDAO {

    public static List<Comment> getEventComments(int eventId){
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT username,comment FROM comment where eventId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String commentstr = resultSet.getString("comment");
                Comment comment= new Comment(username,eventId,commentstr);
                comments.add(comment);
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
        System.out.println("Comments fetched for eventId " + eventId + ": " + comments);

        return comments;
    }

    public static boolean addEventComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isSuccess = false;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO comment (username, eventId, comment) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, comment.getUsername());
            preparedStatement.setInt(2, comment.getEventId());
            preparedStatement.setString(3, comment.getComment());
            int rowsAffected = preparedStatement.executeUpdate();
            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
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
        System.out.println("Comment added for eventId " + comment.getEventId() + ": " + isSuccess);

        return isSuccess;
    }
    public static boolean deleteCommentsByEventId(int eventId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isSuccess = false;
        try {
            connection = DatabaseConnector.connect();
            String sql = "DELETE FROM comment WHERE eventId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            int rowsAffected = preparedStatement.executeUpdate();
            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
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
        System.out.println("Comments deleted for eventId " + eventId + ": " + isSuccess);
        return isSuccess;
    }

}
