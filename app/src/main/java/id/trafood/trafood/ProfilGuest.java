package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import id.trafood.trafood.Menu.GuestAdapater;
import id.trafood.trafood.Models.GetUserView;
import id.trafood.trafood.Models.UserView;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilGuest extends AppCompatActivity {
    String namauser, userid;
    TextView tvNamaUser;
    ApiInterface apiInterface;
    public static ProfilGuest pg;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_guest);
        tvNamaUser = (TextView) findViewById(R.id.tvNamaUserProfil);
        Intent mIntent = getIntent();
        userid = mIntent.getStringExtra("USERID");
        namauser = mIntent.getStringExtra("NAMAUSER");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        tvNamaUser.setText(namauser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(namauser);

        recyclerView = (RecyclerView) findViewById(R.id.rvGuest);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        pg = this;

        Call <GetUserView> callUser = apiInterface.getProfil(userid);
        callUser.enqueue(new Callback<GetUserView>() {
            @Override
            public void onResponse(Call<GetUserView> call, Response<GetUserView> response) {
                List<UserView> userViews = response.body().getListUserView();
                adapter = new GuestAdapater(userViews);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetUserView> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
