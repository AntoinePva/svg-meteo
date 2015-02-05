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
public class DataController extends AsyncTask<Void, Void, Void> {
<<<<<<< HEAD
    //    private static final String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=city,country#sthash.Q7DzQboM.dpuf";

=======
>>>>>>> origin/Antoine

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
<<<<<<< HEAD
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
=======
>>>>>>> origin/Antoine
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
<<<<<<< HEAD
=======

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }
>>>>>>> origin/Antoine
}
