package com.example.hackatonproject;

import java.util.ArrayList;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Integer points;
    private BonusesHistory history;
    private ArrayList<Task> tasks;
    private static UserDBHelper dbHelper;

    public User(Integer _id, String _login, String _password, Integer _points, BonusesHistory _history) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        history = _history;
        //dbHelper.saveUser(user);
    }

    public void setDBHelper(UserDBHelper helper){

    }
    public static User getUserByLoginPassword(String _login, String _password){
        User user = null;

        return user;
    }


}
