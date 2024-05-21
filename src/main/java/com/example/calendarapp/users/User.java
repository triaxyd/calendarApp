package com.example.calendarapp.users;

public class User {
    private final String username;
    private String password;
    private int age;
    private String salt;

    public User(String username, String password, int age, String salt) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.salt = salt;
    }

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public int getAge() {return this.age;}
    public String getSalt() {return this.salt;}

    public void setPassword(String password) {this.password = password;}
    public void setAge(int age) {this.age = age;}
    public void setSalt(String salt) {this.salt = salt;}


}
