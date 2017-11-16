package id.trafood.trafood;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;


import java.util.ArrayList;

import id.trafood.trafood.Home.HomePagerAdapater;

public class HomeActivity extends AppCompatActivity /* implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener */ {

    private Location mLastlocation;
    private GoogleApiClient googleApiClient;
    ViewPager viewPager;
    TabLayout tableLayout;
    TextView editText,latitude, longitude,location;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.vpHome);
        tableLayout = (TabLayout) findViewById(R.id.tlHome);
        editText = (TextView) findViewById(R.id.tvSearchHome);
        latitude = (TextView) findViewById(R.id.latHome);
        longitude = (TextView) findViewById(R.id.lngHome);
        location = (TextView) findViewById(R.id.tvLocation);

        dataLatLng();

      //  setUpGoogleApi();
    }

   /* private void setUpGoogleApi() {
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

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    } */

    private void dataLatLng() {
        double latt = mLastlocation.getLatitude();
        double lngg = mLastlocation.getLongitude();
        final String lats = Double.toString(latt);
        final String longs = Double.toString(lngg);

        String message = getIntent().getStringExtra("MESSAGE");
        final String lat = getIntent().getStringExtra("LATS");
        final String lng = getIntent().getStringExtra("LNGS");
        final String name = getIntent().getStringExtra("NAMES");
        //this is for filter
        final String likes = getIntent().getStringExtra("LIKES");
        final String price = getIntent().getStringExtra("PRICES");
        final String distance = getIntent().getStringExtra("DISTANCES");

        //this is for fasility
        final String expensive = getIntent().getStringExtra("EXPENSIVES");
        final String cheapest = getIntent().getStringExtra("CHEAPESTS");
        final String wifi = getIntent().getStringExtra("WIFI");
        final String mushola = getIntent().getStringExtra("MUSHOLA");
        final String music = getIntent().getStringExtra("MUSIC");
        final String parkir = getIntent().getStringExtra("PARKIR");
        final String smoking = getIntent().getStringExtra("SMOKING");
        final String wc = getIntent().getStringExtra("WC");

        Bundle bundle = new Bundle();
        location.setText(name);
        latitude.setText(lats);
        longitude.setText(longs);
        editText.setText(message);
        String key = editText.getText().toString();

        if (getIntent().getStringExtra("NEAR") != null) {
            bundle.putString("LNGS", longitude.getText().toString());
            bundle.putString("LATS", latitude.getText().toString());
            bundle.putString("NAMES", "Nearest");
            bundle.putString("SEARCH", key);
            bundle.putString("LIKES", likes);

        } else {
            bundle.putString("SEARCH", key);
            bundle.putString("LATS", lat);
            bundle.putString("LNGS", lng);
            //sort
            bundle.putString("LIKES", likes);
            bundle.putString("PRICES", price);
            bundle.putString("DISTANCES", distance);
            //filter
            bundle.putString("EXPENSIVES", expensive);
            bundle.putString("CHEAPESTS", cheapest);
            bundle.putString("WIFI", wifi);
            bundle.putString("MUSHOLA", mushola);
            bundle.putString("MUSIC", music);
            bundle.putString("PARKIR", parkir);
            bundle.putString("SMOKING", smoking);
            bundle.putString("WC", wc);

        }
            Home_menu_fragment homemenu = new Home_menu_fragment();
            Home_Restaurant_Fragment homresto = new Home_Restaurant_Fragment();
            Home_Article_Fragment homart = new Home_Article_Fragment();
            homemenu.setArguments(bundle);
            homresto.setArguments(bundle);
            homart.setArguments(bundle);

            ArrayList<Fragment> fragments = new ArrayList<>();
            fragments.add(homemenu);
            fragments.add(homresto);
            fragments.add(homart);

            ArrayList<String> titles = new ArrayList<>();
            titles.add("MENU");
            titles.add("RESTAURANT");
            titles.add("ARTICLE");

            HomePagerAdapater adapater = new HomePagerAdapater(getSupportFragmentManager(), fragments, titles);

            viewPager.setAdapter(adapater);
            tableLayout.setupWithViewPager(viewPager);


            //intent data

            editText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String search = editText.getText().toString();
                    Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                    if (getIntent().getStringExtra("NEAR") != null) {
                        intent.putExtra("LAT", latitude.getText().toString());
                        intent.putExtra("LNG", longitude.getText().toString());
                    } else {
                        intent.putExtra("LAT", lat);
                        intent.putExtra("LNG", lng);
                    }
                    intent.putExtra("SEARCH", search);
                    intent.putExtra("NAME", name);
                    HomeActivity.this.startActivity(intent);
                }
            });

            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String search = editText.getText().toString();
                    Intent mintent = new Intent(HomeActivity.this, LocationSearch.class);
                    HomeActivity.this.startActivity(mintent);
                }
            });

        }

    public void search(View view) {
        String lat = getIntent().getStringExtra("LATS");
        String lng = getIntent().getStringExtra("LNGS");
        String name = getIntent().getStringExtra("NAMES");

        String search = editText.getText().toString();
        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
        if (getIntent().getStringExtra("NEAR") != null) {
            intent.putExtra("LAT", latitude.getText().toString());
            intent.putExtra("LNG", longitude.getText().toString());
        } else {
            intent.putExtra("LAT", lat);
            intent.putExtra("LNG", lng);
        }
        intent.putExtra("SEARCH", search);
        intent.putExtra("NAME", name);
        HomeActivity.this.startActivity(intent);
    }

    public void back(View view) {
        Intent mIntent = new Intent(HomeActivity.this, MainActivity.class);
        HomeActivity.this.startActivity(mIntent);

    }

    public void filter(View view) {
        String lat = getIntent().getStringExtra("LATS");
        String lng = getIntent().getStringExtra("LNGS");
        String name = getIntent().getStringExtra("NAMES");

        //this is for filter
        String likes = getIntent().getStringExtra("LIKES");
        String price = getIntent().getStringExtra("PRICES");
        String distance = getIntent().getStringExtra("DISTANCES");

        //this is for fasility
        String expensive = getIntent().getStringExtra("EXPENSIVES");
        String cheapest = getIntent().getStringExtra("CHEAPESTS");
        String wifi = getIntent().getStringExtra("WIFI");
        String mushola = getIntent().getStringExtra("MUSHOLA");
        String music = getIntent().getStringExtra("MUSIC");
        String parkir = getIntent().getStringExtra("PARKIR");
        String smoking = getIntent().getStringExtra("SMOKING");
        String wc = getIntent().getStringExtra("WC");

        String search = editText.getText().toString();
        Intent mIntent = new Intent(HomeActivity.this, FilterActivity.class);
        if (getIntent().getStringExtra("NEAR") != null) {
            mIntent.putExtra("LAT", latitude.getText().toString());
            mIntent.putExtra("LNG", longitude.getText().toString());
            //sort
            mIntent.putExtra("LIKES", likes);
            mIntent.putExtra("PRICES", price);
            mIntent.putExtra("DISTANCES", distance);
            //filter
            mIntent.putExtra("EXPENSIVES", expensive);
            mIntent.putExtra("CHEAPESTS", cheapest);
            mIntent.putExtra("WIFI", wifi);
            mIntent.putExtra("MUSHOLA", mushola);
            mIntent.putExtra("MUSIC", music);
            mIntent.putExtra("PARKIR", parkir);
            mIntent.putExtra("SMOKING", smoking);
            mIntent.putExtra("WC", wc);
        } else {
            mIntent.putExtra("LAT", lat);
            mIntent.putExtra("LNG", lng);
            //sort
            mIntent.putExtra("LIKES", likes);
            mIntent.putExtra("PRICES", price);
            mIntent.putExtra("DISTANCES", distance);
            //filter
            mIntent.putExtra("EXPENSIVES", expensive);
            mIntent.putExtra("CHEAPESTS", cheapest);
            mIntent.putExtra("WIFI", wifi);
            mIntent.putExtra("MUSHOLA", mushola);
            mIntent.putExtra("MUSIC", music);
            mIntent.putExtra("PARKIR", parkir);
            mIntent.putExtra("SMOKING", smoking);
            mIntent.putExtra("WC", wc);
        }
        mIntent.putExtra("SEARCH", search);
        mIntent.putExtra("NAME", name);
        HomeActivity.this.startActivity(mIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        HomeActivity.this.startActivity(intent);
    }
}
