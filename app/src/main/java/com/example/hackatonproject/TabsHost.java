package com.example.hackatonproject;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabsHost extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_host);

        TabHost tabHost = getTabHost();

        // Вкладка Info
        android.widget.TabHost.TabSpec infoTab = tabHost.newTabSpec("Info");
        // устанавливаем заголовок и иконку
        infoTab.setIndicator("Основная информация");
        // устанавливаем окно, которая будет показываться во вкладке
        Intent infoTabIntent = new Intent(this, MainActivity.class);
        infoTab.setContent(infoTabIntent);

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
        tabHost.addTab(infoTab);
        tabHost.addTab(availableTasksTab);
        tabHost.addTab(acceptedTasksTab);
        tabHost.addTab(shopTab);
        tabHost.addTab(leaderboardTab);
    }
}

