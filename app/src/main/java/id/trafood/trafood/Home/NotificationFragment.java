package id.trafood.trafood.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import id.trafood.trafood.CartActivity;
import id.trafood.trafood.LoginActivity;
import id.trafood.trafood.MainActivity;
import id.trafood.trafood.Models.GetUserView;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.Profil.ProfilUserAdapter;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.SharedPrefManager;
import id.trafood.trafood.ThanksActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    LinearLayout linearKeranjang, linearStatusPengiriman, linearPesanMasuk;

    Button login;
    LinearLayout linearLayoutlogin;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static  NotificationFragment fp;
    ApiInterface apiInterface;


    public NotificationFragment() {
        // Required empty public constructor
    }

    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View  view = inflater.inflate(R.layout.fragment_notification, container, false);
        sharedPrefManager = new SharedPrefManager(this.getContext());


        linearKeranjang = (LinearLayout) view.findViewById(R.id.linearKeranjang);
        linearPesanMasuk = (LinearLayout) view.findViewById(R.id.linearPesanMasuk);
        linearStatusPengiriman = (LinearLayout) view.findViewById(R.id.linearStatusPengiriman);

        login = (Button) view.findViewById(R.id.btnLoginView);
        linearLayoutlogin = (LinearLayout) view.findViewById(R.id.linearLogin);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvProfil);

        linearKeranjang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                view.getContext().startActivity(intent);
            }
        });



        return view;
    }

}
