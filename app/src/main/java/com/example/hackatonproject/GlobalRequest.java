package com.example.hackatonproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GlobalRequest {
    final static String url = "http://u0896591.plsk.regruhosting.ru/";
    private final static OkHttpClient client = new OkHttpClient();
//    RequestBody formBody = new FormBody.Builder()
//            .add("search", "Jurassic Park")
//            .build();


    public static String GetOrganizationCoords() {
        Request request = new Request.Builder()
                .url(url + "GetCoords.php")
                .build();
        Response response;
        String ret = "";
        {
            try {
                response = client.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static String GetBonusesList() {
        Request request = new Request.Builder()
                .url(url + "GetBonusesList.php")
                .build();
        Response response;
        String ret = "";
        {
            try {
                response = client.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static boolean CheckAccess(RequestBody formBody) {
        Request request = new Request.Builder()
                .url(url + "ProvideAccess.php")
                .post(formBody)
                .build();
        Response response;
        String ret = "";
        {
            try {
                response = client.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(ret, Boolean.class);
    }

    public static boolean RegisterUser(RequestBody formBody) {
        Request request = new Request.Builder()
                .url(url + "RegisterUser.php")
                .post(formBody)
                .build();
        Response response;
        String ret = "";
        {
            try {
                response = client.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(ret, Boolean.class);
    }

    public static String TestPost(RequestBody formBody) {
        Request request = new Request.Builder()
                .url(url + "ShowRequest.php")
                .post(formBody)
                .build();
        Response response;
        String ret = "";
        {
            try {
                response = client.newCall(request).execute();
                ret = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}
