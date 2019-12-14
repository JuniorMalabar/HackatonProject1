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
    private Integer rating; // Хранятся ОЧКИ рейтинга
    private boolean studentMode;

    public User(Integer _id, String _login, String _password, Integer _points, BonusesHistory _history, Integer _rating, boolean _studentMode) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        history = _history;
        rating = _rating;
        studentMode = _studentMode;
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
        String value = null;
        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String, String> Usermap = gson.fromJson(value, HashMap.class);
        if (Usermap == null) {
            return null;
        }

        query = RequestAsync.Url + "GetHistoryOfThisOne.php";
        map = new HashMap<String, String>();
        map.put("id", Usermap.get("id"));
        task = new RequestAsync(map);
        value = null;
        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map = gson.fromJson(value, HashMap.class);
        return new User(Integer.parseInt(Usermap.get("id")), Usermap.get("login"), Usermap.get("password"), Integer.parseInt(Usermap.get("current_value_of_bonuses")), null, Integer.parseInt(Usermap.get("whole_received_bonuses")),false); // DEBUG
    }

    public static ArrayList<User> getUsersByRating() {
        // Нужно получить всех юзеров сортированных ПО УБЫВАНИЮ рейтигна из бд
        // Нужно найти, по какому индексу в полученном массиве находится AppData.getInstance().getUser()
        // Сделать AppData.getInstance().setUserPlace(найденный_индекс + 1)
        // Вернуть массив из первых 10 юзеров + самого юзера (AppHelper.getInstance().getUser()), т.е. в ответе 11 элементов
        return null;
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
