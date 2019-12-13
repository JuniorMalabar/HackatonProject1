package com.example.hackatonproject;

public class Task {
    public static final int TASK_TYPE_STEPS_COUNT = 0;
    public static final int TASK_TYPE_GO_TO_POINT = 1;

    private Integer id;
    private int type;
    private String value;
    private static TaskDBHelper dbHelper;

    public Task(int id, int type, String value){
    }
}
