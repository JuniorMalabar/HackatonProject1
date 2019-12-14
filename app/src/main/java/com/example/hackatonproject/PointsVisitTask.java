package com.example.hackatonproject;

import android.location.Location;

public class PointsVisitTask extends Task {
    private Location location;

    public PointsVisitTask(int _id, int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision, Location _location){
        super(_id, _type, _ratingReward, _pointsReward,  _decision);
        location = new Location(_location);
    }

    @Override
    public void parseValueString() {

    }
}
