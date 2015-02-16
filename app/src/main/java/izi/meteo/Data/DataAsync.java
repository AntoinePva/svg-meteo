package izi.meteo.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Antoine on 04/02/2015.
 */
public class DataAsync extends AsyncTask<DataModel, Void, DataModel> {

    private Context mContext;
    private static String url = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("preExecute","loader antoine");
    }

    @Override
    protected DataModel doInBackground(DataModel... params) {
        Log.e("doInBackground",this.getWeatherData(params[0].getName()));
        DataModel newModel = new DataModel(params[0].getName());
        return newModel;
    }

    @Override
    protected void onPostExecute(DataModel newModel) {
        super.onPostExecute(newModel);
        Log.e("postExecute",newModel.getName());
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.e("onProgressUpdate","progress");
    }

    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(this.url + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }
}
