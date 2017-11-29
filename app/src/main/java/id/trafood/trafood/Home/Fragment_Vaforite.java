package id.trafood.trafood.Home;


import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Home_fragmet;
import id.trafood.trafood.LoginActivity;
import id.trafood.trafood.Models.GetLike;
import id.trafood.trafood.Models.Like;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.SearchActivity;
import id.trafood.trafood.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Vaforite extends Fragment {

    SharedPrefManager sharedPrefManager;
    Button login;
    ImageView searchbar;
    LinearLayout linearLayoutlogin;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    public static  Fragment_Vaforite fva;
    TextView location,lat,lng,pengumuman;

    public Fragment_Vaforite() {
        // Required empty public constructor
    }

    public static Fragment_Vaforite newIstnce (Bundle b){
        Fragment_Vaforite tab = new Fragment_Vaforite();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaforite, container, false);
        sharedPrefManager = new SharedPrefManager(this.getContext());

        login = (Button) view.findViewById(R.id.btnLoginLike);
        linearLayoutlogin = (LinearLayout) view.findViewById(R.id.linearLoginLike);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListLike);
        lat = (TextView) view.findViewById(R.id.lats);
        lng = (TextView) view.findViewById(R.id.lngs);
        pengumuman = (TextView) view.findViewById(R.id.pengumumanFavorit);
        location = (TextView) view.findViewById(R.id.locations);
        Bundle bundle = this.getArguments();
        final String name = bundle.getString("NAME");
        final String lngs = bundle.getString("LNG");
        final String lats = bundle.getString("LAT");
        location.setText(name);
        lat.setText(lats);
        lng.setText(lngs);
        searchbar = (ImageView) view.findViewById(R.id.barseacrhs);
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

        pengumuman.setVisibility(View.GONE);
        layoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fva = this;

        if (sharedPrefManager.getSPSudahLogin()){
            linearLayoutlogin.setVisibility(View.GONE);
            setTulisan();

        }
        if (!sharedPrefManager.getSPSudahLogin()){
            recyclerView.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }

    private void setTulisan() {
        String userid = sharedPrefManager.getSpUserid();
        Call<GetModelMenu> getLikeCall = apiInterface.userLike(userid);
        getLikeCall.enqueue(new Callback<GetModelMenu>() {
            @Override
            public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {
                if(response.body().getStatus().equals("200")){
                    List<ModelMenu> modelMenuList = response.body().getLisModelmenu();
                    adapter = new HomeMenuAdapter(modelMenuList);
                    recyclerView.setAdapter(adapter);
                }else{
                    pengumuman.setVisibility(View.VISIBLE);
                }



            }

            @Override
            public void onFailure(Call<GetModelMenu> call, Throwable t) {

            }
        });
    }

}
