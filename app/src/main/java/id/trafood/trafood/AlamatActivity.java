package id.trafood.trafood;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import id.trafood.trafood.Models.PostPutDelRm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Button submit;
    private Marker marker;
    EditText etNamaPemesan, etTeleponPemesan, etAlamatPemesan;

    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        etNamaPemesan = (EditText) findViewById(R.id.etNamaPemesan);
        etTeleponPemesan = (EditText) findViewById(R.id.etTeleponPemesan);
        etAlamatPemesan = (EditText) findViewById(R.id.etAlamatPemesan);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAlamat);
        mapFragment.getMapAsync(this);
        sharedPrefManager = new SharedPrefManager(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //String lat = getIntent().getStringExtra("LAT");
        //String lng = getIntent().getStringExtra("LNG");
        final String rmid = getIntent().getStringExtra("RMID");
        String lat = "-6.939008";
        String lng = "107.740753";

        Double doublelat = Double.parseDouble(lat);
        Double doublelong = Double.parseDouble(lng);

        LatLng pp = new LatLng(doublelat, doublelong);
        MarkerOptions options = new MarkerOptions();
        options.position(pp).title("Map");
        marker = map.addMarker(new MarkerOptions().position(pp));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

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
                Toast.makeText(AlamatActivity.this, "Lat"+latLng.latitude
                        +"long"+latLng.longitude, Toast.LENGTH_SHORT).show();



            }
        });
    }
}
