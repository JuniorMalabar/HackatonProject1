package com.example.hackatonproject;

public class User {
    public Integer id;
    public String login;
    public String password;
    public Integer points;
    public BonusesHistory history;
    public static UserDBHelper dbHelper;

    public User() {

    }
    public static User getUserByLoginPassword(String l, String p){
        User user = null;

        return user;
    }

    
}
