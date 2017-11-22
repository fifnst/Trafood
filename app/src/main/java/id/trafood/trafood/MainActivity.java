package id.trafood.trafood;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import id.trafood.trafood.Home.Fragment_Profil;
import id.trafood.trafood.Home.Fragment_Vaforite;

public class MainActivity extends AppCompatActivity  implements LocationListener {


    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private TextView latitude, longitude, address, wait;
    LocationManager locationManager;
    String locations;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        latitude = (TextView) findViewById(R.id.tvLat);
        longitude = (TextView) findViewById(R.id.tvLng);
        address = (TextView) findViewById(R.id.tvgoogleaddress);
        wait = (TextView) findViewById(R.id.waiting);
        progressBar = (ProgressBar) findViewById(R.id.pbPrgress);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
            if (getIntent().getStringExtra("LNGS") == null){
                latitude.setText("-6.939008");
                longitude.setText("107.740753");
            sendBundle();
            }
            if (getIntent().getStringExtra("LNGS") != null){
                sendBundle();
            }

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latt = location.getLatitude();
        double lngg = location.getLongitude();
        String lats = Double.toString(latt);
        String longs = Double.toString(lngg);
        latitude.setText(lats);
        longitude.setText(longs);

        //latitude.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        //longitude.setText("107.740753");
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            address.setText(addresses.get(0).getAddressLine(0));
        } catch (Exception e) {
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    private void sendBundle() {
        wait.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        String lats = latitude.getText().toString();
        String lngs = longitude.getText().toString();
        String road = address.getText().toString();

        Bundle bundle = new Bundle();

       /* if (getIntent().getStringExtra("NEAR") != null){
            bundle.putString("LNG", lngs);
            bundle.putString("LAT", lats );
            bundle.putString("NAME", road);
        } */

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
            locations = getIntent().getStringExtra("NAME");
            bundle.putString("NAME", locations);
        }
        if (getIntent().getStringExtra("NAME") == null){
            bundle.putString("NAME", "Nearest");
        }

        final Home_fragmet fraghom = new Home_fragmet();
        fraghom.setArguments(bundle);
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
                    case R.id.navigation_favorite:
                        fragment = new Fragment_Vaforite();
                        break;
                    case R.id.navigation_profil:
                        fragment = new Fragment_Profil();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        finishAffinity();
        super.onBackPressed();
    }


}
