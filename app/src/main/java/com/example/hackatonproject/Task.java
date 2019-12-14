package com.example.hackatonproject;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.Random;

public abstract class Task {
    public static final int TASK_TYPE_STEPS_COUNT = 0;
    public static final int TASK_TYPE_GO_TO_POINT = 1;
    public static final int TASK_TYPE_GO_TO_ROUTE = 2;
    public static final int TASK_TYPE_COUNT = 3;

    protected Integer id;
    protected Integer type;
    protected String value;
    protected Integer ratingReward;
    protected Integer pointsReward;
    protected boolean decision;
    protected static TaskDBHelper dbHelper;

    public Task(int _id, int _type, String _value, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        id = _id;
        type = _type;
        value = _value;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        decision = _decision;
    }

    public Task(int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        type = _type;
        value = null;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        decision = _decision;
    }

    public static void setDBHelper(Context context) {
        dbHelper = new TaskDBHelper(context);
    }

    public TaskDBHelper getDBHelper(){
        return dbHelper;
    }

    public int getType() {
        return type;
    }

    public String getValue(){
        return value;
    }

    public Integer getRatingReward(){
        return ratingReward;
    }

    public Integer getPointsReward(){
        return pointsReward;
    }

    public boolean getDecision(){
        return decision;
    }

    public void finishCompletion(){
        User user = AppHelper.getInstance().getUser();
        user.giveRatingReward(ratingReward);
        user.givePointsReward(pointsReward);
        dbHelper.delete(this);
    }

    public static Task generateTask(){
        Random random = new Random();
        int _type = random.nextInt(TASK_TYPE_COUNT);
        switch (_type){
            case TASK_TYPE_STEPS_COUNT:
                int steps = random.nextInt(5001) + 5000;
                return new StepsCountTask(steps / 10, (int) Math.round(steps * AppHelper.getInstance().getUser().getMultiplier() / 50), false, steps);
            case TASK_TYPE_GO_TO_POINT:
                int probability = random.nextInt(100);
                if (probability < 25){
                    Organization organization = Organization.getClosestPoint();
                    ArrayList<Location> list = new ArrayList<>();
                    list.add(organization.getLocation());
                    Integer distance = organization.distanceFromCurrentLocation();
                    return new PointsVisitTask(_type, distance / 20, (int) Math.round(distance * AppHelper.getInstance().getUser().getMultiplier() / 100), false, list);
                }
                else{

                    return new PointsVisitTask()
                }
                break;
            case TASK_TYPE_GO_TO_ROUTE:
                break;
            default:
                break;
        }
        return null;
    }

    public Integer saveTask(){
        return dbHelper.insert(type, value, ratingReward, pointsReward, decision ? 1 : 0);
    }

    public abstract void parseValueString();
    public abstract void progressCompletion(String _value);
}
