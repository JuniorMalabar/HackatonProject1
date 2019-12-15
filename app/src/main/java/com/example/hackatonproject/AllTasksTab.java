package com.example.hackatonproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class AllTasksTab extends ListActivity {
    private ListView listView;
    private ArrayList<Task> tasks;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_tab);
        tasks = new ArrayList<>(Task.getAllTasks());
        listView = findViewById(android.R.id.list);
        TasksAdapter adapter = new TasksAdapter(this, tasks, true);
        listView.setAdapter(adapter);
    }
}




