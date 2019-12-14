package com.example.hackatonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Leaderboard extends AppCompatActivity {
    private ListView listView;
    private ArrayList<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        listView = findViewById(android.R.id.list);
        users = User.getUsersByRating();
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, users, AppHelper.getInstance().getUserPlace());
        listView.setAdapter(adapter);
    }
}
