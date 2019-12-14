package com.example.hackatonproject;

import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Random;

public abstract class Task {
    public static final int TASK_TYPE_STEPS_COUNT = 0;
    public static final int TASK_TYPE_GO_TO_POINT = 1;
    public static final int TASK_TYPE_GO_TO_ROUTE = 2;
    public static final int TASK_TYPE_COUNT = 3;

    protected Integer id;
    protected Integer type;
    protected String value;
    protected Integer ratingReward;
    protected Integer pointsReward;
    protected boolean decision;
    protected static TaskDBHelper dbHelper;

    public Task(int _id, int _type, String _value, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        id = _id;
        type = _type;
        value = _value;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        decision = _decision;
    }

    public Task(int _type, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        type = _type;
        value = null;
        ratingReward = _ratingReward;
        pointsReward = _pointsReward;
        decision = _decision;
    }

    public static void setDBHelper(Context context) {
        dbHelper = new TaskDBHelper(context);
    }

    public static ArrayList<Task> getAllTasks() {
        return new ArrayList<>(dbHelper.getAllTasks());
    }

    public TaskDBHelper getDBHelper(){
        return dbHelper;
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

    public void finishCompletion(){
        User user = AppHelper.getInstance().getUser();
        user.giveRatingReward(ratingReward);
        user.givePointsReward(pointsReward);
        Context context = AppHelper.getInstance().getTabHostContext();
        final AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
        alertbox.setTitle("Задание выполнено!");
        String TextToast = "Задание " + getDescription() + " успешно выполнено!\n" + getPointsReward() + " очков начислено на ваш счёт!";
        alertbox.setMessage(TextToast);

        alertbox.setNeutralButton("Ок", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.cancel();
            }
        });

        alertbox.show();

        dbHelper.delete(this);
    }

    public static Task generateTask(){
        Random random = new Random();
        int _type = random.nextInt(TASK_TYPE_COUNT);
        switch (_type){
            case TASK_TYPE_STEPS_COUNT:
                int steps = random.nextInt(5001) + 5000;
                return new StepsCountTask(steps / 10, (int) Math.round(steps * AppHelper.getInstance().getUser().getMultiplier() / 50), false, steps);
            case TASK_TYPE_GO_TO_POINT:
                int probability = random.nextInt(100);
                Organization organization = Organization.getRandomPoint();
                ArrayList<Location> list = new ArrayList<>();
                if (probability < 25){
                    list.add(organization.getLocation());
                    Integer distance = organization.distanceFromCurrentLocation();
                    return new PointsVisitTask(_type, distance / 20, (int) Math.round(distance * AppHelper.getInstance().getUser().getMultiplier() / 100), false, list);
                }
                else{
                    int partition = random.nextInt(100);
                    Location location = new Location(LocationManager.GPS_PROVIDER);
                    location.setLatitude((organization.getLocation().getLatitude()*partition)/100);
                    location.setLongitude((organization.getLocation().getLongitude()*partition)/100);
                    Integer distance = organization.distanceFromCurrentLocation();
                    list.add(organization.getLocation());
                    return new PointsVisitTask(_type, distance / 20, (int) Math.round(distance * AppHelper.getInstance().getUser().getMultiplier() / 100), false, list);
                }
            case TASK_TYPE_GO_TO_ROUTE:
                int pointsNum = random.nextInt(3) + 2;
                Integer distance = 0;
                list = new ArrayList<>();
                for (int i = 0; i < pointsNum; i++) {
                    probability = random.nextInt(100);
                    organization = Organization.getRandomPoint();
                    if (probability < 25){
                        list.add(organization.getLocation());
                        distance += organization.distanceFromCurrentLocation();
                    }
                    else{
                        int partition = random.nextInt(100);
                        Location location = new Location(LocationManager.GPS_PROVIDER);
                        location.setLatitude((organization.getLocation().getLatitude()*partition)/100);
                        location.setLongitude((organization.getLocation().getLongitude()*partition)/100);
                        distance = organization.distanceFromCurrentLocation();
                        list.add(organization.getLocation());
                    }
                }
                return new PointsVisitTask(_type, distance / 20, (int) Math.round(distance * AppHelper.getInstance().getUser().getMultiplier() / 100), false, list);
            default:
                break;
        }
        return null;
    }

    public Integer saveTask(){
        return dbHelper.insert(type, value, ratingReward, pointsReward, decision ? 1 : 0);
    }

    public void declineTask(){

    }

    public void acceptTask(){

    }

    public abstract void parseValueString();
    public abstract void progressCompletion(String _value);
    public abstract String getDescription();
}

