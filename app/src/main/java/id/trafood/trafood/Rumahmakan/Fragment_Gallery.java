package id.trafood.trafood.Rumahmakan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Adapter.RumahmakanAdapter;
import id.trafood.trafood.Models.Galery;
import id.trafood.trafood.Models.GetGalery;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.RmGaleryAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Gallery extends Fragment {

    ProgressBar progressBar;
    String rmid;
    ApiInterface apiInterface;
    TextView pengumuman;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Fragment_Gallery fg;

    public Fragment_Gallery() {
        // Required empty public constructor
    }

    public static Fragment_Gallery newInstance (Bundle b){
        Fragment_Gallery tabs = new Fragment_Gallery();
        tabs.setArguments(b);
        return tabs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rm_gallery, container, false);
        Bundle bundle = this.getArguments();
        String rmid = bundle.getString("rmid");
        recyclerView = (RecyclerView) view.findViewById(R.id.rvGaleri);
        progressBar = (ProgressBar) view.findViewById(R.id.pbGaleri);
        pengumuman = (TextView) view.findViewById(R.id.pengumumanGaleri);
        layoutManager = new GridLayoutManager(this.getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fg=this;

        Call<GetGalery> galeryCall = apiInterface.getGalery(rmid);
        galeryCall.enqueue(new Callback<GetGalery>() {
            @Override
            public void onResponse(Call<GetGalery> call, Response<GetGalery> response) {
                if (response.body().getStatus().equals("200")) {
                    progressBar.setVisibility(View.GONE);
                    pengumuman.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<Galery> Galerylist = response.body().getListDataGalery();
                    adapter = new RmGaleryAdapter(Galerylist);
                    recyclerView.setAdapter(adapter);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    pengumuman.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetGalery> call, Throwable t) {

            }
        });

        return view;
    }

}
