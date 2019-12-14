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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    boolean running = false;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.steps);

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
            textView.setText(String.valueOf(event.values[0]));

            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            int stepsPassed = sharedPreferences.getInt("stepsGone", -1);
            if (stepsPassed != -1) {
                int diff = (int) event.values[0] - stepsPassed;
                //убавление разницы у тасков
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
