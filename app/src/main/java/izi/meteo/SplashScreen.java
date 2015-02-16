package izi.meteo;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SplashScreen extends Activity implements LocationListener {
    @InjectView(R.id.noProvider)
    TextView tv_noProvider;
    @InjectView(R.id.progress_location)
    ProgressBar pb_location;
    private ConnectivityManager mCoManager;
    public static String CURRENT_TOWN;
    private LocationManager mGps;
    private String mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.inject(this);
        mCoManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        mGps = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria mCriteria = new Criteria();
        mProvider = mGps.getBestProvider(mCriteria, false);
        mCriteria.setSpeedRequired(true);
        if(checkDataNetwork()){
            mGps.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            mGps.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }else if(mGps.isProviderEnabled(mProvider)){
            mGps.getLastKnownLocation(mProvider);
            mGps.requestLocationUpdates(mProvider, 0, 0, this);
        }


        viewUpdate(tv_noProvider,pb_location);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    private void viewUpdate(TextView tv_noProvider, ProgressBar pb_location) {

        if (mGps.isProviderEnabled(mProvider) || checkDataNetwork()) {
            tv_noProvider.setVisibility(View.GONE);
            pb_location.setVisibility(View.VISIBLE);

        } else {
            tv_noProvider.setVisibility(View.VISIBLE);
            pb_location.setVisibility(View.GONE);
        }
    }

    public boolean checkDataNetwork() {

        NetworkInfo mNetworkInfo = mCoManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) { //if mobile network is activated or wifi
            NetworkInfo.State mCoState = mNetworkInfo.getState();
            return mCoState.equals(NetworkInfo.State.CONNECTED);
        } else {
            return false;
        }
    }

    private void getTown(double lat, double lng) {

        try {
            Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0)
//                  Return current city
                CURRENT_TOWN = addresses.get(0).getLocality();
            Log.e("VILLE",CURRENT_TOWN);
        } catch (Exception e) {

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            getTown(location.getLatitude(), location.getLongitude());
            viewUpdate(tv_noProvider, pb_location);
            Log.e("OK",location.getLatitude()+location.getLongitude()+"");
        }
        mGps.removeUpdates(this);
        finish();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        mGps.requestLocationUpdates(mProvider,0,0,this);
        viewUpdate(tv_noProvider,pb_location);

    }

    @Override
    public void onProviderDisabled(String provider) {
        viewUpdate(tv_noProvider,pb_location);
    }
}
