package com.example.hackatonproject;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskViewGenerator {

    public TaskViewGenerator(){}

    public LinearLayout generateTaskView(Context context, final Task task){
        LinearLayout taskView = new LinearLayout(context);
        taskView.setGravity(Gravity.CENTER);


        TextView description = new TextView(context);
        description.setText(task.getDescription());

        Button decline = new Button(context);
        decline.setText("✗");
        decline.setTextColor(Color.RED);
        View.OnClickListener declineListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.declineTask();
            }
        };
        decline.setOnClickListener(declineListner);

        Button accept = new Button(context);
        accept.setText("✓");
        accept.setTextColor(Color.GREEN);
        View.OnClickListener acceptListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.acceptTask();
            }
        };
        decline.setOnClickListener(acceptListner);

        taskView.addView(accept);
        taskView.addView(decline);
        taskView.addView(description);

        return taskView;
    }
}
