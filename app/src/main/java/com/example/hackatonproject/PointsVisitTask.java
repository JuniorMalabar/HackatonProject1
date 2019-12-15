package com.example.hackatonproject;

import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class PointsVisitTask extends Task {
    private ArrayList<Location> locations;

    public PointsVisitTask(int _type,  String _value){
        super(_type, 0, 0,  false);
        locations = new ArrayList<>();
        value = _value;
    }

    public PointsVisitTask(Integer _id, Integer _type, String _value, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        super(_type, _ratingReward, _pointsReward,  _decision);
        value = _value;
        parseValueString();
        id = _id;
    }

    public PointsVisitTask(int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision, ArrayList<Location> _locations){
        super(_type, _ratingReward, _pointsReward,  _decision);
        locations = new ArrayList<>(_locations);
        value = toString();
        id = saveTask();
    }

    public static ArrayList<PointsVisitTask> getAllPointsTasks() {
        return new ArrayList<>(dbHelper.getPointsTasks());
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
            Location location = new Location(LocationManager.NETWORK_PROVIDER);
            location.setLatitude(Double.parseDouble(params[0].substring(1)));
            location.setLongitude(Double.parseDouble(params[1].substring(0, params[1].length()-2)));
            locations.add(location);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void progressCompletion(String _value) {
        for (Location location : locations) {
            if (location.distanceTo(AppHelper.getInstance().getLocation()) < 10){
                locations.remove(location);
            }
        }
        if (locations.size() == 0){
            finishCompletion();
        }
    }

    @Override
    public String getDescription() {
        String description = "";
        if (type == TASK_TYPE_GO_TO_POINT){
            description = "Посетите заданную точку";
        }
        else{
            description = "Посетите все заданные точки";
        }
        return description;
    }

    public ArrayList<Location> getLocations(){
        return new ArrayList<Location>(locations);
    }
}
