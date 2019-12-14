package com.example.hackatonproject;

import androidx.annotation.NonNull;

public class StepsCountTask extends Task {
    private Integer stepsLeft;

    public StepsCountTask(Integer _ratingReward, Integer _pointsReward, boolean _decision, Integer _steps){
        super(Task.TASK_TYPE_STEPS_COUNT, _ratingReward, _pointsReward,  _decision);
        stepsLeft = _steps;
        value = toString();
        id = saveTask();
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
}
