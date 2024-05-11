package com.example.calendarapp.users;

public class User {
    private int id;
    private String username;
    private String password;
    private int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public int getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public int getAge() {return this.age;}

    public void setId(int id) {this.id = id;}


}
