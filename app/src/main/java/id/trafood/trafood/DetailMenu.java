package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenu extends AppCompatActivity {
    ApiInterface apiInterface;
    Context dm;
    ProgressBar progressBar;
    public TextView tvNamamenu, tvDeskrispisMenu, tvLike, tvNamaRm, tvAlamat,tvKategoriRm, tvDilihat;
    String menuid,fotomenu,namamenu;
    TextView tvHarga, tvEditmenu;
    ImageView ivFoto,ivLike;
    SharedPrefManager sharedPrefManager;


    //PERCOBAAN FAHRI
    private String[] id;
    private TextView[] textViewsTesTag = new TextView[6];



    //String[] kata1;
    //ListView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        tvHarga = (TextView) findViewById(R.id.tvHargaMenuDetail);
        ivFoto =(ImageView) findViewById(R.id.ivFotoMenuDetail);
        tvEditmenu = (TextView) findViewById(R.id.tvEditMenu);
        tvNamamenu = (TextView) findViewById(R.id.tvNamaMenuDetail);
        tvDeskrispisMenu = (TextView) findViewById(R.id.tvDeskripsiMenu);
        tvLike = (TextView) findViewById(R.id.tvLikesDetail);
        tvNamaRm = (TextView) findViewById(R.id.tvNamaRmMenuDetail);
        tvAlamat = (TextView) findViewById(R.id.tvAlamatMenuDetail);
        tvKategoriRm = (TextView) findViewById(R.id.tvKategoriRmMenuDetail);
        //tvTagMenu = (TextView) findViewById(R.id.tvTagMenu);
        tvDilihat = (TextView) findViewById(R.id.tvdilihatDetail);
        ivLike = (ImageView) findViewById(R.id.ivLike);

        //pERCOBAAN FAHRI
        int temp;
        id = new String[]{"tvTagMenu1", "tvTagMenu2", "tvTagMenu3", "tvTagMenu4", "tvTagMenu5", "tvTagMenu6"};

        for(int i=0; i<id.length; i++){
            temp = getResources().getIdentifier(id[i], "id", getPackageName());
            textViewsTesTag[i] = (TextView)findViewById(temp);
            textViewsTesTag[i].setVisibility(View.GONE);
            //textViewsTesTag[i].setText("tes");
        }




        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_detail_menu,kata);
        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_detail_menu,kata1);
        //ListView listview =(ListView) findViewById(R.id.listView);
        //listview.setAdapter(adapter);


        Intent mIntent = getIntent();
        namamenu = mIntent.getStringExtra("NAMAMENU");
        menuid = mIntent.getStringExtra("MENUID");
        fotomenu = mIntent.getStringExtra("FOTOMENU");
        sharedPrefManager = new SharedPrefManager(this);
        final String useridMenu = getIntent().getStringExtra("USERID");
        final String useridUser = sharedPrefManager.getSpUserid();

        Picasso.with(this).load(Connect.IMAGE_MENU_URL+fotomenu).error(R.mipmap.ic_launcher).into(ivFoto);

        toolbar.setTitle(namamenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.pbDetailMenu);

        dm = this;
        apiInterface = UtilsApi.getApiServive();

        setisi(menuid);


        tvEditmenu.setVisibility(View.GONE);


        if (sharedPrefManager.getSPSudahLogin()){
            cekPernahLike(menuid, useridUser); // cek pernah like atau belum
            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveLike(menuid,useridUser);
                }
            });
            if (useridUser.equals(useridMenu)){ // cek apakah useriduser dan useridmenu sama, jika sama maka bisa edit menu
                tvEditmenu.setVisibility(View.VISIBLE);
                tvEditmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String menuids=  getIntent().getStringExtra("MENUID");
                        Intent intent = new Intent(DetailMenu.this, EditMenuActivity.class);
                        intent.putExtra("MENUID",menuid);
                        intent.putExtra("USERID",useridMenu);
                        DetailMenu.this.startActivity(intent);
                    }
                });
            }
        }else {
            final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
            ivLike.setImageDrawable(likebelumlike);
            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(dm, "Anda harus login dulu untuk merekomendasikan menu ini", Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    private void saveLike(String menuid, String useridUser) {
        final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
        final Drawable sudahpernahlike = getResources().getDrawable(R.drawable.ic_after_recomended);
        apiInterface.saveLike(menuid,useridUser).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String like = jsonResult.getString("result");
                    if (jsonResult.getString("status").equals("200")){
                        ivLike.setImageDrawable(sudahpernahlike);
                        tvLike.setText(like);
                        Toast.makeText(dm, "Menu ini telah anda rekomendasikan, dan ditambahkan ke list menu favorit", Toast.LENGTH_LONG).show();
                    }if (jsonResult.getString("status").equals("204")){
                        ivLike.setImageDrawable(likebelumlike);
                        tvLike.setText(like);
                        Toast.makeText(dm, "Menu ini telah dihapus dari daftar favorit anda", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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

    private void cekPernahLike(String menuid, String useridUser) {
        final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
        final Drawable sudahpernahlike = getResources().getDrawable(R.drawable.ic_after_recomended);
        apiInterface.cekLike(menuid,useridUser).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    if (jsonResult.getString("status").equals("204")){
                        ivLike.setImageDrawable(likebelumlike);
                    }else {
                        ivLike.setImageDrawable(sudahpernahlike);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void setisi(String isi) {
        menuid = isi;
        apiInterface.menuDetail(menuid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String nama = jsonResult.getJSONObject("result").getString("namamenu");
                    String deskripsimenu = jsonResult.getJSONObject("result").getString("deskripsi");
                    String hargamenu = jsonResult.getJSONObject("result").getString("harga");
                    String tag = jsonResult.getJSONObject("result").getString("tag");
                    String like = jsonResult.getJSONObject("result").getString("likes");
                    final String namarm = jsonResult.getJSONObject("result").getString("namarm");
                    final String alamat = jsonResult.getJSONObject("result").getString("alamat");
                    final String kategorirm = jsonResult.getJSONObject("result").getString("kategorirm");
                    String dilihat = jsonResult.getJSONObject("result").getString("dilihat");
                    final String rmid = jsonResult.getJSONObject("result").getString("rmid");
                    final String fotosampul = jsonResult.getJSONObject("result").getString("fotosampul");
                    final String lat = jsonResult.getJSONObject("result").getString("latitude");
                    final String lng = jsonResult.getJSONObject("result").getString("longitude");

                    String kalimat = tag;
                    String[] kata = kalimat.split(" ");

                    tvNamamenu.setText(nama);
                    tvDeskrispisMenu.setText(deskripsimenu);
                    tvLike.setText(like);
                    tvNamaRm.setText(namarm);
                    tvAlamat.setText(alamat);
                    tvKategoriRm.setText(kategorirm);
                    tvDilihat.setText("dilihat : "+dilihat);

                    //pERCOBAAN FAHRI
                    //int temp;
                    //id = new String[]{"tvTagMenu1", "tvTagMenu2", "tvTagMenu3", "tvTagMenu4", "tvTagMenu5", "tvTagMenu6"};

                    for(int i=0; i<kata.length; i++){
                        textViewsTesTag[i].setText(kata [i]);
                        if(kata [i] != ""){
                            textViewsTesTag[i].setVisibility(View.VISIBLE);
                        }
                    }


                    tvHarga.setText("Rp. "+hargamenu);

                    tvNamaRm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent mIntent = new Intent(view.getContext(), DetailRm.class);
                            mIntent.putExtra("RMID", rmid);
                            mIntent.putExtra("NAMARM", namarm);
                            mIntent.putExtra("KATEGORI", kategorirm);
                            mIntent.putExtra("ALAMAT", alamat);
                            mIntent.putExtra("FOTO", fotosampul);
                            mIntent.putExtra("LAT", lat);
                            mIntent.putExtra("LONG", lng);
                            view.getContext().startActivity(mIntent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
