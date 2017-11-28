package id.trafood.trafood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.IOException;
import java.util.List;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPetaActivity extends AppCompatActivity implements OnMapReadyCallback, SearchView.OnQueryTextListener{

    private GoogleMap map;
    private Button submit;
    private Marker marker;
    RestApi restApi;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_peta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ganti Lokasi");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
        sharedPrefManager = new SharedPrefManager(this);

        submit = (Button) findViewById(R.id.btnSavePetaEdit);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        String lat = getIntent().getStringExtra("LAT");
        String lng = getIntent().getStringExtra("LNG");
        final String rmid = getIntent().getStringExtra("RMID");

        Double doublelat = Double.parseDouble(lat);
        Double doublelong = Double.parseDouble(lng);
        
        LatLng pp = new LatLng(doublelat, doublelong);
        MarkerOptions options = new MarkerOptions();
        options.position(pp).title("Map");
        marker = map.addMarker(new MarkerOptions().position(pp));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));
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
                Toast.makeText(EditPetaActivity.this, "Lat"+latLng.latitude
                        +"long"+latLng.longitude, Toast.LENGTH_SHORT).show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final double lng = latLng.longitude;
                        final double lat = latLng.latitude;
                        final String lats = String.valueOf(lat);
                        final String lngs = String.valueOf(lng);
                        String userid = sharedPrefManager.getSpUserid();
                        Call<PostPutDelRm> putRmCall = restApi.putLatLngRm(lats,lngs,userid,rmid);
                        putRmCall.enqueue(new Callback<PostPutDelRm>() {
                            @Override
                            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                                Toast.makeText(EditPetaActivity.this, "Sukses Update Lokasi", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditPetaActivity.this, KedaiEditPetaActivity.class);
                                intent.putExtra("LATITUDE",lats);
                                intent.putExtra("LONGITUDE",lngs);
                                intent.putExtra("RMID", rmid);
                                intent.putExtra("KECAMATAN", getIntent().getStringExtra("KECAMATAN"));
                                intent.putExtra("KOTA", getIntent().getStringExtra("KOTA"));
                                intent.putExtra("ALAMAT", getIntent().getStringExtra("ALAMAT"));
                                EditPetaActivity.this.startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                                Toast.makeText(EditPetaActivity.this, "Sukses Update Lokasi", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EditPetaActivity.this, KedaiEditPetaActivity.class);
                                intent.putExtra("LATITUDE",lats);
                                intent.putExtra("LONGITUDE",lngs);
                                intent.putExtra("RMID", rmid);
                                intent.putExtra("KECAMATAN", getIntent().getStringExtra("KECAMATAN"));
                                intent.putExtra("KOTA", getIntent().getStringExtra("KOTA"));
                                intent.putExtra("ALAMAT", getIntent().getStringExtra("ALAMAT"));
                                EditPetaActivity.this.startActivity(intent);

                            }
                        });
                    }
                });

            }
        });
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
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
