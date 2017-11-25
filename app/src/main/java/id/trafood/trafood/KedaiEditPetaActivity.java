package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class KedaiEditPetaActivity extends AppCompatActivity implements OnMapReadyCallback{
    EditText etkecamatan, tvkota, tvalamat;
    Button btnSave;
    private GoogleMap map;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedai_edit_peta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Peta");

        etkecamatan = (EditText) findViewById(R.id.etKecamatanKedaiEdit);
        tvkota = (EditText) findViewById(R.id.etKotaKedaiEdit);
        tvalamat = (EditText) findViewById(R.id.etNamaAlamatKedaiEdit);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        String kecamatan, kota, alamat;

        kecamatan = getIntent().getStringExtra("KECAMATAN");
        kota = getIntent().getStringExtra("KOTA");
        alamat = getIntent().getStringExtra("ALAMAT");
        String rmid = getIntent().getStringExtra("RMID");
        etkecamatan.setText(kecamatan);
        tvkota.setText(kota);
        tvalamat.setText(alamat);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        final String rmid = getIntent().getStringExtra("RMID");
        final String lat = getIntent().getStringExtra("LATITUDE");
        final String lng = getIntent().getStringExtra("LONGITUDE");

        Double doublelat = Double.parseDouble(lat);
        Double doublelong = Double.parseDouble(lng);

        LatLng pp = new LatLng(doublelat, doublelong);
        MarkerOptions options = new MarkerOptions();
        options.position(pp).title("Map");
        marker = map.addMarker(new MarkerOptions().position(pp));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Intent intent = new Intent(KedaiEditPetaActivity.this, EditPetaActivity.class);
                intent.putExtra("LAT",lat);
                intent.putExtra("LNG",lng);
                intent.putExtra("RMID", rmid);
                KedaiEditPetaActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
