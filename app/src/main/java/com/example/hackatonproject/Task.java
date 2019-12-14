package com.example.hackatonproject;

import android.content.Context;

import java.util.Random;

public abstract class Task {
    public static final int TASK_TYPE_STEPS_COUNT = 0;
    public static final int TASK_TYPE_GO_TO_POINT = 1;
    public static final int TASK_TYPE_GO_TO_ROUTE = 2;
    public static final int TASK_TYPE_COUNT = 3;

    private Integer id;
    private int type;
    private String value;
    private Integer ratingReward;
    private Integer pointsReward;
    private boolean decision;
    private static TaskDBHelper dbHelper;

    public Task(int _id, int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        id = _id;
        type = _type;
        value = null;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        decision = _decision;
    }

    public static void setDBHelper(Context context) {
        dbHelper = new TaskDBHelper(context);
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
                break;
            case TASK_TYPE_GO_TO_POINT:
                break;
            case TASK_TYPE_GO_TO_ROUTE:
                break;
        }
    }

    public void saveTask(){
        dbHelper.insert(this);
    }

    public void setValueString(String valueString) {
        value = valueString;
    }

    public abstract void parseValueString();
}
