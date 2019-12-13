package com.example.hackatonproject;

enum Test_type {
    TASK_TYPE_STEPS_COUNT,
    TASK_TYPE_GO_TO_POINT
}

public class Task {

    private Integer id;
    private int type;
    private String value;
    private static TaskDBHelper dbHelper;

    public Task(int id, int type, String value) {
    }
}
