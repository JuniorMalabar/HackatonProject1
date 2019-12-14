package com.example.hackatonproject;

import java.util.ArrayList;

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

    public static boolean tryToRegister(String _login, String _password){
        // если логин уникален, то пишем в бд и возвращаем true, иначе false
        return false;
    }

    public static User tryToSignIn(String _login, String _password) {
        // если связка логин/пароль верна, то возвращаем объект юзера, иначе null
        return new User(1, "Fedor", "Talisman", 0, null, 0,false); // DEBUG
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

    public Integer getPoints(){
        return points;
    }

    //public ArrayList<Task> getTasks(){
        //return new ArrayList<>(tasks);
    //}

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

    public boolean tryToPurchaseBonus(Bonus bonus){
        if (points >= bonus.getCost()){
            points -= bonus.getCost();
            history.addBonus(bonus);
            // Сохранить изменения в бд
            return true;
        }
        return false;
    }
}
