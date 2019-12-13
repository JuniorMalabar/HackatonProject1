package com.example.hackatonproject;

import android.content.Context;

enum Task_type {
    TASK_TYPE_STEPS_COUNT,
    TASK_TYPE_GO_TO_POINT
}

enum Task_decision {
    TASK_DECISION_NOT_DECIDED,
    TASK_DECISION_DECLINED,
    TASK_DECISION_ACCEPTED
}

public abstract class Task {
    private Integer id;
    private int type;
    private String value;
    private Integer ratingReward;
    private Integer pointsReward;
    private int decision;
    private static TaskDBHelper dbHelper;

    public Task(int _id, int _type, String _value, Integer _ratingReward, Integer _pointsReward, int _decision){
        id = _id;
        type = _type;
        value = _value;
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
