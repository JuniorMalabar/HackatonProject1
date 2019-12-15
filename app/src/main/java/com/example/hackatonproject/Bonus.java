package com.example.hackatonproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Bonus {
    private Integer id;
    private String organization;
    private String description;
    private String code;
    private Integer cost;

    public Bonus(String _organization, String _description, String _code, Integer _cost) {
        organization = _organization;
        description = _description;
        code = _code;
        cost = _cost;
    }

    public Bonus(Integer id, String _organization, String _description, String _code, Integer _cost) {
        this.id = id;
        organization = _organization;
        description = _description;
        code = _code;
        cost = _cost;
    }

    public static ArrayList<Bonus> getBonusesCatalog() {

        String query = RequestAsync.Url + "GetBonusesList.php";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        RequestAsync task = new RequestAsync(null);
        String value = null;
        try {
            value = task.execute(query).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String, String>[] Bonusmaps = gson.fromJson(value, HashMap[].class);
        ArrayList<Bonus> retArr = new ArrayList<Bonus>();

        for (HashMap<String, String> Bonusmap : Bonusmaps) {
            retArr.add(new Bonus(Integer.parseInt(Bonusmap.get("id")), Bonusmap.get("organozation_name"), Bonusmap.get("description"), Bonusmap.get("promo_code"), Integer.parseInt(Bonusmap.get("cost"))));
        }
        return retArr;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getId() {
        return this.id;
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
