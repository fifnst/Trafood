package id.trafood.trafood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Location mLastlocation;
    private GoogleApiClient googleApiClient;
    private TextView getmTextMessage;
    ViewPager myviewPagerHome;
    TabLayout mytabLayoutHome;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    String location;
    private TextView mTextMessage,latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        latitude = (TextView) findViewById(R.id.tvLat);
        longitude = (TextView) findViewById(R.id.tvLng);


        setUpGoogleApi();
    }

    private void setUpGoogleApi() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mLastlocation == null ){
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLastlocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                dataLatLng();

            }
        }
    }

    private void dataLatLng() {
        double latt = mLastlocation.getLatitude();
        double lngg = mLastlocation.getLongitude();
        String lats = Double.toString(latt);
        String longs = Double.toString(lngg);
        latitude.setText(lats);
        longitude.setText(longs);

        sendBundle();
    }

    private void sendBundle() {
        String lats = latitude.getText().toString();
        String lngs = longitude.getText().toString();

        Bundle bundle = new Bundle();

        if (getIntent().getStringExtra("NEAR") != null){
            bundle.putString("LNG", lngs);
            bundle.putString("LAT", lats );
            bundle.putString("NAME", "Nearest");
        }

        if (getIntent().getStringExtra("LNGS") != null){
            String lng = getIntent().getStringExtra("LNGS");
            bundle.putString("LNG", lng);
        }
        if (getIntent().getStringExtra("LNGS") == null){

            bundle.putString("LNG", lngs);
        }
        if (getIntent().getStringExtra("LATS") != null){
            String lat = getIntent().getStringExtra("LATS");
            bundle.putString("LAT", lat);
        }
        if (getIntent().getStringExtra("LATS") == null){

            bundle.putString("LAT", lats );
        }
        if (getIntent().getStringExtra("NAME") != null){
            location = getIntent().getStringExtra("NAME");
            bundle.putString("NAME", location);
        }
        if (getIntent().getStringExtra("NAME") == null){
            bundle.putString("NAME", "Nearest");
        }

        final Home_fragmet fraghom = new Home_fragmet();
        fraghom.setArguments(bundle);
        //bottomNavigationView.inflateMenu(R.menu.navigation);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fraghom).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_home:
                        fragment = fraghom;
                        break;
                    case R.id.navigation_dashboard:
                        fragment = new Home_Restaurant_Fragment();
                        break;
                    case R.id.navigation_notifications:
                        fragment = new Home_Article_Fragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
