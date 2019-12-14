package com.example.hackatonproject;
import android.os.AsyncTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RequestAsync extends AsyncTask<String, Void, String> {
    Map<String, String> postData;
    String tempStr;
    final static String Url = "http://u0896591.plsk.regruhosting.ru/";

    // This is a constructor that allows you to pass in the JSON body
    public RequestAsync(Map<String, String> postData) {
        if (postData != null) {
            this.postData = (postData);
        }
    }

    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @Override
    protected String doInBackground(String... params) {

        try {
            // This is getting the url from the string we passed in
            URL url = new URL(params[0] + GlobalRequest.GenerateQuery(postData));

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();
            StringBuilder sb = new StringBuilder();
            if (statusCode == 200) {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
        }
        return null;
    }


}