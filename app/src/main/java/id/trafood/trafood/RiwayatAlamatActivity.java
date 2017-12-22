package id.trafood.trafood;

import android.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import id.trafood.trafood.Models.Courier;
import id.trafood.trafood.Models.GetCourier;
import id.trafood.trafood.OrderP.CourierAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;


public class RiwayatAlamatActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleMap map;
    Button btnEditAlamatini, btnLanjutkan;
    Context mContext;
    TextView tvNamaAlamt, tvAlamat,tvJarak,tvHargaOngkir;
    EditText alamatCustom;
    Marker marker;
    public static RiwayatAlamatActivity ra;
    ApiInterface apiInterface;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout linearTidakAda,linearAda;
    TextView pengumuman;

    private double longitude, latitude, fromLongitude, fromLatitude, toLatitude, toLongitude;

    private GoogleApiClient googleApiClient;
    String lat,lng,rmid,namarm,kodetrans,userid,telp,namauser,jauh,jauhh,address,total,ongkir,idKurir, kuriNama,alamatrm;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_alamat);
        sharedPrefManager = new SharedPrefManager(this);
        userid = sharedPrefManager.getSpUserid();
        namauser = sharedPrefManager.getSPNama();

        Intent intent = getIntent();
        lat = intent.getStringExtra("LAT");
        lng = intent.getStringExtra("LNG");
        rmid = intent.getStringExtra("RMID");
        namarm = intent.getStringExtra("NAMARM");
        kodetrans = intent.getStringExtra("NOMOR");
        telp = intent.getStringExtra("TELP");
        total = intent.getStringExtra("TOTAL");
        alamatrm = intent.getStringExtra("ALAMATRM");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapOrder);
        mapFragment.getMapAsync(this);

        tvAlamat = (TextView) findViewById(R.id.tvRiwayatAlamatPemesan);
        tvNamaAlamt = (TextView) findViewById(R.id.tvNamaAlamat);
        btnLanjutkan = (Button) findViewById(R.id.btnNextFR);
        tvJarak = (TextView) findViewById(R.id.tvJarakAlamat);
        alamatCustom = (EditText) findViewById(R.id.catatanAlamat);
        recyclerView = (RecyclerView) findViewById(R.id.rvKurirMap);
        linearAda = (LinearLayout) findViewById(R.id.LnAdaKurirMap);
        linearTidakAda = (LinearLayout) findViewById(R.id.LnTidakAdaKurirMap);
        pengumuman = (TextView) findViewById(R.id.tvPengumumanKurirMap);
        tvHargaOngkir = (TextView) findViewById(R.id.tvHargaOngkir);

        linearTidakAda.setVisibility(View.GONE);
        linearAda.setVisibility(View.GONE);

        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mContext = this;
        ra = this;

        btnLanjutkan.setVisibility(View.GONE);



    }

    public void iniHargaKurir(String price, String per, String hargaplus, String perplus,String unit,String kuririd, String namaKurir) {
        Double harga = Double.parseDouble(price);
        Double perr = Double.parseDouble(per);
        Double hargapluss = Double.parseDouble(hargaplus);
        Double perrplus = Double.parseDouble(perplus);

        Double jarak = Double.valueOf(jauh);
        Double realdistance = Math.ceil(jarak / 1000.0); //bulatkan angka nya dulu ke atas
        Double disper = realdistance / perr;
        Double ini = Math.ceil(disper / 1.0);
        btnLanjutkan.setVisibility(View.VISIBLE);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        idKurir = kuririd;
        kuriNama = namaKurir;
        if (unit.equals("km")) {
            tvHargaOngkir.setText(kursIndonesia.format(ini * harga));
            ongkir = String.valueOf(harga * ini);
            Log.d("TAG", "ongkir = " + String.valueOf(ini * perr));
        }
        if (unit.equals("transaction")) {
            tvHargaOngkir.setText(kursIndonesia.format(harga * perr));
            ongkir = String.valueOf(harga * perr);
            Log.d("TAG", "ongkir = " + String.valueOf(harga * perr));
        }//untuk menghitung yang kilometer pertama dan keduanya berbeda
        if (unit.equals("kmplus")) {
            if (realdistance < perr) {
                tvHargaOngkir.setText(kursIndonesia.format(harga * 1));
                ongkir = String.valueOf(harga);
                Log.d("TAG", "ongkir = " + String.valueOf(harga));
            }
            if (realdistance > perr) {
                Double sisa = realdistance - perr;
                Double bagi = sisa / perrplus;
                Double hasill = Math.ceil(bagi / 1.0);
                Double nyaan = hasill * hargapluss;
                tvHargaOngkir.setText(kursIndonesia.format(harga + nyaan));
                Double tambah = harga + nyaan;
                ongkir = String.valueOf(harga + nyaan);
                Log.d("TAG", "ongkir = " + String.valueOf(harga + nyaan));
            }
        }

        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanjutkan();
            }
        });
    }

    private void lanjutkan() {
        String latu = String.valueOf(toLatitude);
        String lngu = String.valueOf(toLongitude);
        String catam = alamatCustom.getText().toString();
        Intent intent = new Intent(RiwayatAlamatActivity.this, ConfirmationActivity.class);
        intent.putExtra("LATK",lat);
        intent.putExtra("LNGK",lng);
        intent.putExtra("LATU",latu);
        intent.putExtra("LNGU",lngu);
        intent.putExtra("NAMARM",namarm);
        intent.putExtra("KODE",kodetrans);
        intent.putExtra("TELEPON",telp);
        intent.putExtra("RDISTANCE",jauh);
        intent.putExtra("GDISTANCE",jauhh);
        intent.putExtra("ADDRESS",address);
        intent.putExtra("TOTAL",total);
        intent.putExtra("RMID",rmid);
        intent.putExtra("ONGKIR",ongkir);
        intent.putExtra("ALAMATRM",alamatrm);
        intent.putExtra("NAMAKURIR",kuriNama);
        intent.putExtra("CUSTOM", catam);
        intent.putExtra("KURIRID", idKurir);
        RiwayatAlamatActivity.this.startActivity(intent);
        Log.d("TAG", ongkir);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDirection() {
        //Getting the Url
        // moveMap();
        String url = makeUrl(fromLatitude, fromLongitude, toLatitude, toLongitude);
        LatLng pp = new LatLng(fromLatitude, fromLongitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));
        //Showing dialog till we get the route
        final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //Calling the method drawPath to draw the path
                        drawPath(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });
        //Adding the request to request quee
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private String makeUrl(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(this.fromLatitude));
        urlString.append(",");
        urlString.append(Double.toString(this.fromLongitude));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(this.toLatitude));
        urlString.append(",");
        urlString.append(Double.toString(this.toLongitude));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=AIzaSyCQ6wygkCR2zoxkxK0K4G_wguzQ-GdZDJU");
        return urlString.toString();
    }

    //The parameter is the server response
    private void drawPath(String response) {
        LatLng from = new LatLng(fromLatitude, fromLongitude);
        LatLng to = new LatLng(toLatitude, toLongitude);

        try {
            //parsing json
            final JSONObject json = new JSONObject(response);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            JSONArray legsArray = routes.getJSONArray("legs");
            JSONObject legs = legsArray.getJSONObject(0);
            JSONObject distanceObj = legs.getJSONObject("distance");
            String parsedDistance = distanceObj.getString("text");
            String parsedDistanceValue = distanceObj.getString("value");
            String userAddress = legs.getString("end_address");
            String kedaiAddress = legs.getString("start_address");
            address = userAddress;
            Double distance = Double.parseDouble(parsedDistanceValue);
            Double jarak = distance * 0.001;
            DecimalFormat dfs = new DecimalFormat("#.#");
            String ds = dfs.format(jarak);
            jauhh = parsedDistance;
            jauh = parsedDistanceValue;
            tvJarak.setText("Jarak ("+parsedDistance+")");
            tvAlamat.setText(userAddress);
            tvNamaAlamt.setText(kedaiAddress);
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = map.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(15)
                    .color(R.color.ectasy)
                    .geodesic(true)
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private List<LatLng> decodePoly(String encodedString) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encodedString.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encodedString.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);

        }
        return poly;
    }


    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getCurrentLocation();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void getCurrentLocation() {
        map.clear();
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();

            //moving the map to location
            moveMap();
        }
    }

    private void moveMap() {
        //user
        //Creating a LatLng Object to store Coordinates
        BitmapDescriptor iconJingga = BitmapDescriptorFactory.fromResource(R.drawable.location_jingga10);
        BitmapDescriptor iconPink = BitmapDescriptorFactory.fromResource(R.drawable.location_pink10);

        LatLng latLng = new LatLng(latitude, longitude);
        //Adding marker to map
        map.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .title("Your Location")
                .icon(iconJingga)); //Adding a title
        //Moving the camera
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Animating the camera
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        toLongitude = longitude;
        toLatitude = latitude;
       /* double lat = -6.9290627;
        double lng = 107.7170812;
        LatLng pp = new LatLng(lat, lng);

        map.addMarker(new MarkerOptions().position(pp).draggable(true)); */

        //rumahmakan
        double latu = Double.parseDouble(lat);
        double lngu = Double.parseDouble(lng);
        fromLatitude = latu;
        fromLongitude = lngu;
        LatLng posisiuser = new LatLng(latu, lngu);
        map.addMarker(new MarkerOptions()
                .position(posisiuser)
                .draggable(true)
                .title("Kedai "+namarm)
                .icon(iconPink));

        getDirection();
        getKurir();
    }

    private void getKurir() {
        String rmLat = String.valueOf(fromLatitude);
        String rmLng = String.valueOf(fromLongitude);
        String uLat = String.valueOf(toLatitude);
        String uLng = String.valueOf(toLongitude);
        Call<GetCourier> getCourierCall = apiInterface.getKurir(rmLat,rmLng,uLat,uLng);
        getCourierCall.enqueue(new Callback<GetCourier>() {
            @Override
            public void onResponse(Call<GetCourier> call, retrofit2.Response<GetCourier> response) {
                if (response.body().getStatus().equals("201")){
                    linearTidakAda.setVisibility(View.VISIBLE);
                    pengumuman.setText("Kedai berada diluar jangkuan kurir");
                    //loading.dismiss();
                }if (response.body().getStatus().equals("200")){
                    linearAda.setVisibility(View.VISIBLE);
                    List<Courier> couriers = response.body().getListDataCourier();
                    Log.d("TAG",String.valueOf(couriers.size()));
                    adapter = new CourierAdapter(couriers);
                    recyclerView.setAdapter(adapter);
                    //loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetCourier> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lats = Double.parseDouble(lat);
        double lngs = Double.parseDouble(lng);
        map = googleMap;
        LatLng latLng = new LatLng(lats, lngs);
        map.addMarker(new MarkerOptions().position(latLng).draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);
    }

}
