package izi.meteo.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import javax.xml.datatype.Duration;

import izi.meteo.R;

/**
 * Created by Antoine on 05/02/2015.
 */
public class ExternalData {

    private static ConnectivityManager  mCoManager;
    private static LocationManager mGps;


//**********CHECK INTERNET CONNEXION**********
    public static boolean checkDataNetwork(Context mContext) {
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

    //**********CHECK GPS STATUS [ENABLED OR NOT]**********
    public static boolean checkLocation(Context mContext){
         mGps=(LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return mGps.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    public static String getLocation(Context mContext){
        Geocoder gcd = new Geocoder(mContext, Locale.FRANCE);
        LocationManager lm = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double lng = location.getLongitude();
        double lat = location.getLatitude();
        try {
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0)
               Log.e("adr",addresses.get(0).getLocality());
        }catch (Exception e){
            Log.e("adr","erreur");
        }

        return "";
    }
}


