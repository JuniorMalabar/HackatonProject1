package com.example.hackatonproject;

public class Bonus {
    private String organization;
    private String description;
    private String code;
    private Integer cost;

    public Bonus(String _description, String _code, Integer _cost){
        description = _description;
        code = _code;
        cost = _cost;
    }
}
