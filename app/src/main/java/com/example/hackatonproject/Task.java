package com.example.hackatonproject;

import android.content.Context;

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

    public Task(int _id, int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        id = _id;
        type = _type;
        value = null;
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

    public void finishCompletion(User user){
        user.giveRatingReward(ratingReward);
        user.givePointsReward(pointsReward);
    }

    public static Task generateTask(User user){
        Random random = new Random();
        int _type = random.nextInt(TASK_TYPE_COUNT);
        switch (_type){
            case TASK_TYPE_STEPS_COUNT:
                return new StepsCountTask.generate()
                break;
            case TASK_TYPE_GO_TO_POINT:
                break;
            case TASK_TYPE_GO_TO_ROUTE:
                break;
            default:
                break;
        }
    }

    public void saveTask(){
        dbHelper.insert(type, value, ratingReward, pointsReward, decision ? 1 : 0);
    }

    public abstract void parseValueString();
}
