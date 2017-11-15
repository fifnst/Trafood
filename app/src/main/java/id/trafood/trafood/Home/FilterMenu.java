package id.trafood.trafood.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.OnCheckedChanged;
import id.trafood.trafood.HomeActivity;
import id.trafood.trafood.Home_Restaurant_Fragment;
import id.trafood.trafood.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterMenu extends Fragment {

    Button buttonApply;
    TextView location,kaku,sort,jarak,rating, harga;
    Spinner mulai,sampai;
    ImageButton ibJarak, ibRating, ibHarga;
    CheckBox cbWifi, cbParkir, cbMusic, cbMushola, cbSmoking, cbWc;
    public FilterMenu() {
        // Required empty public constructor
    }

    public static FilterMenu newInstance (Bundle b){
        FilterMenu tab = new FilterMenu();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_filter_menu, container, false);
        buttonApply = (Button) view.findViewById(R.id.buttonAply);
        location = (TextView) view.findViewById(R.id.lokasi);
        sort = (TextView) view.findViewById(R.id.sort);
        kaku = (TextView) view.findViewById(R.id.katakunci);
        jarak = (TextView) view.findViewById(R.id.tvJarak);
        rating = (TextView) view.findViewById(R.id.tvRating);
        harga = (TextView) view.findViewById(R.id.tvHarga);
        mulai = (Spinner) view.findViewById(R.id.spCheapestPrice);
        sampai = (Spinner) view.findViewById(R.id.spExpensivePrice);
        ibJarak = (ImageButton) view.findViewById(R.id.ibJarak);
        ibRating = (ImageButton) view.findViewById(R.id.ibRating);
        ibHarga = (ImageButton) view.findViewById(R.id.ibHarga);
        cbWifi = (CheckBox) view.findViewById(R.id.cbWifi);
        cbParkir = (CheckBox) view.findViewById(R.id.cbParki);
        cbMusic = (CheckBox) view.findViewById(R.id.cbMusic);
        cbMushola = (CheckBox) view.findViewById(R.id.cbMosque);
        cbSmoking = (CheckBox) view.findViewById(R.id.cbSmoking);
        cbWc = (CheckBox) view.findViewById(R.id.cbToilet);


        Bundle bundle = this.getArguments();
        final String lats = bundle.getString("LAT");
        final String lngsa = bundle.getString("LNG");
        final String message = bundle.getString("SEARCH");
        final String name = bundle.getString("NAME");

        String likes = bundle.getString("LIKES");
        String price = bundle.getString("PRICES");
        String distance = bundle.getString("DISTANCES");
        String expensive = bundle.getString("EXPENSIVES");
        String cheapest = bundle.getString("CHEAPESTS");

        String wifi = bundle.getString("WIFI");
        String mushola = bundle.getString("MUSHOLA");
        String parkir = bundle.getString("MUSIC");
        String music = bundle.getString("PARKIR");
        String smoking = bundle.getString("SMOKING");
        String wc = bundle.getString("WC");

        location.setText(name);
        kaku.setText(message);

        //check if theres data--------------------------------
        if (wifi != null){
            cbWifi.setChecked(true);
            cbWifi.setTextColor(getResources().getColor(R.color.green));
        }
        if (mushola != null){
            cbMushola.setChecked(true);
            cbMushola.setTextColor(getResources().getColor(R.color.green));
        }
        if (parkir != null){
            cbParkir.setChecked(true);
            cbParkir.setTextColor(getResources().getColor(R.color.green));
        }
        if (music != null){
            cbMusic.setChecked(true);
            cbMusic.setTextColor(getResources().getColor(R.color.green));
        }
        if (smoking != null){
            cbSmoking.setChecked(true);
            cbSmoking.setTextColor(getResources().getColor(R.color.green));
        }
        if (wc != null){
            cbWc.setChecked(true);
            cbWc.setTextColor(getResources().getColor(R.color.green));
        }
        //chek if thers sort -----------------------------------------------------------------------
        if (likes != null){
            sort.setText("2");
            jarak.setTextColor(getResources().getColor(R.color.radablack));
            rating.setTextColor(getResources().getColor(R.color.green));
            harga.setTextColor(getResources().getColor(R.color.radablack));
        }
        if (distance != null){
            sort.setText("1");
            jarak.setTextColor(getResources().getColor(R.color.green));
            rating.setTextColor(getResources().getColor(R.color.radablack));
            harga.setTextColor(getResources().getColor(R.color.radablack));
        }
        if (price != null){
            sort.setText("3");
            jarak.setTextColor(getResources().getColor(R.color.radablack));
            rating.setTextColor(getResources().getColor(R.color.radablack));
            harga.setTextColor(getResources().getColor(R.color.green));
        }
        //sort set ---------------------------------------------------------------------------------
        ibJarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort.setText("1");
                jarak.setTextColor(getResources().getColor(R.color.green));
                rating.setTextColor(getResources().getColor(R.color.radablack));
                harga.setTextColor(getResources().getColor(R.color.radablack));
            }
        });

        ibRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort.setText("2");
                jarak.setTextColor(getResources().getColor(R.color.radablack));
                rating.setTextColor(getResources().getColor(R.color.green));
                harga.setTextColor(getResources().getColor(R.color.radablack));
            }
        });

        ibHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sort.setText("3");
                jarak.setTextColor(getResources().getColor(R.color.radablack));
                rating.setTextColor(getResources().getColor(R.color.radablack));
                harga.setTextColor(getResources().getColor(R.color.green));
            }
        });

    //checkbox set color_________________________________________________________________________________________
       cbWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbWifi.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbWifi.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });
        cbParkir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbParkir.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbParkir.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });
        cbMushola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbMushola.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbMushola.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });
        cbWc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbWc.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbWc.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });
        cbMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbMusic.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbMusic.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });
        cbSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox) view).isChecked();
                if (isChecked){
                    cbSmoking.setTextColor(getResources().getColor(R.color.green));
                }
                else {
                    cbSmoking.setTextColor(getResources().getColor(R.color.radablack));
                }
            }
        });

        // intnet data -------------------------------------------------------------------------------------
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spinner
                Intent mIntent = new Intent(view.getContext(), HomeActivity.class);
                if (mulai.getSelectedItemPosition()!= 0  ){
                    mIntent.putExtra("CHEAPESTS", mulai.getSelectedItem().toString());
                }
                if (sampai.getSelectedItemPosition() != 0){
                    mIntent.putExtra("EXPENSIVES", sampai.getSelectedItem().toString());
                }
                if (sort.getText().toString().equals("1")){
                    mIntent.putExtra("DISTANCES", "1");
                }
                if (sort.getText().toString().equals("2")){
                    mIntent.putExtra("LIKES", "1");
                }
                if (sort.getText().toString().equals("3")){
                    mIntent.putExtra("PRICES", "1");
                }

                //check box
                if (cbWifi.isChecked()){
                    mIntent.putExtra("WIFI", "1");
                    Toast.makeText(view.getContext(), "checked", Toast.LENGTH_SHORT).show();
                }
                if (cbMushola.isChecked()){
                    mIntent.putExtra("MUSHOLA", "1");
                }
                if (cbMusic.isChecked()){
                    mIntent.putExtra("MUSIC", "1");
                }
                if (cbParkir.isChecked()){
                    mIntent.putExtra("PARKIR", "1");
                }
                if (cbSmoking.isChecked()){
                    mIntent.putExtra("SMOKING", "1");
                }
                if (cbWc.isChecked()){
                    mIntent.putExtra("WC", "1");
                }

                mIntent.putExtra("MESSAGE", message);
                mIntent.putExtra("LATS", lats);
                mIntent.putExtra("LNGS", lngsa);
                mIntent.putExtra("NAMES", name);
                view.getContext().startActivity(mIntent);
            }
        });

        return view;
    }

}
