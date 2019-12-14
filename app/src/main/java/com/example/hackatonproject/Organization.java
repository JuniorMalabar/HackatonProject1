package com.example.hackatonproject;

import android.location.Location;

import java.util.ArrayList;
import java.util.Random;

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
        for (Organization org: organizations) {
            if (org.distanceFromCurrentLocation() < minDist){
                minDist = org.distanceFromCurrentLocation();
                closestOrg = org;
            }
        }
        return closestOrg;
    }

    public static Organization getRandomPoint() {
        ArrayList<Organization> organizations = new ArrayList<>(); // по факту - собрать с бд все организации
        Random random = new Random();
        return organizations.get(random.nextInt(organizations.size()));
    }

    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation(){
        return location;
    }

    public Integer distanceFromCurrentLocation() {
        return Math.round(location.distanceTo(AppHelper.getInstance().getLocation()));
    }

    // метод для сейва в бд
}
