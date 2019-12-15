package com.example.hackatonproject;

import java.util.Map;


public class GlobalRequest {

    final static String url = "http://u0896591.plsk.regruhosting.ru/";

    public static String GenerateQuery(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        String _ret = "?";
        boolean begin = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!begin) {
                _ret += "&";
            }
            begin = false;
            _ret += (entry.getKey() + "=" + entry.getValue());
        }


        return _ret.replace(' ', '+');
    }

}
