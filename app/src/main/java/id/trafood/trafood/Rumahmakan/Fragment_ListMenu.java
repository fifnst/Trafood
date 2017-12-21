package id.trafood.trafood.Rumahmakan;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.trafood.trafood.CartActivity;
import id.trafood.trafood.DetailRm;
import id.trafood.trafood.Models.Galery;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rumahmakan.Adapter.MenuListAdapter;
import id.trafood.trafood.Rumahmakan.Adapter.RmGaleryAdapter;
import id.trafood.trafood.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ListMenu extends Fragment {

    TextView tvContoh,pengumuman;
    //String rmid;
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static Fragment_ListMenu fl;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    RelativeLayout relativeLayout;
    TextView harga,rtr;


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
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_rm_listmenu, container, false);
        tvContoh = (TextView) view.findViewById(R.id.tvjh);


        pengumuman = (TextView) view.findViewById(R.id.pengumumanMenu);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListMenuBuatRM);
        progressBar = (ProgressBar) view.findViewById(R.id.pbRMListmenu);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.linearTotalHarga);
        harga = (TextView) view.findViewById(R.id.tvTotalhargaPerkiraan);
        rtr = (TextView) view.findViewById(R.id.tvQtyj);

        relativeLayout.setVisibility(View.GONE);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fl=this;
        sharedPrefManager = new SharedPrefManager(view.getContext());

        isi();
        setRetainInstance(true);


       // cekdataapi();

        return view;
    }

    private void isi() {
        Bundle bundle = this.getArguments();
        String rmid = bundle.getString("rmid");
        Call<GetMenu> menuCall = apiInterface.getMenu(rmid);
        menuCall.enqueue(new Callback<GetMenu>() {
            @Override
            public void onResponse(Call<GetMenu> call, Response<GetMenu> response) {
                if (response.body().getStatus().equals("200")) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    pengumuman.setVisibility(View.GONE);
                    List<Menu> Menulist = response.body().getListDataMenu();
                    adapter = new MenuListAdapter(Menulist);
                    recyclerView.setAdapter(adapter);
                }else {
                    progressBar.setVisibility(View.GONE);
                    pengumuman.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GetMenu> call, Throwable t) {
                //   Log.e("Retrofit get " , t.toString());
            }
        });
        rtr.setText("0");
        harga.setText("0");
    }

    public void cekdataapi(String jumlah, String status,String qty) {
        String userid = sharedPrefManager.getSpUserid();
        relativeLayout.setVisibility(View.VISIBLE);

        int qts = Integer.valueOf(rtr.getText().toString());
        int sums = Integer.valueOf(qty);

        int hrafs = Integer.valueOf(harga.getText().toString());
        int sum = Integer.valueOf(jumlah);

        if (status.equals("0")){//0 artinya dia harus tamab
            hrafs += sum;
            qts += sums;
            harga.setText(String.valueOf(hrafs));
            rtr.setText(String.valueOf(qts));
        }if (status.equals("1")){ // 1 artinya dia harus di kurangi
            hrafs -= sum;
            qts -= sums;
            harga.setText(String.valueOf(hrafs));
            rtr.setText(String.valueOf(qts));
            if (qts == 0){ //untuk menghide keterangan harga
                relativeLayout.setVisibility(View.GONE);
            }
        }if (status.equals("2")){// 2 artinya sama
           // hrafs -= sum;
            harga.setText(String.valueOf(hrafs));
            rtr.setText(String.valueOf(qts));
        }

       /* relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                view.getContext().startActivity(intent);
            }
        });*/

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
