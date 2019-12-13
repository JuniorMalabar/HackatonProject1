package com.example.hackatonproject;

import java.util.ArrayList;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Integer points;
    private BonusesHistory history;
    private ArrayList<Task> tasks;
    private Integer rating; // Хранятся ОЧКИ рейтинга
    private static UserDBHelper dbHelper;

    public User(Integer _id, String _login, String _password, Integer _points, BonusesHistory _history, Integer _rating) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        history = _history;
        rating = _rating;
        //dbHelper.saveUser(user) or dbHelper.editUser(user);
    }

    public void setDBHelper(){
        dbHelper = new UserDBHelper();
    }

    public static boolean tryToRegistrate(String _login, String _password){

    }

    public static boolean isUnigueLogin(String _login){
        return false; // на самом деле return запись_из_дб != null
    }

    public Integer getPoints(){
        return points;
    }

    public ArrayList<Task> getTasks(){
        return new ArrayList<>(tasks);
    }

    public BonusesHistory getHistory(){
        return history;
    }

    public Integer getRating(){
        return rating;
    }

    public double getMultiplier(){

    }
}
