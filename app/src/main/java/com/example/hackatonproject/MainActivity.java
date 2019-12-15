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

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean running = false;
    private ListView listView;
    private ArrayList<Bonus> bonuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bonuses = new ArrayList<>(AppHelper.getInstance().getUser().getBonuses());
        listView = findViewById(R.id.bonusesList);
        BonusesAdapter adapter = new BonusesAdapter(this, bonuses);
        listView.setAdapter(adapter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found, sorry", Toast.LENGTH_SHORT).show();
        }
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        running = false;
//
//    } @Override
//    protected void onStop() {
//        super.onStop();
//        running = false;
//
//    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            Integer stepsPassed = sharedPreferences.getInt("stepsGone", -1);
            if (stepsPassed != -1) {
                Integer diff = (int) event.values[0] - stepsPassed;
                for(StepsCountTask task : StepsCountTask.getAllStepsCountTasks()){
                    task.progressCompletion(diff.toString());
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putInt("stepsGone", (int) event.values[0]);
                editor.apply();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
