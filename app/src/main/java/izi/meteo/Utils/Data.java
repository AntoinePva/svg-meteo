package izi.meteo.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telecom.TelecomManager;
import android.util.Log;

/**
 * Created by Antoine on 05/02/2015.
 */
public class Data {
    //**********************************************************************
//********************Check Internet Connectivity***********************
//**********************************************************************
    private ConnectivityManager mCoManager, mMobile;
    private NetworkInfo mNetwork;
    WifiManager mWifi;

    public Data(Context c) {
        mCoManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        mMobile = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.e("TELECOM", mMobile.isDefaultNetworkActive() + "");// verif si données mobile = ok
    }



    private void dataManager() {
        NetworkInfo.State mCoState = mCoManager.getActiveNetworkInfo().getState();

        if (!mCoState.equals(NetworkInfo.State.CONNECTED)) {
            if (!mWifi.isWifiEnabled()) {
                mWifi.setWifiEnabled(true);
                dataManager();
            } else {
                if ( !mMobile.isDefaultNetworkActive()) {

                }
            }
        }
    }

}
