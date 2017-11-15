package id.trafood.trafood.Rumahmakan;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.DetailRm;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.MenuListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ListMenu extends Fragment {

    TextView tvContoh;
    String rmid;
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Fragment_ListMenu fl;
    ProgressBar progressBar;

    public Fragment_ListMenu() {
        // Required empty public constructor
    }

    public static Fragment_ListMenu newIstanace (Bundle b){
        Fragment_ListMenu tab = new Fragment_ListMenu();
        tab.setArguments(b);
        return tab;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rm_listmenu, container, false);
        tvContoh = (TextView) view.findViewById(R.id.tvjh);
        Bundle bundle = this.getArguments();
        rmid = bundle.getString("rmid");
        tvContoh.setText(rmid);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvListMenuBuatRM);
        progressBar = (ProgressBar) view.findViewById(R.id.pbRMListmenu);
        layoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fl=this;

        Call<GetMenu> menuCall = apiInterface.getMenu(rmid);
        menuCall.enqueue(new Callback<GetMenu>() {
            @Override
            public void onResponse(Call<GetMenu> call, Response<GetMenu> response) {
                progressBar.setVisibility(View.GONE);
                List<Menu> Menulist = response.body().getListDataMenu();
                adapter = new MenuListAdapter(Menulist);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetMenu> call, Throwable t) {
             //   Log.e("Retrofit get " , t.toString());
            }
        });

        return view;
    }




}
