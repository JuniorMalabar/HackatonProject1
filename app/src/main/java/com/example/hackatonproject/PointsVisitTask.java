package com.example.hackatonproject;

import android.location.Location;

import androidx.annotation.NonNull;

public class PointsVisitTask extends Task {
    private Location location;

    public PointsVisitTask(int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision, Location _location){
        super(_type, _ratingReward, _pointsReward,  _decision);
        location = new Location(_location);
        value = toString();
        id = saveTask();
    }

    @NonNull
    @Override
    public String toString() {
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();
        return  lat.toString() + " " + lng.toString();
    }

    @Override
    public void parseValueString() {
        String[] params = value.split(" ");
        location.setLatitude(Double.parseDouble(params[0]));
        location.setLongitude(Double.parseDouble(params[1]));
    }
}
