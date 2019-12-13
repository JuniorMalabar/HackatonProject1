package com.example.hackatonproject;

import android.content.Context;

enum Test_type {
    TASK_TYPE_STEPS_COUNT,
    TASK_TYPE_GO_TO_POINT
}

public class Task {
    private Integer id;
    private int type;
    private String value;
    private Integer ratingReward;
    private Integer pointsReward;
    private boolean accepted;
    private static TaskDBHelper dbHelper;

    public Task(int _id, int _type, String _value, Integer _ratingReward, Integer _pointsReward, boolean _accepted){
        id = _id;
        type = _type;
        value = _value;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        accepted = _accepted;
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


}
