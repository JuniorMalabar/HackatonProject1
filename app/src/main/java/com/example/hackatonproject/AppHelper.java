package com.example.hackatonproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.Map;

import static androidx.core.content.ContextCompat.getSystemService;

public class AppHelper {
    private User user;
    private static AppHelper helper;
    private Location location;
    private LocationListener listener;
    private LocationManager manager;
    private Context tabHostContext;
    private Integer place;

    public AppHelper() {
        user = null;
        location = null;
        tabHostContext = null;
        place = null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initTracking(Context context) {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                AppHelper.getInstance().setLocation(location);
                for (PointsVisitTask task : PointsVisitTask.getAllPointsTasks()) {
                    task.progressCompletion("");
                }
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
    }

    public void refreshLocation(Context context) {
        setTabHostContext(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

    public User getUser() {
        return user;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLocation() {
        if (getTabHostContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getTabHostContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Context getTabHostContext() {
        return tabHostContext;
    }

    public void setTabHostContext(Context tabHostContext) {
        this.tabHostContext = tabHostContext;
    }

    public static AppHelper getInstance(){
        if (helper == null){
            helper = new AppHelper();
        }
        return helper;
    }

    public Integer getUserPlace() {
        return place;
    }

    public void setUserPlace(Integer place) {
        this.place = place;
    }
    final static String url = "http://u0896591.plsk.regruhosting.ru/";

    public static String GenerateQuery(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        String _ret = "?";
        boolean begin = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!begin) {
                _ret += "&";
            }
            begin = false;
            _ret += (entry.getKey() + "=" + entry.getValue());
        }


        return _ret.replace(' ', '+');
    }

}
