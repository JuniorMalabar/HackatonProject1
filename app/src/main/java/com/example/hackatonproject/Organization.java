package com.example.hackatonproject;

import android.location.Location;

public class Organization {
    private Integer id;
    private String name;
    private Location location;

    public Organization(int _id, String _name, Location _location) {
        id = _id;
        name = _name;
        location = _location;
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
}
