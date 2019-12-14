package com.example.hackatonproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class LeaderboardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> users;
    private LayoutInflater lInflater;

    public LeaderboardAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = new ArrayList<>(users);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.task_item, parent, false);
        }
        final User user = (User) getItem(position);
        return view;
    }
}
