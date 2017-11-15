package id.trafood.trafood.Rumahmakan;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import id.trafood.trafood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_map extends Fragment implements OnMapReadyCallback {

    //XML nya fragment_rm_map
    MapView mMapView;
    private GoogleMap map;

    TextView tvrmid, tvlat, tvlong;

    public Fragment_map() {
        // Required empty public constructor
    }

    public static Fragment_map newInstance (Bundle d){
        Fragment_map tabs = new Fragment_map();
        tabs.setArguments(d);
        return tabs;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rm_map, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =  (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        Bundle bundle = this.getArguments();
        String rmid = bundle.getString("rmid");
        String lat = bundle.getString("lat");
        String longs = bundle.getString("longs");

        Double doublelat = Double.parseDouble(lat);
        Double doublelong = Double.parseDouble(longs);

        LatLng pp = new LatLng(doublelat, doublelong);
        MarkerOptions option = new MarkerOptions();
        option.position(pp).title("tes map");
        map.addMarker(option);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, 17));

    }
}
