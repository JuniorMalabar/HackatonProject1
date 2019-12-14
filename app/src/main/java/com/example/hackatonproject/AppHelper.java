package com.example.hackatonproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import static androidx.core.content.ContextCompat.getSystemService;

public class AppHelper {
    private User user;
    private static AppHelper helper;
    private Location location;
    private LocationListener listener;
    private LocationManager manager;

    public AppHelper(){
        user = null;
        location = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initTracking(Context context){
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                AppHelper.getInstance().setLocation(location);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    public User getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static AppHelper getInstance(){
        if (helper == null){
            helper = new AppHelper();
        }
        return helper;
    }
}
