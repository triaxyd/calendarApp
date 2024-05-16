package com.example.calendarapp.calendar;

public class Comment {
    private final String username;
    private final int commentId;
    private String comment;

    public Comment(String creatorUsername, int commentId, String comment) {
        this.username = creatorUsername;
        this.commentId = commentId;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public int getCommentId() {
        return commentId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
