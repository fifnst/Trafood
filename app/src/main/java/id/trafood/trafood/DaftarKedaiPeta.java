package id.trafood.trafood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKedaiPeta extends AppCompatActivity implements OnMapReadyCallback, SearchView.OnQueryTextListener{
    private GoogleMap map;
    private Button submit;
    private Marker marker;
    RestApi restApi;
    Context mContext;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kedai_peta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map4);
        mapFragment.getMapAsync(this);
        sharedPrefManager = new SharedPrefManager(this);
        submit = (Button) findViewById(R.id.btnSavePetaTambah);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);


    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("cari Lokasi");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        List<Address> addressList = null;

        if (newText != null || newText.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(newText, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title("marker"));
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        double lng = 107.609810;
        double lats = -6.914744;

        LatLng pp = new LatLng(lats, lng);
        MarkerOptions options = new MarkerOptions();
        options.position(pp).title("Pilih Lokasi Kedai");
        marker = map.addMarker(new MarkerOptions().position(pp));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp,15));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                if (marker != null){
                    marker.remove();
                }
                marker = map.addMarker(new MarkerOptions().position(latLng));
                Toast.makeText(DaftarKedaiPeta.this, "Lat"+latLng.latitude
                        +"long"+latLng.longitude, Toast.LENGTH_SHORT).show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final double lng = latLng.longitude;
                        final double lat = latLng.latitude;
                        final String lats = String.valueOf(lat);
                        final String lngs = String.valueOf(lng);
                        String userid = sharedPrefManager.getSpUserid();


                        Call<PostPutDelRm> putDelRmCall = restApi.postLatLngRm(lats,lngs,userid);
                        putDelRmCall.enqueue(new Callback<PostPutDelRm>() {
                            @Override
                            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                                Intent intent = new Intent(mContext, DaftarKedaiInfo.class);
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                                Intent intent = new Intent(mContext, DaftarKedaiInfo.class);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                });
            }
        });


    }
}
