
package com.example.hackatonproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class AcceptedTasks extends ListActivity {
    private ListView listView;
    private ArrayList<Task> acceptedTasks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_tasks);
        listView = findViewById(android.R.id.list);
        acceptedTasks = Task.getAcceptedTasks();
        TasksAdapter adapter = new TasksAdapter(this, acceptedTasks, false);
        listView.setAdapter(adapter);
//        listView = new ListView(this);
//        listView.setId(android.R.id.list);
//        ll = findViewById(R.id.ll);
//        ll.addView(listView);

    }
}




