package com.example.hackatonproject;

public class AppHelper {
    private User user;
    private static AppHelper helper;

    public AppHelper(){
        user = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public static AppHelper getInstance(){
        if (helper == null){
            helper = new AppHelper();
        }
        return helper;
    }
}
