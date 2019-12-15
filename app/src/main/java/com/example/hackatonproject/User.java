package com.example.hackatonproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

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
    private ArrayList<Bonus> historyOfBonuses;
    private Integer rating; // Хранятся ОЧКИ рейтинга
    private boolean studentMode;

    public User(Integer _id, String _login, String _password, Integer _points, ArrayList<Bonus> _history, Integer _rating, boolean _studentMode) {
        id = _id;
        login = _login;
        password = _password;
        points = _points;
        historyOfBonuses = _history;
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
        String value = null;

        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(value, Boolean.class);
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
        HashMap<String, String>[] massOfBonuses = gson.fromJson(value, HashMap[].class);

        ArrayList<Bonus> retArr = new ArrayList<Bonus>();

        for (HashMap<String, String> Bonusmap : massOfBonuses) {
            retArr.add(new Bonus(Integer.parseInt(Bonusmap.get("id")), Bonusmap.get("organozation_name"), Bonusmap.get("description"), Bonusmap.get("promo_code"), Integer.parseInt(Bonusmap.get("cost"))));
        }
        return new User(Integer.parseInt(Usermap.get("id")), Usermap.get("login"), Usermap.get("password"), Integer.parseInt(Usermap.get("current_value_of_bonuses")), retArr, Integer.parseInt(Usermap.get("whole_received_bonuses")), Integer.parseInt(Usermap.get("student_mode")) == 1); // DEBUG
    }

    public static ArrayList<User> getUsersByRating() {
        // Нужно получить всех юзеров сортированных ПО УБЫВАНИЮ рейтигна из бд
        // Нужно найти, по какому индексу в полученном массиве находится AppData.getInstance().getUser()
        // Сделать AppData.getInstance().setUserPlace(найденный_индекс + 1)
        // Вернуть массив из первых 10 юзеров + самого юзера (AppHelper.getInstance().getUser()), т.е. в ответе 11 элементов

        String query = RequestAsync.Url + "GetLiders.php";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RequestAsync task = new RequestAsync(null);
        String value = null;
        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String, String>[] UsersList = gson.fromJson(value, HashMap[].class);

        if (UsersList == null) {
            return null;
        }

        ArrayList<User> retArr = new ArrayList<User>();

        for (HashMap<String, String> Usermap : UsersList) {
            retArr.add(new User(Integer.parseInt(Usermap.get("id")), Usermap.get("login"), Usermap.get("password"), Integer.parseInt(Usermap.get("current_value_of_bonuses")), null, Integer.parseInt(Usermap.get("whole_received_bonuses")), Integer.parseInt(Usermap.get("student_mode")) == 1));
        }
        return retArr;
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

    public ArrayList<Bonus> getHistory() {
        return historyOfBonuses;
    }

    public Integer getRating() {
        return rating;
    }

    public double getMultiplier() {
        return 0.25 * exp(-rating / 1000.0) + 0.75;
    }

    public void givePointsReward(int reward) {
        points += (int) reward;
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", AppHelper.getInstance().getUser().id.toString());
        map.put("additionalValue", Integer.toString(reward));
        String query = RequestAsync.Url + "AddBonusesToUser.php";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RequestAsync task = new RequestAsync(map);
        try {
            task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void giveRatingReward(int reward) {
        rating += reward;
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", AppHelper.getInstance().getUser().id.toString());
        map.put("additionalValue", Integer.toString(reward));
        String query = RequestAsync.Url + "AddRatingToUser.php";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RequestAsync task = new RequestAsync(map);
        try {
            task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean tryToPurchaseBonus(Bonus bonus) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", AppHelper.getInstance().getUser().id.toString());
        map.put("bonus_id", Integer.toString(bonus.getId()));
        String query = RequestAsync.Url + "AddBonusToHistory.php";
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
            return false;
        } else {
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
            HashMap<String, String>[] massOfBonuses = gson.fromJson(value, HashMap[].class);

            ArrayList<Bonus> retArr = new ArrayList<Bonus>();

            for (HashMap<String, String> Bonusmap : massOfBonuses) {
                retArr.add(new Bonus(Integer.parseInt(Bonusmap.get("id")), Bonusmap.get("organozation_name"), Bonusmap.get("description"), Bonusmap.get("promo_code"), Integer.parseInt(Bonusmap.get("cost"))));
            }
            AppHelper.getInstance().setUser(new User(Integer.parseInt(Usermap.get("id")), Usermap.get("login"), Usermap.get("password"), Integer.parseInt(Usermap.get("current_value_of_bonuses")), retArr, Integer.parseInt(Usermap.get("whole_received_bonuses")), Integer.parseInt(Usermap.get("student_mode")) == 1));
            return true;
        }
    }
}
