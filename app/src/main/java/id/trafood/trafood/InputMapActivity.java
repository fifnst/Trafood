package id.trafood.trafood;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InputMapActivity extends AppCompatActivity implements OnMapReadyCallback,
        View.OnClickListener{
    private GoogleMap map;
    private Marker marker;
    private double longitude,latitude,fromLongitude,fromLatitude,toLatitude,toLongitude;
    private TextView tvDistance, tvShippingPrice;
    private Button btnCalcDistace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapOrder);
        mapFragment.getMapAsync(this);


        btnCalcDistace = (Button) findViewById(R.id.btnCalcDistance);
        tvDistance = (TextView) findViewById(R.id.tvDistanceAddress);
        tvShippingPrice = (TextView) findViewById(R.id.tvShippingPrice);

        btnCalcDistace.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == btnCalcDistace){
            getDirection();
        }

    }

    private void getDirection() {
        //Getting the Url
       // moveMap();
        String url = makeUrl(fromLatitude,fromLongitude,toLatitude,toLongitude);
        LatLng pp = new LatLng(fromLatitude,fromLongitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));
        //Showing dialog till we get the route
        final ProgressDialog loading = ProgressDialog.show(this,"Getting Route","Please wait...",false,false);

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
        LatLng from = new LatLng(fromLatitude,fromLongitude);
        LatLng to = new LatLng(toLatitude,toLongitude);

        try {
            //parsing json
            final JSONObject json = new JSONObject(response);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            JSONArray legsArray = routes.getJSONArray("legs");
            JSONObject legs = legsArray.getJSONObject(0);
            JSONObject distanceObj = legs.getJSONObject("distance");
            String parsedDistance=distanceObj.getString("text");
            String parsedDistanceValue=distanceObj.getString("value");
            String userAddress = legs.getString("end_address");

            Double distance = Double.parseDouble(parsedDistanceValue);
            Double jarak = distance*0.001;
            DecimalFormat dfs = new DecimalFormat("#.#");
            String dx = dfs.format(jarak);
            tvDistance.setText(parsedDistance+", "+dx);
            tvShippingPrice.setText(userAddress);
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = map.addPolyline(new PolylineOptions()
                            .addAll(list)
                            .width(20)
                            .color(R.color.green)
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

        while (index < len){
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

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);

        }
        return poly;
    }


    private void moveMap() {
        //Creating a LatLng Object to store Coordinates
        double lat = fromLatitude;
        double lng = fromLongitude;
        LatLng pp = new LatLng(lat,lng);
        fromLongitude = lng;
        fromLatitude=lat;
        map.addMarker(new MarkerOptions().position(pp).draggable(true));
        // map.setOnMarkerDragListener(this);
        // map.setOnMapLongClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));

        double latu = toLatitude;
        double lngu = toLongitude;
        toLatitude = latu;
        toLongitude = lngu;
        LatLng posisiuser = new LatLng(latu,lngu);
        marker = map.addMarker(new MarkerOptions().position(posisiuser));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) { //ini ambil get intent dari lat lng rumah makan
        map = googleMap;
        //lt lng rumah makan atau asalnya, rumah makan kirim barang
        double lat = -6.9290627;
        double lng = 107.7170812;
        LatLng pp = new LatLng(lat,lng);
        fromLongitude = lng;
        fromLatitude=lat;
        map.addMarker(new MarkerOptions().position(pp).draggable(true));
       // map.setOnMarkerDragListener(this);
       // map.setOnMapLongClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 15));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        double latu = -6.932194;
        double lngu = 107.7530734;
        toLatitude = latu;
        toLongitude = lngu;
        LatLng posisiuser = new LatLng(latu,lngu);
        marker = map.addMarker(new MarkerOptions().position(posisiuser));
        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //moveMap();
                if (marker != null){
                    marker.remove();
                }
                marker = map.addMarker(new MarkerOptions().position(latLng));
                toLatitude = latLng.latitude;
                toLongitude = latLng.longitude;
                Toast.makeText(InputMapActivity.this, "Lat"+latLng.latitude
                        +"long"+latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
