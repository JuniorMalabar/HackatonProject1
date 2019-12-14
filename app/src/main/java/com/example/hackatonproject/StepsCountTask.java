package com.example.hackatonproject;

public class StepsCountTask extends Task {
    private Integer stepsLeft;

    public StepsCountTask(Integer _ratingReward, Integer _pointsReward, boolean _decision, Integer _steps){
        super(Task.TASK_TYPE_STEPS_COUNT, _ratingReward, _pointsReward,  _decision);
        stepsLeft = _steps;
        value = toString();
        saveTask();
    }

    @Override
    public void parseValueString() {

    }
}
