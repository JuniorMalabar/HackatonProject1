package com.example.hackatonproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BonusesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Bonus> bonuses;
    private LayoutInflater lInflater;
    private boolean forShop;

    public BonusesAdapter(Context context, ArrayList<Bonus> bonuses, boolean forShop) {
        this.context = context;
        this.bonuses = bonuses;
        this.forShop = forShop;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bonuses.size();
    }

    @Override
    public Object getItem(int position) {
        return bonuses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            if (forShop){
                view = lInflater.inflate(R.layout.bonus_view, parent, false);
            }
            else{
                view = lInflater.inflate(R.layout.history_item, parent, false);
            }
        }
        final Bonus bonus = (Bonus) getItem(position);
        if (forShop){
            TextView organization = view.findViewById(R.id.organization);
            TextView description = view.findViewById(R.id.description);
            TextView cost = view.findViewById(R.id.cost);
            Button buyBtn = view.findViewById(R.id.buyBtn);
            organization.setText(bonus.getOrganization());
            description.setText(bonus.getDescription());
            cost.setText(bonus.getCost().toString());
            final View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if (AppHelper.getInstance().getUser().tryToPurchaseBonus(bonus)){
                       bonuses.remove(bonus);
                       notifyDataSetChanged();
                   }
                }
            };
            cost.setOnClickListener(listener);
        }
        else{
            TextView organization = view.findViewById(R.id.organization);
            TextView description = view.findViewById(R.id.description);
            TextView code = view.findViewById(R.id.code);
            organization.setText(bonus.getOrganization());
            description.setText(bonus.getDescription());
            code.setText(bonus.getCode());
        }
        return view;
    }
}
