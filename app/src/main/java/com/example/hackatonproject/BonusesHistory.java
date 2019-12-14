package com.example.hackatonproject;

import java.util.ArrayList;

public class BonusesHistory {
    private Integer userId;
    private ArrayList<Bonus> bonuses;

    public BonusesHistory(Integer _userId, ArrayList<Bonus> _bonuses){
        userId = _userId;
        bonuses = new ArrayList<>(_bonuses);
    }

    public void addBonus(Bonus bonus) {
        // добавление в бд
    }

    public ArrayList<Bonus> getBonuses() {
        return bonuses;
    }
}
