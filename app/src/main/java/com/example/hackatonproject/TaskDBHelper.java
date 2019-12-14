package com.example.hackatonproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class TaskDBHelper extends SQLiteOpenHelper {

    public TaskDBHelper(Context context){
        super(context, "TasksDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tasks (id integer primary key autoincrement, type integer not null, value text not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tasks");
    }

    public void insert(Integer type, String value, Integer ratingReward, Integer pointsReward, Integer decision) {
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("value", value);
        cv.put("ratingReward", ratingReward);
        cv.put("pointsReward", pointsReward);
        cv.put("decision", type);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("tasks", null, cv);
    }
}
