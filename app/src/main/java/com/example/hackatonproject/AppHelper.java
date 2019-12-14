package com.example.hackatonproject;

import android.app.AlarmManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class AppHelper {
    private User user;
    private static AppHelper helper;
    AlarmManager alarmManager;

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
