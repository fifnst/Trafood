package id.trafood.trafood;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import id.trafood.trafood.Adapter.RumahmakanAdapter;
import id.trafood.trafood.Home.ArticleAdapter;
import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.HomeMenuAdapter;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Models.Article;
import id.trafood.trafood.Models.GetArticle;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Fragment_ListMenu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragmet extends Fragment {
    private RecyclerView recyclerViewMenu , recyclerViewRumahMakan, recyclerViewArticle ;
    private RecyclerView.LayoutManager layoutManagerMenu, layoutManagerRumahmakan, layoutManagerArticle;
    private RecyclerView.Adapter adapterMenu,adapterRumahmakan, adapterArticle;
    public static Home_fragmet hf;
    ApiInterface apiInterface;
    ProgressBar progressBar;
    ImageView searchbar;
    private Location mLastlocation;
    private GoogleApiClient googleApiClient;
    TextView lat, lng,location;

    public Home_fragmet() {
        // Required empty public constructor
    }

    public static Home_fragmet newIstnce (Bundle b){
        Home_fragmet tab = new Home_fragmet();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen

        View view = inflater.inflate(R.layout.fragment_home_fragmet, container, false);
        recyclerViewMenu = (RecyclerView) view.findViewById(R.id.rvHomeMenu);
        recyclerViewRumahMakan = (RecyclerView) view.findViewById(R.id.rvHomeRm);
        recyclerViewArticle = (RecyclerView) view.findViewById(R.id.rvHomeArticle);
        lat = (TextView) view.findViewById(R.id.lat);
        lng = (TextView) view.findViewById(R.id.lng);
        location = (TextView) view.findViewById(R.id.location);
        Bundle bundle = this.getArguments();
        final String name = bundle.getString("NAME");
        final String lngs = bundle.getString("LNG");
        final String lats = bundle.getString("LAT");
        location.setText(name);
        lat.setText(lats);
        lng.setText(lngs);
        progressBar = (ProgressBar) view.findViewById(R.id.pbHomeMenu);
        searchbar = (ImageView) view.findViewById(R.id.barseacrh);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                intent.putExtra("SEARCH", "");
                intent.putExtra("LAT", lat.getText().toString());
                intent.putExtra("LNG", lng.getText().toString());
                intent.putExtra("NAME", location.getText().toString());
               // Toast.makeText(view.getContext(), "Select location first", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LocationActivity.class);
                view.getContext().startActivity(mIntent);
            }
        });



        layoutManagerMenu = new GridLayoutManager(this.getActivity(),2);
        recyclerViewMenu.setLayoutManager(layoutManagerMenu);

        layoutManagerRumahmakan = new GridLayoutManager(this.getActivity(),2);
        recyclerViewRumahMakan.setLayoutManager(layoutManagerRumahmakan);

        layoutManagerArticle = new GridLayoutManager(this.getActivity(), 2);
        recyclerViewArticle.setLayoutManager(layoutManagerArticle);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        hf = this;

        loadMenu();
        loadArticle();
        loadRumahMakan();
        return view;
    }

    public void loadArticle() {
        Call<GetArticle> getArticleCall = apiInterface.getArticleLimit();
        getArticleCall.enqueue(new Callback<GetArticle>() {
            @Override
            public void onResponse(Call<GetArticle> call, Response<GetArticle> response) {
                List<Article> articleList = response.body().getGetListArticle();
                adapterArticle = new ArticleAdapter(articleList);
                recyclerViewArticle.setAdapter(adapterArticle);
            }

            @Override
            public void onFailure(Call<GetArticle> call, Throwable t) {

            }
        });
    }

    public void loadRumahMakan() {
        Bundle bundle = this.getArguments();
        String lngs = bundle.getString("LNG");
        String lats = bundle.getString("LAT");

        Call<GetRumahmakan> getRumahmakanCall = apiInterface.getRMlimit(lats,lngs);
        getRumahmakanCall.enqueue(new Callback<GetRumahmakan>() {
            @Override
            public void onResponse(Call<GetRumahmakan> call, Response<GetRumahmakan> response) {
                List<Rumahmakan> rumahmakanList = response.body().getListDataRumahmakan();
                adapterRumahmakan = new RumahmakanAdapter(rumahmakanList);
                recyclerViewRumahMakan.setAdapter(adapterRumahmakan);
            }

            @Override
            public void onFailure(Call<GetRumahmakan> call, Throwable t) {

            }
        });

    }

    private void loadMenu() {
        Bundle bundle = this.getArguments();
        String lats = bundle.getString("LAT");
        String lngs = bundle.getString("LNG");

        Call<GetModelMenu> getModelMenuCall = apiInterface.getModelMenu(lats,lngs);
        getModelMenuCall.enqueue(new Callback<GetModelMenu>() {
            @Override
            public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {
                List<ModelMenu> modelMenuList = response.body().getLisModelmenu();
                progressBar.setVisibility(View.GONE);
                Log.d("Retrofit Get ", "Jumlah data Rumah makan: " +
                        String.valueOf(modelMenuList.size()));

                adapterMenu = new HomeMenuAdapter(modelMenuList);
                recyclerViewMenu.setAdapter(adapterMenu);
            }

            @Override
            public void onFailure(Call<GetModelMenu> call, Throwable t) {

            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("LOG", "onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("LOG", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LOG", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LOG", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LOG", "onDestroy");
    }


}
