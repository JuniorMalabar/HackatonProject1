package com.example.hackatonproject;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class PointsVisitTask extends Task {
    private ArrayList<Location> locations;
    public PointsVisitTask(int _type,  String _value){
        super(_type, 0, 0,  false);
        locations = new ArrayList<>();
        value = _value;
    }

    public PointsVisitTask(int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision, ArrayList<Location> _locations){
        super(_type, _ratingReward, _pointsReward,  _decision);
        locations = new ArrayList<>(_locations);
        value = toString();
        id = saveTask();
    }

    @NonNull
    @Override
    public String toString() {
        String result = "";
        for (Location location : locations){
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            result += "(" + lat.toString() + ", " + lng.toString() + "); ";
        }
        return result;
    }

    @Override
    public void parseValueString() {
        int lastFoundIndex = -1;
        int index;
        locations = new ArrayList<>();
        while (true){
            index = value.indexOf("(", lastFoundIndex + 1);
            if (index == -1){
                break;
            }
            lastFoundIndex = value.indexOf(");", index+1);
            String[] params = value.substring(index, lastFoundIndex).split(",");
            Location location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(Double.parseDouble(params[0]));
            location.setLongitude(Double.parseDouble(params[1]));
            locations.add(location);
        }
    }

    @Override
    public void progressCompletion(String _value) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    public ArrayList<Location> getLocations(){
        return new ArrayList<Location>(locations);
    }
}
