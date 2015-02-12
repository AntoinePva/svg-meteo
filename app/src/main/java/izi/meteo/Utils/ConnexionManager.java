package izi.meteo.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Locale;

import izi.meteo.DisplayWeather;
import izi.meteo.R;

/**
 * Created by Antoine on 10/02/2015.
 */
public class ConnexionManager extends AsyncTask<Void, Void, Void> implements LocationListener {
    private ConnectivityManager mCoManager;
    private LocationManager mGps;
    private String mProvider;
    public static String CURRENT_TOWN;
    private Criteria mCriteria;
    private Context mContext;
    private boolean doTask;
    private Activity mActivity;
    ProgressBar pb_location;
    LinearLayout ll_retry;
    private double lat, lng;

    public ConnexionManager(Context c) {
        mContext = c;
        CURRENT_TOWN = null;
        mCoManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        mGps = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        mCriteria = new Criteria();
        mProvider = mGps.getBestProvider(mCriteria, false);
        mCriteria.setSpeedRequired(true);
        mGps.getLastKnownLocation(mProvider);
        mActivity = (Activity) mContext;
    }

    //**********CHECK INTERNET CONNEXION**********
    public boolean checkDataNetwork() {

        NetworkInfo mNetworkInfo = mCoManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) { //if mobile network is activated or wifi
            NetworkInfo.State mCoState = mNetworkInfo.getState();
            if (!mCoState.equals(NetworkInfo.State.CONNECTED)) //if network can access to the Internet
                return false;
            else
                return true;
        } else {
            return false;
        }
    }

//    @Override
//    public void onLocationChanged(Location location) {

//        String Text = "My current location is: " +
//                "Latitud = " + location.getLatitude() +
//                "Longitud = " + location.getLongitude();
//
//        Log.e("GPS", Text);
//        try {
//            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
//            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
//            if (addresses.size() > 0) {
////                  Return current city
//                CURRENT_TOWN=addresses.get(0).getLocality();
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        Log.e("PROVIDER", "disable");
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        Log.e("PROVIDER", "enable");
//
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        Log.e("Latitude", "status");
//    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pb_location.setVisibility(View.GONE);
        if (doTask) ll_retry.setVisibility(View.GONE);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        doTask = false;
        pb_location = (ProgressBar) mActivity.findViewById(R.id.progress_location);
        ll_retry = (LinearLayout) mActivity.findViewById(R.id.retry);

        if (mGps.isProviderEnabled(mProvider) && checkDataNetwork()) {
            ll_retry.setVisibility(View.GONE);
            doTask = true;
            pb_location.setVisibility(View.VISIBLE);
            mGps.requestLocationUpdates(mProvider, 1000, 0, this);

        } else {
            ll_retry.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (doTask) {
            Location loc = mGps.getLastKnownLocation(mProvider);
            if (loc != null) {
                lat = loc.getLatitude();
                lng = loc.getLongitude();
                getTown();
            } else {
                while (CURRENT_TOWN == null) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        getTown();
        mGps.removeUpdates(this);
    }

    public void getTown() {
        try {
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
//                  Return current city
                CURRENT_TOWN = addresses.get(0).getLocality();
                Intent intent = new Intent(mContext, DisplayWeather.class);
                mContext.startActivity(intent);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
