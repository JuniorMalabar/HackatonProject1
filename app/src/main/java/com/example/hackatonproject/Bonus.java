package com.example.hackatonproject;

import java.util.ArrayList;

public class Bonus {
    private Integer id;
    private String organization;
    private String description;
    private String code;
    private Integer cost;

    public Bonus(String _organization, String _description, String _code, Integer _cost){
        organization = _organization;
        description = _description;
        code = _code;
        cost = _cost;
    }

    public static ArrayList<Bonus> getBonusesCatalog(){
        return null; // На самом деле нужно сгенерить каталог бонусов из бд
    }

    public Integer getCost() {
        return cost;
    }

    public String getOrganization() {
        return organization;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    // нужен метод для удаления бонуса
}
