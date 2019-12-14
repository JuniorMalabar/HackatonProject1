package com.example.hackatonproject;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class StepsCountTask extends Task {
    private Integer stepsLeft;

    public StepsCountTask(Integer _id, String _value, Integer _ratingReward, Integer _pointsReward, boolean _decision){
        super(Task.TASK_TYPE_STEPS_COUNT, _ratingReward, _pointsReward,  _decision);
        value = _value;
        parseValueString();
        id = _id;
    }

    public StepsCountTask(Integer _ratingReward, Integer _pointsReward, boolean _decision, Integer _steps){
        super(Task.TASK_TYPE_STEPS_COUNT, _ratingReward, _pointsReward,  _decision);
        stepsLeft = _steps;
        value = toString();
        id = saveTask();
    }

    public static ArrayList<StepsCountTask> getAllStepsCountTasks() {
        return new ArrayList<>(dbHelper.getStepsCountTasks());
    }

    @NonNull
    @Override
    public String toString() {
        return stepsLeft.toString();
    }

    @Override
    public void parseValueString() {
        stepsLeft = Integer.parseInt(value);
    }

    @Override
    public void progressCompletion(String _value) {
        stepsLeft -= Integer.parseInt(_value);
        if (stepsLeft <= 0){
            finishCompletion();
        }
    }

    @Override
    public String getDescription() {
        return "Пройти шагов: " + stepsLeft.toString();
    }
}
