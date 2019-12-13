package com.example.hackatonproject;

import java.util.ArrayList;

public class BonusesHistory {
    private Integer userId;
    private ArrayList<Bonus> bonuses;

    public BonusesHistory(Integer _userId, ArrayList<Bonus> _bonuses){
        userId = _userId;
        bonuses = new ArrayList<>(bonuses);
    }
}
