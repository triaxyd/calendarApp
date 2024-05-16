package com.example.calendarapp.users;

public class User {
    private final String username;
    private String password;
    private int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public int getAge() {return this.age;}

    public void setPassword(String password) {this.password = password;}
    public void setAge(int age) {this.age = age;}


}
