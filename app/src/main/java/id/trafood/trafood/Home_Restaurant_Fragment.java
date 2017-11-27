package id.trafood.trafood;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Adapter.RumahmakanAdapter;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Restaurant_Fragment extends Fragment {

    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static Home_Restaurant_Fragment ma;
    ProgressBar progressBar;
    TextView lat,lng;

    public Home_Restaurant_Fragment() {
        // Required empty public constructor
    }

    public static Home_Restaurant_Fragment newInstance (Bundle b){
        Home_Restaurant_Fragment tab = new Home_Restaurant_Fragment();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home__restaurant_,container,false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rvRumahmakan);
        progressBar = (ProgressBar) mView.findViewById(R.id.progressbarRm);
        mLayoutManager = new GridLayoutManager(this.getActivity(),2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;

        //lat = (TextView) mView.findViewById(R.id.tvcpnh);
        //lng = (TextView) mView.findViewById(R.id.tvsdsd);
        refresh();

        return mView;
    }

    private void refresh() {
        Bundle bundle = this.getArguments();
            String lats = bundle.getString("LATS");
            String lngsa = bundle.getString("LNGS");
            String cari = bundle.getString("SEARCH");

            String wifi = bundle.getString("WIFI");
        String mushola = bundle.getString("MUSHOLA");
        String parkir = bundle.getString("MUSIC");
        String music = bundle.getString("PARKIR");
        String smoking = bundle.getString("SMOKING");
        String wc = bundle.getString("WC");

        //  lat.setText(lats);
           // lng.setText(lngsa);

            Call <GetRumahmakan> rumahmakanCall = mApiInterface.getRumahmakan(lats,lngsa,cari,wifi,parkir,music,mushola,wc,smoking);
            rumahmakanCall.enqueue(new Callback<GetRumahmakan>() {
                @Override
                public void onResponse(Call<GetRumahmakan> call, Response<GetRumahmakan> response) {
                    if (response.body().getStatus().equals("200")) {
                        progressBar.setVisibility(View.GONE);
                        List<Rumahmakan> RumahmakanList = response.body().getListDataRumahmakan();
                        mAdapter = new RumahmakanAdapter(RumahmakanList);
                        mRecyclerView.setAdapter(mAdapter);
                    }else {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<GetRumahmakan> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                }
            });
    }
}
