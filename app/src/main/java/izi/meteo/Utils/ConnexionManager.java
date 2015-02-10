package izi.meteo.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by Antoine on 10/02/2015.
 */
public class ConnexionManager implements LocationListener {
    private ConnectivityManager mCoManager;
    private LocationManager mGps;
    private Location location;
    private static ConnexionManager instance;
    private double lat, lng;
    public static String CURRENT_TOWN;

    private ConnexionManager() {
        CURRENT_TOWN = null;
    }

    //**********CHECK INTERNET CONNEXION**********
    public boolean checkDataNetwork(Context mContext) {
        mCoManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public String getLocation(Context mContext) {
        mGps = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

//        If GPS location is enabled
        if (mGps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {

                mGps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
                Log.e("lng lat", "OK" + lng + "     fgfghfh " + lat);
                List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
                if (addresses.size() > 0) {
//                  Return current city
                    return addresses.get(0).getLocality();
                } else return null;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }


    }


    public static ConnexionManager getInstance() {
        if (instance == null)
            instance = new ConnexionManager();
        return instance;
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        String Text = "My current location is: " +
                "Latitud = " + location.getLatitude() +
                "Longitud = " + location.getLongitude();

      Log.e("GPS",Text);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("PROVIDER", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("PROVIDER", "enable");


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("Latitude", "status");
    }
}
