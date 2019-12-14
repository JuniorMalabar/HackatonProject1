package com.example.hackatonproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class Shop extends ListActivity {
    private ListView listView;
    private ArrayList<Bonus> bonuses;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        listView = findViewById(android.R.id.list);
        bonuses = new ArrayList<>(Bonus.getBonusesCatalog());
        BonusesAdapter adapter = new BonusesAdapter(this, bonuses);
        listView.setAdapter(adapter);
    }
}




