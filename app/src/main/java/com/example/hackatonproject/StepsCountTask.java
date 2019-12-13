package com.example.hackatonproject;

public class StepsCountTask extends Task {
    private Integer stepsLeft;

    public StepsCountTask(int _id, Integer _ratingReward, Integer _pointsReward, boolean _decision, Integer _steps){
        super(_id, Task.TASK_TYPE_STEPS_COUNT, _ratingReward, _pointsReward,  _decision);
        stepsLeft = _steps;
        super.setValueString(toString());
        super.saveTask();
    }

    @Override
    public void parseValueString() {

    }
}
