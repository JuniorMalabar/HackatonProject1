package com.example.hackatonproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDBHelper extends SQLiteOpenHelper {

    public TaskDBHelper(Context context){
        super(context, "TasksDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tasks (_id integer primary key autoincrement, type integer not null, value text not null, ratingReward integer not null, pointsReward integer not null, decision integer not null);");
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
        cv.put("decision", decision);
        SQLiteDatabase db = getWritableDatabase();
        return (int)db.insert("tasks", null, cv);
//        db = getReadableDatabase();
//        Cursor cursor = db.query("tasks", new String[] {"_id"}, "type = ? and value = ? and ratingReward = ? and pointsReward = ? and decision = ?", new String[] {type.toString(), value, ratingReward.toString(), pointsReward.toString(), decision.toString()}, null, null, null);
//        cursor.moveToFirst();
//        return cursor.getInt(cursor.getColumnIndex("_id"));
    }

    public void delete(Task task) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tasks", "_id = ?", new String[] {task.id.toString()});
    }

    public ArrayList<StepsCountTask> getStepsCountTasks() {
        ArrayList<StepsCountTask> tasks = new ArrayList<>();
        Integer type = Task.TASK_TYPE_STEPS_COUNT;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("tasks", new String[] {"*"}, "type = ?", new String[] {type.toString()}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String value = cursor.getString(cursor.getColumnIndex("value"));
                int ratingReward = cursor.getInt(cursor.getColumnIndex("ratingReward"));
                int pointsReward = cursor.getInt(cursor.getColumnIndex("pointsReward"));
                boolean decision = cursor.getInt(cursor.getColumnIndex("decision")) == 1;
                tasks.add(new StepsCountTask(id, value, ratingReward, pointsReward, decision));
            }
        }
        return tasks;
    }

    public ArrayList<PointsVisitTask> getPointsTasks() {
        ArrayList<PointsVisitTask> tasks = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Integer type1 = Task.TASK_TYPE_GO_TO_POINT;
        Integer type2 = Task.TASK_TYPE_GO_TO_ROUTE;
        Cursor cursor = db.query("tasks", new String[] {"*"}, "type = ? or type = ?", new String[] {type1.toString(), type2.toString()}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String value = cursor.getString(cursor.getColumnIndex("value"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int ratingReward = cursor.getInt(cursor.getColumnIndex("ratingReward"));
                int pointsReward = cursor.getInt(cursor.getColumnIndex("pointsReward"));
                boolean decision = cursor.getInt(cursor.getColumnIndex("decision")) == 1;
                tasks.add(new PointsVisitTask(id, type, value, ratingReward, pointsReward, decision));
            }
        }
        return tasks;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<StepsCountTask> stepsCountTasks = new ArrayList<>(getStepsCountTasks());
        ArrayList<PointsVisitTask> pointsVisitTasks = new ArrayList<>(getPointsTasks());
        ArrayList<Task> tasks = new ArrayList<>();
        for (StepsCountTask task : stepsCountTasks) {
            tasks.add(task);
        }
        for (PointsVisitTask task : pointsVisitTasks) {
            tasks.add(task);
        }
        return tasks;
    }

    public void recreateTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table if exists tasks");
        db.execSQL("create table tasks (_id integer primary key autoincrement, type integer not null, value text not null, ratingReward integer not null, pointsReward integer not null, decision integer not null);");
    }

    public void update(Integer id, String column, int value) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column, value);
        db.update("tasks", cv, "_id = ?", new String[] {id.toString()});
    }
}
