package com.example.hackatonproject;

import android.app.ListActivity;
import android.os.Bundle;


public class TasksView extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_tab);
    }
}

