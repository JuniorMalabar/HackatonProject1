
package com.example.hackatonproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;


public class AcceptedTasks extends ListActivity {

    ListView listView;
    LinearLayout ll;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_tasks);
//        listView = new ListView(this);
//        listView.setId(android.R.id.list);
//        ll = findViewById(R.id.ll);
//        ll.addView(listView);

    }
}




