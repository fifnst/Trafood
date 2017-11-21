package id.trafood.trafood.Home;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.trafood.trafood.LoginActivity;
import id.trafood.trafood.Models.GetUserView;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.Profil.ProfilUserAdapter;
import id.trafood.trafood.R;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.UtilsApi;
import id.trafood.trafood.SharedPrefManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Profil extends Fragment {

    Button login;
    Button logout;
    LinearLayout linearLayoutlogin;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public static  Fragment_Profil fp;
    ApiInterface apiInterface;

    public Fragment_Profil() {
        // Required empty public constructor.
    }

    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_profil, container, false);
        sharedPrefManager = new SharedPrefManager(this.getContext());


        login = (Button) view.findViewById(R.id.btnLoginView);
        logout = (Button) view.findViewById(R.id.btnLogoutView);
        linearLayoutlogin = (LinearLayout) view.findViewById(R.id.linearLogin);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvProfil);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        fp = this;

        if (sharedPrefManager.getSPSudahLogin()){
            logout.setVisibility(View.VISIBLE);
            linearLayoutlogin.setVisibility(View.GONE);
            setTulisan();

            }
        if (!sharedPrefManager.getSPSudahLogin()){
            logout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });


        return view;
    }

    private void setTulisan() {

        String userid = sharedPrefManager.getSpUserid();
        Call<GetUserView> calluser = apiInterface.getProfil(userid);
        calluser.enqueue(new Callback<GetUserView>() {
            @Override
            public void onResponse(Call<GetUserView> call, Response<GetUserView> response) {
                List<UserView> userViews = response.body().getListUserView();
                adapter = new ProfilUserAdapter(userViews);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetUserView> call, Throwable t) {

            }
        });

    }

}
