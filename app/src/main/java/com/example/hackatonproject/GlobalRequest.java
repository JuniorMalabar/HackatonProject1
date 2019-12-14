package com.example.hackatonproject;

import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


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
