package com.example.hackatonproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public Integer insert(Integer type, String value, Integer ratingReward, Integer pointsReward, Integer decision) {
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("value", value);
        cv.put("ratingReward", ratingReward);
        cv.put("pointsReward", pointsReward);
        cv.put("decision", type);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("tasks", null, cv);
        Cursor cursor = db.query("tasks", new String[] {"id"}, "type = ? and value = ? and ratingReward = ? and pointsReward = ? and decision = ?", new String[] {type.toString(), value, ratingReward.toString(), pointsReward.toString(), decision.toString()}, null, null, null);
        return cursor.getInt(cursor.getColumnIndex("id"));
    }
}
