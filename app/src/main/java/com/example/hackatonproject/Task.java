package com.example.hackatonproject;

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
    private static TaskDBHelper dbHelper;

    public Task(int _id, int _type, String _value, Integer _ratingReward, Integer _pointsReward){

    }
}
