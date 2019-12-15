package com.example.hackatonproject;

import android.app.AlarmManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class TabsHost extends TabActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private boolean running;
    public TextView username;

    @Override
    protected void onResume() {
        super.onResume();
        AppHelper.getInstance().refreshLocation(this);
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found, sorry", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host);
        username = (TextView) findViewById(R.id.username);
        username.setText( AppHelper.getInstance().getUser().getLogin() + "\nБаллы " +  AppHelper.getInstance().getUser().getPoints());
        TabHost tabHost = getTabHost();
        Task.regenerate();

        // Вкладка Info
        android.widget.TabHost.TabSpec historyTab = tabHost.newTabSpec("History");
        // устанавливаем заголовок и иконку
        historyTab.setIndicator("История полученных бонусов");
        // устанавливаем окно, которая будет показываться во вкладке
        Intent historyTabIntent = new Intent(this, MainActivity.class);
        historyTab.setContent(historyTabIntent);

        // Вкладка AllTasks
        android.widget.TabHost.TabSpec availableTasksTab = tabHost.newTabSpec("AllTasks");
        availableTasksTab.setIndicator("Доступные задания");
        Intent availableTasksIntent = new Intent(this, AllTasksTab.class);
        availableTasksTab.setContent(availableTasksIntent);

        // Вкладка AcceptedTasks
        android.widget.TabHost.TabSpec acceptedTasksTab = tabHost.newTabSpec("AcceptedTasks");
        acceptedTasksTab.setIndicator("Принятые задания");
        Intent acceptedTasksIntent = new Intent(this, AcceptedTasks.class);
        acceptedTasksTab.setContent(acceptedTasksIntent);

        // Вкладка Shop
        android.widget.TabHost.TabSpec shopTab = tabHost.newTabSpec("Shop");
        shopTab.setIndicator("Магазин промокодов");
        Intent shopIntent = new Intent(this, Shop.class);
        shopTab.setContent(shopIntent);

        // Вкладка Leaderboard
        android.widget.TabHost.TabSpec leaderboardTab = tabHost.newTabSpec("Leaderboard");
        leaderboardTab.setIndicator("Таблица лидеров");
        Intent leaderboardIntent = new Intent(this, Leaderboard.class);
        leaderboardTab.setContent(leaderboardIntent);

        // Добавляем вкладки в TabHost
        tabHost.addTab(availableTasksTab);
        tabHost.addTab(acceptedTasksTab);
        tabHost.addTab(historyTab);
        tabHost.addTab(shopTab);
        tabHost.addTab(leaderboardTab);

      //  AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        RecurringTasks recurringTasks = new RecurringTasks();
        recurringTasks.setRecurringTasks(this);


        AppHelper.getInstance().setTabHostContext(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void logOut (View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

