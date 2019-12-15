package com.example.hackatonproject;

import android.location.Location;
import android.location.LocationManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Organization {
    private Integer id;
    private String name;
    private Location location;

    public Organization(int _id, String _name, Location _location) {
        id = _id;
        name = _name;
        location = _location;
    }

    public static Organization getClosestPoint() {
        ArrayList<Organization> organizations = new ArrayList<>(); // по факту - собрать с бд все организации
        Integer minDist = Integer.MAX_VALUE;
        Organization closestOrg = null;
        for (Organization org : organizations) {
            if (org.distanceFromCurrentLocation() < minDist) {
                minDist = org.distanceFromCurrentLocation();
                closestOrg = org;
            }
        }
        return closestOrg;
    }

    public static Organization getRandomPoint() {
        ArrayList<Organization> organizations = new ArrayList<>(); // по факту - собрать с бд все организации
        String query = RequestAsync.Url + "GetCoords.php";
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
        HashMap<String,String>[] CoordsStringList = gson.fromJson(value, HashMap[].class);
        for (HashMap<String,String> Coords : CoordsStringList) {
            Location loc = new Location(LocationManager.GPS_PROVIDER);
            loc.setLatitude(Double.parseDouble(Coords.get("latitude")));
            loc.setLongitude(Double.parseDouble(Coords.get("longtitude")));
            organizations.add(new Organization(Integer.parseInt(Coords.get("id")), Coords.get("organize_name"), loc));
        }
        Random random = new Random();
        return organizations.get(random.nextInt(organizations.size()));
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Integer distanceFromCurrentLocation() {
        return Math.round(location.distanceTo(AppHelper.getInstance().getLocation()));
    }

    // метод для сейва в бд
}
