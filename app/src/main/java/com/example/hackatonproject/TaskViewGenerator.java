package com.example.hackatonproject;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class TaskViewGenerator {

    public TaskViewGenerator(){}

    public LinearLayout generateTaskView(Context context){
        LinearLayout taskView = new LinearLayout(context);
        taskView.setGravity(Gravity.CENTER);

        Button decline = new Button(context);
        decline.setText("✗");
        decline.setTextColor(Color.RED);

        Button accept = new Button(context);
        accept.setText("✓");
        accept.setTextColor(Color.GREEN);

        taskView.addView(accept);
        taskView.addView(decline);

        return taskView;
    }
}
