package com.example.hackatonproject;

import android.content.Context;

public abstract class Task {
    public static int TASK_TYPE_STEPS_COUNT = 0;
    public static int TASK_TYPE_GO_TO_POINT = 1;
    public static int TASK_DECISION_NOT_DECIDED = 0;
    public static int TASK_DECISION_DECLINED = 1;
    public static int TASK_DECISION_ACCEPTED = 2;

    private Integer id;
    private int type;
    private String value;
    private Integer ratingReward;
    private Integer pointsReward;
    private int decision;
    private static TaskDBHelper dbHelper;

    public Task(int _id, int _type, Integer _ratingReward, Integer _pointsReward, int _decision){
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

    public int getDecision(){
        return decision;
    }

    public abstract void parseValueString();
}
