package com.example.hackatonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ListView listView;
    private ArrayList<Bonus> bonuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bonuses = AppHelper.getInstance().getUser().getHistory();
        listView = findViewById(R.id.bonusesList);
        AppHelper.getInstance().generateHistoryAdapter(this);
        listView.setAdapter(AppHelper.getInstance().historyAdapter);
    }
}
