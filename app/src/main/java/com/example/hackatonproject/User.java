package com.example.hackatonproject;

import java.util.ArrayList;

import static java.lang.Math.exp;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Integer points;
    private BonusesHistory history;
    private ArrayList<Task> tasks;
    private Integer rating; // Хранятся ОЧКИ рейтинга

    public User(Integer _id, String _login, String _password, Integer _points, BonusesHistory _history, Integer _rating) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        history = _history;
        rating = _rating;
    }

    public static boolean tryToRegister(String _login, String _password){
        // если логин уникален, то пишем в бд и возвращаем true, иначе false
        return false;
    }

    public static User tryToSignIn(String _login, String _password) {
        // если связка логин/пароль верна, то возвращаем объект юзера, иначе null
        return new User(1, "Fedor", "Talisman", 0, null, 0); // DEBUG
    }

    public String getLogin() {
        return login;
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
        return 0.25*exp(-rating/1000.0) + 0.75;
    }

    public void givePointsReward(double reward) {
        points += (int)reward;
    }

    public void giveRatingReward(Integer reward) {
        rating += reward;
    }
}
