package id.trafood.trafood.Rumahmakan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Models.GetGalery;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.RmInfoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Info extends Fragment {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Fragment_Info fi;
    ProgressBar progressBar;
    String rmid;

    public Fragment_Info() {
        // Required empty public constructor
    }

    public static Fragment_Info newInstance (Bundle c){
        Fragment_Info tabc = new Fragment_Info();
        tabc.setArguments(c);
        return tabc;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rm_info, container, false);
        Bundle bundle = this.getArguments();
        rmid = bundle.getString("rmid");
        recyclerView = (RecyclerView) view.findViewById(R.id.rvInform);
        progressBar = (ProgressBar) view.findViewById(R.id.pbInform);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fi = this;

        Call<GetRumahmakan> infoCall = apiInterface.by(rmid);
        infoCall.enqueue(new Callback<GetRumahmakan>() {
            @Override
            public void onResponse(Call<GetRumahmakan> call, Response<GetRumahmakan> response) {
               // progressBar.setVisibility(View.GONE);
                List<Rumahmakan> InfoList = response.body().getListDataRumahmakan();
                adapter = new RmInfoAdapter(InfoList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetRumahmakan> call, Throwable t) {

            }
        });

        return view;
    }

}
