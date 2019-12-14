package com.example.hackatonproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskViewGenerator {

    public TaskViewGenerator(){}

    public LinearLayout generateTaskView(final Context context, final Task task){
        final LinearLayout taskView = new LinearLayout(context);
        taskView.setGravity(Gravity.CENTER);

        TextView description = new TextView(context);
        description.setText(task.getDescription());

        final Button decline = new Button(context);
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
        if (task.type == Task.TASK_TYPE_GO_TO_POINT || task.type == Task.TASK_TYPE_GO_TO_ROUTE){
            Button showMap = new Button(context);
            View.OnClickListener mapConnector = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("type",task.type);
                    intent.putExtra("value",task.value);
                    context.startActivity(intent);
                }
            };
            showMap.setOnClickListener(mapConnector);

            taskView.addView(showMap);
        }


        taskView.addView(accept);
        taskView.addView(decline);
        taskView.addView(description);

        return taskView;
    }
}
