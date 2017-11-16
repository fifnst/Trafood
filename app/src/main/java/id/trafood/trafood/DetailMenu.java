package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.trafood.trafood.Menu.DetailMenuAdapter;
import id.trafood.trafood.Models.GetMenuDetail;
import id.trafood.trafood.Models.MenuDetail;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenu extends AppCompatActivity {
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static DetailMenu dm;
    ProgressBar progressBar;

    String menuid,harga,fotomenu,namamenu;
    TextView tvHarga;
    ImageView ivFoto;
//11
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        tvHarga = (TextView) findViewById(R.id.tvHargaMenuDetail);
        ivFoto =(ImageView) findViewById(R.id.ivFotoMenuDetail);
        Intent mIntent = getIntent();
        namamenu = mIntent.getStringExtra("NAMAMENU");
        menuid = mIntent.getStringExtra("MENUID");
        harga = mIntent.getStringExtra("HARGA");
        fotomenu = mIntent.getStringExtra("FOTOMENU");
        tvHarga.setText("Rp. " + harga);
        Picasso.with(this).load(Connect.IMAGE_MENU_URL+fotomenu).error(R.mipmap.ic_launcher).into(ivFoto);

        toolbar.setTitle(namamenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.rvDetailMenu);
        progressBar = (ProgressBar) findViewById(R.id.pbDetailMenu);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dm = this;

        Call<GetMenuDetail> menuDetailCall = apiInterface.getDetailMenu(menuid);
        menuDetailCall.enqueue(new Callback<GetMenuDetail>() {
            @Override
            public void onResponse(Call<GetMenuDetail> call, Response<GetMenuDetail> response) {
                progressBar.setVisibility(View.GONE);
                List<MenuDetail> menuDetails = response.body().getListDataMenuDetail();
                adapter = new DetailMenuAdapter(menuDetails);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<GetMenuDetail> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
