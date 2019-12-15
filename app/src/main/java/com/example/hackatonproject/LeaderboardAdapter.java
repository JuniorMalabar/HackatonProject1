package com.example.hackatonproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderboardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> users;
    private LayoutInflater lInflater;
    private Integer currentUserPlace;

    public LeaderboardAdapter(Context context, ArrayList<User> users, Integer place) {
        this.context = context;
        this.users = new ArrayList<>(users);
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentUserPlace = place;
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
            view = lInflater.inflate(R.layout.leader_item, parent, false);
        }
        final User user = (User) getItem(position);
        TextView place = view.findViewById(R.id.place);
        TextView name = view.findViewById(R.id.name);
        TextView ratingPoints = view.findViewById(R.id.ratingPoints);
        if (position == 10){
            if (currentUserPlace <= 10)
                return view;
            place.setTextColor(Color.YELLOW);
            name.setTextColor(Color.YELLOW);
            ratingPoints.setTextColor(Color.YELLOW);
            place.setText(currentUserPlace);
        }
        else {
            if (position == getCount()-1){
                return view;
            }
            if (position == currentUserPlace-1){
                place.setTextColor(Color.YELLOW);
                name.setTextColor(Color.YELLOW);
                ratingPoints.setTextColor(Color.YELLOW);
                place.setText(Integer.toString(currentUserPlace));
            }
            else{
                place.setText(position+1);
            }
        }
        name.setText(user.getLogin());
        ratingPoints.setText(user.getRating().toString());
        return view;
    }
}
