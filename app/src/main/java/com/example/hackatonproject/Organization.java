package com.example.hackatonproject;

import android.location.Location;

import java.util.ArrayList;

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
            if (org.distanceFromCurrentLocation() < minDist)
        }
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
    }
}
