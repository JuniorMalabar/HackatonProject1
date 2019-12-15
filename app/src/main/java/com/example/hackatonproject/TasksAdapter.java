package com.example.hackatonproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Task> tasks;
    private boolean allTasks;
    private LayoutInflater lInflater;

    public TasksAdapter(Context context, ArrayList<Task> tasks, boolean allTasks) {
        this.context = context;
        this.tasks = new ArrayList<>(tasks);
        this.allTasks = allTasks;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.task_item, parent, false);
        }
        final Task task = (Task) getItem(position);

        TextView description = view.findViewById(R.id.description);
        description.setText(task.getDescription());

        Button decline = view.findViewById(R.id.decline);
        Button accept = view.findViewById(R.id.accept);
        Button showMap = view.findViewById(R.id.showInfo);
        if (allTasks){

            decline.setText("✗");
            decline.setTextColor(Color.RED);
            View.OnClickListener declineListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.declineTask();
                    notifyDataSetChanged();
                }
            };
            decline.setOnClickListener(declineListener);

            accept.setText("✓");
            accept.setTextColor(Color.GREEN);
            View.OnClickListener acceptListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.acceptTask();
                    notifyDataSetChanged();
                }
            };
            accept.setOnClickListener(acceptListener);

            if (task.type == Task.TASK_TYPE_GO_TO_POINT || task.type == Task.TASK_TYPE_GO_TO_ROUTE){
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
            }
        }
        else{
            accept.setVisibility(View.GONE);
            decline.setVisibility(View.GONE);
            showMap.setVisibility(View.GONE);
        }

        return view;
    }
}
