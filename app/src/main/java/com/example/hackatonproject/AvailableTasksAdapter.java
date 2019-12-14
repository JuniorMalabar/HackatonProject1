package com.example.hackatonproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AvailableTasksAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Task> tasks;

    public AvailableTasksAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Task task = (Task) getItem(position);

        final LinearLayout taskView = new LinearLayout(context);
        taskView.setGravity(Gravity.CENTER);

        TextView description = new TextView(context);
        description.setText(task.getDescription());

        final Button decline = new Button(context);
        decline.setText("✗");
        decline.setTextColor(Color.RED);
        View.OnClickListener declineListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.declineTask();
            }
        };
        decline.setOnClickListener(declineListener);

        Button accept = new Button(context);
        accept.setText("✓");
        accept.setTextColor(Color.GREEN);
        View.OnClickListener acceptListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.acceptTask();
            }
        };
        accept.setOnClickListener(acceptListener);
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
