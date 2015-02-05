package izi.meteo.Data;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Antoine on 04/02/2015.
 */
public class Controller extends AsyncTask<String, Void, String> {

    private static final String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=city,country#sthash.Q7DzQboM.dpuf";
    private InputStream in;
    private  int responseCode;

    @Override
    protected void onPreExecute() {
            
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {


        try {
            URL url = new URL(weatherUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
             responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = httpConnection.getInputStream();
                Log.e("OK", "OK");
            }
        } catch (MalformedURLException e) {

        } catch (IOException i) {

        }
        Log.e("PASOK", ""+responseCode);
        return "ok"
                ;
    }


}
