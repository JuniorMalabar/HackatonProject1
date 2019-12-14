package com.example.hackatonproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.lang.Math.exp;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Integer points;
    private BonusesHistory history;
    //private ArrayList<Task> tasks;
    private Integer rating; // Хранятся ОЧКИ рейтинга

    public User(Integer _id, String _login, String _password, Integer _points, BonusesHistory _history, Integer _rating) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        history = _history;
        rating = _rating;
    }

    public static boolean tryToRegister(String _login, String _password) {
        // если логин уникален, то пишем в бд и возвращаем true, иначе false
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", _login);
        map.put("password", _password);
        String query = RequestAsync.Url + "RegisterUser.php";
        RequestAsync task = new RequestAsync(map);
        try {
            task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return false;

    }

    public static User tryToSignIn(String _login, String _password) {
        // если связка логин/пароль верна, то возвращаем объект юзера, иначе null

        Map<String, String> map = new HashMap<String, String>();
        map.put("login", _login);
        map.put("password", _password);
        String query = RequestAsync.Url + "ProvideAccess.php";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RequestAsync task = new RequestAsync(map);
        String value= null;
        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map = gson.fromJson(value, HashMap.class);

        return new User(1, "Fedor", "Talisman", 0, null, 0); // DEBUG
       // return new User(1, "Fedor", "Talisman", 0, null, 0); // DEBUG
    }

    public String getLogin() {
        return login;
    }

    public Integer getPoints() {
        return points;
    }

    //public ArrayList<Task> getTasks(){
    //return new ArrayList<>(tasks);
    //}

    public BonusesHistory getHistory() {
        return history;
    }

    public Integer getRating() {
        return rating;
    }

    public double getMultiplier() {
        return 0.25 * exp(-rating / 1000.0) + 0.75;
    }

    public void givePointsReward(double reward) {
        points += (int) reward;
    }

    public void giveRatingReward(Integer reward) {
        rating += reward;
    }

    public boolean tryToPurchaseBonus(Bonus bonus) {
        if (points >= bonus.getCost()) {
            points -= bonus.getCost();
            history.addBonus(bonus);
            // Сохранить изменения в бд
            return true;
        }
        return false;
    }
}
