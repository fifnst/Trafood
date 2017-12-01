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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.trafood.trafood.Adapter.RumahmakanAdapter;
import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.HomeMenuAdapter;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_menu_fragment extends Fragment {

    private RecyclerView recyclerView ;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;
    ApiInterface apiInterface;
    public static Home_menu_fragment hmf;
    LinearLayout linearLayout;
    TextView warning;
    public Home_menu_fragment() {
        // Required empty public constructor
    }

    public static Home_menu_fragment newInstance (Bundle b){
        Home_menu_fragment tab = new Home_menu_fragment();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_home_menu_fragment, container, false);
        recyclerView = (RecyclerView) mView.findViewById(R.id.rvMenu);
        progressBar = (ProgressBar) mView.findViewById(R.id.pbMenus);
        linearLayout = (LinearLayout) mView.findViewById(R.id.linearWarningMenu);
        warning = (TextView) mView.findViewById(R.id.warningMenu);
        linearLayout.setVisibility(View.GONE);

        layoutManager = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        hmf = this;

        getDatamenu();
        return mView;
    }

    public void getDatamenu() {
        Bundle bundle = this.getArguments();
        String lats = bundle.getString("LATS");
        String lngsa = bundle.getString("LNGS");
        final String cari = bundle.getString("SEARCH");
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

        Call<GetModelMenu> modelMenuCall = apiInterface.getMenu(lats,lngsa,cari,likes,distance,price,cheapest,expensive,wifi,parkir,music,mushola,wc,smoking);
        modelMenuCall.enqueue(new Callback<GetModelMenu>() {
            @Override
            public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {

                    if (response.body().getStatus().equals("200")){
                        progressBar.setVisibility(View.GONE);
                        List<ModelMenu> modelMenuList = response.body().getLisModelmenu();
                        adapter = new HomeMenuAdapter(modelMenuList);
                        recyclerView.setAdapter(adapter);
                    }else{
                        progressBar.setVisibility(View.GONE);
                       linearLayout.setVisibility(View.VISIBLE);
                       warning.setText("Pencarian '"+cari+ "' tidak ditemukan");
                    }

            }

            @Override
            public void onFailure(Call<GetModelMenu> call, Throwable t) {

            }
        });

        
    }




}
